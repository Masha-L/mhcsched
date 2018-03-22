package Database;
import java.util.ArrayList;

import Logic.SchedNode;

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

	/**
	 * THIS IS WRITTEN FOR THE TESTER
	 */
	public Subject() {
		lectures = new ArrayList<Section>();
		labs = new ArrayList<Section>();
	}

	/**
	 * THIS IS WRITTEN FOR THE TESTER
	 */
	public void addLectureSection(Section lecture) {
		lectures.add(lecture);
	}

	/**
	 * THIS IS WRITTEN FOR THE TESTER
	 */
	public void addLabSection(Section lab) {
		labs.add(lab);
	}

	/**
	 * Returns the list of the subject's nodes
	 * 
	 * @precondition: subject has at least one lecture section or one lab
	 * @return the list of the subject's nodes
	 */
	public ArrayList<SchedNode> getAllNodes() {

		ArrayList<SchedNode> nodeList = new ArrayList<SchedNode>();

		if (lectures.isEmpty() && labs.isEmpty()) {
			nodeList.add(new SchedNode());
		}

		else {
			if (lectures.isEmpty()) {
				for (Section lab : labs) {
					nodeList.add(new SchedNode(null, lab));
				}
			}

			else if (labs.isEmpty()) {
				for (Section lecture : lectures) {
					nodeList.add(new SchedNode(lecture, null));
				}
			}

			else {
				for (Section lecture : lectures) {
					for (Section lab : labs) {
						nodeList.add(new SchedNode(lecture, lab));
					}
				}
			}
		}

		return nodeList;
	}
}