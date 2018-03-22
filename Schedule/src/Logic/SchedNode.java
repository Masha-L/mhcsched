package Logic;
import Database.Section;

public class SchedNode {
	private Section lectureSection; 
	private Section labSection;
	private boolean unscheduled;
	
	/**
	 * Constructor
	 * 
	 * @param lectureSection
	 * @param labSection
	 */
	public SchedNode(Section lectureSection, Section labSection) {
		this.lectureSection = lectureSection;
		this.labSection = labSection;
	}
	
	/**
	 * Constructor for the single node for the unscheduled subject
	 */
	public SchedNode() {
		unscheduled = true;
	}
}
