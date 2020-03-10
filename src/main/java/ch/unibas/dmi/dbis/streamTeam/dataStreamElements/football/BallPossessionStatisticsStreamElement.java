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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.BallPossessionStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains ball possession statistics.
 * A ballPossessionStatistics stream element is a statistics stream element.
 */
public class BallPossessionStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the ballPossessionStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "ballPossessionStatistics";

    /**
     * BallPossessionStatisticsStreamElement constructor.
     *
     * @param key                 The key of the ballPossessionStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the ballPossessionStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the ballPossessionStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the ballPossessionStatistics stream element
     * @param content             The content of the ballPossessionStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the ballPossessionStatistics stream element could not be generated
     */
    public BallPossessionStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a ballPossessionStatistics stream element.
     *
     * @param matchId             The identifier of the match to which the ball possession statistics data belong
     * @param generationTimestamp The generation timestamp of the ball possession statistics
     * @param statisticsItemInfo  Statistics item info
     * @param timeInS             Time the statistics item has been in possession of the ball in s
     * @param percentage          Time the statistics item has been in possession of the ball as a percentage value
     * @return ballPossessionStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the ballPossessionStatistics stream element could not be generated
     */
    public static BallPossessionStatisticsStreamElement generateBallPossessionStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, long timeInS, double percentage) throws CannotGenerateDataStreamElement {
        BallPossessionStatisticsStreamElementPayloadProtos.BallPossessionStatisticsStreamElementPayload payload = BallPossessionStatisticsStreamElementPayloadProtos.BallPossessionStatisticsStreamElementPayload.newBuilder()
                .setTimeInS(timeInS)
                .setPercentage(percentage)
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

        return new BallPossessionStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the ballPossessionStatistics stream element contains data for a team.
     *
     * @return True if the ballPossessionStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the ballPossessionStatistics stream element contains data and null if the ballPossessionStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the ballPossessionStatistics stream element contains data and null if the ballPossessionStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the ballPossessionStatistics stream element contains data.
     * Attention: The ballPossessionStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the ballPossessionStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the time the statistics item has been in possession of the ball in s.
     *
     * @return Time the statistics item has been in possession of the ball in s.
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionStatistics stream element is ill-formed
     */
    public long getTimeInS() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(BallPossessionStatisticsStreamElementPayloadProtos.BallPossessionStatisticsStreamElementPayload.class).getTimeInS();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getTimeInS on ballPossessionStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the time the statistics item has been in possession of the ball as a percentage value.
     *
     * @return Time the statistics item has been in possession of the ball as a percentage value
     * @throws CannotRetrieveInformationException Thrown if the ballPossessionStatistics stream element is ill-formed
     */
    public double getPercentage() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(BallPossessionStatisticsStreamElementPayloadProtos.BallPossessionStatisticsStreamElementPayload.class).getPercentage();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPercentage on ballPossessionStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(BallPossessionStatisticsStreamElementPayloadProtos.BallPossessionStatisticsStreamElementPayload.class);
    }
}
