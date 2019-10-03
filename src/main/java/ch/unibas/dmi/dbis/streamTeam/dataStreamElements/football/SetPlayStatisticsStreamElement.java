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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.SetPlayStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains set play statistics.
 * A setPlayStatistics stream element is a statistics stream element.
 */
public class SetPlayStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the setPlayStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "setPlayStatistics";

    /**
     * PassStatisticsStreamElement constructor.
     *
     * @param key                 The key of the setPlayStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the setPlayStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the setPlayStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the setPlayStatistics stream element
     * @param content             The content of the setPlayStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the setPlayStatistics stream element could not be generated
     */
    public SetPlayStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a setPlayStatistics stream element.
     *
     * @param matchId             The identifier of the match to which the set play statistics data belong
     * @param generationTimestamp The generation timestamp of the set play statistics
     * @param statisticsItemInfo  Statistics item info
     * @param numFreekicks        Number of freekicks
     * @param numCornerkicks      Number of cornerkicks
     * @param numGoalkicks        Number of goalkicks
     * @param numPenalties        Number of penalties
     * @param numThrowins         Number of throwins
     * @return setPlayStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the setPlayStatistics stream element could not be generated
     */
    public static SetPlayStatisticsStreamElement generatePassStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, long numFreekicks, long numCornerkicks, long numGoalkicks, long numPenalties, long numThrowins) throws CannotGenerateDataStreamElement {
        SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload payload = SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.newBuilder()
                .setNumFreekicks(numFreekicks)
                .setNumCornerkicks(numCornerkicks)
                .setNumGoalkicks(numGoalkicks)
                .setNumPenalties(numPenalties)
                .setNumThrowins(numThrowins)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addGroupIdentifiers(statisticsItemInfo.getGroupId())
                .setPayload(Any.pack(payload));

        if (statisticsItemInfo.getObjectId() != null) {
            contentBuilder.addObjectIdentifiers(statisticsItemInfo.getObjectId());
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new SetPlayStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the setPlayStatistics stream element contains data for a team.
     *
     * @return True if the setPlayStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() > 0;
    }

    /**
     * Gets the identifier of the player for which the setPlayStatistics stream element contains data and null if the setPlayStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the setPlayStatistics stream element contains data and null if the setPlayStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the setPlayStatistics stream element contains data.
     * Attention: The setPlayStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the setPlayStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of freekicks the statistics item has performed.
     *
     * @return Number of freekicks the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public long getNumFreekicks() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class).getNumFreekicks();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumFreekicks on setPlayStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of cornerkicks the statistics item has performed.
     *
     * @return Number of cornerkicks the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public long getNumCornerkicks() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class).getNumCornerkicks();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumCornerkicks on setPlayStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of goalkicks the statistics item has performed.
     *
     * @return Number of goalkicks the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public long getNumGoalkicks() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class).getNumGoalkicks();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumGoalkicks on setPlayStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of penalties the statistics item has performed.
     *
     * @return Number of penalties the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public long getNumPenalties() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class).getNumPenalties();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumPenalties on setPlayStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of throwins the statistics item has performed.
     *
     * @return Number of throwins the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the setPlayStatistics stream element is ill-formed
     */
    public long getNumThrowins() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class).getNumThrowins();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumThrowins on setPlayStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(SetPlayStatisticsStreamElementPayloadProtos.SetPlayStatisticsStreamElementPayload.class);
    }
}
