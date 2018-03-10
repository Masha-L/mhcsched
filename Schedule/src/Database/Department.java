package Database;
import java.util.ArrayList;

/**
 * Holds the name of the department and the list of offered classes
 */
public class Department {
	// The department's name
	private String name;
	// The list of classes (sorted in numerical order)
	private ArrayList<Subject> classes;
	
	/**
	 * Creates a department 
	 * 
	 * @param name the name
	 * @param classes the classes
	 */
	public Department(String name, ArrayList<Subject> classes) {
		
	}
	
	/**
	 * Finds a subject in the department's list
	 * 
	 * @return the subject
	 */
	public Subject findSubject(String name) {
		
		return null;
	}
	
	/**
	 * Adds a new subject to the department's list
	 * 
	 * @param subj the subject
	 */
	public void addSubject(Subject subj) {
		
	}
	
	
	/**
	 * Calculates the hash function for a given department name
	 * 
	 * @return the hash value
	 */
	public int hashFunction() {
		
		return 0;
	}
}
