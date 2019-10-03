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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.DoublePassEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import org.javatuples.Pair;

import java.util.Deque;

/**
 * Immutable data stream element that represents and ships all information about a double pass event.
 * A doublePassEvent stream element is an atomic event stream element.
 */
public class DoublePassEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the doublePassEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "doublePassEvent";

    /**
     * DoublePassEventStreamElement constructor.
     *
     * @param key                 The key of the doublePassEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the doublePassEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the doublePassEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the doublePassEvent stream element
     * @param content             The content of the doublePassEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the doublePassEvent stream element could not be generated
     */
    public DoublePassEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a doublePassEvent stream element.
     *
     * @param matchId             The identifier of the match in which the double pass took place
     * @param generationTimestamp The generation timestamp of the double pass
     * @param teamId              Team identifier
     * @param passSequence        Pass sequence (deque of <kickPlayerInfo,receivePlayerInfo> pairs) with 2 passes
     * @return doublePassEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the doublePassEvent stream element could not be generated
     */
    public static DoublePassEventStreamElement generateDoublePassEventStreamElement(String matchId, long generationTimestamp, String teamId, Deque<Pair<ObjectInfo, ObjectInfo>> passSequence) throws CannotGenerateDataStreamElement {
        if (passSequence.size() != 2) {
            throw new CannotGenerateDataStreamElement("Cannot generate a doublePassEvent since the passSequence comprises " + passSequence.size() + " instead of 2 passes.");
        }

        DoublePassEventStreamElementPayloadProtos.DoublePassEventStreamElementPayload payload = DoublePassEventStreamElementPayloadProtos.DoublePassEventStreamElementPayload.newBuilder()
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(passSequence.getFirst().getValue0().getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(passSequence.getFirst().getValue1().getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(passSequence.getLast().getValue0().getPosition()))
                .addPositions(generateAbstractImmutableDataStreamElementPosition(passSequence.getLast().getValue1().getPosition()))
                .addObjectIdentifiers(passSequence.getFirst().getValue0().getObjectId())
                .addObjectIdentifiers(passSequence.getFirst().getValue1().getObjectId())
                .addGroupIdentifiers(teamId)
                .setPayload(Any.pack(payload))
                .build();

        return new DoublePassEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who kicked the first and received the second pass.
     *
     * @return Identifier of the player who kicked the first and received the second pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public String getFirstPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the player who received the first and kicked the second pass.
     *
     * @return Identifier of the player who received the first and kicked the second pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public String getSecondPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(1);
    }

    /**
     * Gets the identifier of the team whose players performed the double pass.
     *
     * @return Identifier of the team whose players performed the double pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position where the ball has been kicked in the first pass.
     *
     * @return Position where the ball has been kicked in the first pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public Geometry.Vector getFirstKickPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the position where the ball has been kicked in the second pass.
     *
     * @return Position where the ball has been kicked in the second pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public Geometry.Vector getSecondKickPosition() throws CannotRetrieveInformationException {
        return this.getPosition(2);
    }

    /**
     * Gets the position where the ball has been received in the first pass.
     *
     * @return Position where the ball has been received in the first pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public Geometry.Vector getFirstReceivePosition() throws CannotRetrieveInformationException {
        return this.getPosition(1);
    }

    /**
     * Gets the position where the ball has been received in the second pass.
     *
     * @return Position where the ball has been received in the second pass
     * @throws CannotRetrieveInformationException Thrown if the doublePassEvent stream element is ill-formed
     */
    public Geometry.Vector getSecondReceivePosition() throws CannotRetrieveInformationException {
        return this.getPosition(3);
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(DoublePassEventStreamElementPayloadProtos.DoublePassEventStreamElementPayload.class);
    }
}
