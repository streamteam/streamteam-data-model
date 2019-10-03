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

import java.io.Serializable;

/**
 * Helper class for geometric operations.
 */
public class Geometry {

    /**
     * 3D Vector.
     */
    public static class Vector implements Serializable {

        /**
         * X coordinate
         */
        public final double x;

        /**
         * Y coordinate
         */
        public final double y;

        /**
         * Z coordinate
         */
        public final double z;

        /**
         * Vector constructor.
         *
         * @param x X coordinate
         * @param y Y coordinate
         * @param z Z coordinate
         */
        public Vector(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Generates a string representation of the vector.
         *
         * @return String representation of the vector
         */
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("<");
            stringBuilder.append(this.x);
            stringBuilder.append(",");
            stringBuilder.append(this.y);
            stringBuilder.append(",");
            stringBuilder.append(this.z);
            stringBuilder.append(">");
            return stringBuilder.toString();
        }

        /**
         * Calculates the euclidean norm of a vector.
         *
         * @return Euclidean norm.
         */
        public double norm() {
            return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        }

        /**
         * Calculates the euclidean norm of the projection to v.
         *
         * @param v Vector to which the vector should be projected before calculating the norm
         * @return Euclidean norm of the projection to v
         */
        public double projectedNorm(Vector v) {
            // http://www.chemgapedia.de/vsengine/vlu/vsc/de/ma/1/mc/ma_02/ma_02_01/ma_02_01_04.vlu/Page/vsc/de/ma/1/mc/ma_02/ma_02_01/ma_02_01_10.vscml.html
            return dotProduct(this, v) / v.norm();
        }
    }

    /**
     * Calculates the euclidean dot product of two vectors.
     *
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return Euclidean dot product
     */
    public static double dotProduct(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    /**
     * Calculates the angle (in rad) between two vectors.
     *
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return Angle (in rad)
     */
    public static double angle(Vector v1, Vector v2) {
        double dotProduct = dotProduct(v1, v2);
        double normV1 = v1.norm();
        double normV2 = v2.norm();
        double angle = Math.acos(dotProduct / (normV1 * normV2));

        if (Double.isNaN(angle)) {
            return 0.0;
        } else {
            return angle;
        }
    }

    /**
     * Calculates the euclidean distance between two vectors.
     *
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return Euclidean distance
     */
    public static double distance(Vector v1, Vector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        double dz = v1.z - v2.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Calculates the euclidean distance between two vectors without regarding the z axis.
     *
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return Euclidean distance without regarding the z axis
     */
    public static double distanceWithoutZ(Vector v1, Vector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
