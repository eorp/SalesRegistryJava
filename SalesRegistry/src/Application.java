import controller.Controller;
import model.Customer;
import model.CustomerManager;
import model.Model;
import view.AboutWindow;
import view.MainWindow;
import view.PrintWindow;
import view.RecordsWindow;



public class Application {

	public static void main(String[] args) {
		
		//AboutWindow print = new AboutWindow();
		
		//initialise main window
		MainWindow main = new MainWindow();
		//initialise model
		Model model = new Model();
		//add observer
		model.addObserver(main);
		//System.out.println("added observer to main");
		//read data from file
		model.readFromFile("data.xml");
		//RecordsWindow records = new RecordsWindow();
		//initialise controller
		Controller control = new Controller(model, main);
		//add action listener controller to main
		main.addController(control);
		//add change listener to main
		main.addControl(control);
		
		//model.readFromFile("data.xml");
	}

}
