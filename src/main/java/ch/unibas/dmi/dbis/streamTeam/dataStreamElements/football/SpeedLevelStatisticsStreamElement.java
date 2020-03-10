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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.SpeedLevelStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable data stream element that contains speed level statistics.
 * A speedLevelStatistics stream element is a statistics stream element.
 */
public class SpeedLevelStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the speedLevelStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "speedLevelStatistics";

    /**
     * SpeedLevelStatisticsStreamElement constructor.
     *
     * @param key                 The key of the speedLevelStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the speedLevelStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the speedLevelStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the speedLevelStatistics stream element
     * @param content             The content of the speedLevelStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the speedLevelStatistics stream element could not be generated
     */
    public SpeedLevelStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a speedLevelStatistics stream element.
     *
     * @param matchId               The identifier of the match to which the speed level statistics data belong
     * @param generationTimestamp   The generation timestamp of the speed level statistics
     * @param statisticsItemInfo    Statistics item info
     * @param speedLevelTimesInS    List containing the total time (in s) spent in each speed level
     * @param speedLevelPercentages List containing the percentages of the overall time spent in each speed level
     * @return speedLevelStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the speedLevelStatistics stream element could not be generated
     */
    public static SpeedLevelStatisticsStreamElement generateSpeedLevelStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, List<Long> speedLevelTimesInS, List<Double> speedLevelPercentages) throws CannotGenerateDataStreamElement {
        SpeedLevelStatisticsStreamElementPayloadProtos.SpeedLevelStatisticsStreamElementPayload payload = SpeedLevelStatisticsStreamElementPayloadProtos.SpeedLevelStatisticsStreamElementPayload.newBuilder()
                .addAllTimeInS(speedLevelTimesInS)
                .addAllPercentage(speedLevelPercentages)
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

        return new SpeedLevelStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the speedLevelStatistics stream element contains data for a team.
     *
     * @return True if the speedLevelStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the speedLevelStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the speedLevelStatistics stream element contains data and null if the speedLevelStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the speedLevelStatistics stream element contains data and null if the speedLevelStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the speedLevelStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the speedLevelStatistics stream element contains data.
     * Attention: The speedLevelStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the speedLevelStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the speedLevelStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the list of total times the statistics item has spent in the different speed levels (in s).
     *
     * @return List of total times the statistics item has spent in the different speed levels (in s).
     * @throws CannotRetrieveInformationException Thrown if the speedLevelStatistics stream element is ill-formed
     */
    public List<Long> getTimeInSList() throws CannotRetrieveInformationException {
        try {
            ArrayList<Long> list = new ArrayList<>();
            for (Object timeInS : this.getPayload().unpack(SpeedLevelStatisticsStreamElementPayloadProtos.SpeedLevelStatisticsStreamElementPayload.class).getTimeInSList().toArray()) {
                list.add((Long) timeInS);
            }
            return list;
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getTimeInSList on speedLevelStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the list of percentage values how long the statistics item has spent in the different speed levels.
     *
     * @return List of percentage values how long the statistics item has spent in the different speed levels.
     * @throws CannotRetrieveInformationException Thrown if the speedLevelStatistics stream element is ill-formed
     */
    public List<Double> gePercentageList() throws CannotRetrieveInformationException {
        try {
            ArrayList<Double> list = new ArrayList<>();
            for (Object percentage : this.getPayload().unpack(SpeedLevelStatisticsStreamElementPayloadProtos.SpeedLevelStatisticsStreamElementPayload.class).getPercentageList().toArray()) {
                list.add((Double) percentage);
            }
            return list;
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPercentageList on speedLevelStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(SpeedLevelStatisticsStreamElementPayloadProtos.SpeedLevelStatisticsStreamElementPayload.class);
    }
}
