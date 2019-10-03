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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a kick event.
 * A kickEvent stream element is an atomic event stream element.
 */
public class KickEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the kickEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "kickEvent";

    /**
     * KickEventStreamElement constructor.
     *
     * @param key                 The key of the kickEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the kickEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the kickEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the kickEvent stream element
     * @param content             The content of the kickEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the kickEvent stream element could not be generated
     */
    public KickEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a kickEvent stream element.
     *
     * @param matchId                The identifier of the match in which the kick took place
     * @param generationTimestamp    The generation timestamp of the kick
     * @param playerInfo             Information about the player who kicked the ball (incl. position information)
     * @param posBall                Position of the ball when the kick was detected
     * @param numPlayersNearerToGoal Number of players of the opposing team which are nearer to the goal
     * @param attacked
     * @param zone                   Zone in which the ball was kicked
     * @return kickEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the kickEvent stream element could not be generated
     */
    public static KickEventStreamElement generateKickEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, Geometry.Vector posBall, int numPlayersNearerToGoal, boolean attacked, String zone) throws CannotGenerateDataStreamElement {
        KickEventStreamElementPayloadProtos.KickEventStreamElementPayload payload = KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.newBuilder()
                .setNumPlayersNearerToGoal(numPlayersNearerToGoal)
                .setAttacked(attacked)
                .setZone(zone)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(playerInfo.getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(posBall))
                .addObjectIdentifiers(playerInfo.getObjectId())
                .addGroupIdentifiers(playerInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new KickEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who kicked the ball.
     *
     * @return Identifier of the player who kicked the ball
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player kicked the ball.
     *
     * @return Identifier of the team whose player kicked the ball
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player when he/she kicked the ball.
     *
     * @return Position of the player when he/she kicked the ball
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public Geometry.Vector getPlayerPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position of the ball when the kick was detected.
     *
     * @return Position of the ball when the kick was detected
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public Geometry.Vector getBallPosition() throws CannotRetrieveInformationException {
        return this.getPosition(1);
    }

    /**
     * Gets the number of players of the opposing team which are nearer to the goal when the kick was detected.
     *
     * @return Number of players of the opposing team which are nearer to the goal when the kick was detected
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public int getNumPlayersNearerToGoal() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class).getNumPlayersNearerToGoal();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumPlayersNearerToGoal on kickEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets if the player was attacked when the kick was detected.
     *
     * @return True if the player is attacked, false if he/she is not attacked
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public boolean isAttacked() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class).getAttacked();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAttacked on kickEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the zone in which the ball was kicked (outside, left, center, right).
     *
     * @return Zone in which the ball was kicked (outside, left, center, right)
     * @throws CannotRetrieveInformationException Thrown if the kickEvent stream element is ill-formed
     */
    public String getZone() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class).getZone();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getZone on kickEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(KickEventStreamElementPayloadProtos.KickEventStreamElementPayload.class);
    }
}
