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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.TeamAreaStateStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

import java.util.List;

/**
 * Immutable data stream element that contains information which describe the state of the areas defined by the positions of the players of a team.
 * A teamAreaState stream element is state stream element.
 */
public class TeamAreaStateStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the teamAreaState stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "teamAreaState";

    /**
     * TeamAreaStateStreamElement constructor.
     *
     * @param key                 The key of the teamAreaState stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the teamAreaState stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the teamAreaState stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the teamAreaState stream element
     * @param content             The content of the teamAreaState stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the teamAreaState stream element could not be generated
     */
    public TeamAreaStateStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATE, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a teamAreaState stream element.
     *
     * @param matchId             The identifier of the match for which the offside line is calculated
     * @param generationTimestamp The generation timestamp of the offside line
     * @param teamId              Identifier of the team
     * @param players             Information about the players of the team (incl. position information)
     * @param mbrSurface          Surface of the minimum bounding rectangle around the players of the team
     * @param pchSurface          Surface of the planar convex hull around the players of the team
     * @return teamAreaState stream element
     * @throws CannotGenerateDataStreamElement Thrown if the teamAreaState stream element could not be generated
     */
    public static TeamAreaStateStreamElement generateTeamAreaStateStreamElement(String matchId, long generationTimestamp, String teamId, List<ObjectInfo> players, double mbrSurface, double pchSurface) throws CannotGenerateDataStreamElement {
        TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload payload = TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.newBuilder()
                .setMbrSurface(mbrSurface)
                .setPchSurface(pchSurface)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addGroupIdentifiers(teamId)
                .setPayload(Any.pack(payload));

        for (ObjectInfo player : players) {
            contentBuilder.addPositions(generateAbstractImmutableDataStreamElementPosition(player.getPosition()))
                    .addObjectIdentifiers(player.getObjectId());
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new TeamAreaStateStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the team for which the team area state is calculated.
     *
     * @return Identifier of the team for which the team area state is calculated
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of players.
     *
     * @return Number of players
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public long getNumberOfPlayers() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize();
    }

    /**
     * Gets the identifier of the player with given index.
     *
     * @param playerIndex Index of the player
     * @return Identifier of the player with given index
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public String getIdOfPlayer(int playerIndex) throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(playerIndex);
    }

    /**
     * Gets the position of the player with given index.
     *
     * @param playerIndex Index of the player
     * @return Position of the player with given index
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public Geometry.Vector getPositionOfPlayer(int playerIndex) throws CannotRetrieveInformationException {
        return this.getPosition(playerIndex);
    }

    /**
     * Gets the surface of the minimum bounding rectangle around the players of the team.
     *
     * @return Surface of the minimum bounding rectangle around the players of the team
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public double getMbrSurface() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.class).getMbrSurface();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getMbrSurface on teamAreaState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the surface of the planar convex hull around the players of the team.
     *
     * @return Surface of the planar convex hull around the players of the team
     * @throws CannotRetrieveInformationException Thrown if the teamAreaState stream element is ill-formed
     */
    public double getPchSurface() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.class).getPchSurface();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPchSurface on teamAreaState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(TeamAreaStateStreamElementPayloadProtos.TeamAreaStateStreamElementPayload.class);
    }
}
