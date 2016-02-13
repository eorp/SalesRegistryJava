package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class Customer extends Person implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	//customer id
	private int id;
	//product object associated with customer
	private Product product;
	//constructors
	public Customer()
	{
		this(0, new Product());
	}
	
	public Customer(int id, Product product) {
		super();
		this.id = id;
		this.product = product;
	}

	public Customer(int id, String firstName, String lastName, String email,
			Address address, Product product) {
		super(firstName, lastName, email, address);
		this.id = id;
		this.product = product;
	}
//getters ans setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}

	@Override
	public String toString() {
		return String.format("%-4s %-40s %30s", id,super.toString(),product.toString());
				/*"Customer [id=" + id + ", person=" + super.toString() + ", product"
				+ getProduct().toString() + "]";*/
	}
	
	

}
