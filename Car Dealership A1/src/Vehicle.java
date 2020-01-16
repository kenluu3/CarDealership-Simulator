/*
 * Name: Ken Luu
 * ID: 500901583
*/

/*
 * Vehicle Superclass 
 */
public class Vehicle
{
    /*
     * Instance variables of Vehicle object. 
     */
    private String mfr;
    private String color;
    private int power;
    private int numWheels;
    private int VIN;
    
    /*
     * Constants for the power source of the Vehicle object.
     */
    public static final int ELETRIC_MOTOR = 0;
    public static final int GAS_ENGINE = 1; 

    /*
     * Constructor method to create Vehicle object.
     */
    public Vehicle(String mfr, String color, int power, int numWheels)
    {
      this.mfr = mfr;
      this.color = color;
      this.power = power;
      this.numWheels = numWheels;
      this.VIN = (int)(Math.random() * 400) + 100;
    }
    
    /*
     * Collection of get methods for each instance variable of the Vehicle.
     */
    /**
     * @return the manufacturer of the vehicle.
     */
    public String getMfr()
    {
      return mfr;
    }
    /**
     * @return the color of the vehicle.
     */
    public String getColor()
    {
      return color;
    }
    /**
     * @return the power source of the vehicle.
     */
    public int getPower()
    {
      return power;
    }
    /**
     * @return the number of wheels of the vehicle.
     */
    public int getNumWheels()
    {
      return numWheels;
    }
    /**
     * @return the VIN of the vehicle.
     */
    public int getVIN()
    {
    	return VIN;
    }

    /*
     * Collection of set methods for each instance variable of the Vehicle. 
     */
    /**
     * @param mfr manufacturer of the vehicle.
     */
    public void setMfr(String mfr)
    {
      this.mfr = mfr;
    }
    /**
     * @param color of the vehicle.
     */
    public void setColor(String color)
    {
      this.color = color;
    }
    /**
     * @param power source of the vehicle.
     */
    public void setPower(int power)
    {
      this.power = power;
    }
    /**
     * @param numWheels: The number of wheels of the vehicle.
     */
    public void setNumWheels(int numWheels)
    {
    	this.numWheels = numWheels;
    }

    /**
     * Compares this vehicle object with another object vehicle: Equal if the manufacturer, power, and number of wheels are equal.
     * @param other the vehicle to be compared.
     * @return true if equal, otherwise false. 
     */
    public boolean equals(Object other)
    {
      // Cast to vehicle object.
      Vehicle test = (Vehicle) other; 
      // If the checked fields are the same. 
      if (mfr.equals(test.mfr) && power == test.power && numWheels == test.numWheels)
      {
        return true;
      }
      // False otherwise.
      return false;
    }

    /**
     * Displays the manufacturer and color of the vehicle.
     */
    public String display()
    {
      return "VIN: " + VIN + " " + mfr + " " + color;
    }
}