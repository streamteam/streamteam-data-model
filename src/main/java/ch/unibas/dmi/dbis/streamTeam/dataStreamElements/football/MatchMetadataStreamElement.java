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
import ch.unibas.dmi.dbis.streamTeam.dataStreamElements.protobuf.football.MatchMetadataStreamElementPayloadProtos;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/**
 * Immutable data stream element that contains metadata for a match.
 * A matchMetadata stream element is a raw input stream element.
 */
public class MatchMetadataStreamElement extends AbstractImmutableDataStreamElement {

    /**
     * The name of the data stream in which the matchMetadata stream elements are shipped (i.e., the Kafka topic)
     */
    public static String STREAMNAME = "matchMetadata";

    /**
     * MatchMetadataStreamElement constructor.
     *
     * @param key                 The key of the matchMetadata stream element which defines the partition of the data stream to which it belongs
     * @param sequenceNumber      The globally unambiguous sequence number of the matchMetadata stream element (i.e., the Kafka offset)
     * @param processingTimestamp The processor-specific processing timestamp of the matchMetadata stream element
     * @param ingestionTimestamp  The data stream analysis system specific ingestion timestamp of the matchMetadata stream element
     * @param content             The content of the matchMetadata stream element (contained as byte array in the value field of the Kafka message)
     * @throws CannotGenerateDataStreamElement Thrown if the matchMetadata stream element could not be generated
     */
    public MatchMetadataStreamElement(String key, Long sequenceNumber, Long processingTimestamp, Long ingestionTimestamp, ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content) throws CannotGenerateDataStreamElement {
        super(STREAMNAME, StreamCategory.RAWINPUT, key, sequenceNumber, processingTimestamp, ingestionTimestamp, content);
    }

    /**
     * Generates a matchMetadata stream element.
     *
     * @param matchId                                             The identifier of the match in which for which this matchMetadata stream element ships metadata
     * @param generationTimestamp                                 The generation timestamp of the matchMetadata stream element
     * @param generationTimestampFirstDataStreamElementOfTheMatch The generation timestamp of the first data stream element of the match
     * @param sport                                               Sports discipline (e.g., "Football" or "Ice hockey")
     * @param fieldLength                                         Length of the field in m (e.g., 105.0)
     * @param fieldWidth                                          Width of the field in m (e.g., 68.0)
     * @param mirroredX                                           Boolean which specifies if the x coordinate of the tracked positions is mirrored
     * @param mirroredY                                           Boolean which specifies if the y coordinate of the tracked positions is mirrored
     * @param areaInfos                                           List of area infos (e.g., {field:-52.5@52.5@-34.0@34.0},{leftThird:-52.5@-17.5@-34.0@34.0},{centerThird:-17.5@17.5@-34.0@34.0},...")
     * @param matchStartUnixTs                                    Unix timestamp specifying the start of the match
     * @param competition                                         Competition (e.g., "Bundesliga" or "UEFA Champtionsleague")
     * @param venue                                               Venue (e.g., "St. Jakob-Park" or "Maracana")
     * @param objectRenameMap                                     String specifying the object rename map
     * @param teamRenameMap                                       String specifying the team rename map
     * @param videoPath                                           Path to the video file
     * @param videoOffset                                         Video offset (in s) of the start of the match
     * @param teamColorMap                                        String specifying the team color map
     * @return matchMetadata stream element
     * @throws CannotGenerateDataStreamElement Thrown if the matchMetadata stream element could not be generated
     */
    public static MatchMetadataStreamElement generateMatchMetadataStreamElement(String matchId, long generationTimestamp, long generationTimestampFirstDataStreamElementOfTheMatch, String sport, double fieldLength, double fieldWidth, boolean mirroredX, boolean mirroredY, String areaInfos, long matchStartUnixTs, String competition, String venue, String objectRenameMap, String teamRenameMap, String videoPath, long videoOffset, String teamColorMap) throws CannotGenerateDataStreamElement {
        MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload payload = MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.newBuilder()
                .setGenerationTimestampFirstDataStreamElementOfTheMatch(generationTimestampFirstDataStreamElementOfTheMatch)
                .setSport(sport)
                .setFieldLength(fieldLength)
                .setFieldWidth(fieldWidth)
                .setMirroredX(mirroredX)
                .setMirroredY(mirroredY)
                .setAreaInfos(areaInfos)
                .setMatchStartUnixTs(matchStartUnixTs)
                .setCompetition(competition)
                .setVenue(venue)
                .setObjectRenameMap(objectRenameMap)
                .setTeamRenameMap(teamRenameMap)
                .setVideoPath(videoPath)
                .setMatchStartVideoOffset(videoOffset)
                .setTeamColorMap(teamColorMap)
                .build();

        ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent content = ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.newBuilder()
                .setAtomic(true)
                .setPhase(ImmutableDataStreamElementContentProtos.ImmutableDataStreamElementContent.Phase.NULL)
                .setGenerationTimestamp(generationTimestamp)
                .setPayload(Any.pack(payload))
                .build();

        return new MatchMetadataStreamElement(matchId, null, null, null, content);
    }

    /**
     * Gets the generation timestamp of the first data stream element of the match.
     *
     * @return Generation timestamp of the first data stream element of the match
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public long getGenerationTimestampFirstDataStreamElementOfTheMatch() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getGenerationTimestampFirstDataStreamElementOfTheMatch();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getGenerationTimestampFirstDataStreamElementOfTheMatch on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the sports discipline (e.g., "Football" or "Ice hockey").
     *
     * @return Sports discipline (e.g., "Football" or "Ice hockey")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getSport() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getSport();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getSport on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the length of the field (e.g., 105.0).
     *
     * @return Length of the field (e.g., 105.0)
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public double getFieldLength() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getFieldLength();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getFieldLength on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the width of the field (e.g., 68.0).
     *
     * @return Width of the field (e.g., 68.0)
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public double getFieldWidth() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getFieldWidth();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getFieldWidth on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets if the x coordinate of the tracked positions is mirrored.
     *
     * @return True if the x coordinate of the tracked positions is mirrored, false otherwise
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public boolean isXMirrored() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getMirroredX();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process isXMirrored on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets if the y coordinate of the tracked positions is mirrored.
     *
     * @return True if the y coordinate of the tracked positions is mirrored, false otherwise
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public boolean isYMirrored() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getMirroredY();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process isYMirrored on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the list of area infos (e.g., {field:-52.5@52.5@-34.0@34.0},{leftThird:-52.5@-17.5@-34.0@34.0},{centerThird:-17.5@17.5@-34.0@34.0},...").
     *
     * @return List of area infos (e.g., {field:-52.5@52.5@-34.0@34.0},{leftThird:-52.5@-17.5@-34.0@34.0},{centerThird:-17.5@17.5@-34.0@34.0},...")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getAreaInfos() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getAreaInfos();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getAreaInfos on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the Unix timestamp specifying the start of the match.
     *
     * @return Unix timestamp specifying the start of the match
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public long getMatchStartUnixTs() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getMatchStartUnixTs();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getMatchStartUnixTs on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the competition (e.g., "Bundesliga" or "UEFA Champtionsleague").
     *
     * @return Competition (e.g., "Bundesliga" or "UEFA Champtionsleague")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getCompetition() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getCompetition();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getCompetition on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the venue (e.g., "St. Jakob-Park" or "Maracana").
     *
     * @return Venue (e.g., "St. Jakob-Park" or "Maracana")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getVenue() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getVenue();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVenue on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the String that specifies the object rename map (e.g., "{200:BALL:Ball}%{1:A1:PLAYERNAMEA1}%{2:A2:PLAYERNAMEA2}%{4:A3:PLAYERNAMEA3}%...").
     *
     * @return String that specifies the object rename map (e.g., "{200:BALL:Ball}%{1:A1:PLAYERNAMEA1}%{2:A2:PLAYERNAMEA2}%{4:A3:PLAYERNAMEA3}%...")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getObjectRenameMap() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getObjectRenameMap();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getObjectRenameMap on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the String that specifies the team rename map (e.g., "{ball:BALL:Ball}%{home:A:TEAMNAMEA}%{away:B:TEAMNAMEB}").
     *
     * @return String that specifies the team rename map (e.g., "{ball:BALL:Ball}%{home:A:TEAMNAMEA}%{away:B:TEAMNAMEB}")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getTeamRenameMap() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getTeamRenameMap();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getTeamRenameMap on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the path to the video file.
     *
     * @return Path to the video file
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getVideoPath() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getVideoPath();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getVideoPath on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the video offset (in s) of the start of the match.
     *
     * @return Video offset (in s) of the start of the match
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public long getMatchStartVideoOffset() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getMatchStartVideoOffset();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getMatchStartVideoOffset on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
        }
    }

    /**
     * Gets the String that specifies the team color map (e.g., "{A:yellow}%{B:black}").
     *
     * @return String that specifies the team color map (e.g., "{A:yellow}%{B:black}")
     * @throws CannotRetrieveInformationException Thrown if the matchMetadata stream element is ill-formed
     */
    public String getTeamColorMap() throws CannotRetrieveInformationException {
        try {
            return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class).getTeamColorMap();
        } catch (InvalidProtocolBufferException e) {
            throw new CannotRetrieveInformationException("Cannot process getTeamColorMap on matchMetadata stream element " + this + ": InvalidProtocolBufferException:" + e.toString());
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
        return this.getPayload().unpack(MatchMetadataStreamElementPayloadProtos.MatchMetadataStreamElementPayload.class);
    }
}
