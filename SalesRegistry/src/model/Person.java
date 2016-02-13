package model;

import java.util.Observable;

import org.simpleframework.xml.Element;

public class Person extends Observable {
	//first name
	@Element
	private String firstName;
	//last name
	@Element
	private String lastName;
	//email address
	@Element
	private String email;
	//address object
	@Element
	private Address address;
	//constructors
	public Person()
	{
		this("John", "Doe", "jdoe@gmail.com", new Address());
	}
	
	public Person(String firstName, String lastName, String email,
			Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
//gettera and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}
	
	public String getFullName() {
		return (getFirstName() + " "+getLastName()).toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}

	@Override
	public String toString() {
		return String.format("%-20s %1s %-30s", getFullName(),address.toString(), email);
				/*"Person [full name="+getFullName() + "email=" + email + ", address=" + address.toString()
				+ "]";*/
	}
	
	
	
	

}
