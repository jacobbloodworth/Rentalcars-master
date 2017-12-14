import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        VehicleOutput vehicleOutput = new VehicleOutput();
        List<Vehicle> vehicleList = vehicleOutput.getVehicleList();
        System.out.println(vehicleOutput.getPriceOutput(vehicleList));
        System.out.println(vehicleOutput.getSippOutput(vehicleList));
        System.out.println(vehicleOutput.getSupplierOutput(vehicleList));
        System.out.println(vehicleOutput.getScoreOutput(vehicleList));
    }
}
