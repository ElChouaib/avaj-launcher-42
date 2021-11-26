package dao.enumeration;

public enum AircraftType {
    HELICOPTER("Helicopter"),
    BALLOON("Balloon"),
    JETPLANE("Jetplane");

    private String name;

    AircraftType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
