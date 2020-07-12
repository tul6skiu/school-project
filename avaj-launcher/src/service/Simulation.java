package service;

import model.*;
import utils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Simulation {
    public static void main(String[] args) {
        List<Flyable> airCrafts = new ArrayList<>();
        WeatherTower weatherTower = new WeatherTower();
        String[] lines = new String[0];
        int countIterations;
        byte[] arr = "".getBytes();

        if (args.length != 1) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_COUNT_ARGUMENT));
        }

        try {
            Files.write(Paths.get("simulation.txt"), arr);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
        }

        try {
            lines = checkValidateFile(args[0]);
        } catch (IOException e) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_READ_FILE));
        }

        countIterations = Integer.parseInt(lines[0]);
        if (countIterations == 0) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.ILLEGAL_COUNT_ITERATION));
            return;
        }

        for (int i = 1; i < lines.length;i++) {
            Flyable flyable = checkingAndGetFlyableItem(lines[i]);
            airCrafts.add(flyable);
            System.out.println(lines[i]);
        }

        for (Flyable flyable: airCrafts) {
            try {
                flyable.registerTower(weatherTower);
            } catch (IOException e) {
                ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
            }
        }

        updateWeather(countIterations, weatherTower);

    }

    private static void updateWeather(int countIterations, WeatherTower weatherTower) {
        while (countIterations != 0) {
            weatherTower.changeWeather();
            countIterations--;
        }
    }

    private static Flyable checkingAndGetFlyableItem(String line) {
        String[] str = line.split(" ");
        String type = str[0];
        String name = str[1];
        Coordinate coordinate = null;
        try {
            checkingTypeAircraft(type);
            coordinate = checkCoordinateAndComposition(Integer.parseInt(str[2]),
                    Integer.parseInt(str[3]), Integer.parseInt(str[4]));
        }catch (CustomException e) {
            ErrorInterceptor.intercept(e);
        }
        return AircraftFactory.newAircraft(type, name, coordinate);
    }

    private static void checkingTypeAircraft(String type) throws CustomException {
        if (!type.equals(Type.BALOON.getType())
                && !type.equals(Type.JET_PLANE.getType())
                && !type.equals(Type.HELICOPTER.getType())) {
            throw new CustomException(CommonResponse.ILLEGAL_AIRCRAFT);
        }
    }

    private static Coordinate checkCoordinateAndComposition(int longitudeInput, int latitudeInput, int heightInput) {
        if (longitudeInput < 0 || latitudeInput < 0 || heightInput < 0) {
           throw new CustomException(CommonResponse.ILLEGAL_COORDINATES);
        }
          return reorderingValue(latitudeInput, latitudeInput, heightInput);
    }
    
    private static Coordinate reorderingValue(int longitude, int latitude, int height) {
        int newLongitude = 0;
        int newLatitude = 0;
        int newHeight  = 0;
        if (longitude > Point.MAX_LONGITUDE.getPoint()) 
            newLongitude = reorder(longitude, Point.MAX_LONGITUDE.getPoint(), Point.MAX_LONGITUDE.getTrackCode());
        if (latitude > Point.MAX_LATITUDE.getPoint()) 
            newLatitude = reorder(latitude, Point.MAX_LATITUDE.getPoint(), Point.MAX_LATITUDE.getTrackCode());
        if (height > Point.MAX_HEIGHT.getPoint())
            newHeight = reorder(height, Point.MAX_HEIGHT.getPoint(), Point.MAX_HEIGHT.getTrackCode());
        
        return constructCoordinate(newLongitude, newLatitude, newHeight);
        
    }

    private static Coordinate constructCoordinate(int l1, int l2, int l3) {
        return new Coordinate(l1, l2, l3);
    }

    private static int reorder(int currentPoint, int max, String string) {
        int randomCount;
        Random random;
        if (string.equals(Point.MAX_LONGITUDE.getTrackCode()))
            random = new Random(10);
        else if (string.equals(Point.MAX_LATITUDE.getTrackCode()))
            random = new Random(15);
        else
            random = new Random(13);

        while (currentPoint > max) {
             randomCount = random.nextInt();
             currentPoint = currentPoint - randomCount;
        }
        return currentPoint;
    }

    private static String[] checkValidateFile(String fileName) throws IOException {
       byte[] arr;
           arr = Files.readAllBytes(Paths.get(fileName));
           String text = Arrays.toString(arr);
           return text.split("\n");
    }
}
