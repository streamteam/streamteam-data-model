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

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Information about an object.
 */
public class ObjectInfo implements StatisticsItemInfo {

    /**
     * Comparator for sorting ObjectInfos regarding their distance to something
     */
    private static Comparator objectInfoWithDistanceComparator = new Comparator<ObjectInfoWithDistance>() {
        @Override
        public int compare(ObjectInfoWithDistance oiwd1, ObjectInfoWithDistance oiwd2) {
            if (oiwd1.distance > oiwd2.distance) {
                return 1;
            } else if (oiwd1.distance < oiwd2.distance) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    /**
     * Identifier of the object
     */
    private String objectId;

    /**
     * Identifier of the group the object belongs to
     */
    private String groupId;

    /**
     * Position of the object
     */
    private Geometry.Vector position;

    /**
     * Velocity of the object
     */
    private Geometry.Vector velocity;

    /**
     * Absolute velocity of the object
     */
    private Double velocityAbs;

    /**
     * ObjectInfo constructor.
     *
     * @param objectId    Identifier of the object
     * @param groupId     Identifier of the group the object belongs to
     * @param position    Position of the object
     * @param velocity    Velocity of the object
     * @param velocityAbs Absolute velocity of the object
     */
    public ObjectInfo(String objectId, String groupId, Geometry.Vector position, Geometry.Vector velocity, Double velocityAbs) {
        this.objectId = objectId;
        this.groupId = groupId;
        this.position = position;
        this.velocity = velocity;
        this.velocityAbs = velocityAbs;
    }

    /**
     * ObjectInfo constructor.
     *
     * @param objectId Identifier of the object
     * @param groupId  Identifier of the group the object belongs to
     * @param position Position of the object
     */
    public ObjectInfo(String objectId, String groupId, Geometry.Vector position) {
        this.objectId = objectId;
        this.groupId = groupId;
        this.position = position;
        this.velocity = null;
        this.velocityAbs = null;
    }

    /**
     * ObjectInfo constructor.
     *
     * @param objectId Identifier of the object
     * @param groupId  Identifier of the group the object belongs to
     */
    public ObjectInfo(String objectId, String groupId) {
        this.objectId = objectId;
        this.groupId = groupId;
        this.position = null;
        this.velocity = null;
        this.velocityAbs = null;
    }

    /**
     * Gets identifier of the object.
     *
     * @return Identifier of the object
     */
    @Override
    public String getObjectId() {
        return this.objectId;
    }

    /**
     * Gets identifier of the group the object belongs to.
     *
     * @return Identifier of the group the object belongs to
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
        return this.objectId;
    }

    /**
     * Gets the position of the object.
     *
     * @return Position of the object
     */
    public Geometry.Vector getPosition() {
        return this.position;
    }

    /**
     * Sets the position of the object.
     *
     * @param position Position of the object
     */
    public void setPosition(Geometry.Vector position) {
        this.position = position;
    }

    /**
     * Gets the velocity of the object.
     *
     * @return Velocity of the object
     */
    public Geometry.Vector getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of the object.
     *
     * @param velocity Velocity of the object
     */
    public void setVelocity(Geometry.Vector velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the absolute velocity of the object.
     *
     * @return Absolute velocity of the object
     */
    public Double getAbsoluteVelocity() {
        return this.velocityAbs;
    }

    /**
     * Sets the absolute velocity of the object.
     *
     * @param velocityAbs Absolute velocity of the object
     */
    public void setAbsoluteVelocity(double velocityAbs) {
        this.velocityAbs = velocityAbs;
    }

    /**
     * Checks if the ObjectInfo is equal to an object.
     *
     * @param o Object
     * @return True if the ObjectInfo is equal to the object. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectInfo that = (ObjectInfo) o;
        return Objects.equals(this.objectId, that.objectId) &&
                Objects.equals(this.groupId, that.groupId) &&
                Objects.equals(this.position, that.position) &&
                Objects.equals(this.velocity, that.velocity) &&
                Objects.equals(this.velocityAbs, that.velocityAbs);
    }

    /**
     * Generates an hashCode for the ObjectInfo.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.objectId, this.groupId, this.position, this.velocity, this.velocityAbs);
    }

    /**
     * Gets the nearest object from a list of objects if its distance is smaller than a maximum distance.
     *
     * @param objects List of objects
     * @param maxDist Maximum distance
     * @param ignoreZ True if the z axis should be ignored
     * @return Nearest object
     */
    public ObjectInfo getNearestObjectWithMaxDist(List<ObjectInfo> objects, double maxDist, boolean ignoreZ) {
        double distToNearestObject = Double.MAX_VALUE;
        ObjectInfo nearestObject = null;
        for (ObjectInfo curObject : objects) {
            double curDist;
            if (ignoreZ) {
                curDist = Geometry.distanceWithoutZ(this.position, curObject.position);
            } else {
                curDist = Geometry.distance(this.position, curObject.position);
            }
            if (curDist < distToNearestObject) {
                nearestObject = curObject;
                distToNearestObject = curDist;
            }
        }
        if (distToNearestObject <= maxDist) {
            return nearestObject;
        } else {
            return null;
        }
    }

    /**
     * Gets the nearest object from a list of objects.
     *
     * @param objects List of objects
     * @param ignoreZ True if the z axis should be ignored
     * @return Nearest object
     */
    public ObjectInfo getNearestObject(List<ObjectInfo> objects, boolean ignoreZ) {
        return getNearestObjectWithMaxDist(objects, Double.MAX_VALUE, ignoreZ);
    }

    /**
     * Gets a list containing the (maximum) num nearest objects from a list of objects whose distance is smaller than a maximum distance.
     * Note that the list may contain less than num objects if there are less objects whose distance is smaller than the given maximum distance.
     *
     * @param objects List of objects
     * @param num     Number of objects
     * @param maxDist Maximum distance
     * @param ignoreZ True if the z axis should be ignored
     * @return List of nearest object
     */
    public List<ObjectInfo> getNearestObjectsWithMaxDist(List<ObjectInfo> objects, int num, double maxDist, boolean ignoreZ) {
        List<ObjectInfoWithDistance> objectsWithDistance = new LinkedList<>();
        for (ObjectInfo curObject : objects) {
            double curDist;
            if (ignoreZ) {
                curDist = Geometry.distanceWithoutZ(this.position, curObject.position);
            } else {
                curDist = Geometry.distance(this.position, curObject.position);
            }
            objectsWithDistance.add(new ObjectInfoWithDistance(curObject, curDist));
        }

        objectsWithDistance.sort(objectInfoWithDistanceComparator);

        List<ObjectInfo> nearestObjects = new LinkedList<>();
        for (ObjectInfoWithDistance oiwd : objectsWithDistance) {
            if (oiwd.distance <= maxDist && nearestObjects.size() < num) {
                nearestObjects.add(oiwd.objectInfo);
            } else {
                break;
            }
        }

        if (nearestObjects.isEmpty()) {
            return null;
        } else {
            return nearestObjects;
        }
    }

    /**
     * Gets a list containing the num nearest objects from a list of objects.
     *
     * @param objects List of objects
     * @param num     Number of objects
     * @param ignoreZ True if the z axis should be ignored
     * @return List of nearest object
     */
    public List<ObjectInfo> getNearestObjects(List<ObjectInfo> objects, int num, boolean ignoreZ) {
        return getNearestObjectsWithMaxDist(objects, num, Double.MAX_VALUE, ignoreZ);
    }

    /**
     * Helper class for storing an ObjectInfo together with its distance to something.
     */
    private static class ObjectInfoWithDistance {

        /**
         * ObjectInfo
         */
        private final ObjectInfo objectInfo;

        /**
         * Distance
         */
        private final double distance;

        /**
         * ObjectInfoWithDistance constructor.
         *
         * @param objectInfo Object info
         * @param distance   Distance
         */
        private ObjectInfoWithDistance(ObjectInfo objectInfo, double distance) {
            this.objectInfo = objectInfo;
            this.distance = distance;
        }
    }
}
