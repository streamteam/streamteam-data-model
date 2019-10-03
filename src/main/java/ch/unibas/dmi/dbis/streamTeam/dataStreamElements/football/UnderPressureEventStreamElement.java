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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.UnderPressureEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that ships updates of an under pressure event.
 * A underPressureEvent stream element is a non-atomic event stream element.
 */
public class UnderPressureEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the underPressureEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "underPressureEvent";

    /**
     * UnderPressureEventStreamElement constructor.
     *
     * @param key                 The key of the underPressureEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the underPressureEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the underPressureEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the underPressureEvent stream element
     * @param content             The content of the underPressureEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the underPressureEvent stream element could not be generated
     */
    public UnderPressureEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a underPressureEvent stream element.
     *
     * @param matchId                     The identifier of the match in which the under pressure event takes place
     * @param generationTimestamp         The generation timestamp of the under pressure event update
     * @param playerInfo                  Information about the player who is under pressure (incl. position information)
     * @param pressingIndex               Pressing index
     * @param phase                       Phase of the under pressure event
     * @param eventIdentifierCounterValue Counter value for the event identifier
     * @return underPressureEvent stream element
     * * @throws CannotGenerateDataStreamElement Thrown if the underPressureEvent stream element could not be generated
     */
    public static UnderPressureEventStreamElement generateUnderPressureEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, double pressingIndex, NonAtomicEventPhase phase, long eventIdentifierCounterValue) throws CannotGenerateDataStreamElement {
        UnderPressureEventStreamElementPayloadProtos.UnderPressureEventStreamElementPayload payload = UnderPressureEventStreamElementPayloadProtos.UnderPressureEventStreamElementPayload.newBuilder()
                .setPressingIndex(pressingIndex)
                .build();

        String eventIdentifierInnerKey = "all"; // There are never two simultaneous/overlapping under pressure events (only the player in ball possession can be under pressure) --> no need to have player-specific inner keys and counters

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(false)
                .setEventIdentifier(generateEventIdentifier(STREAMNAME, matchId, eventIdentifierInnerKey, eventIdentifierCounterValue))
                .setPhase(generateAbstractImmutableDataStreamElementPhaseFromNonAtomicEventPhase(phase))
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(playerInfo.getPosition()))
                .addObjectIdentifiers(playerInfo.getObjectId())
                .addGroupIdentifiers(playerInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new UnderPressureEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who is under pressure.
     *
     * @return Identifier of the player who is under pressure
     * @throws CannotRetrieveInformationException Thrown if the underPressureEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the team identifier of the player who is under pressure.
     *
     * @return Team identifier of the player who is under pressure
     * @throws CannotRetrieveInformationException Thrown if the underPressureEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player who is under pressure.
     *
     * @return Position of the player who is under pressure
     * @throws CannotRetrieveInformationException Thrown if the underPressureEvent stream element is ill-formed
     */
    public Geometry.Vector getPlayerPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the pressing index.
     *
     * @return Pressing index.
     * @throws CannotRetrieveInformationException Thrown if the underPressureEvent stream element is ill-formed
     */
    public double getPressingIndex() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(UnderPressureEventStreamElementPayloadProtos.UnderPressureEventStreamElementPayload.class).getPressingIndex();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPressingIndex on underPressureEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(UnderPressureEventStreamElementPayloadProtos.UnderPressureEventStreamElementPayload.class);
    }
}
