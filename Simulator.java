import dao.Exception.ParsingException;
import dao.entity.AircraftFactory;
import dao.entity.WeatherTower;
import dao.enumeration.AircraftType;
import dao.service.LivensteinService;
import javafx.stage.PopupWindow.AnchorLocation;

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
            simulationCounter = Integer.parseInt(bufferReader.readLine().trim());
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
                aircraftInfos = currentLine.split("\\s+"); //  curent line
                aircraftType = getTypeOfAircraft(aircraftInfos[0]); // get the type of aircraft
                AircraftFactory.newAircraft(aircraftType,
                        aircraftInfos[1], // name of aircraft
                        Integer.parseInt(aircraftInfos[2]), // Longitude
                        Integer.parseInt(aircraftInfos[3]), // Latitude
                        Integer.parseInt(aircraftInfos[4])) // Height
                        .registerTower(tower);
            }
            bufferReader.close();
        } catch (IOException e) {
            throw new ParsingException(e);
        }
    }


    // This method allows to create output file , and point the Stream out to this file.
    private static void createOutputFile() throws ParsingException {
        PrintStream fileOut;
        try {
            fileOut = new PrintStream("./simulation.txt");
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {
           throw new ParsingException(e);
        }
            
    }

    // This method allows to get the type of an aircraft based on the string argument passed in params
    private static AircraftType getTypeOfAircraft(String stringType) throws ParsingException {
        if (stringType.equalsIgnoreCase("Balloon") || stringType.equalsIgnoreCase("Baloon"))
       return AircraftType.BALLOON;
         else {
            try {
             return AircraftType.valueOf(stringType.toUpperCase());
         } catch (IllegalArgumentException e) {
            if (LivensteinService.calculate(stringType.toLowerCase(),AircraftType.BALLOON.toString().toLowerCase()) < 3 )
                return AircraftType.BALLOON;
            else if (LivensteinService.calculate(stringType.toLowerCase(),AircraftType.HELICOPTER.toString().toLowerCase()) < 3 )
                return AircraftType.HELICOPTER;
            else if (LivensteinService.calculate(stringType.toLowerCase(),AircraftType.JETPLANE.toString().toLowerCase()) < 3 )
                return AircraftType.JETPLANE;
            else 
                throw new ParsingException(e);
         }
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide scenario file!");
            return;
        }
        try {
            parseFile(new File(args[0]));
            createOutputFile();
            registerAircrafts();
        } catch (ParsingException e) {
            System.out.println("program throw exception-> " + e.getMessage());
            return;
        }

        while (simulationCounter-- > 0) {
            tower.changeWeather();
        }
    }
}
