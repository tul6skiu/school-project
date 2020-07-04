package model;

import java.nio.file.LinkOption;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (type.equals(Type.BALOON.getType())) {
            return new Baloon(name, new Coordinate(longitude, latitude, height));
        } else if (type.equals(Type.JET_PLANE.getType())) {
            return new JetPlane(name, new Coordinate(longitude, latitude, height));
        } else if (type.equals(Type.HELICOPTER.getType())) {
            return new Helicopter(name, new Coordinate(longitude, latitude, height));
        } else
            throw new RuntimeException("This type + " + type + " is not known ");
    }
}
