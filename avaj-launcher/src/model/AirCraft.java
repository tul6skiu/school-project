package model;

public abstract class AirCraft {
    protected long id;
    protected String name;
    protected Coordinate coordinate;
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
