public class Vehicle  {
    private String sipp;
    private String name;
    private float price;
    private String supplier;
    private float rating;
    private String carType;
    private String doors;
    private String transmission;
    private String fuel;
    private String airCon;
    private int breakdownScore;
    private float sumOfScores;

    public String getSipp() {
        return sipp;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public float getRating() {
        return rating;
    }

    public String getCarType() {
        return carType;
    }

    public String getDoors() {
        return doors;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public String getAirCon() {
        return airCon;
    }

    public int getBreakdownScore() {
        return breakdownScore;
    }

    public float getSumOfScores() {
        return sumOfScores;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setAirCon(String airCon) {
        this.airCon = airCon;
    }

    public void setBreakdownScore(int breakdownScore) {
        this.breakdownScore = breakdownScore;
    }

    public void setSumOfScores(float sumOfScores) {
        this.sumOfScores = sumOfScores;
    }

    /**
     * Uses the first SIPP letter to calculate the type of car
     * @return a String containing the type of car
     */
    public String calculateCarType() {
        char firstSipp = this.sipp.charAt(0);
        switch (firstSipp) {
            case 'M': return "Mini";
            case 'E': return "Economy";
            case 'C': return "Compact";
            case 'I': return "Intermediate";
            case 'S': return "Standard";
            case 'F': return "Full size";
            case 'P': return "Premium";
            case 'L': return "Luxury";
            case 'X': return "Special";
            default: return "Invalid car type";
        }
    }

    /**
     * Uses the second SIPP letter to calculate the number of doors
     * (also includes some more car type options)
     * @return a String containing the door/car type specification
     */
    public String calculateDoors() {
        char secondSipp = sipp.charAt(1);
        switch (secondSipp) {
            case 'B': return "2 doors";
            case 'C': return "4 doors";
            case 'D': return "5 doors";
            case 'W': return "Estate";
            case 'T': return "Convertible";
            case 'F': return "SUV";
            case 'P': return "Pick up";
            case 'V': return "Passenger Van";
            //Not included in the table, but some vehicles in the JSON have an X here, and
            //an online search said an X here can mean 'Special'.
            case 'X': return "Special";
            default: return "Invalid doors/car type";
        }
    }

    /**
     * Uses the third SIPP letter to calculate the transmission
     * @return a String containing the transmission type
     */
    public String calculateTransmission() {
        char thirdSipp = sipp.charAt(2);
        switch (thirdSipp) {
            case 'M': return "Manual";
            case 'A': return "Automatic";
            default: return "Invalid transmission";
        }
    }

    /**
     * Calculates the fuel type.
     * Only petrol cars exist in the JSON file but over SIPP letters for
     * fuel exist, so method is included here for extensibility.
     * @return a String containing the fuel type
     */
    public String calculateFuel() {
        char fourthSipp = sipp.charAt(3);
        switch (fourthSipp) {
            case 'N': return "Petrol";
            case 'R': return "Petrol";
            default: return "Invalid fuel type";
        }
    }

    /**
     * Calculates the presence of air con.
     * Shares a SIPP letter with fuel type.
     * @return a String containing the air con status.
     */
    public String calculateAirCon() {
        char fourthSipp = sipp.charAt(3);
        switch (fourthSipp) {
            case 'N': return "no AC";
            case 'R': return "AC";
            default: return "Invalid air con status";
        }
    }
}
