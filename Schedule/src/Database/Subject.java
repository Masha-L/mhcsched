package Database;
import java.util.ArrayList;

/**
 * Holds the information about a subject:
 * the lists of lecture sections and the labs;
 * its name, description, and the number of credits.
 */
public class Subject {
	// The name of the subject
	private String name;
	// The description of the subject
	private String description;
	// The list of lectures
	private ArrayList<Section> lectures;
	// The list of labs (may be null)
	private ArrayList<Section> labs;
	// The number of credits
	private int credits;
		
	/**
	 * Constructs a subject
	 * 
	 * @param name the name
	 * @param description the description
	 * @param lectures the list of lectures
	 * @param labs the list of labs
	 * @param credits the number of credits
	 */
	public Subject(String name, String description, ArrayList<Section> lectures, ArrayList<Section> labs, int credits) {

	}

}