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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallFieldSideStateStreamElementPayloadProtos;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about an area event.
 * A ballFieldSideState stream element is a state stream element.
 * TODO: The Ball Field Side Worker is a relatively useless worker that was only introduced to check if StreamTeam can perform Deep Learning based analyses. Remove this class as soon as there is a more meaningful Deep Learning based analysis worker.
 */
public class BallFieldSideStateStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the ballFieldSideState stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "ballFieldSideState";

    /**
     * BallFieldSideStateStreamElement constructor.
     *
     * @param key                 The key of the ballFieldSideState stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the ballFieldSideState stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the ballFieldSideState stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the ballFieldSideState stream element
     * @param content             The content of the ballFieldSideState stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the ballFieldSideState stream element could not be generated
     */
    public BallFieldSideStateStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATE, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a ballFieldSideState stream element.
     *
     * @param matchId             The identifier of the match for which the ball field side state is generated
     * @param generationTimestamp The generation timestamp of the ball field side state
     * @param ballOnLeftSide      Flag indicating if the ball is on the left side of the field
     * @return ballFieldSideState stream element
     * @throws CannotGenerateDataStreamElement Thrown if the ballFieldSideState stream element could not be generated
     */
    public static BallFieldSideStateStreamElement generateBallFieldSideStateStreamElement(String matchId, long generationTimestamp, boolean ballOnLeftSide) throws CannotGenerateDataStreamElement {
        BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload payload = BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.newBuilder()
                .setBallOnLeftSide(ballOnLeftSide)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload))
                .build();

        return new BallFieldSideStateStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the ballFieldSideState stream element represents the fact that the ball is on the left side.
     *
     * @return True if the ballFieldSideState stream element represents the fact that the ball is on the left side, false if ballFieldSideState stream element represents the fact that the ball is on the right side.
     * @throws CannotRetrieveInformationException Thrown if the ballFieldSideState stream element is ill-formed
     */
    public boolean isBallOnLeftSide() throws CannotRetrieveInformationException {
        return this.getBallOnLeftSide();
    }

    /**
     * Gets if the ballFieldSideState stream element represents the fact that the ball is on the right side.
     *
     * @return True if the ballFieldSideState stream element represents the fact that the ball is on the right side, false if ballFieldSideState stream element represents the fact that the ball is on the left side.
     * @throws CannotRetrieveInformationException Thrown if the ballFieldSideState stream element is ill-formed
     */
    public boolean isBallOnRightSide() throws CannotRetrieveInformationException {
        return !this.getBallOnLeftSide();
    }

    /**
     * Gets the flag which indicates if the ball is on the left side of the field.
     *
     * @return Flag indicating if the ball is on the left side of the field
     * @throws CannotRetrieveInformationException Thrown if the ballFieldSideState stream element is ill-formed
     */
    private boolean getBallOnLeftSide() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.class).getBallOnLeftSide();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getBallOnLeftSide on ballFieldSideState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(BallFieldSideStateStreamElementPayloadProtos.BallFieldSideStateStreamElementPayload.class);
    }
}
