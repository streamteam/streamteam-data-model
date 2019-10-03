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

import java.util.Objects;

/**
 * Information about a group.
 */
public class GroupInfo implements StatisticsItemInfo {

    /**
     * Identifier of the group
     */
    private String groupId;

    /**
     * GroupInfo constructor.
     *
     * @param groupId Identifier of the group
     */
    public GroupInfo(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets null as a group has no object identifier.
     *
     * @return Null as a group has no object identifier
     */
    @Override
    public String getObjectId() {
        return null;
    }

    /**
     * Gets the identifier of the group.
     *
     * @return Identifier of the group
     */
    @Override
    public String getGroupId() {
        return this.groupId;
    }


    /**
     * Gets a unique inner key for storing information about the statistics item in a single value or history store.
     *
     * @return Unique inner key
     */
    @Override
    public String getUniqueInnerKeyForStore() {
        return this.groupId;
    }

    /**
     * Checks if the GroupInfo is equal to an object.
     *
     * @param o Object
     * @return True if the GroupInfo is equal to the object. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInfo that = (GroupInfo) o;
        return Objects.equals(this.groupId, that.groupId);
    }

    /**
     * Generates an hashCode for the GroupInfo.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.groupId);
    }
}
