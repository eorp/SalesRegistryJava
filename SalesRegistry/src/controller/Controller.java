package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.AboutWindow;
import view.Countries;
import view.MainWindow;
import view.PrintWindow;
import view.RecordsWindow;
import model.Model;
/**
 * controller class
 * @author Win8
 *
 */
public class Controller implements ActionListener, ChangeListener {
	//model
	private Model model;
	//main window view
	private MainWindow view;
	//records window view
	private RecordsWindow recordsView;
	//print window view
	private PrintWindow dialog;
	//about window view
	private AboutWindow aboutDialog;
	//keep track if edit button is clicked
	private boolean editBtnClicked = false;
	//xml file name
	private String file = "data.xml";
	//keep track if records have been sorted
	private boolean sorted = false;

	
	
	public Controller(Model model, MainWindow view) {
		this.model = model;
		this.view = view;
		//showRecordsWindow();
		
	}
	/**
	 * show records window view
	 */
	private void showRecordsWindow()
	{
		recordsView = new RecordsWindow();
		//System.out.println("current object is "+this);
		//add action listener controller 
		recordsView.addController(Controller.this);
		//show records window
		recordsView.setVisible(true);
		//System.out.println("btn is "+e.getSource()+" "+e.getSource().toString());
	
	}

/**
 * manage buttons click actions
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("event is "+e.getSource());
		// TODO Auto-generated method stub
		//edit button clicked
		if(e.getSource()==view.getBtnEditRecord())
		{
			editBtnClicked = true;
			//provided a record is selected
			if(view.isSelected())
			{
				showRecordsWindow();
				
				//add observer
				model.addObserver(recordsView);
				//display selected customer record
				model.showCustomer(view.getLsDisplay().getSelectedIndex());
			}
			//System.out.println("in controller edit btn");
			//model.addCustomer();
		}//add new customer action
		else if(e.getSource()==view.getBtnAddRecord())
		{
			editBtnClicked = false;
			//clear fields
			//recordsView.initFields();
			//show records window
			showRecordsWindow();
			//recordsView.setVisible(true);
			//showRecordsWindow();
			
			
		}//delete record action
		else if(e.getSource()==view.getBtnDeleteRecord())
		{//provided record is selected and user chose to delete that record
			if(view.isSelected())
				if(view.deleteConfirmed())
				{//delete selected customer record
					model.deleteCustomer(view.getLsDisplay().getSelectedIndex());
				}
		}//print monthly report action
		else if(e.getSource()==view.getBtnPrintMonthReport() || e.getSource()==view.getPrintMonthlyReportMenu())
		{
			 //get info for selected month and year
            String[] main = model.showSelectedSales2(view.getModel().getMonth(),view.getModel().getYear());
            //header
    		String header = "Sales Report for " + (view.getModel().getMonth()+1) + "/" + view.getModel().getYear();
    		//total
    		String total = "Total:\t\t" + String.format("%.2f",model.getTotalSales());
    		System.out.println("invoice "+header+"/n"+Arrays.toString(main)+"/n"+total);
    		//show print window
    		showPrintWindow(header,main,total);
			
		}//print yearly report action
		else if(e.getSource()==view.getBtnPrintYearReport() || e.getSource()==view.getPrintYearlyReportMenu())
		{	 //get info for selected  year
            String[] main = model.showSelectedSales2(-1,view.getModel2().getYear());
            //header
    		String header = "Sales Report for " + view.getModel2().getYear();
    		//total
    		String total = "Total:\t\t" + String.format("%.2f",model.getTotalSales());
    		System.out.println("invoice "+header+"/n"+Arrays.toString(main)+"/n"+total);
    		
    		//show print window
    		showPrintWindow(header,main,total);
    		
		}
		//print invoice for selected record action
		else if(e.getSource()==view.getBtnPrintInvoice() || e.getSource()==view.getPrintInvoiceMenu())
		{//provided a record is selected
			if(view.isSelected())
			{
				System.out.println(model.getInvoice("Company Name", "Contact Details", view.getLsDisplay().getSelectedIndex()));
				//show print window
	    		showPrintWindow("Invoice",null,model.getInvoice("Company Name\n", "Contact Details", view.getLsDisplay().getSelectedIndex()));
			}
		}//sort records by country action
		else if(e.getSource()==view.getBtnSortCountry() || e.getSource()==view.getSortCountryMenu())
		{
			model.sortBy("country");
			//set sorted flag
			sorted = true;
			//check refresh button visibility
			checkButtonVisible();
		}//sort records by date
		else if(e.getSource()==view.getBtnSortDate() || e.getSource()==view.getSortDateMenu())
		{
			model.sortBy("date");
			//set sorted flag
			sorted = true;
			//check refresh button visibility
			checkButtonVisible();
		}//sort records by email
		else if(e.getSource()==view.getBtnSortEmail() || e.getSource()==view.getSortEmailMenu())
		{
			model.sortBy("email");
			//set sorted flag
			sorted = true;
			//check refresh button visibility
			checkButtonVisible();
		}//sort records by id
		else if(e.getSource()==view.getBtnSortId())
		{
			model.sortBy("id");
			//set sorted flag
			//sorted = true;
			//check refresh button visibility
			checkButtonVisible();
		}//sort records by name
		else if(e.getSource()==view.getBtnSortName() || e.getSource()==view.getSortNameMenu())
		{
			model.sortBy("name");
			//set sorted flag
			sorted = true;
			//check refresh button visibility
			checkButtonVisible();
		}//sort records by price
		else if(e.getSource()==view.getBtnSortPrice() || e.getSource()==view.getSortPriceMenu())
		{
			model.sortBy("price");
			//set sorted flag
			sorted = true;
			//check refresh button visibility
			checkButtonVisible();
			//checkButtonsEnabled();
	
		}//show about dialog
		else if(e.getSource()==view.getAboutMenu()){
			aboutDialog = new AboutWindow();
			aboutDialog.setVisible(true);
		}
		//show records in original order
		else if(e.getSource()==view.getBtnReset())
		{
			model.showOriginal();
			//set sorted flag
			sorted = false;
			//check refresh button visibility
			checkButtonVisible();
			model.setSortedByTime(0);
			checkButtonsEnabled();
			
		}//add new record or edit record confirmation button action
		else if(recordsView!=null && e.getSource()==recordsView.getBtnOk())
		{//provided entered data is acceptable
			if(recordsView.checkInput())
			{//set customer record with that data
				model.setCustomer(model.readCustomer(recordsView.getFirstName(),recordsView.getSurname(),
						recordsView.getEmail(),recordsView.getStreet(), recordsView.getCity(),
						recordsView.getStates(),recordsView.getZip(),
						(Countries) recordsView.getCountry(),
						recordsView.getProduct(),Double.parseDouble(recordsView.getPrice()),recordsView.getDate(),
						recordsView.getExpiryDate(),recordsView.getRenewed(),recordsView.getDomains(),editBtnClicked));
				//System.out.println("country"+recordsView.getCmbCountry().getSelectedItem());
				//if it's a new record
				if(!editBtnClicked)
				{//add record
				model.addCustomer(model.getCustomer());
				}else
				{//otherwise edit record
					model.editCustomer(view.getLsDisplay().getSelectedIndex(), model.getCustomer());
					
				}//close records window
				recordsView.closeFrame();
				
				//view.getLsDisplay()
			}
			
		}//print contents of text area in print window
		else if(dialog!=null && e.getSource().equals(dialog.getBtnPrint())){
			
				PrintReport.printComponent(dialog.getTxtReport());
			
			
		}
		
		//}
	}

	
	/**
	 * manage dates changes
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		//year changed
		if(e.getSource()==view.getModel2())
		{
		System.out.println("year changed "+view.getModel2().getYear());
		//show records for selected year
		model.showSelectedSales(-1,view.getModel2().getYear());
		//view.getModel2().setSelected(true);
		//set sorted flag
		sorted = true;

		}
		//month and year changed
		else if(e.getSource()==view.getModel())
		{
			System.out.println("date changed "+(view.getModel().getMonth()+1)+" / "+view.getModel().getYear());
			//show records for selected month and year
			model.showSelectedSales(view.getModel().getMonth(),view.getModel().getYear());
			//view.getModel().setSelected(true);
			//set sorted flag
			sorted = true;
			//view.getModel().setValue(view.getModel().getValue());
			
		}
		checkButtonVisible();
		checkButtonsEnabled();
				
	}
	/**
	 * manage reset button visibility
	 */
	private void checkButtonVisible()
	{//if records are sorted - show reset button, otherwise hide it
		if(!sorted)
		{
			view.getBtnReset().setVisible(false);
		}
		else
			view.getBtnReset().setVisible(true);
	}
	/**
	 * disable edit and delete buttons if sorted by time period
	 */
	private void checkButtonsEnabled(){
		if(model.getSortedByTime()>0)
		{
			view.getBtnDeleteRecord().setEnabled(false);
			view.getBtnEditRecord().setEnabled(false);
		}
		else{
			view.getBtnDeleteRecord().setEnabled(true);
			view.getBtnEditRecord().setEnabled(true);
		}
	}
	/**
	 * show print view for selected document
	 * @param header document header
	 * @param main main body
	 * @param footer total
	 */
	private void showPrintWindow(String header, String[] main, String footer){
		dialog = new PrintWindow();
		//add action listener controller 
		dialog.addController(Controller.this);
		//set dialog title
		dialog.setTitle(header);
		String[] body = main;
		//clear text area
		dialog.getTxtReport().setText(null);
		//add header to text area
		if(main!=null)
		dialog.getTxtReport().append(header+"\n\n");
		//loop through main body for sales reports only, not for invoices
		if(body!=null){
		//add main body to text area
		for(int i=0;i<body.length;i++){
		dialog.getTxtReport().append(body[i]+"\n");
		System.out.println(body[i]);
		}
		}
		if(main!=null)
		dialog.getTxtReport().append("\n\n");
		//add footer to text area 
		dialog.getTxtReport().append(footer);
		//show records window
		dialog.setVisible(true);
				
		
	}
	
}
