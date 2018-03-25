package Database;

/**
 * Holds the list of lectures for a given section.
 */
public class Section {

	// An array of classes/labs for the section
	private TimePeriod[] classes;

	// Checks if the user has chosen the section
	private boolean isChosen;

	// Subject/name reference
	private String name;

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
	 * Sets the section chosen/not chosen
	 * 
	 * @param chosen true if chosen
	 */
	public void setInteresting(boolean chosen) {
		isChosen = chosen;
	}

	/**
	 * Sets the section chosen/not chosen
	 */
	public boolean isChosen() {
		return isChosen;
	}

	/**
	 * Returns the section's classes
	 * 
	 * @return classes
	 */
	public TimePeriod[] getClasses() {
		return classes;
	}

	public boolean conflicts(Section section) {

		TimePeriod [] otherClasses = section.getClasses();

		for (TimePeriod firstClass : classes)
			for (TimePeriod secondClass : otherClasses) 
				if (firstClass != null && secondClass != null) 
					if (firstClass.overlap(secondClass))
						return true;		

		return false;
	}

}
