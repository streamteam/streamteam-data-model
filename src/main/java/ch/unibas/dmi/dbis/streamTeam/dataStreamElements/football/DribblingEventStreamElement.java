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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.DribblingEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that ships updates of a dribbling event.
 * A dribblingEvent stream element is a non-atomic event stream element.
 */
public class DribblingEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the dribblingEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "dribblingEvent";

    /**
     * DribblingEventStreamElement constructor.
     *
     * @param key                 The key of the dribblingEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the dribblingEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the dribblingEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the dribblingEvent stream element
     * @param content             The content of the dribblingEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the dribblingEvent stream element could not be generated
     */
    public DribblingEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a dribblingEvent stream element.
     *
     * @param matchId                     The identifier of the match in which the dribbling event takes place
     * @param generationTimestamp         The generation timestamp of the dribbling event update
     * @param playerInfo                  Information about the player who came into ball position (incl. position information)
     * @param duration                    Duration of the dribbling event
     * @param length                      Length of the dribbling event
     * @param velocity                    Velocity of the dribbling event
     * @param phase                       Phase of the dribbling event
     * @param eventIdentifierCounterValue Counter value for the event identifier
     * @return dribblingEvent stream element
     * * @throws CannotGenerateDataStreamElement Thrown if the dribblingEvent stream element could not be generated
     */
    public static DribblingEventStreamElement generateDribblingEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, long duration, double length, double velocity, NonAtomicEventPhase phase, long eventIdentifierCounterValue) throws CannotGenerateDataStreamElement {
        DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload payload = DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload.newBuilder()
                .setDuration(duration)
                .setLength(length)
                .setVelocity(velocity)
                .build();

        String eventIdentifierInnerKey = "all"; // There are never two simultaneous/overlapping under dribbling events (only the player in ball possession can dribble) --> no need to have player-specific inner keys and counters

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

        return new DribblingEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player who is dribbling.
     *
     * @return Identifier of the player who is dribbling
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the team identifier of the player who is dribbling.
     *
     * @return Team identifier of the player who is dribbling
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player who is dribbling.
     *
     * @return Position of the player who is dribbling
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public Geometry.Vector getPlayerPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the duration of the dribbling event in s.
     *
     * @return Duration of the dribbling event in s.
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public double getDuration() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload.class).getDuration();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getDuration on dribblingEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the length of the dribbling event in m.
     *
     * @return Length of the dribbling event in m.
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public double getLength() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload.class).getLength();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getLength on dribblingEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the velocity of the dribbling event in m/s.
     *
     * @return velocity of the dribbling event in m/s.
     * @throws CannotRetrieveInformationException Thrown if the dribblingEvent stream element is ill-formed
     */
    public double getVelocity() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload.class).getVelocity();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVelocity on dribblingEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(DribblingEventStreamElementPayloadProtos.DribblingEventStreamElementPayload.class);
    }
}
