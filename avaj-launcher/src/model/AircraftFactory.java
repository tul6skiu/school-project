package model;

import java.nio.file.LinkOption;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, Coordinate coordinate) {
        if (type.equals(Type.BALOON.getType())) {
            return new Baloon(name, coordinate);
        } else if (type.equals(Type.JET_PLANE.getType())) {
            return new JetPlane(name, coordinate);
        } else if (type.equals(Type.HELICOPTER.getType())) {
            return new Helicopter(name, coordinate);
        } else
            throw new RuntimeException("This type + " + type + " is not known ");
    }
}
