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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PassStatisticsStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.StatisticsItemInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains pass statistics.
 * A passStatistics stream element is a statistics stream element.
 */
public class PassStatisticsStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the passStatistics stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "passStatistics";

    /**
     * PassStatisticsStreamElement constructor.
     *
     * @param key                 The key of the passStatistics stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the passStatistics stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the passStatistics stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the passStatistics stream element
     * @param content             The content of the passStatistics stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the passStatistics stream element could not be generated
     */
    public PassStatisticsStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATISTICS, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a passStatistics stream element.
     *
     * @param matchId               The identifier of the match to which the pass statistics data belong
     * @param generationTimestamp   The generation timestamp of the pass statistics
     * @param statisticsItemInfo    Statistics item info
     * @param numSuccessfulPasses   Number of successful passes
     * @param numInterceptions      Number of interceptions
     * @param numMisplacedPasses    Number of misplaced passes
     * @param numClearances         Number of clearances
     * @param passSuccessRate       Pass success rate
     * @param forwardDirectionRate  Forward direction rate
     * @param backwardDirectionRate Backward direction rate
     * @param leftDirectionRate     Left direction rate
     * @param rightDirectionRate    Right direction rate
     * @param avgPacking            Average packing value
     * @return passStatistics stream element
     * @throws CannotGenerateDataStreamElement Thrown if the passStatistics stream element could not be generated
     */
    public static PassStatisticsStreamElement generatePassStatisticsStreamElement(String matchId, long generationTimestamp, StatisticsItemInfo statisticsItemInfo, long numSuccessfulPasses, long numInterceptions, long numMisplacedPasses, long numClearances, double passSuccessRate, double forwardDirectionRate, double backwardDirectionRate, double leftDirectionRate, double rightDirectionRate, double avgPacking) throws CannotGenerateDataStreamElement {
        PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload payload = PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.newBuilder()
                .setNumSuccessfulPasses(numSuccessfulPasses)
                .setNumInterceptions(numInterceptions)
                .setNumMisplacedPasses(numMisplacedPasses)
                .setNumClearances(numClearances)
                .setPassSuccessRate(passSuccessRate)
                .setForwardDirectionRate(forwardDirectionRate)
                .setBackwardDirectionRate(backwardDirectionRate)
                .setLeftDirectionRate(leftDirectionRate)
                .setRightDirectionRate(rightDirectionRate)
                .setAvgPacking(avgPacking)
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

        return new PassStatisticsStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if the passStatistics stream element contains data for a team.
     *
     * @return True if the passStatistics stream element contains data for a team, false if not
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public boolean isTeamStatistics() throws CannotRetrieveInformationException {
        return this.getObjectIdentifiersListSize() == 0;
    }

    /**
     * Gets the identifier of the player for which the passStatistics stream element contains data and null if the passStatistics stream element contains data for a team.
     *
     * @return Identifier of the player for which the passStatistics stream element contains data and null if the passStatistics stream element contains data for a team
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team for which the passStatistics stream element contains data.
     * Attention: The passStatistics stream element can contain data for the team or for a single player of the team. This can be checked with isTeamStatistics().
     *
     * @return Identifier of the team for which the passStatistics stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the number of successful passes the statistics item has performed.
     *
     * @return Number of successful passes the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public long getNumSuccessfulPasses() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getNumSuccessfulPasses();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumSuccessfulPasses on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of interception the statistics item has performed.
     *
     * @return Number of interceptions the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public long getNumInterceptions() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getNumInterceptions();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumInterceptions on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of misplaced passes the statistics item has performed.
     *
     * @return Number of misplaced passes the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public long getNumMisplacedPasses() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getNumMisplacedPasses();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumMisplacedPasses on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the number of clearances the statistics item has performed.
     *
     * @return Number of clearances the statistics item has performed
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public long getNumClearances() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getNumClearances();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getNumClearances on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the pass success rate for the statistics item.
     *
     * @return Pass success rate for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getPassSuccessRate() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getPassSuccessRate();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPassSuccessRate on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the forward direction rate for the statistics item.
     *
     * @return Forward direction rate for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getForwardDirectionRate() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getForwardDirectionRate();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getForwardDirectionRate on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the backward direction rate for the statistics item.
     *
     * @return Backward direction rate for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getBackwardDirectionRate() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getBackwardDirectionRate();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getBackwardDirectionRate on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the left direction rate for the statistics item.
     *
     * @return Left direction rate for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getLeftDirectionRate() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getForwardDirectionRate();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getLeftDirectionRate on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the right direction rate for the statistics item.
     *
     * @return Right direction rate for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getRightDirectionRate() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getRightDirectionRate();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getRightDirectionRate on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the average packing value for the statistics item.
     *
     * @return Average packing value for the statistics item
     * @throws CannotRetrieveInformationException Thrown if the passStatistics stream element is ill-formed
     */
    public double getAvgPacking() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class).getAvgPacking();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAvgPacking on passStatistics stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(PassStatisticsStreamElementPayloadProtos.PassStatisticsStreamElementPayload.class);
    }
}
