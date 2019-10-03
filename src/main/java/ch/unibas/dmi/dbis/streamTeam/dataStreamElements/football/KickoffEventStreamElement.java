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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.KickoffEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a kickoff event.
 * A kickoffEvent stream element is an atomic event stream element.
 */
public class KickoffEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the kickoffEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "kickoffEvent";

    /**
     * KickoffEventStreamElement constructor.
     *
     * @param key                 The key of the kickoffEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the kickoffEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the kickoffEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the kickoffEvent stream element
     * @param content             The content of the kickoffEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the kickoffEvent stream element could not be generated
     */
    public KickoffEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a kickoffEvent stream element.
     *
     * @param matchId             The identifier of the match in which the kickoff took place
     * @param generationTimestamp The generation timestamp of the kickoff
     * @param playerInfo          Information about the player who performed the kickoff (incl. position information)
     * @param posBall             Position of the ball when the kickoff was performed
     * @param leftTeamId          Identifier of the team playing on the left side of the field
     * @param rightTeamId         Identifier of the team playing on the right side of the field
     * @return kickoffEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the kickoffEvent stream element could not be generated
     */
    public static KickoffEventStreamElement generateKickoffEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, Geometry.Vector posBall, String leftTeamId, String rightTeamId) throws CannotGenerateDataStreamElement {
        KickoffEventStreamElementPayloadProtos.KickoffEventStreamElementPayload payload = KickoffEventStreamElementPayloadProtos.KickoffEventStreamElementPayload.newBuilder()
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(playerInfo.getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(posBall))
                .addObjectIdentifiers(playerInfo.getObjectId())
                .addGroupIdentifiers(playerInfo.getGroupId())
                .addGroupIdentifiers(leftTeamId)
                .addGroupIdentifiers(rightTeamId)
                .setPayload(Any.pack(payload))
                .build();

        return new KickoffEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who performed the kickoff.
     *
     * @return Identifier of the player who performed the kickoff
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player performed the kickoff.
     *
     * @return Identifier of the team whose player performed the kickoff
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player when the kickoff was performed.
     *
     * @return Position of the player when the kickoff was performed
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public Geometry.Vector getPlayerPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position of the ball when the kickoff was performed.
     *
     * @return Position of the ball when the kickoff was performed
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public Geometry.Vector getBallPosition() throws CannotRetrieveInformationException {
        return this.getPosition(1);
    }

    /**
     * Gets the identifier of the team playing on the left side of the field.
     *
     * @return Identifier of the team playing on the left side of the field
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public final String getLeftTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(1);
    }

    /**
     * Gets the identifier of the team playing on the right side of the field.
     *
     * @return Identifier of the team playing on the right side of the field
     * @throws CannotRetrieveInformationException Thrown if the kickoffEvent stream element is ill-formed
     */
    public final String getRightTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(2);
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(KickoffEventStreamElementPayloadProtos.KickoffEventStreamElementPayload.class);
    }
}
