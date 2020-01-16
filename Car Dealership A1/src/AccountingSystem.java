/*
 * Name: Ken Luu
 * ID: 500901583
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.text.DateFormatSymbols;

/*
 * Class Accounting System.
 */
public class AccountingSystem 
{
	private ArrayList<Transaction> transactions;
	private Map<Integer,Boolean> ReturnIDs; // Boolean to represent whether that id is returnable i.e. true == returnable.
	/**
	 * Constructor for the Accounting System.
	 */
	public AccountingSystem()
	{
		transactions = new ArrayList<Transaction>();
		ReturnIDs = new TreeMap<Integer,Boolean>(); 
	}
	
	/**
	 * Adds a transaction to the system.
	 * @param date of transaction.
	 * @param car Object that is either purchased or sold.
	 * @param salesPerson Name of seller.
	 * @param type Transaction type, either buy or return.
	 * @param salePrice of the car.
	 *@return A transaction.
	 */
	public String add(Calendar date, Car car, String salesPerson, String type, double salePrice)
	{
		// Generating a transaction ID.
		int max = 99;
		int min = 1;
		int id = (int) (Math.random() * (max - min + 1) + min); 
		// Add the transaction into the list.
		Transaction transaction = new Transaction(date, id, car, salesPerson, type, salePrice);
		// Adds the transaction receipt onto the list.
		transactions.add(transaction);
		// Map the ID onto the list of possible returnIDs, if the transaction type is BUY, set to true otherwise set to false.
		if (type.equals(Transaction.BUY))
		{
			ReturnIDs.put(id, true);
		}
		// Set the value for that id to false for a returned item.
		else
		{
			ReturnIDs.put(id, false);
		}
		// Display.
		return transaction.display();
	}
	
	/*
	 * Collection of get methods.
	 */
	
	/**
	 * @param id of the transaction.
	 * @return the transaction receipt.
	 */
	public Transaction getTransaction(int id)
	{
		// Check the size of the transaction history to make sure there is at least one transaction.
		if (transactions.size() > 0)
		{
			// Search for the transaction.
			for (int i = 0; i < transactions.size(); i++)
			{
				// Retrieve a transaction object.
				Transaction transaction = transactions.get(i);
				// Check if it matches the id.
				if (transaction.getTransactionID() == id)
				{
					return transaction;
				}
			}
		}
		// If no transactions were recorded.
		return null;
	}
	
	/**
	 * Displays all transactions for a given year.
	 */
	public void listTransactions()
	{
		// Check size of transactions list.
		if (transactions.size() > 0)
		{
			System.out.println("All transactions: ");
			// List all the transactions.
			for (int i = 0; i < transactions.size(); i++)
			{
				// Print all transactions.
				System.out.println(transactions.get(i).display());
			}
		}
		else // No transactions are found.
		{
			System.out.println("No transactions have been recorded.");
		}
	}
	
	/**
	 * @param month 
	 * Displays all transactions for a given month.
	 */
	public void listMonthlyTransactions(int month)
	{
		// Checking size of transactions list.
		if (transactions.size() > 0)
		{
			int numtrans = 0; // Check if there are transactions for that month.
			// Search all transaction objects for the specified month.
			for (int i = 0; i < transactions.size(); i++)
			{
				// Checking for correct month.
				int transactionmonth = transactions.get(i).getDate().get(Calendar.MONTH);
				if (transactionmonth == month)
				{
					numtrans++; // Incrementing means transactions have occurred.
					System.out.println(transactions.get(i).display());
				}
			}
			// If this is true, then no transactions for that month was found.
			if (numtrans == 0)
			{
				System.out.println("No transactions were found for that month.");
			}
		}
		else
		{
			System.out.println("No transactions have been found.");
		}
	}
	
	/**
	 * @return Statistics of the sales. 
	 */
	public String getStatistics()
	{
		// Checking size of transactions.
		if (transactions.size() > 0)
		{
			double totalsales = 0;
			int totalsold = 0;
			int totalreturns = 0;
			double average = 0;
			int highestsalesmonth = 0;
			int highestsales = 0;
			Map<Integer,Integer> SalesPerMonth = new TreeMap<Integer,Integer>();
			// Looping through to examine each transaction.
			for (int i = 0; i < transactions.size(); i++)
			{
				// retrieving the object.
				Transaction transaction = transactions.get(i);
				// Get the month.
				int month = transaction.getDate().get(Calendar.MONTH);
				// Retrieve the price of the transaction and add if the transaction is BUY.
				if (transaction.getTransactionType().equals(Transaction.BUY))
				{
					totalsales = totalsales + transaction.getSalePrice();
					// Increment total sold cars.
					totalsold++;
					// Calculating for Month.
					// If the Month does not exist in the map, we need to put it on the map.
					if (!SalesPerMonth.containsKey(month))
					{
						int sales = 1;
						SalesPerMonth.put(month,sales);
					}
					// Otherwise we increment the counter of sales and update.
					else
					{
						int sales = SalesPerMonth.get(month);
						// Incrementing sales counter.
						sales++;
						// Updating.
						SalesPerMonth.put(month,sales);
					}
				}
				// If transaction is RET, then subtract from total.
				else if (transaction.getTransactionType().equals(Transaction.RETURN))
				{
					totalsales = totalsales - transaction.getSalePrice();
					// Decrement total sold cars.
					totalsold--;
					// Incrementing return counter.
					totalreturns++;
					// Only decrement sales if it is mapped.
				}
			}
			// Calculating average sales per month in terms of $.
			average = totalsales / 12;
			// Determining month with top sales.
			Set<Integer> Months = SalesPerMonth.keySet();
			for (int next : Months)
			{
				// Looping to determine top month.
				int tester = SalesPerMonth.get(next);
				if (tester > highestsales)
				{
					highestsales = tester;
					highestsalesmonth = next;
				}
			}
			// Convert the integer to a month.
			String monthString = new DateFormatSymbols().getMonths()[highestsalesmonth];
			// Returning the statistics in a string.
			return "Total Sales: " + totalsales + " Total Sold: " + totalsold + " Average Sales: $" + (int) average + " Total Returned: " + totalreturns + " Best Month: " + monthString + " Cars Sold: " + highestsales;   
		}
		// If no transaction objects exist.
		return "No transactions were recorded.";
	}
	
	/**
	 * @param ID of transaction to check.
	 * @return True if the ID of a transaction can be returned, otherwise false.
	 */
	public Boolean checkStatus(int ID)
	{
		// Retrieve the status.
		boolean status = ReturnIDs.get(ID);
		// If the status is true, then set to false to prevent more returns.
		if (status == true)
		{
			ReturnIDs.put(ID, false);
		}
		// Returns the initial status.
		return status;
	}
}
