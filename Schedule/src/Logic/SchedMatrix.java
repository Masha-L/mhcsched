package Logic;

/**
 * Manages holding the adjacency matrix for the schedule nodes
 *
 */
public class SchedMatrix {
	// The adjacency matrix 
	//the length of each row = num of classes+1; 
	//last column - valid or not valid
	private boolean[][] matrix;
	// Holds validity of the subjects (degree + 1 >= minNumSubj)
	private boolean[] isExcluded;
	//Holds the degree of vertex in the adjacency matrix
	private int[] degrees;
	// Holds the number of valid subjects
	private int numValid;

	/**
	 * Constructor IS WRITTEN FOR THE TESTER
	 * 
	 * @param matrix
	 */
	public SchedMatrix(boolean[][] matrix) {
		this.matrix = matrix;
		degrees = new int[matrix.length];
		isExcluded = new boolean[matrix.length];
		numValid = matrix.length;
	}

	/**
	 * Constructor
	 * Takes the list of chosen classes from controller
	 */
	public SchedMatrix(SchedNode[] nodes) {
		buildMatrix(nodes);
		degrees = new int[matrix.length];
		isExcluded = new boolean[matrix.length];
		numValid = matrix.length;
	}


	/**
	 * Fills the adjacency matrix with boolean values 
	 * where true stands for a conflict of two nodes
	 * 
	 * @param nodes the list of nodes 
	 */
	private void buildMatrix(SchedNode[] nodes) {

		// если колонка == номеру ряда -> поставить 1 
		// если TP накладываются -> 1 

	}


	/**
	 * Repeatedly counts nodes degrees to determine if they can be used in schedules
	 * 
	 * @param numS the number of subjects the student wants to take
	 */
	public void	assignValidity(int numS){
		boolean changed = true;

		while(changed) {
			changed = false;
			for (int i = 0; i < matrix.length; i++) {
				if(!isExcluded[i]) {
					int degree = vertexDegree(i);
					if (degree + 1 < numS) {
						setNodeInvalid(i);
						changed = true; 
					}
					else
						degrees[i] = degree;
				}
			}
		}
	}

	/**
	 * Calculates the degree of a node from matrix
	 * 
	 * @param row the number of the node in the matrix
	 * @return the degree of the node
	 */
	private int vertexDegree(int node) {
		int degree = 0;
		for(int i = 0; i < matrix.length; i++) {	
			// if the node is valid and there is no conflict with an elt 
			// increase the degree by 1
			if(!isExcluded[i] && !areInConflict(node, i))
				degree++;
		}
		return degree;
	}

	/**
	 * Sets the node invalid for schedule (invalid == true)
	 * 
	 * @param node the node to be set invalid
	 */
	private void setNodeInvalid(int node) {
		isExcluded[node] = true;
		numValid--;
	}

	/**
	 * Creates an array to choose combinations from
	 * 
	 * @return array of indexes to choose schedules from
	 */
	public int[] validNodes() {
		int[] chooseFrom = new int[numValid];

		int currentIndex = 0;
		for (int i = 0; i < isExcluded.length; i++) {
			if (!isExcluded[i]) {
				chooseFrom[currentIndex] = i;
				currentIndex++;
			}
		}
		return chooseFrom;
	}

	/**
	 * Adds an edge between two nodes as a conflict between them
	 * 
	 * @param oneNode
	 * @param anotherNode
	 */
	private void addConflict(int oneNode, int anotherNode) {
		matrix[oneNode][anotherNode] = true;
	}

	/**
	 * Checks if two nodes are in conflict
	 * 
	 * @param oneNode
	 * @param anotherNode
	 */
	public boolean areInConflict(int oneNode, int anotherNode) {		
		return matrix[oneNode][anotherNode];
	}

	/**
	 * Returns connections of the node to the others
	 * 
	 * @param node 
	 * @return the array of connections (true for conflict)
	 */
	public boolean[] getNodeConnections(int node) {
		return matrix[node];
	}
}
