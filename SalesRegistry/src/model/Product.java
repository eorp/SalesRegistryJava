package model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;
import org.simpleframework.xml.Element;

import view.ProductNames;
@XmlRootElement(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//product name
	private String name;
	//price
	private double price;
	//payment date
	private DateTime paymentDate;
	//payment expiry date
	private DateTime expiryDate;
	//domain renewed
	private String renewed;
	//domains
	private String domains;
	
	//constructors
	public Product() {
		this(InputUtility.removeUnderscores(ProductNames.only_one_domain.toString()),
				2.99,new DateTime(), new DateTime().plusYears(1), "no", null);
	}
	
	public Product(String name, double price, DateTime paymentDate,
			DateTime expiryDate, String renewed, String domains) {
		this.name = name;
		this.price = price;
		this.paymentDate = paymentDate;
		this.expiryDate = expiryDate;
		this.renewed = renewed;
		this.domains = domains;
	}
//variables getters ans setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getRenewed() {
		return renewed;
	}

	public void setRenewed(String renewed) {
		this.renewed = renewed;
	}

	public String getDomains() {
		return domains;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}

	public DateTime getExpiryDate() {
		return getPaymentDate().plusYears(1);
	}

	@Override
	public String toString() {
		return String.format("%5s %18s %18s %3s %-20s", price,
				InputUtility.dateTimeToString(paymentDate),InputUtility.dateTimeToString(getExpiryDate()),renewed,domains);
				/*"Product [name=" + name + ", price=" + price + ", paymentDate="
				+ InputUtility.dateToString(paymentDate) + ", expiryDate=" + InputUtility.dateToString(getExpiryDate()) + ", renewed="
				+ renewed + ", domains=" + domains + "]";*/
	}
	
	
	

}
