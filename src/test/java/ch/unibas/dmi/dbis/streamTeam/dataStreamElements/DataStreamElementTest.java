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

package ch.unibas.dmi.dbis.streamTeam.dataStreamElements;

import ch.unibas.dmi.dbis.streamTeam.dataStructures.Geometry;
import ch.unibas.dmi.dbis.streamTeam.dataStructures.NonAtomicEventPhase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;
import java.util.List;

/**
 * Test class for data stream elements.
 */
public class DataStreamElementTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests if getStreamName works properly.
     */
    @Test
    public void testGetStreamName() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("dummy", dse1.getStreamName());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("dummy", dse2.getStreamName());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals("dummy", dse3.getStreamName());

        AbstractImmutableDataStreamElement dse4 = createInternalDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals("dummy", dse4.getStreamName());

        AbstractImmutableDataStreamElement dse5 = createStateDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("dummy", dse5.getStreamName());
    }

    /**
     * Tests if getStreamCategory works properly.
     */
    @Test
    public void testGetStreamCategory() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(AbstractImmutableDataStreamElement.StreamCategory.EVENT, dse1.getStreamCategory());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(AbstractImmutableDataStreamElement.StreamCategory.STATISTICS, dse2.getStreamCategory());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(AbstractImmutableDataStreamElement.StreamCategory.RAWINPUT, dse3.getStreamCategory());

        AbstractImmutableDataStreamElement dse4 = createInternalDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(AbstractImmutableDataStreamElement.StreamCategory.INTERNAL, dse4.getStreamCategory());

        AbstractImmutableDataStreamElement dse5 = createStateDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(AbstractImmutableDataStreamElement.StreamCategory.STATE, dse5.getStreamCategory());
    }

    /**
     * Tests if getKey works properly.
     */
    @Test
    public void testGetKey() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("key", dse1.getKey());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("key", dse2.getKey());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals("key", dse3.getKey());

        AbstractImmutableDataStreamElement dse4 = createInternalDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals("key", dse4.getKey());
    }

    /**
     * Tests if isAtomic works properly.
     */
    @Test
    public void testIsAtomic() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(true, dse1.isAtomic());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(true, dse2.isAtomic());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(true, dse3.isAtomic());

        AbstractImmutableDataStreamElement dse4 = createInternalDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(true, dse4.isAtomic());

        AbstractImmutableDataStreamElement dse5 = createNonatomicEventDummyStreamElementWithStartPhase();
        Assert.assertEquals(false, dse5.isAtomic());

        AbstractImmutableDataStreamElement dse6 = createNonatomicEventDummyStreamElementWithActivePhase();
        Assert.assertEquals(false, dse6.isAtomic());

        AbstractImmutableDataStreamElement dse7 = createNonatomicEventDummyStreamElementWithEndPhase();
        Assert.assertEquals(false, dse7.isAtomic());
    }

    /**
     * Tests if a creation of a non-atomic raw input stream element properly throws an exception.
     */
    @Test
    public void testExceptionOnCreateNonatomicRawInputStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createNonatomicRawInputDummyStreamElement();
    }

    /**
     * Tests if a creation of a non-atomic statistics stream element properly throws an exception.
     */
    @Test
    public void testExceptionOnCreateNonatomicStatisticsStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createNonatomicStatisticsDummyStreamElement();
    }

    /**
     * Tests if a creation of a non-atomic state stream element properly throws an exception.
     */
    @Test
    public void testExceptionOnCreateNonatomicStateStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createNonatomicStateDummyStreamElement();
    }

    /**
     * Tests if getEventIdentifier works properly.
     */
    @Test
    public void getGetEventIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createNonatomicEventDummyStreamElementWithStartPhase();
        Assert.assertEquals("dummy_key_eventIdentifierInnerKey_123", dse1.getEventIdentifier());

        AbstractImmutableDataStreamElement dse2 = createNonatomicEventDummyStreamElementWithActivePhase();
        Assert.assertEquals("dummy_key_eventIdentifierInnerKey_123", dse2.getEventIdentifier());

        AbstractImmutableDataStreamElement dse3 = createNonatomicEventDummyStreamElementWithEndPhase();
        Assert.assertEquals("dummy_key_eventIdentifierInnerKey_123", dse3.getEventIdentifier());
    }

    /**
     * Tests if a getEventIdentifier properly throws an exception when applied on an atomic event stream element.
     */
    @Test
    public void testExceptionOnGetEventIdentifierForAtomic() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getEventIdentifier();
    }

    /**
     * Tests if a creation of a non-atomic event stream element properly throws an exception when done without an event identifier.
     */
    @Test
    public void testExceptionOnCreateNonatomicEventStreamElementWithoutEventIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createNonatomicEventDummyStreamElementWithoutEventIdentifier();
    }

    /**
     * Tests if a creation of an atomic event stream element properly throws an exception when done with an event identifier.
     */
    @Test
    public void testExceptionOnCreateAtomicEventStreamElementWithEventIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithEventIdentifier();
    }

    /**
     * Tests if getPhase works properly.
     */
    @Test
    public void testGetPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse5 = createNonatomicEventDummyStreamElementWithStartPhase();
        Assert.assertEquals(NonAtomicEventPhase.START, dse5.getPhase());

        AbstractImmutableDataStreamElement dse6 = createNonatomicEventDummyStreamElementWithActivePhase();
        Assert.assertEquals(NonAtomicEventPhase.ACTIVE, dse6.getPhase());

        AbstractImmutableDataStreamElement dse7 = createNonatomicEventDummyStreamElementWithEndPhase();
        Assert.assertEquals(NonAtomicEventPhase.END, dse7.getPhase());
    }

    /**
     * Tests if a getPhase properly throws an exception when applied on an atomic event stream element.
     */
    @Test
    public void testExceptionOnGetPhaseForAtomic() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getPhase();
    }

    /**
     * Tests if a creation of a non-atomic event stream element properly throws an exception when done with a null phase.
     */
    @Test
    public void testExceptionOnCreateNonatomicEventStreamElementWithNullPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createNonatomicEventDummyStreamElementWithNullPhase();
    }

    /**
     * Tests if a creation of an atomic event stream element properly throws an exception when done with a non-null phase.
     */
    @Test
    public void testExceptionOnCreateAtomicEventStreamElementWithNonNullPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithNonNullPhase();
    }

    /**
     * Tests if getSequenceNumber works properly.
     */
    @Test
    public void testGetSequenceNumber() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(17L, dse1.getSequenceNumber());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(17L, dse2.getSequenceNumber());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(17L, dse3.getSequenceNumber());
    }

    /**
     * Tests if a getSequenceNumber properly throws an exception when applied on a data stream element without a sequence number.
     */
    @Test
    public void testExceptionOnGetSequenceNumberForElementWithoutSequenceNumber() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getSequenceNumber();
    }

    /**
     * Tests if getProcessingTimestamp works properly.
     */
    @Test
    public void testGetProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(123456L, dse1.getProcessingTimestamp());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(123456L, dse2.getProcessingTimestamp());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(123456L, dse3.getProcessingTimestamp());
    }

    /**
     * Tests if a getProcessingTimestamp properly throws an exception when applied on a data stream element without a processing timestamp.
     */
    @Test
    public void testExceptionOnGetProcessingTimestampForElementWithoutProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getProcessingTimestamp();
    }

    /**
     * Tests if getIngestionTimestamp works properly.
     */
    @Test
    public void testGetIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(789L, dse1.getIngestionTimestamp());
    }

    /**
     * Tests if a getIngestionTimestamp properly throws an exception when applied on a data stream element without an ingestion timestamp.
     */
    @Test
    public void testExceptionOnGetIngestionTimestampForElementWithoutIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createRawInputDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp();
        dse1.getIngestionTimestamp();
    }

    /**
     * Tests if the ingestion timestamp of an event stream element is properly set to null and thus getIngestionTimestamp throws an exception when created with an ingestion timestamp.
     */
    @Test
    public void testExceptionOnGetIngestionTimestampForEventStreamElementWhichIsCreatedWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithIngestionTimestamp();
        dse1.getIngestionTimestamp();
    }

    /**
     * Tests if the ingestion timestamp of a statistics stream element is properly set to null and thus getIngestionTimestamp throws an exception when created with an ingestion timestamp.
     */
    @Test
    public void testExceptionOnGetIngestionTimestampForStatisticsStreamElementWhichIsCreatedWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createStatisticsDummyStreamElementWithIngestionTimestamp();
        dse1.getIngestionTimestamp();
    }

    /**
     * Tests if the ingestion timestamp of a state stream element is properly set to null and thus getIngestionTimestamp throws an exception when created with an ingestion timestamp.
     */
    @Test
    public void testExceptionOnGetIngestionTimestampForStateStreamElementWhichIsCreatedWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createStateDummyStreamElementWithIngestionTimestamp();
        dse1.getIngestionTimestamp();
    }

    /**
     * Tests if a creation of an internal stream element properly throws an exception when done with a sequence number, a processing timestamp, and an ingestion timestamp.
     */
    @Test
    public void testExceptionOnCreateInternalStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createInternalDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
    }

    /**
     * Tests if a creation of an internal stream element properly throws an exception when done with a sequence number and a processing timestamp but no ingestion timestamp.
     */
    @Test
    public void testExceptionOnCreateInternalStreamElementWithSequenceNumberAndProcessingTimestampButNoIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement.class);

        AbstractImmutableDataStreamElement dse1 = createInternalDummyStreamElementWithSequenceNumberAndProcessingTimestampButNoIngestionTimestamp();
    }

    /**
     * Tests if getGenerationTimestamp works properly.
     */
    @Test
    public void testGetGenerationTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1L, dse1.getGenerationTimestamp());

        AbstractImmutableDataStreamElement dse2 = createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1L, dse2.getGenerationTimestamp());

        AbstractImmutableDataStreamElement dse3 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(1L, dse3.getGenerationTimestamp());
    }

    /**
     * Tests if getObjectIdentifier works properly.
     */
    @Test
    public void testGetObjectIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("objId1", dse1.getObjectIdentifier(0));

        AbstractImmutableDataStreamElement dse2 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("objId1", dse2.getObjectIdentifier(0));
        Assert.assertEquals("objId2", dse2.getObjectIdentifier(1));
        Assert.assertEquals("objId3", dse2.getObjectIdentifier(2));
    }

    /**
     * Tests if a getObjectIdentifier properly throws an exception when applied on a data stream element with a too high index.
     */
    @Test
    public void testExceptionOnGetObjectIdentifierForNonExistingIndex() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getObjectIdentifier(999);
    }

    /**
     * Tests if getObjectIdentifiersList works properly.
     */
    @Test
    public void testGetObjectIdentifiersList() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        List<String> objectIdentifiers = dse1.getObjectIdentifiersList();
        if (objectIdentifiers.size() != 1 || !objectIdentifiers.get(0).equals("objId1")) {
            Assert.fail("objectIdentifiersList for dse1 is incorrect.");
        }

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        objectIdentifiers = dse2.getObjectIdentifiersList();
        if (objectIdentifiers.size() != 0) {
            Assert.fail("objectIdentifiersList for dse2 is incorrect.");
        }

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        objectIdentifiers = dse3.getObjectIdentifiersList();
        if (objectIdentifiers.size() != 3 || !objectIdentifiers.get(0).equals("objId1") || !objectIdentifiers.get(1).equals("objId2") || !objectIdentifiers.get(2).equals("objId3")) {
            Assert.fail("objectIdentifiersList for dse3 is incorrect.");
        }
    }

    /**
     * Tests if getObjectIdentifiersListSize works properly.
     */
    @Test
    public void testGetObjectIdentifiersListSize() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1, dse1.getObjectIdentifiersListSize());

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getObjectIdentifiersListSize());

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(3, dse3.getObjectIdentifiersListSize());
    }

    /**
     * Tests if getGroupIdentifier works properly.
     */
    @Test
    public void testGetGroupIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("groupId1", dse1.getGroupIdentifier(0));

        AbstractImmutableDataStreamElement dse2 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("groupId1", dse2.getGroupIdentifier(0));
        Assert.assertEquals("groupId2", dse2.getGroupIdentifier(1));
    }

    /**
     * Tests if a getGroupIdentifier properly throws an exception when applied on a data stream element with a too high index.
     */
    @Test
    public void testExceptionOnGetGroupIdentifierForNonExistingIndex() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getGroupIdentifier(999);
    }

    /**
     * Tests if getGroupIdentifiersList works properly.
     */
    @Test
    public void testGetGroupIdentifiersList() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        List<String> groupIdentifiers = dse1.getGroupIdentifiersList();
        if (groupIdentifiers.size() != 1 || !groupIdentifiers.get(0).equals("groupId1")) {
            Assert.fail("objectIdentifiersList for dse1 is incorrect.");
        }

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        groupIdentifiers = dse2.getGroupIdentifiersList();
        if (groupIdentifiers.size() != 0) {
            Assert.fail("objectIdentifiersList for dse2 is incorrect.");
        }

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        groupIdentifiers = dse3.getGroupIdentifiersList();
        if (groupIdentifiers.size() != 2 || !groupIdentifiers.get(0).equals("groupId1") || !groupIdentifiers.get(1).equals("groupId2")) {
            Assert.fail("objectIdentifiersList for dse3 is incorrect.");
        }
    }

    /**
     * Tests if getObjectIdentifiersListSize works properly.
     */
    @Test
    public void testGetGroupIdentifiersListSize() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1, dse1.getGroupIdentifiersListSize());

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getGroupIdentifiersListSize());

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(2, dse3.getGroupIdentifiersListSize());
    }

    /**
     * Tests if getPosition works properly.
     */
    @Test
    public void testGetPosition() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1.0d, dse1.getPosition(0).x, 0.001);
        Assert.assertEquals(2.0d, dse1.getPosition(0).y, 0.001);
        Assert.assertEquals(3.0d, dse1.getPosition(0).z, 0.001);

        AbstractImmutableDataStreamElement dse2 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1.0d, dse2.getPosition(0).x, 0.001);
        Assert.assertEquals(2.0d, dse2.getPosition(0).y, 0.001);
        Assert.assertEquals(3.0d, dse2.getPosition(0).z, 0.001);
        Assert.assertEquals(4.0d, dse2.getPosition(1).x, 0.001);
        Assert.assertEquals(5.0d, dse2.getPosition(1).y, 0.001);
        Assert.assertEquals(6.0d, dse2.getPosition(1).z, 0.001);
        Assert.assertEquals(7.0d, dse2.getPosition(2).x, 0.001);
        Assert.assertEquals(8.0d, dse2.getPosition(2).y, 0.001);
        Assert.assertEquals(9.0d, dse2.getPosition(2).z, 0.001);
        Assert.assertEquals(10.0d, dse2.getPosition(3).x, 0.001);
        Assert.assertEquals(11.0d, dse2.getPosition(3).y, 0.001);
        Assert.assertEquals(12.0d, dse2.getPosition(3).z, 0.001);
    }

    /**
     * Tests if a getPosition properly throws an exception when applied on a data stream element with a too high index.
     */
    @Test
    public void testExceptionOnGetPositionForNonExistingIndex() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getPosition(999);
    }

    /**
     * Tests if getPositionsList works properly.
     */
    @Test
    public void testGetPositionsList() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        List<Geometry.Vector> positions = dse1.getPositionsList();
        if (positions.size() != 1) {
            Assert.fail("positionsList for dse1 is incorrect.");
        }
        Assert.assertEquals(1.0d, positions.get(0).x, 0.001);
        Assert.assertEquals(2.0d, positions.get(0).y, 0.001);
        Assert.assertEquals(3.0d, positions.get(0).z, 0.001);

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        positions = dse2.getPositionsList();
        if (positions.size() != 0) {
            Assert.fail("positionsList for dse2 is incorrect.");
        }

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        positions = dse3.getPositionsList();
        if (positions.size() != 4) {
            Assert.fail("positionsList for dse3 is incorrect.");
        }
        Assert.assertEquals(1.0d, positions.get(0).x, 0.001);
        Assert.assertEquals(2.0d, positions.get(0).y, 0.001);
        Assert.assertEquals(3.0d, positions.get(0).z, 0.001);
        Assert.assertEquals(4.0d, positions.get(1).x, 0.001);
        Assert.assertEquals(5.0d, positions.get(1).y, 0.001);
        Assert.assertEquals(6.0d, positions.get(1).z, 0.001);
        Assert.assertEquals(7.0d, positions.get(2).x, 0.001);
        Assert.assertEquals(8.0d, positions.get(2).y, 0.001);
        Assert.assertEquals(9.0d, positions.get(2).z, 0.001);
        Assert.assertEquals(10.0d, positions.get(3).x, 0.001);
        Assert.assertEquals(11.0d, positions.get(3).y, 0.001);
        Assert.assertEquals(12.0d, positions.get(3).z, 0.001);
    }

    /**
     * Tests if getPositionsListSize works properly.
     */
    @Test
    public void testGetPositionsListSize() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1, dse1.getPositionsListSize());

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getPositionsListSize());

        AbstractImmutableDataStreamElement dse3 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(4, dse3.getPositionsListSize());
    }

    /**
     * Tests if getFieldValueByName works properly for a non-payload field.
     */
    @Test
    public void testGetFieldValueByNameNotInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1L, dse1.getFieldValueByName("generationTimestamp", false));
    }

    /**
     * Tests if getFieldValueByName works properly for a payload field.
     */
    @Test
    public void testGetFieldValueByNameInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(1337L, dse1.getFieldValueByName("longValue", true));
        Assert.assertEquals("string", dse1.getFieldValueByName("stringValue", true));
        Assert.assertEquals(42.21d, (Double) dse1.getFieldValueByName("doubleValue", true), 0.001);
    }

    /**
     * Tests if a getFieldValueByName properly throws an exception when no field with such a name exists.
     */
    @Test
    public void testExceptionOnFieldValueByNameForNonexistingName() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp();
        dse1.getFieldValueByName("nonexisting", false);
    }

    /**
     * Tests if getArrayValueByName works properly for a non-payload field.
     */
    @Test
    public void testGetArrayValueByNameNotInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals("objId1", dse1.getArrayValueByName("objectIdentifiers", 0, false));
        Assert.assertEquals("objId2", dse1.getArrayValueByName("objectIdentifiers", 1, false));
        Assert.assertEquals("objId3", dse1.getArrayValueByName("objectIdentifiers", 2, false));
    }

    /**
     * Tests if getArrayValueByName works properly for a payload field.
     */
    @Test
    public void testGetArrayValueByNameInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(100L, dse1.getArrayValueByName("repeatedValue", 0, true));
        Assert.assertEquals(200L, dse1.getArrayValueByName("repeatedValue", 1, true));
        Assert.assertEquals(300L, dse1.getArrayValueByName("repeatedValue", 2, true));
    }

    /**
     * Tests if a getArrayValueByName properly throws an exception when no field with such a name exists.
     */
    @Test
    public void testExceptionOnGetArrayValueByNameForNonexistingName() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getArrayValueByName("nonexisting", 0, false);
    }

    /**
     * Tests if a getArrayValueByName properly throws an exception when applied on a data stream element with a too high index.
     */
    @Test
    public void testExceptionOnGetArrayValueByNameForNonExistingIndex() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getArrayValueByName("objectIdentifiers", 999, false);
    }

    /**
     * Tests if getArraySizeByName works properly for a non-payload field.
     */
    @Test
    public void testGetArraySizeByNameNotInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(3, dse1.getArraySizeByName("objectIdentifiers", false));

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getArraySizeByName("objectIdentifiers", false));
    }

    /**
     * Tests if getArraySizeByName works properly for positions field.
     */
    @Test
    public void testGetArraySizeByNamePositions() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(4, dse1.getArraySizeByName("positions", false));

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getArraySizeByName("positions", false));
    }

    /**
     * Tests if getArraySizeByName works properly for a payload field.
     */
    @Test
    public void testGetArraySizeByNameInPlayload() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        Assert.assertEquals(3, dse1.getArraySizeByName("repeatedValue", true));

        AbstractImmutableDataStreamElement dse2 = createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp();
        Assert.assertEquals(0, dse2.getArraySizeByName("repeatedValue", true));
    }

    /**
     * Tests if a getArraySizeByName properly throws an exception when no field with such a name exists.
     */
    @Test
    public void testExceptionOnGetArraySizeByNameForNonexistingName() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement, AbstractImmutableDataStreamElement.CannotRetrieveInformationException {
        this.exception.expect(AbstractImmutableDataStreamElement.CannotRetrieveInformationException.class);

        AbstractImmutableDataStreamElement dse1 = createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp();
        dse1.getArraySizeByName("nonexisting", false);
    }

    /**
     * Creates an atomic event dummy stream element without a sequenceNumber, a processing timestamp, and an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createRawInputDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.RAWINPUT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates an atomic event dummy stream element with a sequenceNumber, a processing timestamp, and an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createRawInputDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();

        List<String> groupIdentifiers = new LinkedList<>();

        List<Geometry.Vector> positions = new LinkedList<>();

        List<Long> repeatedValues = new LinkedList<>();

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.RAWINPUT, "key", 17L, 123456L, 789L, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a non-atomic statistics dummy stream element.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicRawInputDummyStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.RAWINPUT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.ACTIVE, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates an atomic event dummy stream element without a sequenceNumber and a processing timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createAtomicEventDummyStreamElementWithoutSequenceNumberAndProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");
        objectIdentifiers.add("objId2");
        objectIdentifiers.add("objId3");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");
        groupIdentifiers.add("groupId2");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));
        positions.add(new Geometry.Vector(4.0d, 5.0d, 6.0d));
        positions.add(new Geometry.Vector(7.0d, 8.0d, 9.0d));
        positions.add(new Geometry.Vector(10.0d, 11.0d, 12.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates an atomic event dummy stream element with a sequenceNumber and a processing timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createAtomicEventDummyStreamElementWithSequenceNumberAndProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", 17L, 123456L, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates an atomic event dummy stream element with an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createAtomicEventDummyStreamElementWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", 17L, 123456L, 789L, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates an atomic event dummy stream element with an event identifier.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createAtomicEventDummyStreamElementWithEventIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates an atomic event dummy stream element with a non-null phase.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createAtomicEventDummyStreamElementWithNonNullPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, NonAtomicEventPhase.ACTIVE, null, null);
    }

    /**
     * Creates a statistics dummy stream element with a sequenceNumber and a processing timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createStatisticsDummyStreamElementWithSequenceNumberAndProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATISTICS, "key", 17L, 123456L, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a statistics dummy stream element with an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createStatisticsDummyStreamElementWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATISTICS, "key", 17L, 123456L, 789L, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a non-atomic statistics dummy stream element.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicStatisticsDummyStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATISTICS, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.ACTIVE, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a non-atomic event dummy stream element with a start phase.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicEventDummyStreamElementWithStartPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.START, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a non-atomic event dummy stream element with an active phase.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicEventDummyStreamElementWithActivePhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.ACTIVE, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a non-atomic event dummy stream element with an end phase.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicEventDummyStreamElementWithEndPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.END, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a non-atomic event dummy stream element without an event identifier.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicEventDummyStreamElementWithoutEventIdentifier() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.ACTIVE, null, null);
    }

    /**
     * Creates a non-atomic event dummy stream element with a null phase.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicEventDummyStreamElementWithNullPhase() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.EVENT, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, null, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a state dummy stream element with a sequenceNumber and a processing timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createStateDummyStreamElementWithSequenceNumberAndProcessingTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATE, "key", 17L, 123456L, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a state dummy stream element with an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createStateDummyStreamElementWithIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATE, "key", 17L, 123456L, 789L, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a non-atomic state dummy stream element.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createNonatomicStateDummyStreamElement() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.STATE, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, false, NonAtomicEventPhase.ACTIVE, "eventIdentifierInnerKey", 123L);
    }

    /**
     * Creates a internal dummy stream element without a sequenceNumber, a processing timestamp, and an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createInternalDummyStreamElementWithoutSequenceNumberProcessingTimestampAndIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.INTERNAL, "key", null, null, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a internal dummy stream element with a sequenceNumber, a processing timestamp, and an ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createInternalDummyStreamElementWithSequenceNumberProcessingTimestampAndIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.INTERNAL, "key", 17L, 123456L, 789L, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }

    /**
     * Creates a internal dummy stream element with a sequenceNumber and a processing timestamp but no ingestion timestamp.
     *
     * @return dummy stream element
     */
    public static DummyStreamElement createInternalDummyStreamElementWithSequenceNumberAndProcessingTimestampButNoIngestionTimestamp() throws AbstractImmutableDataStreamElement.CannotGenerateDataStreamElement {

        List<String> objectIdentifiers = new LinkedList<>();
        objectIdentifiers.add("objId1");

        List<String> groupIdentifiers = new LinkedList<>();
        groupIdentifiers.add("groupId1");

        List<Geometry.Vector> positions = new LinkedList<>();
        positions.add(new Geometry.Vector(1.0d, 2.0d, 3.0d));

        List<Long> repeatedValues = new LinkedList<>();
        repeatedValues.add(100L);
        repeatedValues.add(200L);
        repeatedValues.add(300L);

        return DummyStreamElement.generateDummyStreamElement("dummy", AbstractImmutableDataStreamElement.StreamCategory.INTERNAL, "key", 17L, 123456L, null, 1L, objectIdentifiers, groupIdentifiers, positions, 1337L, true, "string", 42.21d, repeatedValues, true, null, null, null);
    }
}