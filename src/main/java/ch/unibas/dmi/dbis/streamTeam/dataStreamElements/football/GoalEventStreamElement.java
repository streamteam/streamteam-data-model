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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.GoalEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a goal event.
 * An goalEvent stream element is an atomic event stream element.
 */
public class GoalEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the goalEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "goalEvent";

    /**
     * GoalEventStreamElement constructor.
     *
     * @param key                 The key of the goalEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the goalEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the goalEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the goalEvent stream element
     * @param content             The content of the goalEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the goalEvent stream element could not be generated
     */
    public GoalEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates an goalEvent stream element.
     *
     * @param matchId              The identifier of the match in which the goal took place
     * @param generationTimestamp  The generation timestamp of the goal
     * @param kickPlayerInfo       Information about the player who kicked the ball (incl. position information)
     * @param leftFieldObjectDummy Dummy object information containing the position where the ball left the field / entered the goal
     * @param length               The length of the shot (in m)
     * @param velocity             The velocity of the shot (in m/s)
     * @param angleInRad           The angle of the shot in playing direction (in rad)
     * @param directionCategory    The direction category of the shot in playing direction
     * @return goalEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the goalEvent stream element could not be generated
     */
    public static GoalEventStreamElement generateGoalEventStreamElement(String matchId, long generationTimestamp, ObjectInfo kickPlayerInfo, ObjectInfo leftFieldObjectDummy, double length, double velocity, double angleInRad, String directionCategory) throws CannotGenerateDataStreamElement {
        GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload payload = GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.newBuilder()
                .setLength(length)
                .setVelocity(velocity)
                .setAngle(angleInRad * 180 / Math.PI)
                .setDirection(directionCategory)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(kickPlayerInfo.getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(leftFieldObjectDummy.getPosition()))
                .addObjectIdentifiers(kickPlayerInfo.getObjectId())
                .addGroupIdentifiers(kickPlayerInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new GoalEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who kicked the ball.
     *
     * @return Identifier of the player who kicked the ball
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public String getKickPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player kicked the ball.
     *
     * @return Identifier of the team whose player kicked the ball
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public String getKickTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position where the ball has been kicked.
     *
     * @return Position where the ball has been kicked
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public Geometry.Vector getKickPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position where the ball has left the field / entered the goal.
     *
     * @return Position where the ball has left the field / entered the goal
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public Geometry.Vector getLeftFieldPosition() throws CannotRetrieveInformationException {
        return this.getPosition(1);
    }

    /**
     * Gets the length of the shot in m.
     *
     * @return Length of the shot in m.
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public double getLength() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.class).getLength();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getLength on goalEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the velocity of the shot in m/s.
     *
     * @return Velocity of the shot in m/s.
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public double getVelocity() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.class).getVelocity();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVelocity on goalEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the angle of the shot in playing direction (in degree).
     *
     * @return Angle of the shot in playing direction (in degree)
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public double getAngleInDegree() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.class).getAngle();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAngle on goalEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the direction category of the shot in playing direction.
     *
     * @return Direction category of the shot in playing direction
     * @throws CannotRetrieveInformationException Thrown if the goalEvent stream element is ill-formed
     */
    public String getDirection() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.class).getDirection();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getDirection on goalEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(GoalEventStreamElementPayloadProtos.GoalEventStreamElementPayload.class);
    }
}
