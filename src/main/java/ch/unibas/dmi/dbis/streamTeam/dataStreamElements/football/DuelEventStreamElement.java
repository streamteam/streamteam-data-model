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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.DuelEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that ships updates of a duel event.
 * A duelEvent stream element is a non-atomic event stream element.
 */
public class DuelEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the duelEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "duelEvent";

    /**
     * DuelEventStreamElement constructor.
     *
     * @param key                 The key of the duelEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the duelEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the duelEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the duelEvent stream element
     * @param content             The content of the duelEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the duelEvent stream element could not be generated
     */
    public DuelEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a duelEvent stream element.
     *
     * @param matchId                     The identifier of the match in which the duel event takes place
     * @param generationTimestamp         The generation timestamp of the duel event update
     * @param defendingPlayer             Information about the player who is defending the ball (incl. position information)
     * @param attackingPlayer             Information about the player who is attacking (incl. position information)
     * @param phase                       Phase of the duel event
     * @param eventIdentifierCounterValue Counter value for the event identifier
     * @return duelEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the duelEvent stream element could not be generated
     */
    public static DuelEventStreamElement generateDuelEventStreamElement(String matchId, long generationTimestamp, ObjectInfo defendingPlayer, ObjectInfo attackingPlayer, NonAtomicEventPhase phase, long eventIdentifierCounterValue) throws CannotGenerateDataStreamElement {
        DuelEventStreamElementPayloadProtos.DuelEventStreamElementPayload payload = DuelEventStreamElementPayloadProtos.DuelEventStreamElementPayload.newBuilder()
                .build();

        String eventIdentifierInnerKey = "all"; // There are never two simultaneous/overlapping under duel events (only the player in ball possession can be in a duel with its nearest neighbor) --> no need to have player-specific inner keys and counters

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(false)
                .setEventIdentifier(generateEventIdentifier(STREAMNAME, matchId, eventIdentifierInnerKey, eventIdentifierCounterValue))
                .setPhase(generateAbstractImmutableDataStreamElementPhaseFromNonAtomicEventPhase(phase))
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(defendingPlayer.getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(attackingPlayer.getPosition()))
                .addObjectIdentifiers(defendingPlayer.getObjectId())
                .addObjectIdentifiers(attackingPlayer.getObjectId())
                .addGroupIdentifiers(defendingPlayer.getGroupId())
                .addGroupIdentifiers(attackingPlayer.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new DuelEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who is defending the ball.
     *
     * @return Identifier of the player who is defending the ball
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public String getDefendingPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the player who is attacking.
     *
     * @return Identifier of the player who is attacking
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public String getAttackingPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(1);
    }

    /**
     * Gets the identifier of the team whose player is defending the ball.
     *
     * @return Identifier of the team whose player is defending the ball
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public String getDefendingTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player is attacking.
     *
     * @return Identifier of the team whose player is attacking
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public String getAttackingTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(1);
    }

    /**
     * Gets the position of the player who is defending the ball.
     *
     * @return Position of the player who is defending the ball
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public Geometry.Vector getDefendingPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position of the player who is attacking.
     *
     * @return Position of the player who is attacking
     * @throws CannotRetrieveInformationException Thrown if the duelEvent stream element is ill-formed
     */
    public Geometry.Vector getAttackingPosition() throws CannotRetrieveInformationException {
        return this.getPosition(1);
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(DuelEventStreamElementPayloadProtos.DuelEventStreamElementPayload.class);
    }
}
