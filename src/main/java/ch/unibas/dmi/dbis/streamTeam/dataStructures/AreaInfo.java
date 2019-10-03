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

/**
 * Information about an rectangular area.
 */
public class AreaInfo {

    /**
     * Identifier of the area
     */
    public final String areaId;

    /**
     * Minimal x position of the rectangular area
     */
    public final double minX;

    /**
     * Maximal x position of the rectangular area
     */
    public final double maxX;

    /**
     * Minimal y position of the rectangular area
     */
    public final double minY;

    /**
     * Maximal x position of the rectangular area
     */
    public final double maxY;

    /**
     * AreaInfo constructor.
     *
     * @param areaId Identifier of the area
     * @param minX   Minimal x position of the rectangular area
     * @param maxX   Maximal x position of the rectangular area
     * @param minY   Minimal y position of the rectangular area
     * @param maxY   Maximal x position of the rectangular area
     */
    public AreaInfo(String areaId, double minX, double maxX, double minY, double maxY) {
        this.areaId = areaId;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    /**
     * Returns true if the object is contained in the area.
     *
     * @param objectPosition Position of the object
     * @return True if the object is contained in the area, otherwise false
     */
    public boolean isContained(Geometry.Vector objectPosition) {
        if (this.minX <= objectPosition.x && this.minY <= objectPosition.y && this.maxX >= objectPosition.x && this.maxY >= objectPosition.y) {
            return true;
        } else {
            return false;
        }
    }
}
