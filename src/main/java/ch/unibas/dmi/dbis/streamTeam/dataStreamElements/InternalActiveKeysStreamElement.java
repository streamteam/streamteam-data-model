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

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements;

import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.ImmutableDataStreamElementContentProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.InternalActiveKeysStreamElementPayloadProtos;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element which is used to signal which keys are active and should thus be regarded in the next window() call.
 * ATTENTION: ELEMENTS OF THIS DATA STREAM SHOULD ONLY BE USED IN THE INNER MODULE GRAPH OF A WORKER!!!
 */
public class InternalActiveKeysStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the internalActiveKeys stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "internalActiveKeys";

    /**
     * InternalActiveKeysStreamElement constructor.
     *
     * @param key                 The key of the internalActiveKeys stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the internalActiveKeys stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the internalActiveKeys stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the internalActiveKeys stream element
     * @param content             The content of the internalActiveKeys stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the internalActiveKeys stream element could not be generated
     */
    public InternalActiveKeysStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.INTERNAL, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }


    /**
     * Generates a internalActiveKeys stream element.
     *
     * @param key                 Key
     * @param generationTimestamp The generation timestamp of the pass statistics
     * @return internalActiveKeys stream element
     * @throws CannotGenerateDataStreamElement Thrown if the internalActiveKeys stream element could not be generated
     */
    public static InternalActiveKeysStreamElement generateInternalActiveKeysStreamElement(String key, long generationTimestamp) throws CannotGenerateDataStreamElement {
        InternalActiveKeysStreamElementPayloadProtos.InternalActiveKeysStreamElementPayload payload = InternalActiveKeysStreamElementPayloadProtos.InternalActiveKeysStreamElementPayload.newBuilder()
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload))
                .build();

        return new InternalActiveKeysStreamElement(key, null, null, null, content);
    }

    /**
     * Returns unpacked payload from the content as a casted Protobuf message.
     *
     * @return Unpacked payload from the content as a casted Protobuf message
     * @throws InvalidProtocolBufferException Thrown if thrown by unpack()
     */
    @Override
    protected Message getUnpackedPayload() throws InvalidProtocolBufferException {
        return this.getPayload().unpack(InternalActiveKeysStreamElementPayloadProtos.InternalActiveKeysStreamElementPayload.class);
    }
}
