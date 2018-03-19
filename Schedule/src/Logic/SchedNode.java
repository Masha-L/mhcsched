package Logic;
import Database.Section;

/**
 * 
 */
public class SchedNode {
	private Section lectureSection; 
	private Section labSection;
	
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

}
