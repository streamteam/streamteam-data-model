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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.DribblingStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains dribbling statistics.
 * A dribblingStatistics stream element is a statistics stream element.
 */
public class DribblingStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the dribblingStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "dribblingStatistics";

    /**
     * DribblingStatisticsStreamElement constructor.
     *
     * @param key                 The key of the dribblingStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the dribblingStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the dribblingStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the dribblingStatistics stream element
     * @param content             The content of the dribblingStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the dribblingStatistics stream element could not be generated
     */
    public DribblingStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a dribblingStatistics stream element.
     *
     * @param matchId                 The identifier of the match to which the dribbling statistics data belong
     * @param generationTimestamp     The generation timestamp of the dribbling statistics
     * @param statisticsItemInfo      Statistics item info
     * @param numDribblings           Number of dribblings
     * @param avgDribblingDurationInS Average dribbling duration (in s)
     * @param avgDribblingLength      Average dribbling length (in m)
     * @return dribblingStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the dribblingStatistics stream element could not be generated
     */
    public static DribblingStatisticsStreamElement generateDribblingStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, long numDribblings, double avgDribblingDurationInS, double avgDribblingLength) throws CannotGenerateDataStreamElement {
        DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload payload = DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload.newBuilder()
                .setNumDribblings(numDribblings)
                .setAvgDribblingDuration(avgDribblingDurationInS)
                .setAvgDribblingLength(avgDribblingLength)
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

        return new DribblingStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the dribblingStatistics stream element contains data for a team.
     *
     * @return True if the dribblingStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() > 0;
    }

    /**
     * Gets the identifier of the player for which the dribblingStatistics stream element contains data and null if the dribblingStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the dribblingStatistics stream element contains data and null if the dribblingStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the dribblingStatistics stream element contains data.
     * Attention: The dribblingStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the dribblingStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of dribblings the statistics item has performed.
     *
     * @return Number of dribblings the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public long getNumDribblings() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload.class).getNumDribblings();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumDribblings on dribblingStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the average dribbling duration (in s) for the statistics item.
     *
     * @return Average dribbling duration (in s) for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public double getAvgDribblingDuration() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload.class).getAvgDribblingDuration();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAvgDribblingDuration on dribblingStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the average dribbling length (in m) for the statistics item.
     *
     * @return Average dribbling length (in m) for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the dribblingStatistics stream element is ill-formed
     */
    public double getAvgDribblingLength() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload.class).getAvgDribblingLength();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAvgDribblingLength on dribblingStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(DribblingStatisticsStreamElementPayloadProtos.DribblingStatisticsStreamElementPayload.class);
    }
}
