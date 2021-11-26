package dao.entity;

import dao.enumeration.Coordinate;

import java.util.HashMap;

/**
 * @Author Chouaib
 * @Date 24-11-2021
 * @Project : Avaj-launcher-42
 */
public class Aircraft {
    private static long idCounter = 0;
    // attributes

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.nextId();
    }

    private long nextId() {
        return (Aircraft.idCounter++);
    }

    // method allows to update the coordinates of the given aircraft based on weather and based on changes
    protected void updatePosition(String weather, HashMap<String, int[]> changes) {
        int updatedHeight = this.coordinates.getHeight() + changes.get(weather)[Coordinate.HEIGHT.ordinal()];
        // check if the new height depass the limit (100)
        updatedHeight = updatedHeight <= 100 ? updatedHeight : 100;
        this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes.get(weather)[Coordinate.LONGITUDE.ordinal()],
                this.coordinates.getLatitude() + changes.get(weather)[Coordinate.LATITUDE.ordinal()],
                updatedHeight
        );
    }

}
