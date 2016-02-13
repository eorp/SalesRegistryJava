package model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import view.Countries;
@Root
public class Address {
	//city
	@Element
	private String city;
	//street
	@Element
	private String street;
	//state
	@Element
	private String state;
	//zip code
	@Element
	private String zipCode;
	//country
	@Element
	private Countries country;
	//constructors
	public Address()
	{
		this("Cork", "Main Street", null);
	}
	
	
	public Address(String city, String street, String state) {
		this(city, street, state, null);
	}

	public Address(String city, String street, String state, String zipCode) {
		this(city, street, state, zipCode, Countries.Ireland);
	}

	public Address(String street, String city, String state, String zipCode,
			Countries country) {
		this.city = city;
		this.street = street;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
//getters ans setters
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	@Override
	public String toString() {
	
		return String.format("%-14s", InputUtility.removeUnderscores(country.toString().toUpperCase()));
				/*"Address [city=" + city + ", street=" + street + ", zipCode="
				+ zipCode + ", state=" + state + ", country=" + InputUtility.removeUnderscores(country.toString()) + "]";*/
	}
	
	

}
