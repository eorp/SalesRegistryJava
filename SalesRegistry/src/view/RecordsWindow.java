package view;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import model.Customer;
import model.InputUtility;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.joda.time.DateTime;
//import InputUtility;
/**
 * records window view class is an observer
 * @author Win8
 *
 */
public class RecordsWindow extends JFrame implements Observer {

	private JPanel contentPane, pCustomerDetails, pAddress, pProductDetails, pButtons;
	private JLabel lbName, lbSurname, lbEmail, lbStreet, lbCity, lbState;
	private JLabel lbZip, lbCountry, lbProduct, lbPrice, lbDate, lbExpDate, lbRenewed, lbDomains;
	private JTextField txtName, txtSurname, txtEmail, txtStreet, txtCity, txtState;
	private JTextField txtZip, txtPrice;
	private JComboBox<Countries> cmbCountry;
	private JComboBox<String> cmbProduct, cmbRenewed;
	private JButton btnOk, btnCancel;
	private JTextArea txtDomains;
	private UtilDateModel model= new UtilDateModel(), model2= new UtilDateModel();
	private JDatePanelImpl datePanel, datePanel2;
	private JDatePickerImpl date, expDate;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordsWindow frame = new RecordsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public RecordsWindow() {
		setTitle("Sales Details");
		initialiseGUI();
		//setUIFont(new FontUIResource(new Font("Cambria",Font.PLAIN, 18)));
		listAllComponentsIn(getContentPane());
		//setVisible(true);
	}
	
	private void initialiseGUI(){
		//setTitle("Sales Details");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(90, 40, 900, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout());
		
		
		//labels
		lbName = new JLabel("First Name:");
		lbSurname  = new JLabel("Surname:");
		lbEmail = new JLabel("Email:");
		lbStreet = new JLabel("Street:");
		lbCity = new JLabel("City:");
		lbState = new JLabel("State:");
		lbZip = new JLabel("Zip");
		lbCountry = new JLabel("Country");
		lbProduct = new JLabel("Name:");
		lbPrice = new JLabel("Price:");
		lbDate = new JLabel("Payment Date:");
		lbExpDate = new JLabel("Expiry Date:");
		lbRenewed = new JLabel("Renewed:");
		lbDomains = new JLabel("Domains:");
		
		initFields();
		JScrollPane scrollPane = new JScrollPane(txtDomains); 
		//txtDomains.setEditable(false);
			
		//buttons
		btnOk = new JButton("OK");
		/*btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
            	if(checkInput())
            		closeFrame();
            }
        });    
		*/
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
            	if(JOptionPane.showConfirmDialog(null, 
            			   "Are you sure you want to cancel this action?",
            			   "Confirm action", 
            			   JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
           closeFrame();
                //JOptionPane.showMessageDialog(contentPane, "Are you sure you want to cancel this action?",
                    //    "Confirm action", JOptionPane.YES_NO_OPTION);
            }
        });    
		
		//panels
		pCustomerDetails = new JPanel();
		pCustomerDetails.setBorder(BorderFactory.createTitledBorder("Customer Details"));
		pCustomerDetails.setLayout(new MigLayout());
		
		pProductDetails = new JPanel();
		pProductDetails.setBorder(BorderFactory.createTitledBorder("Product Details"));
		pProductDetails.setLayout(new MigLayout());
		
		pAddress = new JPanel();
		pAddress.setBorder(BorderFactory.createTitledBorder("Address"));
		pAddress.setLayout(new MigLayout());
		
		pButtons = new JPanel();
		
		
		pButtons.add(btnOk);
		pButtons.add(btnCancel);
		
		pCustomerDetails.add(lbName, "cell 0 0");
		pCustomerDetails.add(txtName, "cell 1 0");
		pCustomerDetails.add(lbSurname, "cell 0 1");
		pCustomerDetails.add(txtSurname, "cell 1 1");
		pCustomerDetails.add(lbEmail, "cell 0 2");
		pCustomerDetails.add(txtEmail, "cell 1 2");
		pCustomerDetails.add(pAddress, "cell 0 3, span");
		
		pAddress.add(lbStreet, "cell 0 0");
		pAddress.add(txtStreet, "cell 1 0, gapleft 15");
		pAddress.add(lbCity, "cell 0 1");
		pAddress.add(txtCity, "cell 1 1, gapleft 15");
		pAddress.add(lbState, "cell 0 2");
		pAddress.add(txtState, "cell 1 2, gapleft 15");
		pAddress.add(lbZip, "cell 0 3");
		pAddress.add(txtZip, "cell 1 3, gapleft 15");
		pAddress.add(lbCountry, "cell 0 4");
		pAddress.add(cmbCountry, "cell 1 4, gapleft 15");
		
		pProductDetails.add(lbProduct, "cell 0 0");
		pProductDetails.add(cmbProduct, "cell 1 0");
		pProductDetails.add(lbPrice, "cell 0 1");
		pProductDetails.add(txtPrice, "cell 1 1");
		pProductDetails.add(lbDate, "cell 0 2");
		pProductDetails.add(date, "cell 1 2, h 15");
		pProductDetails.add(lbExpDate, "cell 0 3");
		pProductDetails.add(expDate, "cell 1 3");
		pProductDetails.add(lbRenewed, "cell 0 4");
		pProductDetails.add(cmbRenewed, "cell 1 4");
		pProductDetails.add(lbDomains, "cell 0 5");
		pProductDetails.add(scrollPane, "cell 1 5");
		
		contentPane.add(pCustomerDetails, "cell 0 0");
		contentPane.add(pProductDetails, "cell 1 0");
		contentPane.add(pButtons, "cell 0 1, span, align center");
		
		
	}
	public void initFields(){
		//text fields
				txtName = new JTextField("");
				//txtName.setPreferredSize(new Dimension(300,10));
				txtSurname = new JTextField("");
				txtEmail = new JTextField("");
				txtStreet = new JTextField("");
				txtCity = new JTextField("");
				txtState = new JTextField("");
				txtZip = new JTextField("");
				txtPrice = new JTextField("2.99");
				txtPrice.setEditable(false);
				
				//combo boxes
				cmbCountry = new JComboBox(Countries.values());
				//populateCombobox2(cmbCountry,Countries.toArray());
				cmbCountry.setSelectedItem(Countries.USA);
				cmbCountry.addActionListener(new ActionListener() {
					 
				    @Override
				    public void actionPerformed(ActionEvent event) {
				        JComboBox<String> combo = (JComboBox<String>) event.getSource();
				       // String selected = (String) combo.getSelectedItem();
				        int choice =(int) combo.getSelectedIndex();
				        //System.out.println("item "+selected+", index: "+choice);
				        setPrice(choice);
				        //System.out.println("country "+cmbCountry.getSelectedIndex());
				    }
				});
				cmbProduct = new JComboBox<String>();
				populateCombobox(cmbProduct,ProductNames.toArray());
				
				cmbProduct.addActionListener(new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        JComboBox cmb = (JComboBox)e.getSource();
				    	setPrice(cmb.getSelectedIndex());
				    }
				});
			
				cmbRenewed = new JComboBox<String>(new String[]{"yes","no"});
				cmbRenewed.setSelectedIndex(1);
				
				//date picker
				
				model.setSelected(true);
				model2.setSelected(true);
				
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				p.put("text.font", new Font("Cambria",Font.PLAIN, 10));
				datePanel = new JDatePanelImpl(model, p);
				datePanel.setSize(new Dimension(300,10));
				date = new JDatePickerImpl(datePanel, new DateComponentFormatter());
				//monthPicker.setFont(new Font("Cambria",Font.PLAIN, 10));
				//UIManager.put("Label.font", labelFont);
				datePanel2 = new JDatePanelImpl(model2, p);
				expDate = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
				date.setPreferredSize(new Dimension(300,10));
				expDate.setPreferredSize(new Dimension(300,10));
				
				
				date.addActionListener(new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				    	model2.setValue(setExpiryDate((Date) date.getModel().getValue())); 
				    }
				});
				txtDomains = new JTextArea(5, 20);
				
			
	}
	private void populateCombobox(JComboBox<String> combo, String[] fill)
	{
		//String[] data = fill;
		for(int i=0; i<fill.length;i++)
			combo.addItem(fill[i]);
	}
	private void populateCombobox2(JComboBox combo, String[] fill)
	{
		//String[] data = fill;
		for(int i=0; i<fill.length;i++)
			combo.addItem(fill[i]);
	}
	//set font to all elements
		public void setUIFont(FontUIResource f) {
	        Enumeration<Object> keys = UIManager.getDefaults().keys();
	        while (keys.hasMoreElements()) {
	            Object key = keys.nextElement();
	            Object value = UIManager.get(key);
	            if (value instanceof FontUIResource) {
	                FontUIResource orig = (FontUIResource) value;
	                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
	                UIManager.put(key, new FontUIResource(font));
	            }
	        }
		}
		
		private Date setExpiryDate(Date date)
		{
			//get selected payment date
	    	Date selectedDate = date;
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(selectedDate);
	    	//add one year to that date
	    	cal.add(Calendar.YEAR, 1);
	    	
	    	return cal.getTime();
		}
		
		public void listAllComponentsIn(Container parent)
		{
		    for (Component c : parent.getComponents())
		    {
		        //System.out.println(c.toString());
		        	if ((Container)c instanceof JTextField) {
		        		//System.out.println(c.toString());
		        		if(!(c instanceof JFormattedTextField))
		        		   ((JTextField)c).setPreferredSize(new Dimension(300,10));
		        		   //((JFormattedTextField)c).setPreferredSize(new Dimension(300,10));
					        
				        }
		        	if(c instanceof JComboBox)
		        		((JComboBox)c).setPreferredSize(new Dimension(300,10));
		        	
		        	if(!(c instanceof org.jdatepicker.impl.JDatePickerImpl))
		        	{
		        		//System.out.println("found datepicker");
		        		//((org.jdatepicker.impl.JDatePickerImpl)c).setFont(new Font("Cambria",Font.PLAIN, 10));
		        		//c.setFont(new Font("Cambria",Font.PLAIN, 18));
		        	}
		            listAllComponentsIn((Container)c);
		    }
		}
		private void setPrice(int index){
			
			switch(index)
			{
			case 0: txtPrice.setText("2.99"); break;
			case 1: txtPrice.setText("9.99"); break; 
			case 2: txtPrice.setText("15.99"); break;
			case 3: txtPrice.setText("24.99"); break;
			
			}
		}
		
		public void closeFrame()
		{
			this.dispose();
		}
		
		
		private boolean readAndValidateName(String text, JTextField field)
		{
			if(InputUtility.isInValidString(text))
			{
				JOptionPane.showMessageDialog(contentPane, 
						"<html>Invalid input in the field!<br>Please enter a valid name (at least one character is required)</html>",
						"Invalid input!", 
						JOptionPane.OK_OPTION);
				field.setText("");
				field.grabFocus();;
			
			}
			else
				return true;
			return false;
		}
		
		private boolean readAndValidateEmail(String email)
		{
			if(!InputUtility.isValidEmail(email))
			{
				JOptionPane.showMessageDialog(contentPane, 
						"<html>Invalid email address</html>",
						"Invalid input!", 
						JOptionPane.OK_OPTION);
				txtEmail.setText("");
				txtEmail.grabFocus();
			}
			else
				return true;
			return false;
		}
	
		public boolean checkInput()
		{
			if(readAndValidateName(txtName.getText(),txtName) && readAndValidateName(txtSurname.getText(),txtSurname) && readAndValidateEmail(txtEmail.getText()))
				return true;
			return false;
		}
		
		/////get values for text fields
		public String getFirstName()
		{
			return txtName.getText();
		}
		
		public String getSurname()
		{
			return txtSurname.getText();
		}
		
		public String getEmail()
		{
			return txtEmail.getText();
		}
		
		public String getCity()
		{
			return txtCity.getText();
		}
		
		public String getStreet()
		{
			return txtStreet.getText();
		}
		
		public String getStates()
		{
			return txtState.getText();
		}
		
		public String getZip()
		{
			return txtZip.getText();
		}
		
		public Countries getCountry()
		{
			
			return (Countries)cmbCountry.getSelectedItem();
		}
		
		public String getProduct()
		{
			
			return cmbProduct.getSelectedItem().toString();
		}
		
		public String getRenewed()
		{
			
			return cmbRenewed.getSelectedItem().toString();
		}
		
		public String getPrice()
		{
			return txtPrice.getText();
		}
		
		public String getDomains()
		{
			return txtDomains.getText();
		}
		
		public DateTime getDate()
		{
			return new DateTime(date.getModel().getValue());
		}
		
		public DateTime getExpiryDate()
		{
			return new DateTime(expDate.getModel().getValue());
		}

		public JButton getBtnOk() {
			return btnOk;
		}
		
		
		public JComboBox<Countries> getCmbCountry() {
			return cmbCountry;
		}

		//adding controller
		public void addController(ActionListener controller){
			System.out.println("records      : adding controller");
			btnOk.addActionListener(controller);
			
		}
//called from the model
		@Override
		public void update(Observable obs, Object obj) {
			System.out.println("customer to edit update: "+obj);
			if(obj instanceof Customer)
			{
				txtName.setText(((Customer) obj).getFirstName());
				txtSurname.setText(((Customer) obj).getLastName());
				txtEmail.setText(((Customer) obj).getEmail());
				txtStreet.setText(((Customer) obj).getAddress().getStreet());
				txtCity.setText(((Customer) obj).getAddress().getCity());
				txtState.setText(((Customer) obj).getAddress().getState());
				txtZip.setText(((Customer) obj).getAddress().getZipCode());
				cmbCountry.setSelectedItem(((Customer) obj).getAddress().getCountry());
				cmbProduct.setSelectedItem(((Customer) obj).getProduct().getName());
				txtPrice.setText(String.valueOf(((Customer) obj).getProduct().getPrice()));
				model.setValue(((Customer) obj).getProduct().getPaymentDate().toDate());
				model2.setValue(((Customer) obj).getProduct().getExpiryDate().toDate());
				cmbRenewed.setSelectedItem(((Customer) obj).getProduct().getRenewed());
				txtDomains.setText(((Customer) obj).getProduct().getDomains());
				
			}
			
		}
}
