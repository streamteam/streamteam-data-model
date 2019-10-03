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

import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.DummyStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.ImmutableDataStreamElementContentProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

import java.util.List;

/**
 * DummyStreamElement used in DataStreamElementTest and in the JUnit tests of the data stream analysis system.
 * DO NOT USE FOR OTHER PURPOSES!!!
 */
public class DummyStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * DummyStreamElement constructor.
     *
     * @param streamName          The name of the data stream in which the dummy stream element is shipped (i.e., the Kafka topic)
     * @param streamCategory      The category of the data stream in which the dummy stream element is shipped
     * @param key                 The key of the dummy stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the dummy stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the dummy stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the dummy stream element
     * @param content             The content of the dummy stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the dummy stream element could not be generated
     */
    public DummyStreamElement(String streamName, StreamCategory streamCategory, String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(streamName, streamCategory, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a dummy stream element.
     *
     * @param streamName                              The name of the data stream in which the dummy stream element is shipped (i.e., the Kafka topic)
     * @param streamCategory                          The category of the data stream in which the dummy stream element is shipped
     * @param key                                     he key of the dummy stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber                          The globally unambiguous sequence number of the dummy stream element (i.e., the Kafka offset)
     * @param processingTimestamp                     The processor-specific processing timestamp of the dummy stream element
     * @param ingestionTimestamp                      The data stream analysis system specific ingestion timestamp of the dummy stream element
     * @param generationTimestamp                     The generation timestamp of the dummy stream element
     * @param objectIdentifiers                       The objectIdentifiers of the dummy stream element
     * @param groupIdentifiers                        The groupIdentifiers of the dummy stream element
     * @param positions                               The positions of the dummy stream element
     * @param longValue                               The longValue of the dummy stream element (in payload)
     * @param boolValue                               The boolValue of the dummy stream element (in payload)
     * @param stringValue                             The stringValue of the dummy stream element (in payload)
     * @param doubleValue                             The doubleValue of the dummy stream element (in payload)
     * @param repeatedValues                          The repeatedValues of the dummy stream element (in payload)
     * @param atomic                                  Flag which indicates if the dummy stream element is atomic or not
     * @param phase                                   Phase of the dummy event
     * @param eventIdentifierInnerKey                 Inner key to be able to have different counters for the same key and streamName (e.g., required to support simultaneous/overlapping non-atomic runningEvents for two players in the same match)
     * @param perStreamNameKeyAndInnerKeyCounterValue Counter value for the stream name, the key, and the innerKey
     * @return dummy stream element
     * @throws CannotGenerateDataStreamElement Thrown if the dummy stream element could not be generated
     */
    public static DummyStreamElement generateDummyStreamElement(String streamName, StreamCategory streamCategory, String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, Long generationTimestamp, List<String> objectIdentifiers, List<String> groupIdentifiers, List<Geometry.Vector> positions, long longValue, boolean boolValue, String stringValue, double doubleValue, List<Long> repeatedValues, boolean atomic, NonAtomicEventPhase phase, String eventIdentifierInnerKey, Long perStreamNameKeyAndInnerKeyCounterValue) throws CannotGenerateDataStreamElement {
        DummyStreamElementPayloadProtos.DummyStreamElementPayload payload = DummyStreamElementPayloadProtos.DummyStreamElementPayload.newBuilder()
                .setLongValue(longValue)
                .setBoolValue(boolValue)
                .setStringValue(stringValue)
                .setDoubleValue(doubleValue)
                .addAllRepeatedValue(repeatedValues)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase protobufPhase = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL;
        if (phase != null) {
            protobufPhase = generateAbstractImmutableDataStreamElementPhaseFromNonAtomicEventPhase(phase);
        }

        String eventIdentifier = "";
        if (eventIdentifierInnerKey != null && !eventIdentifierInnerKey.equals("") && perStreamNameKeyAndInnerKeyCounterValue != null) {
            eventIdentifier = generateEventIdentifier(streamName, key, eventIdentifierInnerKey, perStreamNameKeyAndInnerKeyCounterValue);
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(atomic)
                .setEventIdentifier(eventIdentifier)
                .setPhase(protobufPhase)
                .setGenerationTimestamp(generationTimestamp)
                .addAllObjectIdentifiers(objectIdentifiers)
                .addAllGroupIdentifiers(groupIdentifiers)
                .setPayload(Any.pack(payload));

        for (Geometry.Vector position : positions) {
            contentBuilder.addPositions(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Position.newBuilder().setX(position.x).setY(position.y).setZ(position.z).build());
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new DummyStreamElement(streamName, streamCategory, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Gets the long value of the dummy stream element.
     *
     * @return Long value of the dummy stream element.
     * @throws CannotRetrieveInformationException Thrown if the dummy stream element is ill-formed
     */
    public long getLongValue() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class).getLongValue();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getLongValue on dummy stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the bool value of the dummy stream element.
     *
     * @return Bool value of the dummy stream element.
     * @throws CannotRetrieveInformationException Thrown if the dummy stream element is ill-formed
     */
    public boolean getBoolValue() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class).getBoolValue();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getBoolValue on dummy stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the string value of the dummy stream element.
     *
     * @return String value of the dummy stream element.
     * @throws CannotRetrieveInformationException Thrown if the dummy stream element is ill-formed
     */
    public String getStringValue() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class).getStringValue();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getStringValue on dummy stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the double value of the dummy stream element.
     *
     * @return Double value of the dummy stream element.
     * @throws CannotRetrieveInformationException Thrown if the dummy stream element is ill-formed
     */
    public double getDoubleValue() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class).getDoubleValue();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getDoubleValue on dummy stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the value of the repeated field of the dummy stream element given an index.
     *
     * @return Value of the repeated field of the dummy stream element given an index
     * @throws CannotRetrieveInformationException Thrown if the dummy stream element is ill-formed
     */
    public double getRepeatedValue(int index) throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class).getRepeatedValue(index);
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getRepeatedValue(" + index + ") on dummy stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(DummyStreamElementPayloadProtos.DummyStreamElementPayload.class);
    }
}
