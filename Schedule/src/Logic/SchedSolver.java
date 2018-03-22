package Logic;
import java.util.ArrayList;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 */
public class SchedSolver {

	// Holds the schedule options: indexes of the nodes
	private ArrayList<int[]> schLists;

	// The node matrix
	private SchedMatrix matrix;
	// Schedule nodes
	//private SchedNode[] schNodes;
	private ArrayList<SchedNode> schNodes;

	// Holds the schedule options
	private ArrayList<ArrayList<Subject>> schedules; 


	/**
	 * Constructor IS WRITTEN FOR THE TESTER
	 * 
	 * @param matrix
	 * @param numS
	 */
	public SchedSolver(boolean[][] matrix, int numS) {
		this.matrix = new SchedMatrix(matrix);
		this.matrix.assignValidity(numS);	
		schLists = new ArrayList<int[]>();

		createValidSchedules(numS);
	}


	/**
	 * Constructor 
	 * 
	 * @param classes chosen by user
	 * @param numS the number of the subjects the student wants to take
	 */
	public SchedSolver(ArrayList<Subject> classes, int numS) {
		
		classesToNodes(classes);
		//matrix = new SchedMatrix(schNodes);
		//matrix.assignValidity(numS);	
		//schLists = new ArrayList<int[]>();

		//createValidSchedules(numS);
	}


	/**
	 * ======================================== SPLIT ===========================
	 * 
	 * Creates all possible combinations of valid subjects and adds them to the list of schedules
	 * 
	 * @param sched the current schedule option
	 * @param chFrom array that keeps all valid and unused subjects
	 * @param leftToAdd 
	 * @param gen 
	 * @param startFrom 
	 */
	private void traverseGraph(int[] sched, int[] chFrom, int leftToAdd, int gen, int startFrom) {

		if (gen < sched.length && startFrom < chFrom.length) {

			for (int fixedElm = gen; fixedElm < sched.length; fixedElm++) {
				for (int movingElm = startFrom; movingElm < chFrom.length; movingElm++) {

					sched[fixedElm] = chFrom[movingElm];

					if(leftToAdd == 1) {
						if (areInterconnected(sched, 0)) {
							schLists.add(sched.clone());
						}
					}
					traverseGraph(sched, chFrom, leftToAdd - 1, fixedElm + 1, movingElm + 1);
				}
			}
		}
	}

	/**
	 * Checks if the graph is complete
	 * Employs recursion to compare subject with all subsequent ones
	 * 
	 * @param combination of schedule nodes (vertexes of a graph) 
	 * @param gen the round of recursion
	 * @return true if graph is complete, false if it isn't
	 */
	private boolean areInterconnected(int[] combination, int gen) {

		boolean[] compareTo = matrix.getNodeConnections(combination[gen]);

		// gen+1 in order not to compare to itself
		for (int i = gen + 1; i < combination.length; i++) {
			//if there is a conflict - the combination not valid, return false 
			if (compareTo[combination[i]] == true)
				return false;
		}
		//  if the method hasn't checked all the elements, recursion
		if (gen < combination.length - 1)
			return areInterconnected(combination, gen + 1);

		return true;
	}

	/**
	 * Traverses the graph to find every combination possible
	 * and verifies the schedules 
	 *  
	 * @param numS 
	 */
	private void createValidSchedules(int numS) {

		traverseGraph(new int[numS], matrix.validNodes(), numS, 0, 0);	
		//System.out.println("size: " + schLists.size());

	}
	
	// THIS IS WRITTEN FOR THE TESTER
	public ArrayList<int[]> getSchLists() {
		return schLists;
	}

	/**
	 * Turns the subjects lectures and labs into individual nodes
	 * 
	 * @param classes the chosen classes
	 */
	private void classesToNodes(ArrayList<Subject> classes) {	
		
		schNodes = new ArrayList<SchedNode>();
		
		for (Subject subject : classes) {
			schNodes.addAll(subject.getAllNodes());
		}	
	}
	
	/**
	 * kakaya-to huinya 
	 * ya ne znayu zachem eto
	 * 
	 * @return
	 */
	public int getNumNodes() {
		return schNodes.size();
	}

}
