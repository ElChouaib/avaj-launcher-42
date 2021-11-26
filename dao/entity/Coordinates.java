package dao.entity;

/**
 * @Author Chouaib
 * @Date 24-11-2021
 * @Project : Avaj-launcher-42
 */
public class Coordinates {
    // Attributes
    private int longitude;
    private int latitude;
    private int height;

    // constructors
    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    // Getters
    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

}
