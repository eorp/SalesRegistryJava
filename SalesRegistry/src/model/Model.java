package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.thoughtworks.xstream.XStream;

import view.Countries;


public class Model extends Observable {
	//customer object
	private Customer customer;
	//customer manager object
	private CustomerManager manager;
	//total number of customers
	private int totalCustomers = 0;
	//total sales
	private double totalSales=0.0;
	//file name for storing/retreiving the records
	private String file = "data.xml";
	//copy of customer records for selected month
	private List<Customer> copySortedByMonth;
	//copy of customer records for selected year
	private List<Customer> copySortedByYear;
	//variable to keep track whether records were sorted by certain time period to enable further sorting by other parameters
    //0-not sorted by time, 1- sorted by month,year, 2-sorted by year
	private int sortedByTime;
	
	public Model()
	{	//initialise objects
		customer = new Customer();
		manager = new CustomerManager();
		//initially records are not sorted
		sortedByTime = 0;
	}
	/**
	 * get customer object
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * set new customer object with data
	 * notify observers
	 * @param customer 
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(this.customer);
	}
	/**
	 * getter
	 * @return total number of customers
	 */
	public int getTotalCustomers() {
		return totalCustomers;
	}
	/**
	 * set total number of customers
	 * notify observers
	 * @param totalCustomers
	 */
	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(this.totalCustomers);
	
	}
	/**
	 * getter
	 * @return total sales
	 */
	public double getTotalSales() {
		return totalSales;
	}
	/**
	 * set total sales
	 * notify observers
	 * @param totalSales
	 */
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(this.totalSales);
	
	}
	/**
	 * 
	 * @return customer manager object
	 */
	public CustomerManager getManager() {
		return manager;
	}
	
	
	/**
	 * set sorting variable
	 * @param sortedByTime 0:not sorted, 1: sorted by month&year, 2: sorted by year
	 */
	public void setSortedByTime(int sortedByTime) {
		this.sortedByTime = sortedByTime;
	}
	/**
	 * 
	 * @return sorting variable
	 */
	public int getSortedByTime() {
		return sortedByTime;
	}
	
	/**
	 * 
	 * @return copy of customers list sorted by month/year
	 */
	public List<Customer> getCopySortedByMonth() {
		
		return copySortedByMonth;
	}
	/**
	 * 
	 * @return copy of customers list sorted by year
	 */
	public List<Customer> getCopySortedByYear() {
		return copySortedByYear;
	}
	/**
	 * get customer list whether sorted or in original order
	 * @param byTime sorting variable
	 * @return
	 */
	private List<Customer> customerList(int byTime)
	{
		List<Customer> copy = manager.getList();
		switch(byTime)
		{
		case 0: copy = manager.getList();break;
		case 1: copy = copySortedByMonth; break;
		case 2: copy = copySortedByYear; break;
		}
		return copy;
	}
	/**
	 * read data to create customer object
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 * @param name
	 * @param price
	 * @param paymentDate
	 * @param expiryDate
	 * @param renewed
	 * @param domains
	 * @param edit - true is editing the customer object, false - create a new customer
	 * @return new customer object
	 */
	public Customer readCustomer(String firstName, String lastName, String email,
			String street, String city, String state, String zipCode,
			Countries country, String name, double price, DateTime paymentDate,
			DateTime expiryDate, String renewed, String domains, boolean edit)
	{
		int id=0;
		//get id based on whether it is a new customer creation 
		//or modification of existing customer
		if(edit)
		{
			id = customer.getId();
		}
		else
			id = manager.getNewId();
		//create new address
		Address address = new Address(street, city, state, zipCode, (Countries)country);
		//create new product
		Product product = new Product(name, price, paymentDate, expiryDate, renewed, domains);
		//create new customer
		customer = new Customer(id, firstName, lastName, email,
				address, product);
		
		System.out.println("model, readcusstomer, product is "+product.toString()+" date is "+product.getPaymentDate());
		return customer;
	}
	/**
	 * add customer to the list
	 * @param c customer object
	 */
	public void addCustomer(Customer c)
	{
		manager.add(c);
		//reflect changes
		updateGUI();
	}
	/**
	 * get total number of customers and total sales
	 * @param copy list of customers
	 */
	private void getTotals(List<Customer> copy)
	{
		double price = 0.0;
		//loop through the list
		for(Customer c:copy)
		{//get total price
			price+=c.getProduct().getPrice();
		}
		//set total customers number and total sales values
		setTotalSales(price);
		setTotalCustomers(copy.size());
	}
	/**
	 * delete customer from the list
	 * @param index selected position
	 */
	public void deleteCustomer(int index)
	{
		//manager.setList(customerList(sortedByTime));
		//provided customer is selected
		if(manager.checkIndex(index))
		{//delete customer from the list
			manager.deleteAt(index);
			//reflect changes in UI
			updateGUI();
		}
	}
	/**
	 * get selected customer object
	 * @param index selected position
	 */
	public void showCustomer(int index)
	{
		//manager.setList(customerList(sortedByTime));
		//provided such customer exists
		if(manager.getAt(index)!=null)
		{//set customer object to a selected one
			setCustomer(manager.getAt(index));
			//set customers id
			customer.setId(manager.getAt(index).getId());
			
		}
	}
	/**
	 * edit customer object
	 * @param index selected position
	 * @param c customer object
	 */
	public void editCustomer(int index,Customer c)
	{
		//manager.setList(customerList(sortedByTime));
		manager.changeAt(c, index);
		updateGUI();
	}
	/**
	 * update changes
	 */
	private void updateGUI()
	{//save changed data to file
		saveToFile();
		//saveToFile2();
		saveXml();
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(manager.toStringArray());
	      //get totals for current customers list
		  getTotals(manager.getList());
	}
	
	public void saveToFile()
	{
		try{			
			manager.serialiseXML(file);
			//manager.serialiseXML("data2.xml");
			System.out.println("file saved ok");
		}
		catch(Exception e)
		{
			System.out.println("problem writing to file: "+e.toString());
		}
	}
	public void saveToFile2()
	{
		List<Product> product = new ArrayList<Product>(manager.getCount());
		//product
		List<Customer> cust = manager.getList();
		for(Customer c: cust)
			product.add(c.getProduct());
		
		System.out.println("product "+Arrays.toString(product.toArray()));
		try{			
			//manager.serialiseXML(file);
			InputUtility.serialiseToXML("product.xml", product);
			//manager.serialiseXML("data2.xml");
			System.out.println("product file saved ok");
		}
		catch(Exception e)
		{
			System.out.println("problem writing to product file: "+e.toString());
		}
	}
	//save data to xml file
	public void saveXml()
	{
		XStream xstream = new XStream();
		xstream.registerConverter(new JodaTimeConverter());
		CustomerManager m = getManager();
	//	System.out.println("xml "+Arrays.toString(m.toStringArray()));
		//xstream.autodetectAnnotations(true);
		String xml = xstream.toXML(m);
		System.out.println(xml);
	}
	/**
	 * read data from xml file
	 * @param file name
	 */
	public void readFromFile(String file)
	{
		//manager = new CustomerManager();
		try
		{
			manager.deserialiseXML(file);
			System.out.println("read "+Arrays.toString(manager.toStringArray()));
			// call setChanged before notifyObservers to indicate model has changed
		      setChanged();     
		   // notify Observers that model has changed 
		      notifyObservers(manager.toStringArray());
		      getTotals(manager.getList());
		}
		catch(Exception e)
		{
			System.out.println("problem reading from file: "+e.toString()+e.getMessage());
		}
		System.out.println("after reading "+Arrays.toString(manager.toStringArray()));
	}
	
	/**
	 * show sales for selected time period
	 * @param month month value, if 0, sort only by year
	 * @param y year value
	 */
	public void showSelectedSales(int month, int y)
	{
		List<Customer> copy = manager.getList(); 
		//System.out.println("month is "+month+" year is "+y);
		//show sales for selected year
		if(month<0)
		{//set appropriate sorting value
			sortedByTime = 2;
			//sort list by year provided
			copy = copy.stream().filter(c->c.getProduct().getPaymentDate().getYear()==y).collect(Collectors.toList());
			//assign sorted list to appropriate variable
			copySortedByYear = copy;
		}//show sales for selected month
		else
		{//set appropriate sorting value
			sortedByTime = 1;
			//sort list by provided month and year
			copy = copy.stream().filter(c->c.getProduct().getPaymentDate().getYear()==y && c.getProduct().getPaymentDate().getMonthOfYear()==(month+1)).collect(Collectors.toList());
			//assign sorted list to appropriate variable
			copySortedByMonth = copy;	
		}
		//System.out.println("filter "+Arrays.toString(manager.getSortedData(copy)));
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	     notifyObservers(manager.getSortedData(copy,0));
		  getTotals(copy);
		  //System.out.println("model, copy is "+copy);
	}
	/**
	 * show sales for selected time period
	 * @param month selected month
	 * @param y selected year
	 * @return string array of sorted customers list
	 */
	public String[] showSelectedSales2(int month, int y)
	{
		List<Customer> copy = manager.getList(); 
		//System.out.println("month is "+month+" year is "+y);
		//show sales for selected year
		if(month<0)
		{
			sortedByTime = 2;
			copy = copy.stream().filter(c->c.getProduct().getPaymentDate().getYear()==y).collect(Collectors.toList());
			copySortedByYear = copy;
			return manager.getSortedData(copySortedByYear,1);
		}//show sales for selected month
		else
		{
			sortedByTime = 1;
			copy = copy.stream().filter(c->c.getProduct().getPaymentDate().getYear()==y && c.getProduct().getPaymentDate().getMonthOfYear()==(month+1)).collect(Collectors.toList());
			copySortedByMonth = copy;	
			return manager.getSortedData(copySortedByMonth,1);
		}
	}
	//sort records by certain parameter
	public void sortBy(String value)
	{
		List<Customer> copySorted = customerList(sortedByTime);
		/*switch(sortedByTime)
		{
		case 0:copySorted = manager.getList();break;
		case 1: copySorted = copySortedByMonth; break;
		case 2: copySorted = copySortedByYear; break;
		}*/
		switch(value)
		{
		case "name": copySorted.sort(Comparator.comparing((Customer c) -> c.getLastName()).thenComparing((Customer c)->c.getFirstName()));
		break;
		case "id": copySorted.sort(Comparator.comparingInt((Customer c) -> c.getId()));
		break;
		case "price": copySorted.sort(Comparator.comparing((Customer c) -> c.getProduct().getPrice()));
		break;
		case "date": copySorted.sort(Comparator.comparing((Customer c) -> c.getProduct().getPaymentDate()));
		break;
		case "email": copySorted.sort(Comparator.comparing((Customer c) -> c.getEmail()));
		break;
		case "country": copySorted.sort(Comparator.comparing((Customer c) -> c.getAddress().getCountry()));
		break;
		}
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(manager.getSortedData(copySorted,0));
	    //  System.out.println("model, sorting, "+Arrays.toString(copySorted.toArray()));
	     // System.out.println("model, sorting, toarray to string "+copySorted.toArray().toString());
			 
	}
	/**
	 * show unsorted customers list
	 */
	public void showOriginal()
	{//set sorting value to unsorted
		sortedByTime = 0;
		List<Customer> copySorted = manager.getList();
		
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers(manager.getSortedData(copySorted,0));
		  getTotals(manager.getList());
		 // System.out.println("show original "+Arrays.toString(manager.toStringArray()));
	}
	/**
	 * generate invoice for selected customer record
	 * @param companyName
	 * @param companyContact
	 * @param index selected customer record
	 * @return invoice as string
	 */
	public String getInvoice(String companyName, String companyContact,int index)
	{
		String invoice = null;
		List<Customer> list = customerList(sortedByTime);
		/*invoice = companyName+""+companyContact+"\n\nInvoice number:"+(index+17485)+"/n/nTo:      "+list.get(index).getFullName()+
				"/n/nFees:     "+
				list.get(index).getProduct().getName()+
				"/n/n"+list.get(index).getProduct().getPrice()+
				"/n/nTotal:      "+list.get(index).getProduct().getPrice();*/
		invoice = String.format("%s%n%s%n%nInvoice number: %d%n%nTo:        %s%n%nFees for: %s%n%nTotal:  %.2f",companyName,companyContact,(index+17485),list.get(index).getFullName(),list.get(index).getProduct().getName(),list.get(index).getProduct().getPrice(),list.get(index).getProduct().getPrice() );
		return invoice;
	}
	
	
}
