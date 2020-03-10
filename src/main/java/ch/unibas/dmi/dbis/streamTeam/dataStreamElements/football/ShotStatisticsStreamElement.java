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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.ShotStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains shot statistics.
 * A shotStatistics stream element is a statistics stream element.
 */
public class ShotStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the shotStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "shotStatistics";

    /**
     * PassStatisticsStreamElement constructor.
     *
     * @param key                 The key of the shotStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the shotStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the shotStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the shotStatistics stream element
     * @param content             The content of the shotStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the shotStatistics stream element could not be generated
     */
    public ShotStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a shotStatistics stream element.
     *
     * @param matchId             The identifier of the match to which the shot statistics data belong
     * @param generationTimestamp The generation timestamp of the shot statistics
     * @param statisticsItemInfo  Statistics item info
     * @param numGoals            Number of goals
     * @param numShotsOnTarget    Number of shots on target
     * @param numShotsOffTarget   Number of shots off target
     * @return shotStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the shotStatistics stream element could not be generated
     */
    public static ShotStatisticsStreamElement generateShotStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, long numGoals, long numShotsOnTarget, long numShotsOffTarget) throws CannotGenerateDataStreamElement {
        ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload payload = ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload.newBuilder()
                .setNumGoals(numGoals)
                .setNumShotsOnTarget(numShotsOnTarget)
                .setNumShotsOffTarget(numShotsOffTarget)
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

        return new ShotStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the shotStatistics stream element contains data for a team.
     *
     * @return True if the shotStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the shotStatistics stream element contains data and null if the shotStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the shotStatistics stream element contains data and null if the shotStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the shotStatistics stream element contains data.
     * Attention: The shotStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the shotStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of goals the statistics item has performed.
     *
     * @return Number of goals the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public long getNumGoals() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload.class).getNumGoals();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumGoals on shotStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of shots on target the statistics item has performed.
     *
     * @return Number of shots on target the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public long getNumShotsOnTarget() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload.class).getNumShotsOnTarget();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumShotsOnTarget on shotStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of shots off target the statistics item has performed.
     *
     * @return Number of shots off target the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the shotStatistics stream element is ill-formed
     */
    public long getNumShotsOffTarget() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload.class).getNumShotsOffTarget();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumShotsOffTarget on shotStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(ShotStatisticsStreamElementPayloadProtos.ShotStatisticsStreamElementPayload.class);
    }
}
