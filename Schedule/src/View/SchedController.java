package View;
import java.util.ArrayList;

import javax.swing.JPanel;
import Database.Database;

/**
 * Sets up the UI, displays the interactive elements.
 * Takes the user input and sends it to the logic segment.
 *
 */
public class SchedController extends JPanel{
	// ScheduleRenderer, ScheduleBuilder, and other GUI classes

	// The subject database
	private Database database;
	
	//the list of the departments
	private ArrayList<String> depNames;
	
	/**
	 * Creates a new controller for the app
	 * 
	 * @param data the subject database 
	 */
	public SchedController(Database data) {
		
	}
	
	/**
	 * Sets up all the GUI components 
	 * (will branch off in many methods and classes)
	 */
	private void setUpInterface() {

	}
	
	/**
	 * Create the button that starts off the process of schedule building
	 * (probably will be also managed by JavaFX)
	 */
	private void createGoButton() {

	}
	
}
