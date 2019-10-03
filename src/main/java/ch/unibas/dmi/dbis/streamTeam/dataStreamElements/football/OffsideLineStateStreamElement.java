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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.OffsideLineStateStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

import java.util.List;

/**
 * Immutable data stream element that contains information which describe the current offside line situation.
 * A offsideLineState stream element is a state stream element.
 */
public class OffsideLineStateStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the offsideLineState stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "offsideLineState";

    /**
     * OffsideLineStateStreamElement constructor.
     *
     * @param key                 The key of the offsideLineState stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the offsideLineState stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the offsideLineState stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the offsideLineState stream element
     * @param content             The content of the offsideLineState stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the offsideLineState stream element could not be generated
     */
    public OffsideLineStateStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATE, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a offsideLineState stream element.
     *
     * @param matchId                  The identifier of the match for which the offside line is calculated
     * @param generationTimestamp      The generation timestamp of the offside line
     * @param playerInBallPossession   Information about the player in possession of the ball (incl. position information), null if no player is in possession of the ball
     * @param playersInOffsidePosition Information about the players in offside position (incl. position information), null if no player is in offside position
     * @param offsideLineX             X coordinate of the offside line
     * @return offsideLineState stream element
     * @throws CannotGenerateDataStreamElement Thrown if the offsideLineState stream element could not be generated
     */
    public static OffsideLineStateStreamElement generateOffsideLineStateStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInBallPossession, List<ObjectInfo> playersInOffsidePosition, double offsideLineX) throws CannotGenerateDataStreamElement {
        OffsideLineStateStreamElementPayloadProtos.OffsideLineStateStreamElementPayload payload = OffsideLineStateStreamElementPayloadProtos.OffsideLineStateStreamElementPayload.newBuilder()
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload));

        if (playerInBallPossession != null) {
            contentBuilder.addPositions(generateAbstractImmutableDataStreamElementPosition(playerInBallPossession.getPosition()))
                    .addObjectIdentifiers(playerInBallPossession.getObjectId())
                    .addGroupIdentifiers(playerInBallPossession.getGroupId());
        }

        contentBuilder.addPositions(generateAbstractImmutableDataStreamElementPosition(new Geometry.Vector(offsideLineX, 0.0d, 0.0d)));

        if (playersInOffsidePosition != null) {
            for (ObjectInfo playerInOffsidePosition : playersInOffsidePosition) {
                contentBuilder.addObjectIdentifiers(playerInOffsidePosition.getObjectId())
                        .addPositions(generateAbstractImmutableDataStreamElementPosition(playerInOffsidePosition.getPosition()));
            }
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new OffsideLineStateStreamElement(matchId, null, null, null, content);
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
     * Gets the identifier of the player who is in possession of the ball.
     *
     * @return Identifier of the player who is in possession of the ball
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public String getIdOfPlayerInBallPossession() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the offside line is calculated (i.e., the team which in possession of the ball).
     *
     * @return Identifier of the team for which the offside line is calculated (i.e., the team which in possession of the ball).
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player who is in possession of the ball.
     *
     * @return Position of the player who is in possession of the ball
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public Geometry.Vector getPositionOfPlayerInBallPossession() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets x coordinate of the offside line.
     *
     * @return X coordinate of the offside line
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public double getOffsideLineX() throws CannotRetrieveInformationException {
        return this.getPosition(1).x;
    }

    /**
     * Gets the number of players in offside position.
     *
     * @return Number of players in offside position.
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public long getNumberOfPlayersInOffsidePosition() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() - 1;
    }

    /**
     * Gets the identifier of the player in offside position with given index in the offside player list.
     *
     * @param offsidePlayerIndex Index of the player in the offside player list.
     * @return Identifier of the player in offside position with given index in the offside player list.
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public String getIdOfPlayerInOffsidePosition(int offsidePlayerIndex) throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(offsidePlayerIndex + 1);
    }

    /**
     * Gets the position of the player in offside position with given index in the offside player list.
     *
     * @param offsidePlayerIndex Index of the player in the offside player list.
     * @return Position of the player in offside position with given index in the offside player list.
     * @throws CannotRetrieveInformationException Thrown if the offsideLineState stream element is ill-formed
     */
    public Geometry.Vector getPositionOfPlayerInOffsidePosition(int offsidePlayerIndex) throws CannotRetrieveInformationException {
        return this.getPosition(offsidePlayerIndex + 2);
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(OffsideLineStateStreamElementPayloadProtos.OffsideLineStateStreamElementPayload.class);
    }
}
