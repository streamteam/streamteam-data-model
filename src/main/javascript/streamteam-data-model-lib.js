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

// This file has been generated automatically with generateJavascriptLibrary.py. DO NOT MODIFY IT!

// https://stackoverflow.com/questions/2188218/relative-paths-in-javascript-in-an-external-file/4440632#4440632
var jsFileLocation = $('script[src*=streamteam-data-model-lib]').attr('src');  // the js file path
jsFileLocation = jsFileLocation.replace('streamteam-data-model-lib.js', '');   // the js folder path

protobuf.load([jsFileLocation + "/protobuf/streamTeam/immutableDataStreamElementContent.proto",
	jsFileLocation + "protobuf/streamTeam/dummyStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/internalActiveKeysStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/ballPossessionChangeEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/throwinEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/shotStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/dribblingStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/duelEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/clearanceEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/kickoffEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/passSequenceStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/rawPositionSensorDataStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/areaEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/penaltyEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/setPlayStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/distanceStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/dribblingEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/successfulPassEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/passStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/kickEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/matchTimeProgressEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/goalEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/freekickEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/passSequenceEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/doublePassEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/teamAreaStateStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/cornerkickEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/speedLevelStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/misplacedPassEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/offsideLineStateStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/goalkickEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/underPressureEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/speedLevelChangeEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/fieldObjectStateStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/ballPossessionStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/heatmapStatisticsStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/pressingStateStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/matchMetadataStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/interceptionEventStreamElementPayload.proto",
	jsFileLocation + "protobuf/streamTeam/football/shotOffTargetEventStreamElementPayload.proto"], function(err, root) {

	if (err)
		throw err;

	ImmutableDataStreamElementContent = root.lookupType("streamTeam.ImmutableDataStreamElementContent");

	DummyStreamElementPayload = root.lookupType("streamTeam.DummyStreamElementPayload");
	InternalActiveKeysStreamElementPayload = root.lookupType("streamTeam.InternalActiveKeysStreamElementPayload");
	BallPossessionChangeEventStreamElementPayload = root.lookupType("streamTeam.football.BallPossessionChangeEventStreamElementPayload");
	ThrowinEventStreamElementPayload = root.lookupType("streamTeam.football.ThrowinEventStreamElementPayload");
	ShotStatisticsStreamElementPayload = root.lookupType("streamTeam.football.ShotStatisticsStreamElementPayload");
	DribblingStatisticsStreamElementPayload = root.lookupType("streamTeam.football.DribblingStatisticsStreamElementPayload");
	DuelEventStreamElementPayload = root.lookupType("streamTeam.football.DuelEventStreamElementPayload");
	ClearanceEventStreamElementPayload = root.lookupType("streamTeam.football.ClearanceEventStreamElementPayload");
	KickoffEventStreamElementPayload = root.lookupType("streamTeam.football.KickoffEventStreamElementPayload");
	PassSequenceStatisticsStreamElementPayload = root.lookupType("streamTeam.football.PassSequenceStatisticsStreamElementPayload");
	RawPositionSensorDataStreamElementPayload = root.lookupType("streamTeam.football.RawPositionSensorDataStreamElementPayload");
	AreaEventStreamElementPayload = root.lookupType("streamTeam.football.AreaEventStreamElementPayload");
	PenaltyEventStreamElementPayload = root.lookupType("streamTeam.football.PenaltyEventStreamElementPayload");
	SetPlayStatisticsStreamElementPayload = root.lookupType("streamTeam.football.SetPlayStatisticsStreamElementPayload");
	DistanceStatisticsStreamElementPayload = root.lookupType("streamTeam.football.DistanceStatisticsStreamElementPayload");
	DribblingEventStreamElementPayload = root.lookupType("streamTeam.football.DribblingEventStreamElementPayload");
	SuccessfulPassEventStreamElementPayload = root.lookupType("streamTeam.football.SuccessfulPassEventStreamElementPayload");
	PassStatisticsStreamElementPayload = root.lookupType("streamTeam.football.PassStatisticsStreamElementPayload");
	KickEventStreamElementPayload = root.lookupType("streamTeam.football.KickEventStreamElementPayload");
	MatchTimeProgressEventStreamElementPayload = root.lookupType("streamTeam.football.MatchTimeProgressEventStreamElementPayload");
	GoalEventStreamElementPayload = root.lookupType("streamTeam.football.GoalEventStreamElementPayload");
	FreekickEventStreamElementPayload = root.lookupType("streamTeam.football.FreekickEventStreamElementPayload");
	PassSequenceEventStreamElementPayload = root.lookupType("streamTeam.football.PassSequenceEventStreamElementPayload");
	DoublePassEventStreamElementPayload = root.lookupType("streamTeam.football.DoublePassEventStreamElementPayload");
	TeamAreaStateStreamElementPayload = root.lookupType("streamTeam.football.TeamAreaStateStreamElementPayload");
	CornerkickEventStreamElementPayload = root.lookupType("streamTeam.football.CornerkickEventStreamElementPayload");
	SpeedLevelStatisticsStreamElementPayload = root.lookupType("streamTeam.football.SpeedLevelStatisticsStreamElementPayload");
	MisplacedPassEventStreamElementPayload = root.lookupType("streamTeam.football.MisplacedPassEventStreamElementPayload");
	OffsideLineStateStreamElementPayload = root.lookupType("streamTeam.football.OffsideLineStateStreamElementPayload");
	GoalkickEventStreamElementPayload = root.lookupType("streamTeam.football.GoalkickEventStreamElementPayload");
	UnderPressureEventStreamElementPayload = root.lookupType("streamTeam.football.UnderPressureEventStreamElementPayload");
	SpeedLevelChangeEventStreamElementPayload = root.lookupType("streamTeam.football.SpeedLevelChangeEventStreamElementPayload");
	FieldObjectStateStreamElementPayload = root.lookupType("streamTeam.football.FieldObjectStateStreamElementPayload");
	BallPossessionStatisticsStreamElementPayload = root.lookupType("streamTeam.football.BallPossessionStatisticsStreamElementPayload");
	HeatmapStatisticsStreamElementPayload = root.lookupType("streamTeam.football.HeatmapStatisticsStreamElementPayload");
	PressingStateStreamElementPayload = root.lookupType("streamTeam.football.PressingStateStreamElementPayload");
	MatchMetadataStreamElementPayload = root.lookupType("streamTeam.football.MatchMetadataStreamElementPayload");
	InterceptionEventStreamElementPayload = root.lookupType("streamTeam.football.InterceptionEventStreamElementPayload");
	ShotOffTargetEventStreamElementPayload = root.lookupType("streamTeam.football.ShotOffTargetEventStreamElementPayload");
});

function decodeBase64EncodedImmutableDataStreamElement(input) {
	//https://github.com/protobufjs/protobuf.js/issues/735#issuecomment-292107345
	var buffer = Uint8Array.from(window.atob(input), c => c.charCodeAt(0));
	var immutableDataStreamElement = ImmutableDataStreamElementContent.decode(buffer);
	// https://github.com/protocolbuffers/protobuf/issues/2612#issuecomment-442700602
	var payloadBuffer = Uint8Array.from(immutableDataStreamElement.payload.value);
	switch (immutableDataStreamElement.payload.type_url) {
		case "type.googleapis.com/streamTeam.DummyStreamElementPayload":
			immutableDataStreamElement.payload = DummyStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.InternalActiveKeysStreamElementPayload":
			immutableDataStreamElement.payload = InternalActiveKeysStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.BallPossessionChangeEventStreamElementPayload":
			immutableDataStreamElement.payload = BallPossessionChangeEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.ThrowinEventStreamElementPayload":
			immutableDataStreamElement.payload = ThrowinEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.ShotStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = ShotStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.DribblingStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = DribblingStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.DuelEventStreamElementPayload":
			immutableDataStreamElement.payload = DuelEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.ClearanceEventStreamElementPayload":
			immutableDataStreamElement.payload = ClearanceEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.KickoffEventStreamElementPayload":
			immutableDataStreamElement.payload = KickoffEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.PassSequenceStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = PassSequenceStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.RawPositionSensorDataStreamElementPayload":
			immutableDataStreamElement.payload = RawPositionSensorDataStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.AreaEventStreamElementPayload":
			immutableDataStreamElement.payload = AreaEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.PenaltyEventStreamElementPayload":
			immutableDataStreamElement.payload = PenaltyEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.SetPlayStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = SetPlayStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.DistanceStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = DistanceStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.DribblingEventStreamElementPayload":
			immutableDataStreamElement.payload = DribblingEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.SuccessfulPassEventStreamElementPayload":
			immutableDataStreamElement.payload = SuccessfulPassEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.PassStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = PassStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.KickEventStreamElementPayload":
			immutableDataStreamElement.payload = KickEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.MatchTimeProgressEventStreamElementPayload":
			immutableDataStreamElement.payload = MatchTimeProgressEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.GoalEventStreamElementPayload":
			immutableDataStreamElement.payload = GoalEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.FreekickEventStreamElementPayload":
			immutableDataStreamElement.payload = FreekickEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.PassSequenceEventStreamElementPayload":
			immutableDataStreamElement.payload = PassSequenceEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.DoublePassEventStreamElementPayload":
			immutableDataStreamElement.payload = DoublePassEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.TeamAreaStateStreamElementPayload":
			immutableDataStreamElement.payload = TeamAreaStateStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.CornerkickEventStreamElementPayload":
			immutableDataStreamElement.payload = CornerkickEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.SpeedLevelStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = SpeedLevelStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.MisplacedPassEventStreamElementPayload":
			immutableDataStreamElement.payload = MisplacedPassEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.OffsideLineStateStreamElementPayload":
			immutableDataStreamElement.payload = OffsideLineStateStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.GoalkickEventStreamElementPayload":
			immutableDataStreamElement.payload = GoalkickEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.UnderPressureEventStreamElementPayload":
			immutableDataStreamElement.payload = UnderPressureEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.SpeedLevelChangeEventStreamElementPayload":
			immutableDataStreamElement.payload = SpeedLevelChangeEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.FieldObjectStateStreamElementPayload":
			immutableDataStreamElement.payload = FieldObjectStateStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.BallPossessionStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = BallPossessionStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.HeatmapStatisticsStreamElementPayload":
			immutableDataStreamElement.payload = HeatmapStatisticsStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.PressingStateStreamElementPayload":
			immutableDataStreamElement.payload = PressingStateStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.MatchMetadataStreamElementPayload":
			immutableDataStreamElement.payload = MatchMetadataStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.InterceptionEventStreamElementPayload":
			immutableDataStreamElement.payload = InterceptionEventStreamElementPayload.decode(payloadBuffer);
			break;
		case "type.googleapis.com/streamTeam.football.ShotOffTargetEventStreamElementPayload":
			immutableDataStreamElement.payload = ShotOffTargetEventStreamElementPayload.decode(payloadBuffer);
			break;
		default:
			console.warn("Don't know how to deal with this any type: " + immutableDataStreamElement.payload.type_url);
	}

	return immutableDataStreamElement;
}