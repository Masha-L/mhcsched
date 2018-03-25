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
	 * @param subject 
	 */
	public SchedNode(Section lectureSection, Section labSection, Subject subject) {
		this.lectureSection = lectureSection;
		this.labSection = labSection;
		this.subject = subject;
	}
	
	/**
	 * Returns the subject of the node
	 * 
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}
	
	/**
	 * Checks if the node conflicts with this one
	 * 
	 * @param node
	 * @return true if there is a conflict
	 */
	public boolean conflicts(SchedNode node) {
		
		if (subject.equals(node.getSubject())) {
			return true;
		}
		
		return false;
	}
}
