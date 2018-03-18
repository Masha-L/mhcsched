package Logic;
import java.util.ArrayList;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 */
public class SchedSolver {

	// The adjacency matrix
	private boolean [][] matrix;
	// Holds the schedule options: indexes of the nodes
	private ArrayList<int[]> schLists;

	// The node matrix
	private SchedMatrix matrixJ;
	// Schedule nodes
	private SchedNode[] schNodes;
	
	// Holds the schedule options
	private ArrayList<ArrayList<Subject>> schedules; 


	/**
	 * Constructor IS WRITTEN FOR THE TESTER
	 * 
	 * @param matrix
	 * @param numS
	 */
	public SchedSolver(boolean[][] matrix, int numS) {
		matrixJ = new SchedMatrix(matrix);
		matrixJ.assignValidity(numS);	
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
		matrixJ = new SchedMatrix(schNodes);
		matrixJ.assignValidity(numS);	
		schLists = new ArrayList<int[]>();

		createValidSchedules(numS);
	}


	/**
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
	 * @param combination of sched nodes (vertexes of a graph) 
	 * @param gen the round of recursion
	 * @return true if graph is complete, false if it isn't
	 */
	private boolean areInterconnected(int[] combination, int gen)
	{
		
		boolean[] compareTo = matrixJ.getNodeConnections(combination[gen]);

		// gen +1 in order not to compare to itself
		for(int i = gen + 1; i < combination.length; i++)
		{
			//if there is a conflict - the combination not valid, return false 
			if(compareTo[combination[i]] == true)
				return false;
		}
		// if the method hasn't checked connections between all the elements 
		// recursion
		if( gen < combination.length - 1)
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
		
		traverseGraph(new int[numS], matrixJ.validNodes(), numS, 0, 0);	
		
	}


	private void classesToNodes(ArrayList<Subject> classes) {

	}

}
