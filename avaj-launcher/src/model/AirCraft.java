package model;

public abstract class AirCraft {
    private long id;
    private String name;
    private Coordinate coordinate;
    private static long idCounter = 0;

    public AirCraft(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
        this.id = nextId();
    }

    //todo: возвращет текущие количество интераций уменьшая на 1
    private long nextId() {
       return ++idCounter;
    }
}
