package Logic;
import Database.Section;
import Database.Subject;

public class SchedNode {
	private Section lectureSection; 
	private Section labSection;
	private Subject subject;
	
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
	 * Checks if the node conflicts with this one
	 * 
	 * @param node
	 * @return true if there is a conflict
	 */
	public boolean conflicts(SchedNode node) {
		return false;
	}
}
