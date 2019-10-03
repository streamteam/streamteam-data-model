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

/**
 * Test class for AreaInfo.
 */
public class AreaInfoTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests if points inside of an area are properly reported to be contained in the area.
     */
    @Test
    public void testInside() {
        AreaInfo areaInfo = new AreaInfo("testArea", -5, 10, 1, 9.5);

        boolean in1 = areaInfo.isContained(new Geometry.Vector(0, 3, 0));
        Assert.assertEquals(true, in1);

        boolean in2 = areaInfo.isContained(new Geometry.Vector(-1.5, 1.5, 42));
        Assert.assertEquals(true, in2);
    }

    /**
     * Tests if points outside of an area are properly reported to be no contained in the area.
     */
    @Test
    public void testOutside() {
        AreaInfo areaInfo = new AreaInfo("testArea", -5, 10, 1, 9.5);

        boolean in1 = areaInfo.isContained(new Geometry.Vector(100, 100, 0));
        Assert.assertEquals(false, in1);

        boolean in2 = areaInfo.isContained(new Geometry.Vector(-5.1, 9.6, 42));
        Assert.assertEquals(false, in2);
    }

    /**
     * Tests if points on the border of an area are properly reported to be contained in the area.
     */
    @Test
    public void testOnBorder() {
        AreaInfo areaInfo = new AreaInfo("testArea", -5, 10, 1, 9.5);

        boolean in1 = areaInfo.isContained(new Geometry.Vector(-5, 3, 0));
        Assert.assertEquals(true, in1);

        boolean in2 = areaInfo.isContained(new Geometry.Vector(-5, 1, 42));
        Assert.assertEquals(true, in2);
    }

}