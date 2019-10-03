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

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements.football;

import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.AbstractImmutableDataStreamElement;
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.ImmutableDataStreamElementContentProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.FieldObjectStateStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains the position and velocity state of an object (player or ball).
 * A fieldObjectState stream element is a state stream element.
 */
public class FieldObjectStateStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the fieldObjectState stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "fieldObjectState";

    /**
     * FieldObjectStateStreamElement constructor.
     *
     * @param key                 The key of the fieldObjectState stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the fieldObjectState stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the fieldObjectState stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the fieldObjectState stream element
     * @param content             The content of the fieldObjectState stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the fieldObjectState stream element could not be generated
     */
    public FieldObjectStateStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATE, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a fieldObjectState stream element.
     *
     * @param matchId             The identifier of the match to which the fieldObjectState stream element belong
     * @param generationTimestamp The generation timestamp of the fieldObjectState stream element
     * @param objectInfo          Information about the object (player or ball) (incl. position and velocity information)
     * @return fieldObjectState stream element
     * @throws CannotGenerateDataStreamElement Thrown if the fieldObjectState stream element could not be generated
     */
    public static FieldObjectStateStreamElement generateFieldObjectStateStreamElement(String matchId, long generationTimestamp, ObjectInfo objectInfo) throws CannotGenerateDataStreamElement {
        FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload payload = FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.newBuilder()
                .setVx(objectInfo.getVelocity().x)
                .setVy(objectInfo.getVelocity().y)
                .setVz(objectInfo.getVelocity().z)
                .setVabs(objectInfo.getAbsoluteVelocity())
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(objectInfo.getPosition()))
                .addObjectIdentifiers(objectInfo.getObjectId())
                .addGroupIdentifiers(objectInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new FieldObjectStateStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the object (player or ball).
     *
     * @return Identifier of the object (player or ball)
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public String getObjectId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team the object (player or ball) belongs to.
     *
     * @return Identifier of the team the object (player or ball) belongs to
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the object (player or ball).
     *
     * @return Position where the object (player or ball)
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public Geometry.Vector getPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the velocity of the object (player or ball) in x direction.
     *
     * @return Velocity of the object (player or ball) in x direction
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public double getVx() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.class).getVx();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVx on fieldObjectState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the velocity of the object (player or ball) in y direction.
     *
     * @return Velocity of the object (player or ball) in y direction
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public double getVy() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.class).getVy();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVy on fieldObjectState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the velocity of the object (player or ball) in z direction.
     *
     * @return Velocity of the object (player or ball) in z direction
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public double getVz() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.class).getVz();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVz on fieldObjectState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the absolute velocity of the object (player or ball).
     *
     * @return Absolute velocity of the object (player or ball)
     * @throws CannotRetrieveInformationException Thrown if the fieldObjectState stream element is ill-formed
     */
    public double getVabs() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.class).getVabs();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVabs on fieldObjectState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(FieldObjectStateStreamElementPayloadProtos.FieldObjectStateStreamElementPayload.class);
    }
}
