package view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

import com.michaelbaranov.microba.calendar.DatePicker;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.Customer;
import model.CustomerManager;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame implements Observer {

	//private JFrame frame;
	//panels
	private JPanel pDisplayOptions;
	private JPanel pPrintingOptions;
	private JPanel pButtonsHolder;
	private JPanel pTotalDisplay, pRecordsDisplay;
	private JPanel pHeaders;
	private JPanel pSortButtons;
	//buttons
	private JButton btnPrintMonthReport;
	private JButton btnPrintYearReport;
	private JButton btnPreviewMonthReport;
	private JButton btnPreviewYearReport;
	private JButton btnPrintInvoice;
	private JButton btnPreviewInvoice;
	private JButton btnAddRecord;
	private JButton btnEditRecord;
	private JButton btnDeleteRecord;
	private JButton btnSortId;
	private JButton btnSortName;
	private JButton btnSortCountry;
	private JButton btnSortEmail;
	private JButton btnSortPrice;
	private JButton btnSortDate;
	private JButton btnReset;
	//labels
	private JLabel lbMonth;
	private JLabel lbYear;
	private JLabel lbTotalCustomersTxt;
	private JLabel lbTotalSalesTxt;
	private JLabel lbTotalCustomers;
	private JLabel lbTotalSales;
	private JLabel lbId;
	private JLabel lbFullname;
	private JLabel lbEmail;
	private JLabel lbCountry;
	private JLabel lbPrice;
	private JLabel lbDate;
	private JLabel lbExpiry;
	private JLabel lbRenewed;
	private JLabel lbDomains;
	//menu
	private JMenu fileMenu, sortMenu, aboutMenu, printSubmenu;
	private JMenuBar menuBar;
	private JMenuItem printInvoiceMenu;
	private JMenuItem printMonthlyReportMenu;
	private JMenuItem printYearlyReportMenu;
	private JMenuItem sortCountryMenu;
	private JMenuItem sortEmailMenu;
	private JMenuItem sortNameMenu;
	private JMenuItem sortDateMenu;
	private JMenuItem sortPriceMenu;
	private JMenuItem aboutAppMenu;
	
	private JList lsDisplay;
	private DefaultListModel<String> modelList;
	
	private UtilDateModel model = new UtilDateModel();
	private UtilDateModel model2 = new UtilDateModel();
	private JDatePanelImpl datePanel, datePanel2;
	private JDatePickerImpl monthPicker, yearPicker;
	private UtilCalendarModel cal = new UtilCalendarModel();
	
	private JScrollPane scrollPane;
	private final DatePicker myPicker = new DatePicker(new Date());

	/**
	 * Launch the application.
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		//manager = new CustomerManager();
		//Customer c = new Customer();
		//System.out.println(Arrays.toString(c.toString());
		
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setUIFont(new FontUIResource(new Font("Cambria",Font.PLAIN, 18)));
		setTitle("Sales Records");
		//frame.getContentPane().setFont(new Font("Cambria", Font.BOLD, 13));
		//frame.setFont(new Font("Cambria", Font.BOLD, 12));
		setBounds(0, 0, 1380, 685);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JPanel panel = new JPanel();
		//frame.getContentPane().add(panel);
		
		//panels
		pDisplayOptions = new JPanel();
		pDisplayOptions.setBorder(BorderFactory.createTitledBorder("Display Options"));
		
		pPrintingOptions = new JPanel();
		pPrintingOptions.setBorder(BorderFactory.createTitledBorder("Printing Options"));
		//pPrintingOptions.setPreferredSize(new Dimension(843,178));
		pButtonsHolder = new JPanel();
		pTotalDisplay = new JPanel();
		pRecordsDisplay = new JPanel();
		pSortButtons = new JPanel();
		pHeaders = new JPanel();
		//buttons
		btnPrintMonthReport = new JButton("<html>Print Sales Report<br> for Selected Month</html>");
		btnPrintYearReport = new JButton("<html>Print Sales Report<br> for Selected Year</html>");
		btnPreviewMonthReport = new JButton("<html>Preview Sales Report<br> for Selected Month</html>");
		btnPreviewYearReport = new JButton("<html>Preview Sales Report<br> for Selected Year</html>");
		btnPrintInvoice = new JButton("<html>Print<br>Invoice</html>");
		btnPreviewInvoice = new JButton("<html>Preview<br>Invoice</html>");
		btnAddRecord = new JButton("Add Record");
		btnEditRecord = new JButton("Edit Record");
		btnDeleteRecord = new JButton("Delete Record");
		
		btnSortId = new JButton();
		btnSortId.setToolTipText("Sort by ID");
		btnSortName = new JButton();
		btnSortName.setToolTipText("Sort by name");
		btnSortCountry = new JButton();
		btnSortCountry.setToolTipText("Sort by country");
		btnSortEmail = new JButton();
		btnSortEmail.setToolTipText("Sort by email");
		btnSortPrice = new JButton();
		btnSortPrice.setToolTipText("Sort by price");
		btnSortDate = new JButton();
		btnSortDate.setToolTipText("Sort by payment date");
		btnReset = new JButton();
		btnReset.setToolTipText("Reset to original order");
		btnReset.setVisible(false);
		//add images to buttons
		addImage(btnSortId,"a_refresh.png");
		addImage(btnSortName,"a_sort.png");
		addImage(btnSortCountry,"a_sort.png");
		addImage(btnSortEmail,"a_sort.png");
		addImage(btnSortPrice,"a_sort.png");
		addImage(btnSortDate,"a_sort.png");
		addImage(btnAddRecord,"b_add.png");
		addImage(btnEditRecord,"b_edit.png");
		addImage(btnDeleteRecord,"b_delete.png");
		addImage(btnReset,"a_refresh_a.png");
		
		addImage(btnPrintMonthReport,"c_print.png");
		addImage(btnPrintYearReport,"c_print.png");
		addImage(btnPrintInvoice,"c_print.png");
		addImage(btnPreviewMonthReport,"c_preview.png");
		addImage(btnPreviewYearReport,"c_preview.png");
		addImage(btnPreviewInvoice,"c_preview.png");
		
		
		//labels
		lbMonth = new JLabel("Show Sales for the month:");
		lbYear = new JLabel("Show Sales for the year:");
		lbTotalCustomersTxt = new JLabel("Total Number of Customers:");
		lbTotalSalesTxt = new JLabel("Total Number of Sales:");
		lbTotalCustomers = new JLabel("0");
		lbTotalSales = new JLabel("0");
		lbId = new JLabel("ID");
		lbFullname = new JLabel("Full Name");
		lbCountry = new JLabel("Country");
		lbEmail = new JLabel("Email");
		lbPrice = new JLabel("Price");
		lbDate = new JLabel("Payment Date");
		lbExpiry = new JLabel("Expiry Date");
		lbDomains = new JLabel("Domains");
		lbRenewed = new JLabel("Renewed");
		
		model.setSelected(true);
		model2.setSelected(true);
		//model.se
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		monthPicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		monthPicker.setFont(new Font("Cambria",Font.PLAIN, 10));
		//DatePicker monthPicker = new JDatePicker();
		datePanel2 = new JDatePanelImpl(model2, p);
		yearPicker = new JDatePickerImpl(datePanel2, new DateLabelFormatter2());
		//yearPicker.addp
		
		myPicker.setFieldEditable(false);
		myPicker.getComponent(0).setFont(new Font("Cambria",Font.PLAIN, 18));
		
		//menu
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		printSubmenu = new JMenu("Print");
		sortMenu = new JMenu("Sort");
		aboutMenu = new JMenu("About");
		
		printInvoiceMenu = new JMenuItem("Invoice");
		printInvoiceMenu.setMnemonic('I');
		printMonthlyReportMenu = new JMenuItem("Sales Report for Selected Month");
		printMonthlyReportMenu.setMnemonic('M');
		printYearlyReportMenu = new JMenuItem("Sales Report for Selected Year");
		printYearlyReportMenu.setMnemonic('Y');
		sortCountryMenu = new JMenuItem("by Country");
		sortCountryMenu.setMnemonic('C');
		sortEmailMenu = new JMenuItem("by Email");
		sortEmailMenu.setMnemonic('E');
		sortNameMenu = new JMenuItem("by Full Name");
		sortNameMenu.setMnemonic('N');
		sortDateMenu = new JMenuItem("by Payment Date");
		sortDateMenu.setMnemonic('D');
		sortPriceMenu = new JMenuItem("by Product Price");
		sortPriceMenu.setMnemonic('P');
		aboutAppMenu = new JMenuItem("About..");
		aboutAppMenu.setMnemonic('A');
		
		menuBar.add(fileMenu);
		menuBar.add(sortMenu);
		menuBar.add(aboutMenu);
	
		fileMenu.add(printSubmenu);
		printSubmenu.add(printInvoiceMenu);
		printSubmenu.add(printMonthlyReportMenu);
		printSubmenu.add(printYearlyReportMenu);
		
		sortMenu.add(sortCountryMenu);
		sortMenu.add(sortEmailMenu);
		sortMenu.add(sortNameMenu);
		sortMenu.add(sortDateMenu);
		sortMenu.add(sortPriceMenu);
		
		aboutMenu.add(aboutAppMenu);
		
		modelList = new DefaultListModel();
		lsDisplay = new JList(modelList);
		

		
		scrollPane = new JScrollPane(lsDisplay);
		//JScrollPane scrollPane = new JScrollPane(recordsTable);
		
		////////////////////
		pDisplayOptions.setLayout(new MigLayout());
		
		pDisplayOptions.add(lbMonth, "cell 0 0");
		pDisplayOptions.add(monthPicker, "cell 1 0, w 250");
		pDisplayOptions.add(lbYear, "cell 0 1, gaptop 25");
		pDisplayOptions.add(yearPicker, "cell 1 1, w 250, gaptop 25");
		
		pPrintingOptions.setLayout(new MigLayout());
		
		pPrintingOptions.add(btnPrintMonthReport, "cell 0 0, w 253, gapright 15, gapleft 12");
		pPrintingOptions.add(btnPrintYearReport, "cell 1 0, w 253, gapright 15");
		pPrintingOptions.add(btnPrintInvoice, "cell 2 0, w 253");
		//pPrintingOptions.add(btnPreviewMonthReport, "cell 0 1, w 253, gapright 15, gapleft 12");
		//pPrintingOptions.add(btnPreviewYearReport, "cell 1 1, w 253, gapright 15");
		//pPrintingOptions.add(btnPreviewInvoice, "cell 2 1, w 253");
		
		pButtonsHolder.add(btnAddRecord);
		pButtonsHolder.add(btnEditRecord);
		pButtonsHolder.add(btnDeleteRecord);
		
		pTotalDisplay.setLayout(new MigLayout());
		pTotalDisplay.add(lbTotalCustomersTxt, "cell 0 0");
		pTotalDisplay.add(lbTotalCustomers, "cell 1 0, w 10, gapleft 30, gapright 50");
		pTotalDisplay.add(lbTotalSalesTxt, "cell 2 0");
		pTotalDisplay.add(lbTotalSales, "cell 3 0, w 10, gapleft 10");
		
		pRecordsDisplay.setLayout(new MigLayout());
		pRecordsDisplay.add(scrollPane, "cell 0 0, span, w 1300");
		
		pSortButtons.setLayout(new MigLayout());
		pSortButtons.add(btnSortId, "cell 0 0, gapright 5");
		pSortButtons.add(btnSortName, "cell 1 0, gapright 85");
		pSortButtons.add(btnSortCountry, "cell 2 0, gapright 45");
		pSortButtons.add(btnSortEmail, "cell 3 0, gapright 170");
		pSortButtons.add(btnSortPrice, "cell 4 0, gapright 40");
		pSortButtons.add(btnSortDate, "cell 5 0, gapright 400");
		pSortButtons.add(btnReset, "cell 6 0, align right");
		
		pHeaders.setLayout(new MigLayout());
		pHeaders.add(lbId, "cell 0 0, gapright 17");
		pHeaders.add(lbFullname, "cell 1 0, gapright 80");
		pHeaders.add(lbCountry, "cell 2 0, gapright 50");
		pHeaders.add(lbEmail, "cell 3 0, gapright 190");
		pHeaders.add(lbPrice, "cell 4 0, gapright 30");
		pHeaders.add(lbDate, "cell 5 0, gapright 40");
		pHeaders.add(lbExpiry, "cell 6 0, gapright 20");
		pHeaders.add(lbRenewed, "cell 7 0, gapright 10");
		pHeaders.add(lbDomains, "cell 8 0");
		
		setJMenuBar(menuBar);
		getContentPane().setLayout(new MigLayout());
		
		getContentPane().add(pDisplayOptions, "cell 0 0, h 150");
		getContentPane().add(pPrintingOptions, "cell 1 0, w 843, h 150");
		getContentPane().add(pSortButtons, "cell 0 1, span, align center, w 1400");
		getContentPane().add(pHeaders, "cell 0 2, span, align center, w 1300");
		getContentPane().add(pRecordsDisplay, "cell 0 3, span, align center, w 1300");
		getContentPane().add(pTotalDisplay, "cell 0 4, span, align center");
		getContentPane().add(pButtonsHolder, "cell 0 5, span, align center");
		
		
		
		
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
                UIManager.put("List.font", new Font("Courier New",Font.PLAIN, 14));
                UIManager.put("DatePicker.font", new Font("Courier New",Font.PLAIN, 14));
            }
        }
	}

	@Override
	public void update(Observable obs, Object obj) {
		//System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());
		//System.out.println("update! customer changed "+obj);
		//System.out.println("obj class "+obj.getClass());
		if(obj instanceof String[])
		{
			String[] list = (String[])obj;
			modelList.clear();
				for(String o: list)
				{
					//System.out.println("view: "+o.toString());
					modelList.addElement(o);	
				}
				//lsDisplay = new JList(modelList);
				//System.out.println("modellist "+modelList.toString());
				//lsDisplay.setModel(modelList);
				//lsDisplay.setVisible(true);
		}
		else if(obj instanceof Integer)
		{
			lbTotalCustomers.setText(obj.toString());
		}
		else if(obj instanceof Double)
		{
			//display price with 2 digits precision
			lbTotalSales.setText(String.format("%.2f",obj));
		}
			
	}
	
	//adding controller
	public void addController(ActionListener controller){
		System.out.println("View      : adding controller");
		btnAddRecord.addActionListener(controller);
		btnEditRecord.addActionListener(controller);
		btnDeleteRecord.addActionListener(controller);
		
		//myPicker.addActionListener(controller);
		//myPicker.addPropertyChangeListener(controller);
		btnPrintMonthReport.addActionListener(controller);
		btnPrintInvoice.addActionListener(controller);
		btnPrintYearReport.addActionListener(controller);
		
		printInvoiceMenu.addActionListener(controller);
		printMonthlyReportMenu.addActionListener(controller);
		printYearlyReportMenu.addActionListener(controller);
		sortCountryMenu.addActionListener(controller);
		sortEmailMenu.addActionListener(controller);
		sortNameMenu.addActionListener(controller);
		sortDateMenu.addActionListener(controller);
		sortPriceMenu.addActionListener(controller);
		aboutAppMenu.addActionListener(controller);
		
	
		
		btnSortId.addActionListener(controller);
		btnSortName.addActionListener(controller);
		btnSortCountry.addActionListener(controller);
		btnSortEmail.addActionListener(controller);
		btnSortPrice.addActionListener(controller);
		btnSortDate.addActionListener(controller);
		btnReset.addActionListener(controller);
		
	}
	
	public void addControl(ChangeListener control)
	{
		//myPicker.addPropertyChangeListener(control);
		yearPicker.getModel().addChangeListener(control);
		monthPicker.getModel().addChangeListener(control);
		
	}

	public JButton getBtnAddRecord() {
		return btnAddRecord;
	}

	public JButton getBtnEditRecord() {
		return btnEditRecord;
	}

	public JButton getBtnDeleteRecord() {
		return btnDeleteRecord;
	}
	

	public JDatePanelImpl getMonthPicker() {
		return datePanel;
	}

	public JDatePickerImpl getYearPicker() {
		return yearPicker;
	}
	
	
	
	public DatePicker getMyPicker() {
		return myPicker;
	}

	public JButton getBtnPrintMonthReport() {
		return btnPrintMonthReport;
	}

	public JButton getBtnPrintYearReport() {
		return btnPrintYearReport;
	}

	public JButton getBtnPreviewMonthReport() {
		return btnPreviewMonthReport;
	}

	public JButton getBtnPreviewYearReport() {
		return btnPreviewYearReport;
	}

	public JButton getBtnPrintInvoice() {
		return btnPrintInvoice;
	}

	public JButton getBtnPreviewInvoice() {
		return btnPreviewInvoice;
	}

		
	public JButton getBtnSortId() {
		return btnSortId;
	}

	public JButton getBtnSortName() {
		return btnSortName;
	}

	public JButton getBtnSortCountry() {
		return btnSortCountry;
	}

	public JButton getBtnSortEmail() {
		return btnSortEmail;
	}

	public JButton getBtnSortPrice() {
		return btnSortPrice;
	}

	public JButton getBtnSortDate() {
		return btnSortDate;
	}
	
	public JList getLsDisplay() {
		return lsDisplay;
	}
	

	public UtilDateModel getModel() {
		return model;
	}

	public UtilDateModel getModel2() {
		return model2;
	}
	

	public void setModel(UtilDateModel model) {
		this.model = model;
	}

	public void setModel2(UtilDateModel model2) {
		this.model2 = model2;
	}
	
	

	public JButton getBtnReset() {
		return btnReset;
	}

	public JMenu getSortMenu() {
		return sortMenu;
	}

	public JMenu getPrintSubmenu() {
		return printSubmenu;
	}

	public JMenuItem getPrintInvoiceMenu() {
		return printInvoiceMenu;
	}

	public JMenuItem getPrintMonthlyReportMenu() {
		return printMonthlyReportMenu;
	}

	public JMenuItem getPrintYearlyReportMenu() {
		return printYearlyReportMenu;
	}

	public JMenuItem getSortCountryMenu() {
		return sortCountryMenu;
	}

	public JMenuItem getSortEmailMenu() {
		return sortEmailMenu;
	}

	public JMenuItem getSortNameMenu() {
		return sortNameMenu;
	}

	public JMenuItem getSortDateMenu() {
		return sortDateMenu;
	}

	public JMenuItem getSortPriceMenu() {
		return sortPriceMenu;
	}
	
	public JMenuItem getAboutMenu() {
		return aboutAppMenu;
	}

	public boolean isSelected()
	{
		if(lsDisplay.getSelectedIndex()!=-1)
			return true;
		else
		{
			JOptionPane.showMessageDialog(null, 
					"<html>Please select a record first before performing this operation</html>",
					"Warning", 
					JOptionPane.OK_OPTION);
		}
		return false;
	}
	public boolean deleteConfirmed()
	{
		if(JOptionPane.showConfirmDialog(null, 
 			   "Are you sure you want to delete this record?",
 			   "Confirm action", 
 			   JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			return true;

		return false;
	}
	
	private void addImage(JButton btn, String path)
	{
		try {
		    Image img = ImageIO.read(getClass().getResource("/"+path));
		    btn.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
	}
	
	class DateLabelFormatter extends AbstractFormatter {
		 
	    private String datePattern = "MMM yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	     
	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }
	 
	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	         
	        return "";
	    }
	 
	}
	class DateLabelFormatter2 extends AbstractFormatter {
		 
	    private String datePattern = "yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	     
	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }
	 
	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	         
	        return "";
	    }
	 
	}
}
