package Database;

/**
 * Holds the list of lectures for a given section.
 */
public class Section {
	// An array of classes/labs for the section
	private TimePeriod[] classes;
	
	// Checks if the user has chosen the section
	private boolean isInteresting;
	
	// Subject reference
	private Subject subject;
	
	//For the purposes of going back to the schedule
	private boolean isLab;
	
	/**
	 * Constructs a section (a set of lectures / a lab)
	 * 
	 * @param subject
	 * @param classes
	 * @param isLab
	 */
	public Section(Subject subject, TimePeriod[] classes, boolean isLab) {
		this.classes = classes;
	}
	
	/**
	 * Sets the section interesting/not interesting to the user
	 * 
	 * @param interesting true if interesting
	 */
	public void setInteresting(boolean interesting) {
		isInteresting = interesting;
	}
	
	/**
	 * Sets the section interesting/not interesting to the user
	 * 
	 * @param interesting true if interesting
	 */
	public boolean isInteresting() {
		return isInteresting;
	}

}
