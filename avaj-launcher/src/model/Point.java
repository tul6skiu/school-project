package model;

public enum Point {
    MAX_LONGITUDE("#1",100),
    MAX_LATITUDE("#2", 100),
    MAX_HEIGHT("#3",100);

    String trackCode;
    int point;

    public String getTrackCode() {
        return trackCode;
    }

    public int getPoint() {
        return point;
    }

    Point(String trackCode, int point) {
        this.trackCode = trackCode;
        this.point = point;
    }

    public boolean findAndCheckTrackCode(String item) {
        for (Point point: Point.values()) {
            if (point.trackCode.equals(item)) {return true;}
        }
        return false;
    }
}
