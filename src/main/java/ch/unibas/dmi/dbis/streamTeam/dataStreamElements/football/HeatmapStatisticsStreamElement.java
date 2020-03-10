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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.HeatmapStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains a heatmap.
 * A heatmapStatistics stream element is a statistics stream element.
 */
public class HeatmapStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the heatmapStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "heatmapStatistics";

    /**
     * HeatmapStatisticsStreamElement constructor.
     *
     * @param key                 The key of the heatmapStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the heatmapStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the heatmapStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the heatmapStatistics stream element
     * @param content             The content of the heatmapStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the heatmapStatistics stream element could not be generated
     */
    public HeatmapStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a heatmapStatistics stream element.
     *
     * @param matchId             The identifier of the match to which the heatmap statistics data belong
     * @param generationTimestamp The generation timestamp of the heatmap statistics
     * @param statisticsItemInfo  Statistics item info
     * @param numXGridCells       Number of grid cells in x direction
     * @param numYGridCells       Number of grid cells in y direction
     * @param intervalInS         Interval in s
     * @param totalNum            Total number of cell entries
     * @param cells               String specifying the cells of the heatmap
     * @return heatmapStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the heatmapStatistics stream element could not be generated
     */
    public static HeatmapStatisticsStreamElement generateHeatmapStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, int numXGridCells, int numYGridCells, long intervalInS, long totalNum, String cells) throws CannotGenerateDataStreamElement {
        HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload payload = HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.newBuilder()
                .setNumXGridCells(numXGridCells)
                .setNumYGridCells(numYGridCells)
                .setIntervalInS(intervalInS)
                .setTotalNum(totalNum)
                .setCells(cells)
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

        return new HeatmapStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the heatmapStatistics stream element contains data for a team.
     *
     * @return True if the heatmapStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the heatmapStatistics stream element contains data and null if the heatmapStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the heatmapStatistics stream element contains data and null if the heatmapStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the heatmapStatistics stream element contains data.
     * Attention: The heatmapStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the heatmapStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of grid cells in x direction.
     *
     * @return Number of grid cells in x direction
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public long getNumXGridCells() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class).getNumXGridCells();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumXGridCells on heatmapStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of grid cells in y direction.
     *
     * @return Number of grid cells in y direction
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public long getNumYGridCells() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class).getNumYGridCells();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumYGridCells on heatmapStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the interval in s for which the heatmap is calculated.
     *
     * @return Interval in s for which the heatmap is calculated
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public long getIntervalInS() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class).getIntervalInS();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getIntervalInS on heatmapStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the total number of cell entries.
     *
     * @return Total number of cell entries
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public long getTotalNum() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class).getTotalNum();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getTotalNum on heatmapStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the String that specifies the cells of the heatmap.
     *
     * @return String that specifies the cells of the heatmap
     * @throws CannotRetrieveInformationException Thrown if the heatmapStatistics stream element is ill-formed
     */
    public String getCells() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class).getCells();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getCells on heatmapStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(HeatmapStatisticsStreamElementPayloadProtos.HeatmapStatisticsStreamElementPayload.class);
    }
}
