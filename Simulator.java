import dao.Exception.ParsingException;
import dao.entity.AircraftFactory;
import dao.entity.WeatherTower;
import dao.enumeration.AircraftType;

import java.io.*;

/**
 * @Author Chouaib
 * @Date 26-11-2021
 * @Project : Avaj-launcher-42
 */
public class Simulator {
    private static int simulationCounter;
    private static BufferedReader bufferReader;
    private static String currentLine;
    private static WeatherTower tower;


    // this method allows to parse the file in params
    private static void parseFile(File file) throws ParsingException {
        try {
            bufferReader = new BufferedReader(new FileReader(file));
            simulationCounter = Integer.parseInt(bufferReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new ParsingException(e);
        }
    }

    // using the file parsed we register aircraft
    private static void registerAircrafts() throws ParsingException {
        try {
            String aircraftInfos[];
            AircraftType aircraftType;
            tower = new WeatherTower();
            while ((currentLine = bufferReader.readLine()) != null) {
                aircraftInfos = currentLine.split("\\s+");
                if (aircraftInfos[0].equalsIgnoreCase("Balloon") || aircraftInfos[0].equalsIgnoreCase("Baloon"))
                    aircraftType = AircraftType.valueOf("BALLOON");
                else {
                    try {
                        aircraftType = AircraftType.valueOf(aircraftInfos[0].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new ParsingException(e);
                    }

                }

                AircraftFactory.newAircraft(
                        aircraftType, // type of aircraft
                        aircraftInfos[1], // name of aircraft
                        Integer.parseInt(aircraftInfos[2]), //Longitude
                        Integer.parseInt(aircraftInfos[3]), //Latitude
                        Integer.parseInt(aircraftInfos[4])) // Height
                        .registerTower(tower);
            }
            bufferReader.close();
        } catch (IOException e) {
            throw new ParsingException(e);
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            return;
        }
        try {
            parseFile(new File(args[0]));
            PrintStream fileOut = new PrintStream("./simulation.txt");
            System.setOut(fileOut);
            registerAircrafts();
        } catch (ParsingException | FileNotFoundException e) {
            System.out.println("program throw exception-> " + e.getMessage());
            return;
        }

        while (simulationCounter-- > 0) {
            tower.changeWeather();
        }
    }
}
