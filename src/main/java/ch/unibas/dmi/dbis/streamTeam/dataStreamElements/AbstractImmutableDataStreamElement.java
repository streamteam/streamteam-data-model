/*
 * StreamTeam
 * Copyright (C) 2019  University of Basel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements;

import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.ImmutableDataStreamElementContentProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import com.google.protobuf.Any;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.javatuples.Pair;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class for an immutable data stream element.
 */
public abstract class AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the data stream element is shipped (i.e., the Kafka topic)
     */
    private final String streamName;

    /**
     * The category of the data stream
     */
    private final StreamCategory streamCategory;

    /**
     * The key of the data stream element which defines the partition of the data stream to which it belongs
     */
    private final String key;

    /**
     * The globally unambiguous sequence number of the data stream element (i.e., the Kafka offset).
     * Null at the processor where the data stream element is generated (since the sequence number is assigned by Kafka).
     */
    private final Long sequenceNumber;

    /**
     * The processor-specific processing timestamp of the data stream element.
     * Specifies the time when the processor of the worker (i.e., the task of the Samza job) has started processing the data stream element.
     * Measured at the start of the process() method of the worker using the processor's local clock.
     * Null at the processor where the data stream is generated (since it is not processed at this processor).
     */
    private final Long processingTimestamp;

    /**
     * The data stream analysis system specific ingestion timestamp of the data stream element.
     * Specifies the time when a raw input stream element has entered the data stream analysis system.
     * Measured when the data stream element is appended to the Kafka topic using the Kafka broker's local clock. (Attention: log.message.timestamp.type has to be set to LogAppendTime in the Kafka configuration)
     * Null for every event, statistics, and internal data stream element as these elements are not received by the data stream analysis system.
     */
    private final Long ingestionTimestamp;

    /**
     * The content of the data stream element (contained as byte array in the value field of the Kafka message)
     */
    private final ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content;

    /**
     * AbstractImmutableDataStreamElement constructor.
     *
     * @param streamName          The name of the data stream in which the data stream element is shipped (i.e., the Kafka topic)
     * @param streamCategory      The category of the data stream
     * @param key                 The key of the data stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the data stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the data stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the data stream element
     * @param content             The content of the data stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the data stream element could not be generated
     */
    public AbstractImmutableDataStreamElement(String streamName, StreamCategory streamCategory, String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        if (!streamCategory.equals(StreamCategory.RAWINPUT)) {
            ingestionTimestamp = null; // Only raw input stream elements have an ingestion timestamp
        }
        if (streamCategory.equals(StreamCategory.INTERNAL) && (sequenceNumber != null || processingTimestamp != null || ingestionTimestamp != null)) {
            throw new CannotGenerateDataStreamElement("Cannot generate data stream element since the stream category is INTERNAL but the sequence number, the processing timestamp, and/or the ingestion timestamp is set.");
        }
        if ((streamCategory.equals(StreamCategory.RAWINPUT) || streamCategory.equals(StreamCategory.STATISTICS) || streamCategory.equals(StreamCategory.STATE)) && !content.getAtomic()) {
            throw new CannotGenerateDataStreamElement("Cannot generate a non-atomic raw input stream element, a non-atomic statistics, or a non-atomic state stream element.");
        }
        if (content.getAtomic() && content.getPhase() != ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL) {
            throw new CannotGenerateDataStreamElement("Cannot generate atomic data stream element with a (non-null) phase.");
        } else if (!content.getAtomic() && content.getPhase() == ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL) {
            throw new CannotGenerateDataStreamElement("Cannot generate non-atomic data stream element without a proper phase.");
        }
        if (content.getAtomic() && !content.getEventIdentifier().equals("")) {
            throw new CannotGenerateDataStreamElement("Cannot generate atomic data stream element with a (non-empty) event identifier.");
        } else if (!content.getAtomic() && content.getEventIdentifier().equals("")) {
            throw new CannotGenerateDataStreamElement("Cannot generate atomic data stream element without a proper event identifier.");
        }
        this.streamName = streamName;
        this.streamCategory = streamCategory;
        this.key = key;
        this.content = content;
        this.sequenceNumber = sequenceNumber;
        this.processingTimestamp = processingTimestamp;
        this.ingestionTimestamp = ingestionTimestamp;
    }

    /**
     * Generate a casted data stream element from a byte array.
     *
     * @param key                Key
     * @param contentByteArray   Content byte array (contained in the value field of the Kafka message)
     * @param sequenceNumber     Sequence number (i.e., Kafka offset)
     * @param systemTimestamp    System timestamp measured at the start of the process() method of the worker using the processor's local clock
     * @param logAppendTimestamp Timestamp measured when the data stream element is appended to the Kafka topic using the Kafka broker's local clock (Attention: log.message.timestamp.type has to be set to LogAppendTime in the Kafka configuration)
     * @return Casted data stream element
     * @throws ClassNotFoundException         Thrown if the class of the data stream element could not have been found
     * @throws InvalidProtocolBufferException Thrown if there is a Protobuf problem
     * @throws NoSuchMethodException          Thrown if the constructor could not be found
     * @throws IllegalAccessException         Thrown if the constructor is not accessible
     * @throws InvocationTargetException      Thrown if the constructor throws an exception
     * @throws InstantiationException         Thrown if the data stream element object could not have been instantiated
     */
    public static AbstractImmutableDataStreamElement generateDataStreamElementFromByteArray(String key, byte[] contentByteArray, Long sequenceNumber, Long systemTimestamp, Long logAppendTimestamp) throws ClassNotFoundException, InvalidProtocolBufferException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.parseFrom(contentByteArray);

        // Convention Data Stream Elements
        // Protobuf File: /src/main/protobuf/streamTeam/YYY/XXXStreamElementPayload.proto (with package streamTeam.YYY)
        // Java File: XXXStreamElement.java in the subpackage YYY of the package of AbstractImmutableDataStreamElement.java

        // e.g. type.googleapis.com/streamTeam.football.MatchesStreamElementPayload --> ch.unibas.dmi.dbis.streamTeam.dataStreamElements.football.MatchesStreamElement
        String[] payloadTypeUrlParts = content.getPayload().getTypeUrl().split("streamTeam");
        String elementClassName = AbstractImmutableDataStreamElement.class.getPackage().getName() + payloadTypeUrlParts[payloadTypeUrlParts.length - 1].replace("Payload", "");

        //https://stackoverflow.com/questions/5658182/initializing-a-class-with-class-forname-and-which-have-a-constructor-which-tak
        Constructor c = Class.forName(elementClassName).getConstructor(String.class, Long.class, Long.class, Long.class, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.class);
        AbstractImmutableDataStreamElement dataStreamElement = (AbstractImmutableDataStreamElement) c.newInstance(key, sequenceNumber, systemTimestamp, logAppendTimestamp, content);
        return dataStreamElement;
    }

    /**
     * Gets the name of the data stream in which the data stream element is shipped (i.e., the Kafka topic).
     *
     * @return The name of the data stream in which the data stream element is shipped (i.e., the Kafka topic)
     */
    public final String getStreamName() {
        return this.streamName;
    }

    /**
     * Gets the category of the data stream.
     *
     * @return The category of the data stream
     */
    public final StreamCategory getStreamCategory() {
        return this.streamCategory;
    }

    /**
     * Gets the key of the data stream element which defines the partition of the data stream to which it belongs.
     *
     * @return The key of the data stream element which defines the partition of the data stream to which it belongs
     */
    public final String getKey() {
        return this.key;
    }

    /**
     * Gets if the data stream element is atomic or not (and thus non-atomic).
     *
     * @return True if the data stream element is atomic, false if the data stream element is non-atomic
     */
    public final boolean isAtomic() {
        return this.content.getAtomic();
    }

    /**
     * Gets the event identifier of a non-atomic event stream element.
     *
     * @return The event identifier of the non-atomic event stream element
     * @throws CannotRetrieveInformationException Thrown if the data stream element is no non-atomic event stream element
     */
    public final String getEventIdentifier() throws CannotRetrieveInformationException {
        if (!this.isAtomic() && this.streamCategory.equals(StreamCategory.EVENT)) {
            return this.content.getEventIdentifier();
        } else {
            throw new CannotRetrieveInformationException("Cannot get event identifier of data stream element " + this + " since this is not a non-atomic event stream element.");
        }
    }

    /**
     * Gets the phase of a non-atomic data stream element.
     *
     * @return The phase of a non-atomic data stream element
     * @throws CannotRetrieveInformationException Thrown if the data stream element is atomic
     */
    public final NonAtomicEventPhase getPhase() throws CannotRetrieveInformationException {
        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase phase = this.content.getPhase();
        if (phase == ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.START) {
            return NonAtomicEventPhase.START;
        } else if (phase == ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.ACTIVE) {
            return NonAtomicEventPhase.ACTIVE;
        } else if (phase == ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.END) {
            return NonAtomicEventPhase.END;
        } else {
            throw new CannotRetrieveInformationException("Cannot get phase of data stream element " + this + " since it is an atomic data stream element.");
        }
    }

    /**
     * Gets the globally unambiguous sequence number of the data stream element (i.e., the Kafka offset).
     *
     * @return The globally unambiguous sequence number of the data stream element (i.e., the Kafka offset)
     * @throws CannotRetrieveInformationException Thrown if this method is called at the processor where the data stream element is generated (since the sequence numbers is null in this case as it is assigned by Kafka)
     */
    public final long getSequenceNumber() throws CannotRetrieveInformationException {
        if (this.sequenceNumber != null) {
            return this.sequenceNumber;
        } else {
            throw new CannotRetrieveInformationException("Cannot get sequence number of data stream element " + this + " since this element has been generated by the processor who called this method.");
        }
    }

    /**
     * Gets the processor-specific processing timestamp of the data stream element.
     * Specifies the time when the processor of the worker (i.e., the task of the Samza job) has started processing the data stream element.
     * Measured at the start of the process() method of the worker using the processor's local clock.
     *
     * @return The processor-specific processing timestamp of the data stream element
     * @throws CannotRetrieveInformationException Thrown if this method is called at the processor where the data stream element is generated (since the processing timestamp is null in this case as the element is not processed at this processor)
     */
    public final long getProcessingTimestamp() throws CannotRetrieveInformationException {
        if (this.processingTimestamp != null) {
            return this.processingTimestamp;
        } else {
            throw new CannotRetrieveInformationException("Cannot get processing timestamp of data stream element " + this + " since this element has been generated by the processor who called this method.");
        }
    }

    /**
     * Gets the data stream analysis system specific ingestion timestamp of the data stream element.
     * Specifies the time when the raw input stream element has entered the data stream analysis system.
     * Measured when the data stream element is appended to the Kafka topic using the Kafka broker's local clock. (Attention: log.message.timestamp.type has to be set to LogAppendTime in the Kafka configuration)
     *
     * @return The data stream analysis system specific ingestion timestamp of the data stream element
     * @throws CannotRetrieveInformationException Thrown if this method is called for an event, statistics, or internal stream element (since the ingestion timestamp is null in this case as the element is not received by the data stream analysis system)
     */
    public final long getIngestionTimestamp() throws CannotRetrieveInformationException {
        if (this.ingestionTimestamp != null) {
            return this.ingestionTimestamp;
        } else {
            throw new CannotRetrieveInformationException("Cannot get ingestion timestamp of data stream element " + this + " since this element is an event, statistics, or internal stream element.");
        }
    }

    /**
     * Gets the globally unambiguous generation timestamp of the data stream element.
     * Specifies the time of generation (according to one globally consistent time space if the clocks of all raw input generation devices are properly synchronized).
     *
     * @return The globally unambiguous generation timestamp of the data stream element
     */
    public final long getGenerationTimestamp() {
        return this.content.getGenerationTimestamp();
    }

    /**
     * Gets the object identifier contained in the object identifiers tuple at a given index.
     *
     * @param index Index
     * @return Object identifier
     * @throws CannotRetrieveInformationException Thrown if there is an IndexOufOfBoundsException
     */
    public final String getObjectIdentifier(int index) throws CannotRetrieveInformationException {
        try {
            return this.content.getObjectIdentifiers(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of objectIdentifiers from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }
    }

    /**
     * Gets the cardinality of the object identifiers tuple and thus the size of the object identifiers tuple list.
     *
     * @return Cardinality of the object identifiers tuple / Size of the object identifiers tuple list
     */
    public final int getObjectIdentifiersListSize() {
        return this.content.getObjectIdentifiersCount();
    }

    /**
     * Gets the object identifiers tuple from the content as a list.
     *
     * @return Object identifiers tuple as a list.
     */
    public final List<String> getObjectIdentifiersList() {
        ArrayList<String> list = new ArrayList<>();
        for (Object objectIdentifier : this.content.getObjectIdentifiersList().toArray()) {
            list.add((String) objectIdentifier);
        }
        return list;
    }

    /**
     * Gets the group identifier contained in the group identifiers tuple at a given index.
     *
     * @param index Index
     * @return Group identifier
     * @throws CannotRetrieveInformationException Thrown if there is an IndexOufOfBoundsException
     */
    public final String getGroupIdentifier(int index) throws CannotRetrieveInformationException {
        try {
            return this.content.getGroupIdentifiers(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of groupIdentifiers from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }
    }

    /**
     * Gets the cardinality of the group identifiers tuple and thus the size of the group identifiers tuple list.
     *
     * @return Cardinality of the group identifiers tuple / Size of the group identifiers tuple list
     */
    public final int getGroupIdentifiersListSize() {
        return this.content.getGroupIdentifiersCount();
    }

    /**
     * Gets the group identifiers tuple from the content as a list.
     *
     * @return Group identifiers tuple as a list.
     */
    public final List<String> getGroupIdentifiersList() {
        ArrayList<String> list = new ArrayList<>();
        for (Object groupIdentifier : this.content.getGroupIdentifiersList().toArray()) {
            list.add((String) groupIdentifier);
        }
        return list;
    }

    /**
     * Gets the position contained in the position tuple at a given index.
     *
     * @param index Index
     * @return Position
     * @throws CannotRetrieveInformationException Thrown if there is an IndexOufOfBoundsException
     */
    public final Geometry.Vector getPosition(int index) throws CannotRetrieveInformationException {
        try {
            ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position pos = this.content.getPositions(index);
            return new Geometry.Vector(pos.getX(), pos.getY(), pos.getZ());
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of positions from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }
    }

    /**
     * Gets the cardinality of the positions tuple and thus the size of the positions tuple list.
     *
     * @return Cardinality of the positions tuple / Size of the positions tuple list
     */
    public final int getPositionsListSize() {
        return this.content.getPositionsCount();
    }

    /**
     * Gets the positions tuple from the content as a list.
     *
     * @return Positions tuple as a list.
     */
    public final List<Geometry.Vector> getPositionsList() {
        ArrayList<Geometry.Vector> list = new ArrayList<>();
        for (ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position pos : this.content.getPositionsList()) {
            list.add(new Geometry.Vector(pos.getX(), pos.getY(), pos.getZ()));
        }
        return list;
    }

    /**
     * Gets the packed and uncasted payload from the content.
     *
     * @return Packed and uncasted payload from the content
     */
    protected final Any getPayload() {
        return this.content.getPayload();
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    protected abstract Message getUnpackedPayload() throws InvalidProtocolBufferException;

    /**
     * Returns a string representation of the data stream element.
     *
     * @return String representation of the data stream element
     */
    @Override
    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[streamName:<");
        stringBuilder.append(this.streamName);
        stringBuilder.append(">,cat:<");
        stringBuilder.append(this.streamCategory);
        stringBuilder.append(">,key:<");
        stringBuilder.append(this.key);
        stringBuilder.append(">,sequenceNumber:<");
        if (this.sequenceNumber == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.sequenceNumber);
        }
        stringBuilder.append(">,processingTimestamp:<");
        if (this.processingTimestamp == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.processingTimestamp);
        }
        stringBuilder.append(">,ingestionTimestamp:<");
        if (this.ingestionTimestamp == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.ingestionTimestamp);
        }
        stringBuilder.append(">,content:<");
        try {
            stringBuilder.append(this.getUnpackedPayload().toString());
        } catch (InvalidProtocolBufferException e) {
            stringBuilder.append(this.content.toString());
        }
        stringBuilder.append(">]");
        return stringBuilder.toString();
    }

    /**
     * Gets the content as a byte array.
     *
     * @return Content as a byte array
     */
    public byte[] getContentAsByteArray() {
        return this.content.toByteArray();
    }

    /**
     * Gets a field value from the content as a Serializable.
     *
     * @param name      Name of the field
     * @param inPayload Specifies if the field is in the payload (true) or not (false)
     * @return Field value as a Serializable
     * @throws CannotRetrieveInformationException Thrown if it has not been possible to get the field value
     */
    public final Serializable getFieldValueByName(String name, boolean inPayload) throws CannotRetrieveInformationException {
        try {
            Message message;
            if (inPayload) {
                message = this.getUnpackedPayload();
            } else {
                message = this.content;
            }
            // https://stackoverflow.com/questions/38071689/examining-a-protobuf-message-how-to-get-field-values-by-name/38072285#38072285
            Descriptors.FieldDescriptor fieldDescriptor = message.getDescriptorForType().findFieldByName(name);
            return (Serializable) message.getField(fieldDescriptor);
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot get field " + name + " from data stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        } catch (ClassCastException e) {
            throw new CannotRetrieveInformationException("Cannot get field " + name + " from data stream element " + this + ": ClassCastException:" + e.toString());
        } catch (NullPointerException e) {
            throw new CannotRetrieveInformationException("Cannot get field " + name + " from data stream element " + this + ": NullPointerException:" + e.toString());
        }
    }

    /**
     * Gets an array value from the content as a Serializable.
     *
     * @param name      Name of the array (repeated field)
     * @param index     Index
     * @param inPayload Specifies if the array is in the payload (true) or not (false)
     * @return Array value as a Serializable
     * @throws CannotRetrieveInformationException Thrown if it has not been possible to get the array value
     */
    public Serializable getArrayValueByName(String name, int index, boolean inPayload) throws CannotRetrieveInformationException {
        try {
            Message message;
            if (inPayload) {
                message = this.getUnpackedPayload();
            } else {
                message = this.content;
            }
            // https://stackoverflow.com/questions/38071689/examining-a-protobuf-message-how-to-get-field-values-by-name/38072285#38072285
            Descriptors.FieldDescriptor fieldDescriptor = message.getDescriptorForType().findFieldByName(name);
            return (Serializable) message.getRepeatedField(fieldDescriptor, index);
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot get field " + name + " from data stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        } catch (ClassCastException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of repeated field " + name + " from data stream element " + this + ": ClassCastException:" + e.toString());
        } catch (NullPointerException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of repeated field " + name + " from data stream element " + this + ": NullPointerException:" + e.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get index " + index + " of repeated field " + name + " from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }
    }

    /**
     * Gets the size of an array from the content.
     *
     * @param name      Name of the array (repeated field)
     * @param inPayload Specifies if the array is in the payload (true) or not (false)
     * @return Array size
     * @throws CannotRetrieveInformationException Thrown if it has not been possible to get the array size
     */
    public int getArraySizeByName(String name, boolean inPayload) throws CannotRetrieveInformationException {
        try {
            Message message;
            if (inPayload) {
                message = this.getUnpackedPayload();
            } else {
                message = this.content;
            }
            // https://stackoverflow.com/questions/38071689/examining-a-protobuf-message-how-to-get-field-values-by-name/38072285#38072285
            Descriptors.FieldDescriptor fieldDescriptor = message.getDescriptorForType().findFieldByName(name);
            return message.getRepeatedFieldCount(fieldDescriptor);
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot get size of repeated field" + name + " from data stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        } catch (ClassCastException e) {
            throw new CannotRetrieveInformationException("Cannot get size of repeated field " + name + " from data stream element " + this + ": ClassCastException:" + e.toString());
        } catch (NullPointerException e) {
            throw new CannotRetrieveInformationException("Cannot get size of repeated field " + name + " from data stream element " + this + ": NullPointerException:" + e.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get size of repeated field " + name + " from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }
    }

    /**
     * Gets all payload fields as a key value list.
     *
     * @return All payload fields as a key value list
     * @throws CannotRetrieveInformationException Thrown if it has not been possible to get all payload fields
     */
    public List<Pair<String, Serializable>> getPayloadFieldsAsKeyValueList() throws CannotRetrieveInformationException {
        try {
            List<Pair<String, Serializable>> keyValueList = new LinkedList<>();

            // Note: cannot use getAllFields() since this method does not list fields with a default value (see https://github.com/protocolbuffers/protobuf/issues/1772)
            Message message = this.getUnpackedPayload();
            for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                String key = fieldDescriptor.getJsonName();
                Serializable value;
                if (fieldDescriptor.isRepeated()) {
                    LinkedList<Serializable> valueList = new LinkedList<Serializable>();
                    int size = message.getRepeatedFieldCount(fieldDescriptor);
                    for (int i = 0; i < size; i++) {
                        valueList.add((Serializable) message.getRepeatedField(fieldDescriptor, i));
                    }
                    value = (Serializable) valueList;
                } else {
                    value = (Serializable) message.getField(fieldDescriptor);
                }
                keyValueList.add(new Pair(key, value));
            }

            return keyValueList;
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot get all payload fields from data stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        } catch (ClassCastException e) {
            throw new CannotRetrieveInformationException("Cannot get all payload fields from data stream element " + this + ": ClassCastException:" + e.toString());
        } catch (NullPointerException e) {
            throw new CannotRetrieveInformationException("Cannot get all payload fields from data stream element " + this + ": NullPointerException:" + e.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new CannotRetrieveInformationException("Cannot get all payload fields from data stream element " + this + ": IndexOutOfBoundsException:" + e.toString());
        }

    }

    /**
     * Generate an event identifier for a non-atomic event stream element.
     *
     * @param streamName                              Stream name
     * @param key                                     Key
     * @param innerKey                                Inner key to be able to have different counters for the same key and streamName (e.g., required to support simultaneous/overlapping non-atomic runningEvents for two players in the same match)
     * @param perStreamNameKeyAndInnerKeyCounterValue Counter value for the stream name, the key, and the innerKey
     * @return Event identifier
     */
    protected static final String generateEventIdentifier(String streamName, String key, String innerKey, long perStreamNameKeyAndInnerKeyCounterValue) {
        StringBuilder eventIdentifierBuilder = new StringBuilder(streamName);
        eventIdentifierBuilder.append("_");
        eventIdentifierBuilder.append(key);
        eventIdentifierBuilder.append("_");
        eventIdentifierBuilder.append(innerKey);
        eventIdentifierBuilder.append("_");
        eventIdentifierBuilder.append(perStreamNameKeyAndInnerKeyCounterValue);
        return eventIdentifierBuilder.toString();
    }

    /**
     * Generates a phase which can be stored in the phase field in the content of the data stream element given a non-atomic event phase.
     * NonAtomicEventPhase --> ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase
     *
     * @param phase Non-atomic event phase
     * @return phase which can be stored in the phase field in the content of the data stream element
     */
    protected static final ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase generateAbstractImmutableDataStreamElementPhaseFromNonAtomicEventPhase(NonAtomicEventPhase phase) {
        if (phase == NonAtomicEventPhase.START) {
            return ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.START;
        } else if (phase == NonAtomicEventPhase.ACTIVE) {
            return ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.ACTIVE;
        } else {
            return ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.END;
        }
    }

    /**
     * Generates a position which can be stored in the positions tuple in the content of the data stream element given a Vector position.
     * Geometry.Vector --> ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position
     *
     * @param position Vector position
     * @return Position which can be stored in the positions tuple in the content of the data stream element
     */
    protected static final ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position generateAbstractImmutableDataStreamElementPosition(Geometry.Vector position) {
        return ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position.newBuilder().setX(position.x).setY(position.y).setZ(position.z).build();
    }

    /**
     * StreamCategory enumeration.
     */
    public enum StreamCategory {
        /**
         * Raw input stream
         */
        RAWINPUT {
            public String toString() {
                return "RAWINPUT";
            }
        },
        /**
         * Event stream
         */
        EVENT {
            public String toString() {
                return "EVENT";
            }
        },
        /**
         * Statistics stream
         */
        STATISTICS {
            public String toString() {
                return "STATISTICS";
            }
        },
        /**
         * State stream
         */
        STATE {
            public String toString() {
                return "STATE";
            }
        },
        /**
         * Internal stream (CAN ONLY BE TRANSFERRED BETWEEN MODULES OF THE INTERNAL MODULE GRAPH OF A WORKER. MUST NUT BE RECEIVED OR SENT BY A PROCESSOR!!!)
         */
        INTERNAL {
            public String toString() {
                return "INTERNAL";
            }
        };
    }

    /**
     * Indicates that it has not been possible to create the data stream element.
     */
    public static class CannotGenerateDataStreamElement extends Exception {

        /**
         * CannotGenerateDataStreamElement constructor.
         *
         * @param msg Message that explains the problem
         */
        public CannotGenerateDataStreamElement(String msg) {
            super(msg);
        }
    }

    /**
     * Indicates that it has not been possible to retrieve information from the content (incl. payload).
     */
    public static class CannotRetrieveInformationException extends Exception {

        /**
         * CannotRetrieveInformationException constructor.
         *
         * @param msg Message that explains the problem
         */
        public CannotRetrieveInformationException(String msg) {
            super(msg);
        }
    }
}
