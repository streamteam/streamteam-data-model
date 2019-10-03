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

package ch.unibas.dmi.dbis.streamTeam.dataStructures;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for ObjectInfo.
 */
public class ObjectInfoTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests if getNearestObject works properly.
     */
    @Test
    public void testNearestObject() {
        ObjectInfo object = new ObjectInfo("objectId", "groupId", new Geometry.Vector(0.0, 0.0, 0.0));

        List<ObjectInfo> candidates = new LinkedList<ObjectInfo>();
        ObjectInfo candidate1 = new ObjectInfo("objectId1", "groupId1", new Geometry.Vector(4.0, 4.0, 4.0));
        candidates.add(candidate1);
        ObjectInfo candidate2 = new ObjectInfo("objectId2", "groupId2", new Geometry.Vector(2.0, 2.0, 2.0));
        candidates.add(candidate2);
        ObjectInfo candidate3 = new ObjectInfo("objectId3", "groupId3", new Geometry.Vector(1.0, 1.0, 100.0));
        candidates.add(candidate3);
        ObjectInfo candidate4 = new ObjectInfo("objectId4", "groupId4", new Geometry.Vector(0.5, 0.5, 9999.9));
        candidates.add(candidate4);

        ObjectInfo nearestObject = object.getNearestObject(candidates, false);
        Assert.assertEquals("objectId2", nearestObject.getObjectId());

        ObjectInfo nearestObject2 = object.getNearestObject(candidates, true);
        Assert.assertEquals("objectId4", nearestObject2.getObjectId());
    }

    /**
     * Tests if getNearestObjectWithMaxDist works properly.
     */
    @Test
    public void testNearestObjectWithMaxDist() {
        ObjectInfo object = new ObjectInfo("objectId", "groupId", new Geometry.Vector(0.0, 0.0, 0.0));

        List<ObjectInfo> candidates = new LinkedList<ObjectInfo>();
        ObjectInfo candidate1 = new ObjectInfo("objectId1", "groupId1", new Geometry.Vector(4.0, 4.0, 4.0));
        candidates.add(candidate1);
        ObjectInfo candidate2 = new ObjectInfo("objectId2", "groupId2", new Geometry.Vector(2.0, 2.0, 2.0));
        candidates.add(candidate2);
        ObjectInfo candidate3 = new ObjectInfo("objectId3", "groupId3", new Geometry.Vector(1.0, 1.0, 100.0));
        candidates.add(candidate3);
        ObjectInfo candidate4 = new ObjectInfo("objectId4", "groupId4", new Geometry.Vector(0.5, 0.5, 9999.9));
        candidates.add(candidate4);

        ObjectInfo nearestObject = object.getNearestObjectWithMaxDist(candidates, 10.0, false);
        Assert.assertEquals("objectId2", nearestObject.getObjectId());

        ObjectInfo nearestObject2 = object.getNearestObjectWithMaxDist(candidates, 10.0, true);
        Assert.assertEquals("objectId4", nearestObject2.getObjectId());

        ObjectInfo nearestObject3 = object.getNearestObjectWithMaxDist(candidates, 1.0, false);
        Assert.assertNull(nearestObject3);
    }

    /**
     * Tests if getNearestObjects works properly.
     */
    @Test
    public void testNearestObjects() {
        ObjectInfo object = new ObjectInfo("objectId", "groupId", new Geometry.Vector(0.0, 0.0, 0.0));

        List<ObjectInfo> candidates = new LinkedList<ObjectInfo>();
        ObjectInfo candidate1 = new ObjectInfo("objectId1", "groupId1", new Geometry.Vector(4.0, 4.0, 4.0));
        candidates.add(candidate1);
        ObjectInfo candidate2 = new ObjectInfo("objectId2", "groupId2", new Geometry.Vector(2.0, 2.0, 2.0));
        candidates.add(candidate2);
        ObjectInfo candidate3 = new ObjectInfo("objectId3", "groupId3", new Geometry.Vector(1.0, 1.0, 100.0));
        candidates.add(candidate3);
        ObjectInfo candidate4 = new ObjectInfo("objectId4", "groupId4", new Geometry.Vector(0.5, 0.5, 9999.9));
        candidates.add(candidate4);

        List<ObjectInfo> nearestObjects1 = object.getNearestObjects(candidates, 3, false);
        List<ObjectInfo> expectedNeareastObjects1 = Arrays.asList(candidate2, candidate1, candidate3);
        assertObjectInfoList(expectedNeareastObjects1, nearestObjects1);

        List<ObjectInfo> nearestObjects2 = object.getNearestObjects(candidates, 2, false);
        List<ObjectInfo> expectedNeareastObjects2 = Arrays.asList(candidate2, candidate1);
        assertObjectInfoList(expectedNeareastObjects2, nearestObjects2);

        List<ObjectInfo> nearestObjects3 = object.getNearestObjects(candidates, 1, false);
        List<ObjectInfo> expectedNeareastObjects3 = Arrays.asList(candidate2);
        assertObjectInfoList(expectedNeareastObjects3, nearestObjects3);

        List<ObjectInfo> nearestObjects4 = object.getNearestObjects(candidates, 3, true);
        List<ObjectInfo> expectedNeareastObjects4 = Arrays.asList(candidate4, candidate3, candidate2);
        assertObjectInfoList(expectedNeareastObjects4, nearestObjects4);
    }

    /**
     * Tests if getNearestObjectsWithMaxDist works properly.
     */
    @Test
    public void testNearestObjectsWithMaxDist() {
        ObjectInfo object = new ObjectInfo("objectId", "groupId", new Geometry.Vector(0.0, 0.0, 0.0));

        List<ObjectInfo> candidates = new LinkedList<ObjectInfo>();
        ObjectInfo candidate1 = new ObjectInfo("objectId1", "groupId1", new Geometry.Vector(4.0, 4.0, 4.0));
        candidates.add(candidate1);
        ObjectInfo candidate2 = new ObjectInfo("objectId2", "groupId2", new Geometry.Vector(2.0, 2.0, 2.0));
        candidates.add(candidate2);
        ObjectInfo candidate3 = new ObjectInfo("objectId3", "groupId3", new Geometry.Vector(1.0, 1.0, 100.0));
        candidates.add(candidate3);
        ObjectInfo candidate4 = new ObjectInfo("objectId4", "groupId4", new Geometry.Vector(0.5, 0.5, 9999.9));
        candidates.add(candidate4);

        List<ObjectInfo> nearestObjects1 = object.getNearestObjectsWithMaxDist(candidates, 3, 1000.0, false);
        List<ObjectInfo> expectedNeareastObjects1 = Arrays.asList(candidate2, candidate1, candidate3);
        assertObjectInfoList(expectedNeareastObjects1, nearestObjects1);

        List<ObjectInfo> nearestObjects2 = object.getNearestObjectsWithMaxDist(candidates, 2, 1000.0, false);
        List<ObjectInfo> expectedNeareastObjects2 = Arrays.asList(candidate2, candidate1);
        assertObjectInfoList(expectedNeareastObjects2, nearestObjects2);

        List<ObjectInfo> nearestObjects3 = object.getNearestObjectsWithMaxDist(candidates, 3, 10.0, false);
        List<ObjectInfo> expectedNeareastObjects3 = Arrays.asList(candidate2, candidate1);
        assertObjectInfoList(expectedNeareastObjects3, nearestObjects3);

        List<ObjectInfo> nearestObjects4 = object.getNearestObjectsWithMaxDist(candidates, 2, 5.0, false);
        List<ObjectInfo> expectedNeareastObjects4 = Arrays.asList(candidate2);
        assertObjectInfoList(expectedNeareastObjects4, nearestObjects4);

        List<ObjectInfo> nearestObjects5 = object.getNearestObjectsWithMaxDist(candidates, 2, 1.0, false);
        assertObjectInfoList(null, nearestObjects5);

        List<ObjectInfo> nearestObjects6 = object.getNearestObjectsWithMaxDist(candidates, 3, 1.5, true);
        List<ObjectInfo> expectedNeareastObjects6 = Arrays.asList(candidate4, candidate3);
        assertObjectInfoList(expectedNeareastObjects6, nearestObjects6);
    }

    /**
     * Asserts if an object info list is filled as expected.
     *
     * @param expectedList Expected list
     * @param actualList   Actual list
     */
    private static void assertObjectInfoList(List<ObjectInfo> expectedList, List<ObjectInfo> actualList) {
        if (expectedList == null && actualList == null) {
            // success
        } else if (expectedList != null && actualList == null) {
            fail("Expected list to be not null but is null.");
        } else if (expectedList == null && actualList != null) {
            fail("Expected list to be null but is not null.");
        } else if (expectedList.size() != actualList.size()) {
            fail("Wrong number of list items: expected:<" + expectedList.size() + "> but was: <" + actualList.size() + ">");
        } else {
            for (int i = 0; i < expectedList.size(); ++i) {
                assertEquals(expectedList.get(i), actualList.get(i));
            }
        }
    }
}