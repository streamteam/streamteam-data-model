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
 * Information about a statistics item.
 */
public interface StatisticsItemInfo {

    /**
     * Gets a unique inner key for storing information about the statistics item in a single value or history store.
     *
     * @return Unique inner key
     */
    public String getUniqueInnerKeyForStore();

    /**
     * Return the group identifier of the statistics item.
     *
     * @return Group identifier for the statistics item.
     */
    public String getGroupId();

    /**
     * Return the object identifier of the statistics item.
     * Null if the statistics item represents a group
     *
     * @return Object identifier for the statistics item or null if there is none.
     */
    public String getObjectId();
}
