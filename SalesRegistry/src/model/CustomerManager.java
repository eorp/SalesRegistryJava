package model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * class t manipulate with customer records
 * @author Win8
 *
 */
@XmlRootElement(name="customers")
public class CustomerManager extends ListManager<Customer> {
	
	public CustomerManager()
	{
		super();
	}
	/**
	 * 
	 * @return new customer id
	 */
	public int getNewId()
	{
		return this.getCount()+150;
	}
	/**
	 * get sorted customer records
	 * @param sorted - list of sorted customer records
	 * @param print - 0:for display records, 1: for printing records
	 * @return string array of customer records
	 */
	public String[] getSortedData(List<Customer> sorted, int print)
	{
		String[] strArray = new String[sorted.size()];
		int index = 0;
		for(Customer value:sorted)
		{
			if(print==0)
			{
				strArray[index] = value.toString();
				//index++;
			}//output for printing (payment date+product price)
			else
			{
				strArray[index] = InputUtility.dateTimeToString(value.getProduct().getPaymentDate()) + "\t\t"+value.getProduct().getPrice();
			}
			index++;
		}
		return strArray;
	}

}
