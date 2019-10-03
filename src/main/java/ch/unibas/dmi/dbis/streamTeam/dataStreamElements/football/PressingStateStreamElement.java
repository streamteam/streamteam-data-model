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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.PressingStateStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains the current pressing state.
 * A pressingState stream element is a state stream element.
 */
public class PressingStateStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the pressingState stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "pressingState";

    /**
     * PressingStateStreamElement constructor.
     *
     * @param key                 The key of the pressingState stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the pressingState stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the pressingState stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the pressingState stream element
     * @param content             The content of the pressingState stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the pressingState stream element could not be generated
     */
    public PressingStateStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.STATE, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a pressingState stream element.
     *
     * @param matchId             The identifier of the match to which the pressing statistics data belong
     * @param generationTimestamp The generation timestamp of the pressing statistics
     * @param playerInfo          Information about the player in possession of the ball (without position information), null if no player is in possession of the ball
     * @param pressingIndex       Pressing index
     * @return pressingState stream element
     * @throws CannotGenerateDataStreamElement Thrown if the pressingState stream element could not be generated
     */
    public static PressingStateStreamElement generatePressingStateStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, double pressingIndex) throws CannotGenerateDataStreamElement {
        PressingStateStreamElementPayloadProtos.PressingStateStreamElementPayload payload = PressingStateStreamElementPayloadProtos.PressingStateStreamElementPayload.newBuilder()
                .setPressingIndex(pressingIndex)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Builder contentBuilder = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload));

        if (playerInfo != null) {
            contentBuilder.addObjectIdentifiers(playerInfo.getObjectId())
                    .addGroupIdentifiers(playerInfo.getGroupId());
        }

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = contentBuilder.build();

        return new PressingStateStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets if someone (a player) is in possession of the ball or not.
     *
     * @return True someone (a player) is in possession of the ball, otherwise false
     */
    public boolean isSomeoneInBallPossession() {
        if (this.getObjectIdentifiersListSize() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the identifier of the player for which the pressingState stream element contains data.
     *
     * @return Identifier of the player for which the pressingState stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the pressingState stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the team identifier of the player for which the pressingState stream element contains data.
     *
     * @return Team identifier of the player for which the pressingState stream element contains data
     * @throws CannotRetrieveInformationException Thrown if the pressingState stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the pressing index.
     *
     * @return Pressing index
     * @throws CannotRetrieveInformationException Thrown if the pressingState stream element is ill-formed
     */
    public double getPressingIndex() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(PressingStateStreamElementPayloadProtos.PressingStateStreamElementPayload.class).getPressingIndex();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getPressingIndex on pressingState stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(PressingStateStreamElementPayloadProtos.PressingStateStreamElementPayload.class);
    }
}
