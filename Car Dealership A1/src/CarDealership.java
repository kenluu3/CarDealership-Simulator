/*
 * Name: Ken Luu
 * ID: 500901583
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * CarDealership Class
 */

  public class CarDealership 
  {
    /*
     * Instance variables for CarDealership object.
     */
    private ArrayList<Car> cars;
    private AccountingSystem system; // System containing all transactions.
    private SalesTeam team; // Team of sales persons.
    private boolean electricfilter = false;
    private boolean awdfilter = false;
    private boolean pricefilter = false;
    private int numCars;
    private double minPrice;
    private double maxPrice;

    /*
     * Constructor method for Cardealership.
     * Instance variable references the empty list.
     */
    public CarDealership()
    {
        ArrayList<Car> list = new ArrayList<Car>();
        numCars = 0;
        cars = new ArrayList<Car>(list);   
        //calendar = new GregorianCalendar();
        team = new SalesTeam();
        system = new AccountingSystem();
    }

    /*
     * Adds Cars to the list of cars.
     * @param newCars list of Car objects to be added.
     */
    public void addCars(ArrayList<Car> newCars)
    {
    	// Loop for each element in the Car list.
    	for (int i = 0; i < newCars.size(); i++)
    	{
    		// Adds each Car element to the list.
    		cars.add(newCars.get(i));
    		numCars++;
    	}
    }

    /**
     * Removes a Car from the list of cars.
     * @param VIN of the vehicle that is sold. 
     * @return A purchase receipt of the car.
     */
    public String buyCar(int VIN) 
    {
       // Checks to make sure if there are cars in the list.
    	if (cars.size() > 0)
    	{
    		// Searches for the VIN of the vehicle in the array list.
    		for (int i = 0; i < cars.size(); i++)
    		{
    			Car car = cars.get(i);
    			// If the VIN matches.
    			if (VIN == car.getVIN())
    			{
    				// Locating the Car object and removing it.
    				numCars--;
	    			cars.remove(i);
	    			// Retrieves a sales person.
	    			String salesperson = team.SelectedPerson();
	    			// Increment the sales for that sales person.
	    			team.addSales(salesperson);
	    			// Creating a random calendar date.
	    			Calendar calendar = new GregorianCalendar();
	    			int randomMonth = (int) (Math.random() * (Calendar.DECEMBER + 1));	
	    			calendar.set(Calendar.YEAR, 2019);
	    			calendar.set(Calendar.MONTH, randomMonth);
	    			int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    			int min = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	    			int randomDay = (int) (Math.random() * (max - min + 1) + min);
	    			calendar.set(Calendar.DAY_OF_MONTH,randomDay);
	    			// Adding the transaction to the system.
	    			String receipt = system.add(calendar, car, salesperson, Transaction.BUY, car.getPrice());
	    			return receipt;
    			}
    		}
    		// If the loop ever finishes without returning a receipt, then the VIN does not exist. A runtime exception will be thrown.
			throw new RuntimeException("This VIN does not exist.");
    	}
    	// If there are no Cars in the list.
    	return "No cars were found in inventory.";
    }

    /**
     * Returns a purchased car to the dealer.
     * @param id ID of the transaction.
     */
    public String returnCar(int id)
    {
    	// Find the transaction object if exists.
    	if (id > 0)
    	{
    		// reference retrieves the transaction receipt, if it does not exist, it would return null.
    		Transaction reference = system.getTransaction(id);
	    	// If the object exists.
	    	if (reference != null)
	    	{
	    		// Get the status of return, if true then returnable.
	    		Boolean status = system.checkStatus(reference.getTransactionID());
	    		if (status == true) 
	    		{
		    		// Generate a random return date that is after the given date.
		    		Calendar date = reference.getDate();
		    		int OldMonth = date.get(Calendar.MONTH);
		    		int max = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		    		int min = date.get(Calendar.DAY_OF_MONTH); // Minimum is the day of the purchase receipt.
		    		// Random day must be a day in a month after the purchased month.
		    		int randomDay = (int) (Math.random() * (max - min + 1) + min);
		    		// Create a new Calendar object.
		    		Calendar newCalendar = new GregorianCalendar(2019, OldMonth, randomDay);
		    		// Information to create the transaction receipt.
		    		Car car = reference.getCar();
		    		// Get a random sales person.
		    		String salesperson = team.SelectedPerson();
		    		double salePrice = reference.getSalePrice();
		    		// Add the car back to the list and create a transaction object for it.
		    		cars.add(reference.getCar());
		    		numCars++;
		    		String receipt = system.add(newCalendar, car, salesperson, Transaction.RETURN, salePrice);
		    		// Returns a receipt to the user.
		    		return receipt;
	    		}
	    	}
    	}
    	// If the transaction ID does not exist.
    	return "This transaction ID does not exist or the ID corresponds to a vehicle that has already been returned.";
    }

    /**
     * Displays inventory information.
     */
    public void displayInventory()
    {   
    	// If there are cars in inventory.
    	if (cars.size() > 0)
    	{
	        // Loops for each Car object in inventory.
	        for (int i = 0; i < cars.size(); i++)
	        {	
	            // Collects information of each car. 
	            Car car = cars.get(i);
	            // Check if all filters are set then go down the list. 
	            // All filters are set.
	            if (electricfilter == true && awdfilter == true && pricefilter == true)
	            {
	                // Check if its within price range, is AWD, and is electric.
	                if ((car.getPrice() >= minPrice && car.getPrice() <= maxPrice) && car.getAWD() == true && car.getPower() == Vehicle.ELETRIC_MOTOR)
	                {
	                    System.out.println(car.display());
	                }
	            }
	            // Combinations for Two Filters.
	            // Electric and AWD filters are set.
	            else if (electricfilter == true && awdfilter == true)
	            {
	                if (car.getPower() == Vehicle.ELETRIC_MOTOR && car.getAWD() == true)
	                {
	                	System.out.println(car.display());
	                }
	            }
	            // Electric and Price filters are set.
	            else if (electricfilter == true && pricefilter == true)
	            {
	                if ((car.getPrice() >= minPrice && car.getPrice() <= maxPrice) && car.getPower() == Vehicle.ELETRIC_MOTOR)
	                {
	                    System.out.println(car.display());
	                }
	            }
	            // AWD and Price filters are set.
	            else if (awdfilter == true && pricefilter == true)
	            {
	                if (car.getAWD() == true && (car.getPrice() >= minPrice && car.getPrice() <= maxPrice))
	                {
	                	System.out.println(car.display());
	                }
	            }
	            // Specific filters are on.
	            // AWD only.
	            else if (awdfilter == true)
	            {
	                if (car.getAWD() == true)
	                {
	                	System.out.println(car.display());
	                }
	            }
	            // Price only.
	            else if (pricefilter == true)
	            {
	                if (car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
	                {
	                	System.out.println(car.display());
	                }
	            }
	            // Electric only.
	            else if (electricfilter == true)
	            {
	                if (car.getPower() == Vehicle.ELETRIC_MOTOR)
	                {
	                	System.out.println(car.display());
	                }
	            }
	            // Default display if all filters are off.
	            else 
	            { 
	            	System.out.println(car.display());
	            }
	        }
    	}
    	else // Inform the user that there are no cars in the list.
    	{
    		System.out.println("There are no cars in inventory. Please add a list to be viewed.");
    	}
    }

    /*
     * Collection of methods to set the optional filters.
     */
    // Sets the Electric filter.
    public void filterByElectric()
    {
        electricfilter = true;
    }
    // Sets the AWD filter.
    public void filterByAWD()
    {
        awdfilter = true;
    }
    // Sets the Price filter.
    public void filterByPrice(double minPrice, double maxPrice)
    {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        pricefilter = true;
    }
    // Clears all the filers.
    public void FiltersClear()
    { 
        electricfilter = false;
        awdfilter = false;
        pricefilter = false; 
    }

    /*
     * Collection of sort methods by Price, Safety Rating, and Max Range.
     */
    // Sorting by Price using the Comparable Interface.
    public void sortByPrice()
    {
        Collections.sort(cars);
    }
    // Sorting by Safety Rating using Comparator Interface.
    public void sortBySafetyRating()
    {
        SafetyRatingComparator safetyRatingComparator = new SafetyRatingComparator();
        Collections.sort(cars, safetyRatingComparator);
    }
    // Sorting by Max Range using Comparator Interface.
    public void sortByMaxRange()
    {
        MaxRangeComparator maxRangeComparator = new MaxRangeComparator();
        Collections.sort(cars, maxRangeComparator);
    }

    /*
     * Comparable Interfaces For Safety Rating and Max Range.
     * @return -1 is greater than, 1 is less than, 0 is equal for descending order.
     * @param a Car 1 to be compared.
     * @param b Car 2 to be compared.
     */
    class MaxRangeComparator implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            // Less than condition.
            if (a.getMaxRange() > b.getMaxRange())
            {
                return -1;
            }
            // Greater than condition.
            if (a.getMaxRange() < b.getMaxRange())
            {
                return 1;
            }
            // Otherwise equal condition.
            return 0;
        }
    }
    class SafetyRatingComparator implements Comparator<Car>
    {
        public int compare(Car a, Car b)
        {
            // Less than condition.
            if (a.getSafetyRating() > b.getSafetyRating())
            {
                return -1;
            }
            // Greater than condition.
            if (a.getSafetyRating() < b.getSafetyRating())
            {
                return 1;
            }
            // Otherwise equal condition.
            return 0;           
        }
    }
    
    /**
     * @return Number of cars in inventory.
     */
    public int getNumCars()
    {
    	return numCars;
    }
    
    /**
     * @return Accounting system.
     */
    public AccountingSystem getSystem()
    {
    	return system;
    }
    
    /**
     * @return SalesTeam
     */
    public SalesTeam getTeam()
    {
    	return team;
    }
  }