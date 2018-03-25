package Logic;

import java.util.ArrayList;

/**
 * Manages holding the adjacency matrix for the schedule nodes
 *
 */
public class SchedMatrix {
	// The adjacency matrix 
	//the length of each row = num of classes + 1; 
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
	 * Constructor WHICH I HAVE BECAUSE MY CODE IS A LIE
	 * Takes the list of chosen classes from controller
	 * 
	 * @param nodes the list of the schedule nodes
	 */
	public SchedMatrix(ArrayList<SchedNode> nodes) {	

		int numNodes = nodes.size();		

		buildMatrix(nodes);
		degrees = new int[numNodes];
		isExcluded = new boolean[numNodes];
		numValid = numNodes;
	}


	/**
	 * Fills the adjacency matrix with boolean values 
	 * where true stands for a conflict of two nodes
	 * 
	 * @param nodes the list of nodes 
	 */
	private void buildMatrix(ArrayList<SchedNode> nodes) {
		
		int matrixSize = nodes.size();	
		matrix = new boolean[matrixSize][matrixSize];
		
		SchedNode node, anotherNode;		
		for (int row = 0; row < matrixSize; row++) {
			node = nodes.get(row);
			for (int col = 0; col < matrixSize; col++) {
				anotherNode = nodes.get(col);
				if (node.conflicts(anotherNode)) {
					addConflict(row, col);
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			System.out.println("");
		}
	}


	/**
	 * Repeatedly counts nodes degrees to determine if they can be used in schedules
	 * 
	 * @param numS the number of subjects the student wants to take
	 */
	public void	assignValidity(int numS){
		// True if iteration is not over
		boolean changed = true;

		while(changed) {
			changed = false;
			for (int index = 0; index < matrix.length; index++) {
				if (becameInvalid(index, numS)) {
					changed = true;
				}
			}
		}
	}


	/**
	 * Increases the node's degree if appropriate or sets it as invalid
	 * Calculates the current degree of the node and compares it to the 
	 * desired number of classes. 
	 * Updates the degree if appropriate, otherwise sets the node invalid
	 * 
	 * @param index
	 * @param numS
	 * @return false if the node 
	 */
	private boolean becameInvalid(int index, int numS) {
		if(!isExcluded[index]) {
			int degree = vertexDegree(index);		
			if (degree + 1 < numS) {
				setNodeInvalid(index);
				return true;
			}
			else
				degrees[index] = degree;
		}
		return false;
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
