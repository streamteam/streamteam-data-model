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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.MatchTimeProgressEventStreamElementPayloadProtos;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a match time progress event.
 * A matchTimeProgressEvent stream element is an atomic event stream element.
 */
public class MatchTimeProgressEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the matchTimeProgressEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "matchTimeProgressEvent";

    /**
     * MatchTimeProgressEventStreamElement constructor.
     *
     * @param key                 The key of the matchTimeProgressEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the matchTimeProgressEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the matchTimeProgressEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the matchTimeProgressEvent stream element
     * @param content             The content of the matchTimeProgressEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the matchTimeProgressEvent stream element could not be generated
     */
    public MatchTimeProgressEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a matchTimeProgressEvent stream element.
     *
     * @param matchId             The identifier of the match in which the match time progressed
     * @param generationTimestamp The generation timestamp of the match time progress
     * @param matchTimeInS        Match time in seconds since start of the match
     * @return matchTimeProgressEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the matchTimeProgressEvent stream element could not be generated
     */
    public static MatchTimeProgressEventStreamElement generateMatchTimeProgressEventStreamElement(String matchId, long generationTimestamp, long matchTimeInS) throws CannotGenerateDataStreamElement {
        MatchTimeProgressEventStreamElementPayloadProtos.MatchTimeProgressEventStreamElementPayload payload = MatchTimeProgressEventStreamElementPayloadProtos.MatchTimeProgressEventStreamElementPayload.newBuilder()
                .setMatchTimeInS(matchTimeInS)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload))
                .build();

        return new MatchTimeProgressEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the match time in seconds since start of the match.
     *
     * @return Match time in seconds since start of the match
     * @throws CannotRetrieveInformationException Thrown if the matchTimeProgressEvent stream element is ill-formed
     */
    public long getMatchTimeInS() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchTimeProgressEventStreamElementPayloadProtos.MatchTimeProgressEventStreamElementPayload.class).getMatchTimeInS();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getLength on successfullPassEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(MatchTimeProgressEventStreamElementPayloadProtos.MatchTimeProgressEventStreamElementPayload.class);
    }
}
