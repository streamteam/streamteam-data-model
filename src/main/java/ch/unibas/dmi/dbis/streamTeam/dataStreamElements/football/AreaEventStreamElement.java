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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.AreaEventStreamElementPayloadProtos;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.ObjectInfo;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that represents and ships all information about an area event.
 * A areaEvent stream element is an atomic event stream element.
 */
public class AreaEventStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the areaEvent stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "areaEvent";

    /**
     * AreaEventStreamElement constructor.
     *
     * @param key                 The key of the areaEvent stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the areaEvent stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the areaEvent stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the areaEvent stream element
     * @param content             The content of the areaEvent stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the areaEvent stream element could not be generated
     */
    public AreaEventStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.EVENT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a areaEvent stream element.
     *
     * @param matchId             The identifier of the match in which the area event took place
     * @param generationTimestamp The generation timestamp of the area event
     * @param objectInfo          Information about the object (player or ball) who entered or left the area (incl. position information)
     * @param inArea              Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
     * @param areaId              Identifier of the area
     * @return areaEvent stream element
     * @throws CannotGenerateDataStreamElement Thrown if the areaEvent stream element could not be generated
     */
    public static AreaEventStreamElement generateAreaEventStreamElement(String matchId, long generationTimestamp, ObjectInfo objectInfo, boolean inArea, String areaId) throws CannotGenerateDataStreamElement {
        AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload payload = AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.newBuilder()
                .setInArea(inArea)
                .setAreaId(areaId)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .addPositions(generateAbstractImmutableDataStreamElementPosition(objectInfo.getPosition()))
                .addObjectIdentifiers(objectInfo.getObjectId())
                .addGroupIdentifiers(objectInfo.getGroupId())
                .setPayload(Any.pack(payload))
                .build();

        return new AreaEventStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the identifier of the object (player or ball) who entered or left the area.
     *
     * @return Identifier of the object (player or ball) who entered or left the area
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public String getObjectId() throws CannotRetrieveInformationException {
        return this.getObjectIdentifier(0);
    }

    /**
     * Gets the identifier of the team whose object (player or ball) entered or left the area.
     *
     * @return Identifier of the team whose object (player or ball) entered or left the area
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public String getTeamId() throws CannotRetrieveInformationException {
        return this.getGroupIdentifier(0);
    }

    /**
     * Gets the position where the object (player or ball) entered or left the area.
     *
     * @return Position where the object (player or ball) entered or left the area
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public Geometry.Vector getPosition() throws CannotRetrieveInformationException {
        return this.getPosition(0);
    }

    /**
     * Gets the identifier of the area.
     *
     * @return Identifier of the area
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public String getAreaId() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.class).getAreaId();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getArea on areaEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets if the areaEvent stream element represents an area entry event.
     *
     * @return True if the areaEvent stream element is an area entry event, false if areaEvent stream element is an area leave event
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public boolean isAreaEntryEvent() throws CannotRetrieveInformationException {
        return this.getInArea();
    }

    /**
     * Gets if the areaEvent stream element represents an area leave event.
     *
     * @return True if the areaEvent stream element is an area leave event, false if areaEvent stream element is an area entry event
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    public boolean isAreaLeaveEvent() throws CannotRetrieveInformationException {
        return !this.getInArea();
    }

    /**
     * Gets the flag which indicates if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event).
     *
     * @return Flag indicating if the object entered the area (and thus the areaEvent stream elements represents an area entry event) or left the area (and thus the areaEvent stream element represents an area leave event)
     * @throws CannotRetrieveInformationException Thrown if the areaEvent stream element is ill-formed
     */
    private boolean getInArea() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.class).getInArea();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getInArea on areaEvent stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(AreaEventStreamElementPayloadProtos.AreaEventStreamElementPayload.class);
    }
}
