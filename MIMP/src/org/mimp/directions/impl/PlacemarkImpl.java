package org.mimp.directions.impl;

import org.mimp.directions.Placemark;
import org.mimp.newimp.GeoPoint;

public class PlacemarkImpl implements Placemark {
    private GeoPoint location;
    private String instructions;
    private String distance;

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }
}
