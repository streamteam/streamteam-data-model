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
 * Test class for Geometry.
 */
public class GeometryTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests if the norm of a vector is calculated properly.
     */
    @Test
    public void testNorm() {
        // calculated expected values with Wolfram Alpha using norm({v1.x,v1.y,v1.z})
        Geometry.Vector v1 = new Geometry.Vector(1.0, 2.0, 3.0);
        double norm1 = v1.norm();
        Assert.assertEquals(3.74166, norm1, 0.01);

        Geometry.Vector v2 = new Geometry.Vector(-2.3, 7.9, 0);
        double norm2 = v2.norm();
        Assert.assertEquals(8.228, norm2, 0.01);

        Geometry.Vector v3 = new Geometry.Vector(0, 0, 0);
        double norm3 = v3.norm();
        Assert.assertEquals(0, norm3, 0.01);
    }

    /**
     * Tests if the distance between two points represented as vectors is calculated properly.
     */
    @Test
    public void testDistance() {
        // calculated expected values with Wolfram Alpha using dist({v1.x,v1.y,v1.z},{v2.x,v2.y,v2.z})
        Geometry.Vector v1 = new Geometry.Vector(1.0, 2.0, 3.0);
        Geometry.Vector v2 = new Geometry.Vector(4.0, 5.0, 6.0);
        double dist1 = Geometry.distance(v1, v2);
        Assert.assertEquals(5.19615, dist1, 0.01);

        Geometry.Vector v3 = new Geometry.Vector(-2.3, 7.9, 0);
        Geometry.Vector v4 = new Geometry.Vector(20.3, 0, 13.4);
        double dist2 = Geometry.distance(v3, v4);
        Assert.assertEquals(27.4359, dist2, 0.01);

        Geometry.Vector v5 = new Geometry.Vector(1, 1, 1);
        Geometry.Vector v6 = new Geometry.Vector(0, 0, 0);
        double dist3 = Geometry.distance(v5, v6);
        Assert.assertEquals(1.73205, dist3, 0.01);

        Geometry.Vector v7 = new Geometry.Vector(0, 0, 0);
        Geometry.Vector v8 = new Geometry.Vector(0, 0, 0);
        double dist4 = Geometry.distance(v7, v8);
        Assert.assertEquals(0, dist4, 0.01);
    }

    /**
     * Tests if the distance without regarding the z axis between two points represented as vectors is calculated properly.
     */
    @Test
    public void testDistanceWithoutZ() {
        // calculated expected values with Wolfram Alpha using dist({v1.x,v1.y,v1.z},{v2.x,v2.y,v2.z})
        Geometry.Vector v1 = new Geometry.Vector(1.0, 2.0, 3.0);
        Geometry.Vector v2 = new Geometry.Vector(4.0, 5.0, 6.0);
        double dist1 = Geometry.distanceWithoutZ(v1, v2);
        Assert.assertEquals(4.2426, dist1, 0.01);

        Geometry.Vector v3 = new Geometry.Vector(-2.3, 7.9, 0);
        Geometry.Vector v4 = new Geometry.Vector(20.3, 0, 13.4);
        double dist2 = Geometry.distanceWithoutZ(v3, v4);
        Assert.assertEquals(23.9409, dist2, 0.01);

        Geometry.Vector v5 = new Geometry.Vector(1, 1, 1);
        Geometry.Vector v6 = new Geometry.Vector(0, 0, 0);
        double dist3 = Geometry.distanceWithoutZ(v5, v6);
        Assert.assertEquals(1.4142, dist3, 0.01);

        Geometry.Vector v7 = new Geometry.Vector(0, 0, 0);
        Geometry.Vector v8 = new Geometry.Vector(0, 0, 0);
        double dist4 = Geometry.distanceWithoutZ(v7, v8);
        Assert.assertEquals(0, dist4, 0.01);
    }

    /**
     * Tests if the dot product of two vectors is calculated properly.
     */
    @Test
    public void testDotProduct() {
        // calculated expected values with Wolfram Alpha using {v1.x,v1.y,v1.z}.{v2.x,v2.y,v2.z}
        Geometry.Vector v1 = new Geometry.Vector(1.0, 2.0, 3.0);
        Geometry.Vector v2 = new Geometry.Vector(4.0, 5.0, 6.0);
        double dotProduct1 = Geometry.dotProduct(v1, v2);
        Assert.assertEquals(32, dotProduct1, 0.01);

        Geometry.Vector v3 = new Geometry.Vector(-2.3, 7.9, 0);
        Geometry.Vector v4 = new Geometry.Vector(20.3, 0, 13.4);
        double dotProduct2 = Geometry.dotProduct(v3, v4);
        Assert.assertEquals(-46.69, dotProduct2, 0.01);

        Geometry.Vector v5 = new Geometry.Vector(1, 1, 1);
        Geometry.Vector v6 = new Geometry.Vector(0, 0, 0);
        double dotProduct3 = Geometry.dotProduct(v5, v6);
        Assert.assertEquals(0, dotProduct3, 0.01);

        Geometry.Vector v7 = new Geometry.Vector(0, 0, 0);
        Geometry.Vector v8 = new Geometry.Vector(0, 0, 0);
        double dotProduct4 = Geometry.dotProduct(v7, v8);
        Assert.assertEquals(0, dotProduct4, 0.01);
    }

    /**
     * Tests if the angle between two vectors is calculated properly.
     */
    @Test
    public void testAngle() {
        // calculated expected values with Wolfram Alpha using angle({v1.x,v1.y,v1.z},{v2.x,v2.y,v2.z})
        Geometry.Vector v1 = new Geometry.Vector(1.0, 2.0, 3.0);
        Geometry.Vector v2 = new Geometry.Vector(4.0, 5.0, 6.0);
        double angle1 = Geometry.angle(v1, v2);
        Assert.assertEquals(0.225726, angle1, 0.01);

        Geometry.Vector v3 = new Geometry.Vector(-2.3, 7.9, 0);
        Geometry.Vector v4 = new Geometry.Vector(20.3, 0, 13.4);
        double angle2 = Geometry.angle(v3, v4);
        Assert.assertEquals(1.80626, angle2, 0.01);
    }

}