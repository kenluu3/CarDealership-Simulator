/*
 * Name: Ken Luu
 * ID: 500901583
 */

import java.util.Calendar;
import java.text.SimpleDateFormat;
/*
 * Transaction Class
 */

public class Transaction 
{	
	// Instance variables for Transactions.
	private int transactionid;
	private String transactiontype;
	private Calendar date;
	private Car car;
	private String salesperson;
	private double saleprice;
	// Types of Transactions:
	public final static String BUY = "BUY";
	public final static String RETURN = "RETURN";
	
	/**
	 * Constructor for the Transaction object.
	 * @param date of the transaction.
	 * @param transactionid Transaction number.
	 * @param reference Car object.
	 * @param name of Sales Person.
	 * @param saleprice 
	 * @param transactiontype BUY or RETURN.
	 */
	public Transaction(Calendar date, int transactionid, Car car, String name, String transactiontype, double saleprice)
	{
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.car = car;
		this.salesperson = name;
		this.saleprice = saleprice;
		this.date = date;
	}
	/**
	 * @return Date of transaction.
	 */
	public String display()
	{
		SimpleDateFormat formatdate = new SimpleDateFormat("EEE MMM dd, yyyy");
		return "ID: " + transactionid + " " + formatdate.format(date.getTime()) + " Transaction: " + transactiontype + " " + "SalesPerson: " + salesperson + " " + car.display();
	}
	
	/*
	 * Collection of get methods.
	 */
	/**@return Date of the transaction.
	 * 
	 */
	public Calendar getDate()
	{
		return date;
	}
	
	/**
	 * @return The transaction ID.
	 */
	public int getTransactionID()
	{
		return transactionid;
	}
	/**
	 * @return reference to the vehicle.
	 */
	public Car getCar()
	{
		return car;
	}
	/**
	 * @return Sales person.
	 */
	public String getSalesPerson()
	{
		return salesperson;
	}
	/**
	 * @return Price of vehicle.
	 */
	public double getSalePrice()
	{
		return saleprice;
	}
	/**
	 * @return Transaction type.
	 */
	public String getTransactionType()
	{
		return transactiontype;
	}
}
