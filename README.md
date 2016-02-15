# SalesRegistryJava
Sales registry allowing to add, edit and delete customer records, sort records, print invoices and sales reports for selected time period
Most companies have a need to maintain information about their customers and/ or sales.

The main purpose of the Customer Registry application is to enable company's personnel to add, edit and delete records containing information about its customers and sales details. Originally, similar application was written in C#. This is a Java application developed using MVC design.

Additional features include:

- Sorting records by

- Customer's full name;
- Customer's country;
- Customer's email address;
- Product price;
- Payment date

- Displaying records for a selected month of the year;
- Displaying records for a selected year;
- Printing sales report for a selected month of the year (including print preview);
- Printing sales report for a selected year (including print preview);
- Printing an invoice for a selected customer (including print preview)

 ![interface](https://cloud.githubusercontent.com/assets/14193564/13037528/65958446-d37a-11e5-9ee4-2dc5dbaf5dd1.png)


Figure 1 Application Interface

# Usage Instructions

## Add Record

Pressing "Add Record" button will bring up a form where the information can be entered (see Figure 2)

![addrecord](https://cloud.githubusercontent.com/assets/14193564/13037425/80df97d4-d378-11e5-9a43-264967f4accc.png)
 
Figure 2 Adding a New Record

First name, surname and email address are required fields and if those are not entered a corresponding warning message will be displayed.

![invalidinputemail](https://cloud.githubusercontent.com/assets/14193564/13037428/81345eb8-d378-11e5-8a9b-37f4eeaa1259.png)
![invalidinputname](https://cloud.githubusercontent.com/assets/14193564/13037429/8134b840-d378-11e5-857c-08fb6b8315cc.png)
 
Figure 3 Warning Message Examples

A product price is set according to the product name automatically thus the price field editing is disabled.

Expiry date is set automatically to be one year ahead of the payment date.

After all information had been filled and after pressing "OK" button the main window will be displayed again and the list of records will be updated to include the new record. The total number of customers and the total amount of sales will also be updated to reflect the changes made.

## Edit Record

In order to edit an existing record that record will need to be selected from the list first. If no record is being selected a warning message will appear.

![warningselectrecord](https://cloud.githubusercontent.com/assets/14193564/13037422/80d8f29e-d378-11e5-9d9e-2e7588a9ec68.png)
 
Figure 4 Warning Message: Record needs to be selected

After selecting a record and pressing "Edit Record" button a form will be shown containing all the information for the selected record and any required editing can be performed.

Pressing "Cancel" button will discard any changes made. In order to save changes the user needs to press "OK" button. The main window will be displayed and the changes will be reflected in the records list.

## Delete Record

In order to delete a record that record needs to be selected first otherwise a warning message will be shown. After choosing a record to be deleted a confirmation message will be shown to ensure that the record is not being deleted by accident.

![confirmdeleterecord](https://cloud.githubusercontent.com/assets/14193564/13037424/80de753e-d378-11e5-8780-55d78d184ebd.png)
 
Figure 5 Confirmation Message Upon Record Deletion

All changes including adding, editing and deleting records are saved in an XML file enabling to continue working with the list of records the next time the application is started and ensuring that the information is not lost. On application start up any records stored in the XML file are retrieved and displayed.

## Sort Records

The records can be sorted by customer's full name, country, email, product price and payment date by pressing buttons located above the aforementioned fields or from the menu Sort.

![sortrecords](https://cloud.githubusercontent.com/assets/14193564/13037479/51cca030-d379-11e5-8dba-4e153d5744a5.png)

Figure 6 Sorting Records

Records can also be sorted by certain month of a certain year by selecting the time period in the first date box located in the “Display Options” section. The month can be selected by pressing left and right arrows.
It is also possible to display the records for the whole year by selecting the year from the second date box located in the “Display Options” section. The year can be chosen by pressing up and down arrows. 

![selectmonth](https://cloud.githubusercontent.com/assets/14193564/13037478/51cac634-d379-11e5-8113-6e79c1e12e5b.png)
![selectyear](https://cloud.githubusercontent.com/assets/14193564/13037480/51ccb1e2-d379-11e5-8f97-1ddf61adfc25.png)

Figure 7 Selecting Time Period

The records for a selected time period can also be sorted by customer’s name, country, email, product price and payment date the same way as mentioned earlier. Once the records for selected time period had been sorted by some value, a blue refresh button will appear. Clicking this button will restore unsorted order of the records for the currently selected time period. In order to display all the records (unsorted) contained in the list a blue refresh button can be pressed. 



## Printing

The sales reports for the selected time periods and the invoices for a selected record can be printed from the application by pressing the corresponding buttons located in the “Printing Options” section or from the menu File->Print.

Figure 8 Printing Options

The sales reports and the invoices for selected records can also be previewed before printing. There is an option to adjust layout of the reports and invoices before printing by clicking “Enable editing button”. Generated documents can be sent for printing by clicking “Print” button. 

![printinvoice](https://cloud.githubusercontent.com/assets/14193564/13037431/81404f16-d378-11e5-9f85-e0fba092319c.png)

Figure 9 Print Invoice Dialog

![printmonthlyreport](https://cloud.githubusercontent.com/assets/14193564/13037432/8146a53c-d378-11e5-98cb-a2a58be9d5f4.png)

Figure 10 Print Sales Report for Selected Month Dialog

![printyearlyreport](https://cloud.githubusercontent.com/assets/14193564/13037430/813f3414-d378-11e5-9217-5a6bdf4fde92.png)

Figure 11 Print Sales Report for Selected Year Dialog
