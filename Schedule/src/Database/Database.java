package Database;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Provides the database of the offered classes.
 * 
 */
public class Database {
	/*
	 * Maps department name to the set of Subjects
	 * that are offered by this department
	 */
	private Hashtable <String, Department> classes;

	/**
	 * Instantiates the database.
	 * 
	 * Uses a set because picking the department in the ComboBox 
	 * makes all department's subjects to show up. 
	 * There are never too many subjects in one department (i.e. our
	 * psychology department has about 25) so set seems a justified
	 * choice.
	 * 
	 */
	public Database(File database) {
		// initialize the hashtable
	}



	/**
	 *  Adds departments in the table 
	 * @return the list of depNames
	 */
	public ArrayList<String> addDepartments() {

		// makes XML reader read the list of the department
		/* at this point it's empty - we just read the department 
		 * and create empty department class to be filled later */
		// adds this list to the hashtable using for loop
		// before adding reads the name of the department and makes it a key
		return null;
	}

	/**
	 * 
	 * @param depName
	 * @param current
	 */
	public void addClasses(String depName, Department current)
	{
		//calls XML reader to read the classes of a specific department 
		//and all the information about them to create a subject
	}

	/**
	 * Searches hashtable for the list of classes by department's name
	 * 
	 * @param depName department's name
	 * @return the list of classes
	 */
	public ArrayList<Subject> searchClasses(String depName) {

		return null;
	}
}
