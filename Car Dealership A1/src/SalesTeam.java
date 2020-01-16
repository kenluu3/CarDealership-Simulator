/*
 * Name: Ken Luu
 * ID: 500901583
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Random;

/*
 * Sales Team Class
 */
public class SalesTeam 
{
	/*
	 * Instance variables for Sales Team class
	 */
	LinkedList<String> team;
	Map<String,Integer> sales;
	/**
	 * Constructor for the Sales Team object.
	 */
	public SalesTeam()
	{
		team = new LinkedList<String>();
		sales = new TreeMap<String,Integer>();
		// Fixed Sales team added.
		team.add("Ken");
		team.add("Steve");
		team.add("Jones");
		team.add("Parker");
		team.add("Tom");
	}
	
	/**
	 * @return String containing members of the sales team.
	 */
	public String toString()
	{
		ListIterator<String> iterator = team.listIterator();
		String names = "Team: ";
		// While iterator has names.
		while (iterator.hasNext())
		{
			names = names + iterator.next() + " ";
		}
		return names;
	}
	
	/**
	 * Adds to the number of sales of that person.
	 */
	public void addSales(String person)
	{
		int count = 0;
		// Check to see if the person is on the list for sales.
		if (sales.containsKey(person))
		{
			// Get the sales of the member on the team and increment.
			count = sales.get(person) + 1 ;
			// Update the map to store total sales..
			sales.put(person, count);
		}
		// If not then add them to the map.
		else 
		{
			// First sale is added then.
			count = 1;
			sales.put(person, count);
		}
	}
	
	/**
	 * @return Person with top sales on the team.
	 */
	public String TopSales()
	{
		// Make a set containing the name of the people.
		Set<String> person = sales.keySet();
		// Iterator for the set person.
		Iterator<String> iterator = person.iterator();
		int totalpeople = 1; // Determine how many employees are at top sales for display purposes.
		// IF ITERATOR IS NOT EMPTY: 
		if (iterator.hasNext())
		{
			// Store the first person in the placeholder.
			String tester = iterator.next();
			String placeholder = tester;
			int highest = sales.get(tester);
			// Get the person with top sales.
			while (iterator.hasNext())
			{
				String next = iterator.next();
				// Comparing the sale numbers. If its equal to, then display both. Highest remains the same.
				if (sales.get(next) == sales.get(tester))
				{
					placeholder = placeholder + " & " + next;
					totalpeople++;
				}
				// If it is greater, then overwrite.
				else if (sales.get(next) > sales.get(tester))
				{
					// replace the name.
					tester = next;
					placeholder = next;
					highest = sales.get(tester);
				}
			}
			// Return statement for more than one top sale person.
			if (totalpeople > 1) 
			{
				return "Sales Persons: " + placeholder + " Total Sales(respectively): " + highest;
			}
			// Default return for one top person.
			return "Sales Person: " + placeholder + " Total Sales: " + highest;
		}
		return "No sales were detected."; // Default return if no sales were recorded.
	}
	
	/**
	 * Randomly selects a salesperson.
	 * @return Name of selected sales person.
	 */
	public String SelectedPerson()
	{
		// Randomizer to select from 1 to 5.
		Random generator = new Random();
		int chosen = generator.nextInt(5);
		// Return the name of the chosen person.
		return team.get(chosen);
	}
	
}
