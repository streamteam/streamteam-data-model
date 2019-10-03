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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassSequenceEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.javatuples.Pair;

import java.util.Deque;

/**
 * Immutable data stream element that represents and ships all information about a pass sequence event.
 * A passSequenceEvent stream element is an atomic event stream element.
 */
public class PassSequenceEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the passSequenceEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "passSequenceEvent";

    /**
     * PassSequenceEventStreamElement constructor.
     *
     * @param key                 The key of the passSequenceEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the passSequenceEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the passSequenceEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the passSequenceEvent stream element
     * @param content             The content of the passSequenceEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the passSequenceEvent stream element could not be generated
     */
    public PassSequenceEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a passSequenceEvent stream element.
     *
     * @param matchId             The identifier of the match in which the pass sequence took place
     * @param generationTimestamp The generation timestamp of the pass sequence
     * @param teamId              Team identifier
     * @param passSequence        Pass sequence (deque of <kickPlayerInfo,receivePlayerInfo> pairs)
     * @return passSequenceEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the passSequenceEvent stream element could not be generated
     */
    public static PassSequenceEventStreamElement generatePassSequenceEventStreamElement(String matchId, long generationTimestamp, String teamId, Deque<Pair<ObjectInfo, ObjectInfo>> passSequence) throws CannotGenerateDataStreamElement {
        PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload payload = PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.newBuilder()
                .setNumPasses(passSequence.size())
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addGroupIdentifiers(teamId)
                .setPayload(Any.pack(payload));

        contentBuilder.addObjectIdentifiers(passSequence.getFirst().getValue0().getObjectId());
        for (Pair<ObjectInfo, ObjectInfo> pass : passSequence) {
            contentBuilder.addObjectIdentifiers(pass.getValue1().getObjectId())
                    .addPositions(generateAbstractImmutableDataStreamElementPosition(pass.getValue0().getPosition()))
                    .addPositions(generateAbstractImmutableDataStreamElementPosition(pass.getValue1().getPosition()));
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new PassSequenceEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who kicked the pass with given index in the pass sequence.
     *
     * @param passIndex Index of the pass in the pass sequence
     * @return Identifier of the player who kicked the pass with given index in the pass sequence
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public String getKickPlayerId(int passIndex) throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(passIndex);
    }

    /**
     * Gets the identifier of the player who received the pass with given index in the pass sequence.
     *
     * @param passIndex Index of the pass in the pass sequence
     * @return Identifier of the player who received the pass with given index in the pass sequence
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public String getReceivePlayerId(int passIndex) throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(passIndex + 1);
    }

    /**
     * Gets the identifier of the team whose players performed the pass sequence.
     *
     * @return Identifier of the team whose players performed the pass sequence
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position where the ball has been kicked in the pass with given index in the pass sequence.
     *
     * @param passIndex Index of the pass in the pass sequence
     * @return Position where the ball has been kicked in the pass with given index in the pass sequence
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public Geometry.Vector getKickPosition(int passIndex) throws CannotRetrieveInformationException {
        return this.getPosition(passIndex * 2);
    }

    /**
     * Gets the position where the ball has been received in the pass with given index in the pass sequence.
     *
     * @param passIndex Index of the pass in the pass sequence
     * @return Position where the ball has been received in the pass with given index in the pass sequence
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public Geometry.Vector getReceivePosition(int passIndex) throws CannotRetrieveInformationException {
        return this.getPosition((passIndex * 2) + 1);
    }

    /**
     * Gets the number of passes.
     *
     * @return Number of passes
     * @throws CannotRetrieveInformationException Thrown if the passSequenceEvent stream element is ill-formed
     */
    public long getNumPasses() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.class).getNumPasses();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumPasses on passSequenceEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(PassSequenceEventStreamElementPayloadProtos.PassSequenceEventStreamElementPayload.class);
    }
}
