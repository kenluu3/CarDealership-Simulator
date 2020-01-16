/*
 * Name: Ken Luu
 * ID: 500901583
*/

/*
 * Car Subclass of Vehicle.
*/

public class Car extends Vehicle implements Comparable<Car>
{
    /*
     * Instance variables of Car object. 
     */
    private int model;
    private int maxRange;
    private double safetyRating;
    private boolean AWD; // All wheel drive.
    private double price;

    /*
     * Constants to define the car model.
     */
    public static final int SEDAN = 0;
    public static final int SUV = 1;
    public static final int SPORTS = 2;
    public static final int MINIVAN = 3;
    public static final int NUMWHEELS = 4; // All cars have 4 wheels.
    /*
     * Constructor for Car object.
     */
    public Car(String mfr, String color, int power, int model, double safetyRating, int maxRange, boolean AWD, double price)
    {
        super(mfr, color, power, NUMWHEELS);
        this.model = model;
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }

    /**
     * Displays information about the car. 
     */
    public String display()
    {
    	String modelname = ConvertModel(model);
        return super.display() + " " + modelname + " " + price + "$ " + "SF " + safetyRating + " RNG: " + maxRange; 
    }

    /**
     * Compares two car objects.
     * @param other Car object to be compared.
     */
    public boolean equals(Object other)
    {
        // Cast other object as a Car object.
        Car test = (Car) other;
        // Check if mfr, power, and numWheels are equal.
        boolean check1 = super.equals(test);
        // Checking for model and AWD equality only if the superclass conditions were equal. 
        if (check1 == true)
        {
            if (model == test.model && AWD == test.AWD)
            {
                return true;
            }
        }
        // Returns false otherwise.
        return false;
    }

    /**
     * Compares the prices of two Cars, produces a descending order (Highest to Lowest).
     * @param other car object to be compared.
     * @return 1 if this price is greater than the other car, -1 if this price is less than the other car, 0 if price is equal.
     */
    public int compareTo(Car other)
    {
        // If price is less.
        if (price > other.price) 
        {
            return 1; 
        }
        // If price is more.
        if (price < other.price) 
        {
            return -1; 
        }
        // Returns 0 otherwise.
        return 0;
    }

    /*
     * Collection of get methods for each for instance variable of a Car.
     */
    /**
     * @return the model of the car.
     */
    public int getModel()
    {
        return model;
    }
    /**
     * @return the max range of the car.
     */
    public int getMaxRange()
    {
        return maxRange;
    }
    /**
     * @return the safety rating of the car.
     */
    public double getSafetyRating()
    {
        return safetyRating;
    }
    /**
     * @return true if the car is all-wheel drive, false otherwise.
     */
    public boolean getAWD()
    {
        return AWD;
    }
    /**
     * @return the price of the car.
     */
    public double getPrice()
    {
        return price;
    }
    
    /*
     * Collection of set methods for each instance variable of a Car.
     */
    /**
     * @param model of the car.
     */
    public void setModel(int model)
    {
        this.model = model;
    }
    /**
     * @param maxRange of the car.
     */
    public void setMaxRange(int maxRange)
    {
        this.maxRange = maxRange;
    }
    /**
     * @param safetyRating value of the car.
     */
    public void setSafetyRating(double safetyRating)
    {
        this.safetyRating = safetyRating;
    }
    /**
     * @param AWD true if all-wheel drive, false otherwise.
     */
    public void setAWD(boolean AWD)
    {
        this.AWD = AWD;
    }
    /**
     * @param price of the car.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    // Converting Model number for display.
    private String ConvertModel(int model)
    {
    	String Model;
    	if (model == Car.SEDAN)
    	{
    		Model = "SEDAN";
    	}
    	else if (model == Car.MINIVAN)
    	{
    		Model = "MINIVAN";
    	}
    	else if (model == Car.SPORTS)
    	{
    		Model = "SPORTS";
    	}
    	else 
    	{
    		Model = "SUV";
    	}
    	return Model;
    }
}