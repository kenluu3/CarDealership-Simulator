/*
 * Name: Ken Luu
 * ID 500901583
 */

/*
 * Car dealer ship simulator
 */

import java.io.File;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CarDealershipSimulator 
{
  public static void main(String[] args) 
  {
	/*
	 * Dealership Object and a list for Car objects.
	 */
	CarDealership dealer = new CarDealership();
	ArrayList<Car> Cars = new ArrayList<Car>();
	AccountingSystem system = dealer.getSystem();
	SalesTeam team = dealer.getTeam();
	
	// Checks validity of input based on length.
	boolean validinput = false;

	// Create a scanner object
	Scanner input = new Scanner(System.in);
	
	// Displaying possible Commands to the User:
	System.out.println("All Commands: ");
	System.out.println("L: Loads the current inventory of cars.");
	System.out.println("ADD: Adds the list of cars.");
	System.out.println("BUY 'VIN': Buys the car with the indicated VIN.");
	System.out.println("RET 'ID': Returns a car if the id provided is valid for return.");
	System.out.println("SPR(sort by price), SSR(sort by safety rating), SMR(sort by max range)");
	System.out.println("FPR(filter by price), FEL(filter by electric motor), FAW(filter by all-wheel drive), FCL(clear all filters)");
	System.out.println("SALES(Displays all transactions), SALES TEAM(Displays the members of the sales team)");
	System.out.println("SALES TOPSP(Displays the top sales person), SALES \"month\"(Takes an integer (0 being January) and displays the transactions for that month)");
	System.out.println("SALES STATS: Displays all the statistics of the transactions.");
	System.out.println("Q: Quit the program.");
	System.out.print("Please enter a command to be executed: ");
	
	/*
	 * User interaction with the program.
	 * Reads their input to determine the action to execute.
	 * Continues to loop if an input line has been detected, finishes otherwise.
	 */	
	String read = "";
	do 
	{
		// Store the input to a String object.
		read = input.nextLine();
		// Creating another Scanner object to read the String object.
		Scanner commandLine = new Scanner(read);
		// Read the String input command if the commandLine has a line and read does not equal "Q".
		if (commandLine.hasNextLine() && !read.equalsIgnoreCase("Q"))
		{
			// Count the words in the line to ensure correct input length for specific commands. i.e. ADD should == 1, BUY X == 2.
			int countwords = 0;
			// String tokenizer separates all the inputs for counting.
			StringTokenizer Check = new StringTokenizer(read);
			// Count to see number of inputs in a line.
			while (Check.hasMoreTokens())
			{
				Check.nextToken();
				countwords++;
			}
			String command = "";
			// Main command is the first set of text. 
			try 
			{
				command = commandLine.next();
			}
			catch (NoSuchElementException exception) // If only spaces were entered.
			{
				System.out.print("Empty spaces were detected. ");
			}
			// Checking the number of inputs to determine if it is a valid length for the specified command.
			if (countwords == 3)
			{
				// FPR X Y is the only command with three "inputs".
				if (command.equalsIgnoreCase("FPR"))
				{
					validinput = true;
				}
			}
			// Checking commands with two "inputs"
			else if (countwords == 2)
			{
				if (command.equalsIgnoreCase("BUY") || command.equalsIgnoreCase("RET") || command.equalsIgnoreCase("SALES"))
				{
					validinput = true;
				}
			}
			// all other commands have one input, and validity will be checked by inner if statements.
			else if (countwords == 1)
			{
				validinput = true;
			}
			// Checking the possible commands. Does nothing if a list has not been added to be viewed.
			if (validinput == true && !command.isEmpty()) 
			{
				// Other commands can only be utilized if the dealer object contains a list of Cars.
				// "ADD": Adds a list of Cars to the dealer object.
				if (command.equalsIgnoreCase("ADD"))
				{	
					/* 
					 * Adding Car Objects to the list of Cars. 
					 */
					try
					{
						// Create the File object to be read from.
						File file = new File("cars.txt");
						Scanner scan = new Scanner(file);
						// While the file still has input.
						while (scan.hasNextLine())
						{
							// Use another scanner to read the line into its separate words, and store into an array list.
							Scanner line = new Scanner(scan.nextLine());
							ArrayList<String> arguments = new ArrayList<String>();
							while (line.hasNext()) // Loop through the line while it has content.
							{
								// Each argument is added to an array list, will be cycled to create the car object.
								arguments.add(line.next());
							}
							// Using the array list and knowing what each index represents, creates the car object and adds to the list of cars.
							// index 2 determines the model --> Convert the String model into a integer constant.
							String model = arguments.get(2);
							int carmodel = 0;
							if (model.equalsIgnoreCase("SEDAN"))
							{
								carmodel = Car.SEDAN;
							}
							else if (model.equalsIgnoreCase("SUV"))
							{
								carmodel = Car.SUV;
							}
							else if (model.equalsIgnoreCase("SPORTS"))
							{
								carmodel = Car.SPORTS;
							}
							else // Otherwise MINIVAN by default if not one of the other three.
							{
								carmodel = Car.MINIVAN;
							}
							// index 3 determines the engine --> Convert the String engine into an integer.
							String engine = arguments.get(3);
							int power = 0;
							if (engine.equalsIgnoreCase("GAS_ENGINE"))
							{
								power = Vehicle.GAS_ENGINE;
							}
							else // Otherwise Electric Motor by default, only two types.
							{
								power = Vehicle.ELETRIC_MOTOR;
							}
							// index 6 determines if it is AWD --> Convert the String to a boolean.
							String AWD = arguments.get(6);
							boolean awdrive = false;
							if (AWD.equalsIgnoreCase("AWD"))
							{
								awdrive = true;
							}
							else
							{
								awdrive = false;
							}
							// Creating the Car object.
							// Identifying the size of the arguments array list tells is whether it is car or electric car (9 args).
							if (arguments.size() == 9)
							{
								Cars.add(new ElectricCar(arguments.get(0), arguments.get(1), power, carmodel, Double.parseDouble(arguments.get(4)), Integer.parseInt(arguments.get(5)), awdrive, Integer.parseInt(arguments.get(7)), Integer.parseInt(arguments.get(8))));
							}
							// Regular car has 8. 
							else if (arguments.size() == 8)
							{
								Cars.add(new Car(arguments.get(0), arguments.get(1), power, carmodel, Double.parseDouble(arguments.get(4)), Integer.parseInt(arguments.get(5)), awdrive, Integer.parseInt(arguments.get(7))));
							} // If Formats don't match an exception will be caught. 
							else // Arguments length does not match.
							{
								line.close();
								throw new IndexOutOfBoundsException();
							}
							line.close();
						} 
						scan.close();
						// Adds the Cars to the dealership object.
						dealer.addCars(Cars);
						System.out.println("The list of cars has been added.\n");
					}
					catch (IOException exception) // Handles all I/O related errors i.e. FileNotFound.
					{
						System.out.println(exception.getMessage());
					}
					catch (NumberFormatException exception) // Incorrect text format exception.
					{
						System.out.println("The file contents do not match the argument types for the car object.");
					}
					catch (IndexOutOfBoundsException exception) // File does not contain the right amount of length.
					{
						System.out.println("The contents in the file does not contain the right length of arguments for the car object.");
					}
				}
				// Accessible Commands because list of cars exists.
				// "L": Loads the inventory of Car objects.
				else if (command.equalsIgnoreCase("L"))
				{
					// If there are cars in inventory. Display the header.
					if (dealer.getNumCars() > 0)
					{
						System.out.println("Current Number of Cars in Inventory: " + dealer.getNumCars());
					}
					dealer.displayInventory();
					System.out.println();
				}
				// "BUY": Reads an integer number of a Car object that a user would purchase.
				// Car gets removed from inventory.
				else if (command.equalsIgnoreCase("BUY"))
				{
					// Must check that a valid integer is inputed.
					if (commandLine.hasNextInt())
					{
						try // Make sure the user inputs a proper VIN.
						{
							int VIN = commandLine.nextInt();
							System.out.println(dealer.buyCar(VIN) + "\n");			
						}
						catch (RuntimeException exception) // Invalid VIN Exception caught.
						{
							System.out.println(exception.getMessage());
						}
					}
					else
					{
						System.out.println("A non integer input was detected, please enter a number.\n");
					}
				}
				// "RET": Returns the last purchased Car. 
				else if (command.equalsIgnoreCase("RET"))
				{
					// Check if a car was bought i.e. not null.
					if (commandLine.hasNextInt())
					{
						int ID = commandLine.nextInt();
						System.out.println(dealer.returnCar(ID) + "\n");
					}
					else // tells the user they need to input a ID to be returned.
					{
						System.out.println("Please enter an ID to be returned.\n");
					}
				}
				// Sorting Algorithms:
				// "SPR": Sorts the list by price.
				else if (command.equalsIgnoreCase("SPR"))
				{
					dealer.sortByPrice();
					System.out.println("The list of cars has been sorted by price.\n");
				}
				// "SSR": Sorts the list by safety rating.
				else if (command.equalsIgnoreCase("SSR"))
				{
					dealer.sortBySafetyRating();
					System.out.println("The list of cars has been sorted by safety rating.\n");
				}
				// "SMR": Sorts the list by max range.
				else if (command.equalsIgnoreCase("SMR"))
				{
					dealer.sortByMaxRange();
					System.out.println("The list of cars has been sorted by max range.\n");
				}
				// Filter Algorithms:
				// "FPR": Filters the list by price.
				else if (command.equalsIgnoreCase("FPR"))
				{
					// Must check if they have inputed a min price and max price.
					// Checking the MIN value.
					if (commandLine.hasNextInt())
					{
						// Take the min price.
						int minPrice = commandLine.nextInt();
						// Checking the MAX value.
						if (commandLine.hasNextInt())
						{
							int maxPrice = commandLine.nextInt();
							if (maxPrice >= minPrice) // Checks to make sure max price is greater than min price.
							{
								dealer.filterByPrice(minPrice, maxPrice);
								System.out.println("The list of cars outside the price range have been filtered.\n");
							}
							else // Tells the user the range is invalid.
							{
								System.out.println("The maximum price was less than the minimum price. Please enter a proper range.");
							}
						}
						else
						{
							System.out.println("Invalid price for maximum price was detected.\n");
						}
					}
					else
					{
						System.out.println("Invalid minimum and maximum price was detected.\n");
					}
				}
				// "FEL": Filters out cars without electric motors.
				else if (command.equalsIgnoreCase("FEL"))
				{
					dealer.filterByElectric();
					System.out.println("Non-electric cars have been filtered.\n");
				}
				// "FAW": Filters out cars without AWD.
				else if (command.equalsIgnoreCase("FAW"))
				{
					dealer.filterByAWD();
					System.out.println("All non all-wheel drive cars have been filtered.\n");
				}
				// "FCL": Clear all filters.
				else if (command.equalsIgnoreCase("FCL"))
				{
					dealer.FiltersClear();
					System.out.println("All filters have been cleared.\n");
				}
				// "SALES": Prints all the transactions for the year.
				else if (command.equalsIgnoreCase("SALES"))
				{
					// Check if there is two inputs for a more specific sales command.
					// Second input being an integer for month for printing transactions for a month.
					if (commandLine.hasNextInt())
					{
						int month = commandLine.nextInt();
						// Range checking for month, 0 being January. 
						if (month >= 0 && month <= 11)
						{
							system.listMonthlyTransactions(month);
							System.out.println();
						}
						else
						{
							System.out.println("That month does not exist.\n");
						}
					}
					// Second input being a string.
					else if (commandLine.hasNext())
					{
						String command2 = commandLine.next();
						// Team: printing all the members on the sales team.
						if (command2.equalsIgnoreCase("TEAM"))
						{
							System.out.println(team + "\n");
						}
						// Top Sales: Sales person with most sales is displayed.
						else if (command2.equalsIgnoreCase("TOPSP"))
						{
							System.out.println(team.TopSales() + "\n");
						}
						// Stats: Prints all the statistics of sales.
						else if (command2.equalsIgnoreCase("STATS"))
						{
							System.out.println(system.getStatistics() + "\n");
						}
						else // Invalid command.
						{
							System.out.println("The following command is invalid.\n");
						}
					}
					// If only one input for "SALES", will call the display all transactions.
					else
					{
						system.listTransactions();
						System.out.println();
					}
				}
				// Invalid command checker.
				else
				{
					if (!command.equalsIgnoreCase("Q"))
					{
						System.out.println("The following command is invalid.\n");
					}
				}
			} // End of all possible commands.
			else
			{
				System.out.println("The following command is invalid.\n");
			}
			if (!command.equalsIgnoreCase("Q")) // Prompts the user for the next instruction.
			{
				System.out.print("Please enter a command to be executed(Q to quit): ");
			}
			// Close the commandLine scanner.
			commandLine.close();	
			// Reset the valid checker.
			validinput = false;
			// Take next input.
		}
		else if (read.isEmpty())// commandLine.hasNextLine() would yield no line thus detects the empty input.
		{
			System.out.println("Null input has been detected.\n");
			System.out.print("Please enter a command to be executed(Q to quit): ");
		}
	} while (!read.equalsIgnoreCase("Q") && input.hasNextLine()); // Continues to loop if the input is not empty (i.e. null) and the input is not "Q".
	// Closing the input scanner object.
	input.close(); 
	System.out.println("Terminating the program."); 
	return;
  }
}