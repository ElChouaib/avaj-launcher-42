package dao.entity;

import dao.entity.Aircrafts.Balloon;
import dao.entity.Aircrafts.Helicopter;
import dao.entity.Aircrafts.Jetplane;
import dao.enumeration.AircraftType;
import dao.model.Flyable;

/**
 * @Author Chouaib
 * @Date 26-11-2021
 * @Project : Avaj-launcher-42
 */
public class AircraftFactory {
    public static Flyable newAircraft(AircraftType type, String name, int longitude, int latitude, int height) {

        if ((longitude < 0) || (latitude < 0) || (height < 0)) {
            return null;
        }

        if (height > 100) {
            height = 100;
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.toString().equals("Helicopter")) {
            return new Helicopter(name, coordinates);
        } else if (type.toString().equals("Jetplane")) {
            return new Jetplane(name, coordinates);
        } else if (type.toString().equals("Balloon")) {
            return new Balloon(name, coordinates);
        }

        return null;
    }
}
