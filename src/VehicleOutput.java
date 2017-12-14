import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VehicleOutput {
    private List<Vehicle> vehicleList;

    public VehicleOutput() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("vehicles.json"));
        JsonParser jsonParser = new JsonParser();
        //Creates a JSON array out of the JSON file
        JsonArray jsonArray = jsonParser.parse(bufferedReader).getAsJsonObject().getAsJsonObject("Search").getAsJsonArray("VehicleList");
        Type listType = new TypeToken<List<Vehicle>>(){}.getType();
        //Converts the JSON array to a list of Vehicle objects
        vehicleList = gson.fromJson(jsonArray, listType);
        //Calculates and sets the SIPP specifications
        for (Vehicle vehicle : vehicleList) {
            vehicle.setCarType(vehicle.calculateCarType());
            vehicle.setDoors(vehicle.calculateDoors());
            vehicle.setTransmission(vehicle.calculateTransmission());
            vehicle.setFuel(vehicle.calculateFuel());
            vehicle.setAirCon(vehicle.calculateAirCon());
        }
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public String getPriceOutput(List<Vehicle> vehicleList) {
        String priceOutput = "";
        //Sorts the Vehicle list in ascending order by price
        vehicleList.sort(Comparator.comparing(Vehicle::getPrice));
        //Builds a list of the car prices in the appropriate format
        for (int i = 0; i < vehicleList.size(); i++) {
            priceOutput = priceOutput + (i + 1) + ". " + vehicleList.get(i).getName() + " - " + vehicleList.get(i).getPrice() + "\n";
        }
        return priceOutput;
    }

    public String getSippOutput(List<Vehicle> vehicleList) {
        String sippOutput = "";
        //Builds the SIPP specifications in the appropriate format
        for (int i = 0; i < vehicleList.size(); i++) {
            sippOutput = sippOutput + (i + 1) + ". " + vehicleList.get(i).getName() + " - " + vehicleList.get(i).getSipp() +
                    " - " + vehicleList.get(i).getCarType() + " - " + vehicleList.get(i).getDoors() + " - " +
                    vehicleList.get(i).getTransmission() + " - " + vehicleList.get(i).getFuel() + " - " +
                    vehicleList.get(i).getAirCon() + "\n" ;
        }
        return sippOutput;
    }

    public String getSupplierOutput(List<Vehicle> vehicleList) {
        String supplierOutput = "";
        //Makes a set of the unique car types in the Vehicles list
        //Only six instead of the seven listed in the document. Could include the extra car types included in
        //the second SIPP letter, but then there would be more than seven.
        Set<String> carTypesSet = vehicleList.stream().map(Vehicle::getCarType).collect(Collectors.toSet());
        //Converts the set to an ArrayList for easy indexing
        ArrayList<String> carTypesArray = new ArrayList<>(carTypesSet);
        //Makes a temporary ArrayList for checking each car type
        ArrayList<Vehicle> tempArrayList = new ArrayList<>();
        //Makes another ArrayList for holding the highest rated cars
        ArrayList<Vehicle> maxArrayList = new ArrayList<>();
        //Finds the highest rated vehicle of each car type
        for (String aCarTypesArray : carTypesArray) {
            //Adds every vehicle of each car type to the temporary ArrayList
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getCarType().equals(aCarTypesArray)) {
                    tempArrayList.add(vehicle);
                }
            }
            //Gets the vehicle with the highest rating from the temporary ArrayList and adds it to the ArrayList
            maxArrayList.add(tempArrayList.stream().max(Comparator.comparing(Vehicle::getRating)).get());
            //Resets the temporary array for the next loop
            tempArrayList.clear();
        }
        //Sorts the max-rating ArrayList in descending order according to rating
        maxArrayList.sort(Comparator.comparing(Vehicle::getRating).reversed());
        //Prints a list of the car ratings in the appropriate format
        for (int i = 0; i < maxArrayList.size(); i++) {
            supplierOutput = supplierOutput + (i + 1) + ". " + maxArrayList.get(i).getName() + " - " + maxArrayList.get(i).getCarType() +
                    " - " + maxArrayList.get(i).getSupplier() + " - " + maxArrayList.get(i).getRating() + "\n";
        }
        return supplierOutput;
    }

    public String getScoreOutput(List<Vehicle> vehicleList) {
        String scoreOutput = "";
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getTransmission().equals("Manual"))
                vehicle.setBreakdownScore(vehicle.getBreakdownScore() + 1);
            if (vehicle.getTransmission().equals("Automatic"))
                vehicle.setBreakdownScore(vehicle.getBreakdownScore() + 5);
            if (vehicle.getAirCon().equals("AC"))
                vehicle.setBreakdownScore(vehicle.getBreakdownScore() + 2);
            //Combines breakdown score and supplier rating score to create a sum of scores for each vehicle
            vehicle.setSumOfScores(vehicle.getBreakdownScore() + vehicle.getRating());
        }
        //Sorts the vehicles list in descending order according to the sum of scores
        vehicleList.sort(Comparator.comparing(Vehicle::getSumOfScores).reversed());
        //Prints a list of the car scores in the appropriate format
        for (int i = 0; i < vehicleList.size(); i++) {
            scoreOutput = scoreOutput + (i + 1) + ". " + vehicleList.get(i).getName() + " - " + vehicleList.get(i).getBreakdownScore() +
                    " - " + vehicleList.get(i).getRating() + " - " + vehicleList.get(i).getSumOfScores() + "\n";
        }
        return scoreOutput;
    }
}
