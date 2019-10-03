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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallPossessionChangeEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a ball possession change event.
 * A ballPossessionChangeEvent stream element is an atomic event stream element.
 */
public class BallPossessionChangeEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the ballPossessionChangeEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "ballPossessionChangeEvent";

    /**
     * BallPossessionChangeEventStreamElement constructor.
     *
     * @param key                 The key of the ballPossessionChangeEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the ballPossessionChangeEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the ballPossessionChangeEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the ballPossessionChangeEvent stream element
     * @param content             The content of the ballPossessionChangeEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the ballPossessionChangeEvent stream element could not be generated
     */
    public BallPossessionChangeEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a ballPossessionChangeEvent stream element.
     *
     * @param matchId                The identifier of the match in which the ball possession change took place
     * @param generationTimestamp    The generation timestamp of the ball possession change
     * @param playerInfo             Information about the player who came into ball position (incl. position information), null if no player is in possession of the ball
     * @param posBall                Position of the ball when the player came into ball position
     * @param numPlayersNearerToGoal Number of players of the opposing team which are nearer to the goal
     * @return ballPossessionChangeEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the ballPossessionChangeEvent stream element could not be generated
     */
    public static BallPossessionChangeEventStreamElement generateBallPossessionChangeEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, Geometry.Vector posBall, int numPlayersNearerToGoal) throws CannotGenerateDataStreamElement {
        BallPossessionChangeEventStreamElementPayloadProtos.BallPossessionChangeEventStreamElementPayload payload = BallPossessionChangeEventStreamElementPayloadProtos.BallPossessionChangeEventStreamElementPayload.newBuilder()
                .setNumPlayersNearerToGoal(numPlayersNearerToGoal)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload));

        if (playerInfo != null) {
            contentBuilder.addPositions(generateAbstractImmutableDataStreamElementPosition(playerInfo.getPosition()))
                    .addObjectIdentifiers(playerInfo.getObjectId())
                    .addGroupIdentifiers(playerInfo.getGroupId());
        }

        contentBuilder.addPositions(generateAbstractImmutableDataStreamElementPosition(posBall));

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new BallPossessionChangeEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if someone (a player) is in possession of the ball or not.
     *
     * @return True someone (a player) is in possession of the ball, otherwise false
     */
    public boolean isSomeoneInBallPossession() {
        if (this.getObjectIdentifiersListSize() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the identifier of the player who came into ball possession.
     *
     * @return Identifier of the player who came into ball possession
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionChangeEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player came into ball possession.
     *
     * @return Identifier of the team whose player came into ball possession
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionChangeEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player when he/she came into ball possession.
     *
     * @return Position of the player when he/she came into ball possession
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionChangeEvent stream element is ill-formed
     */
    public Geometry.Vector getPlayerPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position of the ball when the player came into ball possession or all players lost the ball possession
     *
     * @return Position of the ball when the player came into ball possession or all players lost the ball possession
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionChangeEvent stream element is ill-formed
     */
    public Geometry.Vector getBallPosition() throws CannotRetrieveInformationException {
        if (isSomeoneInBallPossession()) {
            return this.getPosition(1);
        } else {
            return this.getPosition(0);
        }
    }

    /**
     * Gets the number of players of the opposing team which are nearer to the goal when the player came into ball possession
     *
     * @return Number of players of the opposing team which are nearer to the goal when the player came into ball possession
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionChangeEvent stream element is ill-formed
     */
    public int getNumPlayersNearerToGoal() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(BallPossessionChangeEventStreamElementPayloadProtos.BallPossessionChangeEventStreamElementPayload.class).getNumPlayersNearerToGoal();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumPlayersNearerToGoal on ballPossessionChangeEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(BallPossessionChangeEventStreamElementPayloadProtos.BallPossessionChangeEventStreamElementPayload.class);
    }
}
