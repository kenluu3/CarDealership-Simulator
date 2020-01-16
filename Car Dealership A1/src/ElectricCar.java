/*
 * Name: Ken Luu
 * ID: 500901583
 */

 /*
  * ElectricCar Subclass of Car.
  */
  public class ElectricCar extends Car
  {
    /*
     * Instance variables of Electric Car object.
     */
    private int rechargeTime;
    private String batteryType;
    
    /*
     * Constants for Car battery types.
     */
    public final static String LITHIUM = "Lithium";

    /*
     * Constructor method for Electric Car object.
     */
    public ElectricCar(String mfr, String color, int power, int model, double safetyRating, int maxRange, boolean AWD, double price, int rechargeTime)
    {
        super(mfr, color, power, model, safetyRating, maxRange, AWD, price);
        this.rechargeTime = rechargeTime;
        this.batteryType = LITHIUM;
    }

    /*
     * Collection of get methods for the Electric Car.
     */
    /**
     * @return The recharge time of the battery.
     */
    public int getrechargeTime()
    {
        return rechargeTime;
    }
    /**
     * @return The battery type of the car.
     */
    public String getbatteryType()
    {
        return batteryType;
    }

    /*
     * Collection of set methods for the Electric Car.
     */
    /**
     * @param rechargeTime The recharge time for the battery.
     */
    public void setrechargeTime(int rechargeTime)
    {
        this.rechargeTime = rechargeTime;
    }
    /**
     * @param batteryType The battery type of the electric car.
     */
    public void setbatteryType(String batteryType)
    {
        this.batteryType = batteryType;
    }
    /**
     * Displays information about the electric car.
     */
    public String display()
    {
        return super.display() + " EL," + " BAT: " + batteryType + " RCH: " + rechargeTime;
    }
  }