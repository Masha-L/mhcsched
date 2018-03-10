package Database;
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
	private Hashtable<String, Department> classes;
	
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
	public Database() {
		// initialize the hashtable
	}
	
	/**
	 * Adds a new department in the table
	 *
	 * @param depName department's name
	 * @param classes the set of classes
	 */
	public void addDepartment(String depName, Department classes) {
		
	}
	
	/**
	 * Searches for the list of classes by department's name
	 * 
	 * @param depName department's name
	 * @return the list of classes
	 */
	public ArrayList<Subject> searchClasses(String depName) {
		
		return null;
	}
}
