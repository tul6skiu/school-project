package model;

import javax.swing.*;


public enum  Type {
    BALOON("Baloon"),
    JET_PLANE("JetPlane"),
    HELICOPTER("Helicopter");

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private final String type;


}
