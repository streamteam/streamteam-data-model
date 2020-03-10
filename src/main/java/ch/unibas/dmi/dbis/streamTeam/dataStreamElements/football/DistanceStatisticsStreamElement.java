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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.DistanceStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains distance statistics.
 * A distanceStatistics stream element is a statistics stream element.
 */
public class DistanceStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the distanceStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "distanceStatistics";

    /**
     * DistanceStatisticsStreamElement constructor.
     *
     * @param key                 The key of the distanceStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the distanceStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the distanceStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the distanceStatistics stream element
     * @param content             The content of the distanceStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the distanceStatistics stream element could not be generated
     */
    public DistanceStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a distanceStatistics stream element.
     *
     * @param matchId             The identifier of the match to which the distance statistics data belong
     * @param generationTimestamp The generation timestamp of the distance statistics
     * @param statisticsItemInfo  Statistics item info
     * @param distance            Total distance the statistics item has moved in m
     * @return distanceStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the distanceStatistics stream element could not be generated
     */
    public static DistanceStatisticsStreamElement generateDistanceStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, double distance) throws CannotGenerateDataStreamElement {
        DistanceStatisticsStreamElementPayloadProtos.DistanceStatisticsStreamElementPayload payload = DistanceStatisticsStreamElementPayloadProtos.DistanceStatisticsStreamElementPayload.newBuilder()
                .setDistance(distance)
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

        return new DistanceStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the distanceStatistics stream element contains data for a team.
     *
     * @return True if the distanceStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the distanceStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the distanceStatistics stream element contains data and null if the distanceStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the distanceStatistics stream element contains data and null if the distanceStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the distanceStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the distanceStatistics stream element contains data.
     * Attention: The distanceStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the distanceStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the distanceStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the total distance the statistics item has moved in m.
     *
     * @return Total distance the statistics item has moved in m
     * @throws CannotRetrieveInformationException Thrown if the distanceStatistics stream element is ill-formed
     */
    public double getDistance() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(DistanceStatisticsStreamElementPayloadProtos.DistanceStatisticsStreamElementPayload.class).getDistance();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getDistance on distanceStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(DistanceStatisticsStreamElementPayloadProtos.DistanceStatisticsStreamElementPayload.class);
    }
}
