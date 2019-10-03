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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.SpeedLevelChangeEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about a speed level change event.
 * A speedLevelChangeEvent stream element is an atomic event stream element.
 */
public class SpeedLevelChangeEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the speedLevelChangeEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "speedLevelChangeEvent";

    /**
     * SpeedLevelChangeEventStreamElement constructor.
     *
     * @param key                 The key of the speedLevelChangeEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the speedLevelChangeEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the speedLevelChangeEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the speedLevelChangeEvent stream element
     * @param content             The content of the speedLevelChangeEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the speedLevelChangeEvent stream element could not be generated
     */
    public SpeedLevelChangeEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a speedLevelChangeEvent stream element.
     *
     * @param matchId             The identifier of the match in which the ball possession change took place
     * @param generationTimestamp The generation timestamp of the ball possession change
     * @param playerInfo          Information about the player whose speed level changed (incl. position information)
     * @param speedLevel          Speed level
     * @return speedLevelChangeEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the speedLevelChangeEvent stream element could not be generated
     */
    public static SpeedLevelChangeEventStreamElement generateSpeedLevelChangeEventStreamElement(String matchId, long generationTimestamp, ObjectInfo playerInfo, int speedLevel) throws CannotGenerateDataStreamElement {
        SpeedLevelChangeEventStreamElementPayloadProtos.SpeedLevelChangeEventStreamElementPayload payload = SpeedLevelChangeEventStreamElementPayloadProtos.SpeedLevelChangeEventStreamElementPayload.newBuilder()
                .setSpeedLevel(speedLevel)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(playerInfo.getPosition()))
                .addObjectIdentifiers(playerInfo.getObjectId())
                .addGroupIdentifiers(playerInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new SpeedLevelChangeEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the player whose speed level changed.
     *
     * @return Identifier of the player whose speed level changed
     * @throws CannotRetrieveInformationException Thrown if the speedLevelChangeEvent stream element is ill-formed
     */
    public String getPlayerId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose player's speed level changed.
     *
     * @return Identifier of the team whose player's speed level changed
     * @throws CannotRetrieveInformationException Thrown if the speedLevelChangeEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position of the player when his/her speed level changed.
     *
     * @return Position of the player when his/her speed level changed
     * @throws CannotRetrieveInformationException Thrown if the speedLevelChangeEvent stream element is ill-formed
     */
    public Geometry.Vector getPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the speed level of the player.
     *
     * @return Speed level
     * @throws CannotRetrieveInformationException Thrown if the speedLevelChangeEvent stream element is ill-formed
     */
    public int getSpeedLevel() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(SpeedLevelChangeEventStreamElementPayloadProtos.SpeedLevelChangeEventStreamElementPayload.class).getSpeedLevel();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getSpeedLevel on speedLevelChangeEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(SpeedLevelChangeEventStreamElementPayloadProtos.SpeedLevelChangeEventStreamElementPayload.class);
    }
}
