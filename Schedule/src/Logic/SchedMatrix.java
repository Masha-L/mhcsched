package Logic;
import java.util.ArrayList;

import Database.Subject;

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

	/**
	 * Constructor IS WRITTEN FOR THE TESTER
	 * 
	 * @param matrix
	 */
	public SchedMatrix(boolean[][] matrix) {
		this.matrix = matrix;
		degrees = new int[matrix.length];
		isExcluded = new boolean[matrix.length];
	}

	/**
	 * Constructor
	 * Takes the list of chosen classes from controller
	 */
	public SchedMatrix(SchedNode[] nodes) {
		buildMatrix(nodes);
		degrees = new int[matrix.length];
		isExcluded = new boolean[matrix.length];
	}


	/**
	 * Repeatedly counts nodes degrees to determine
	 * if they can be used in schedules
	 * @param numS int the number of subjects the student wants to take
	 */
	public void	assignValidity(int numS)
	{

		//counter of the invalid nodes
		//change of node validity triggers recursion 

		boolean changed = true;

		while(changed)
		{
			changed = false;
			for(int i = 0; i<matrix.length;i++)
			{
				if(!isExcluded[i]) {
					//finds degree of node with index i
					int degree=vertexDegree(i);
					if(degree+1<numS)
					{
						setNodeInvalid(i);
						changed=true; 
					}
					else
						degrees[i]=degree;
				}
			}
		}
	}

	/**
	 * Calculates the degree of a node from matrix
	 * @param row - int row - the number of the node in the matrix
	 * @return int the degree of the node
	 */
	private int vertexDegree(int node)
	{
		//the degree
		int degree = 0;
		//for each 
		for(int i = 0; i<matrix.length;i++)
		{	
			//if the node is valid and there is no conflict with an elt 
			// increase the degree by 1
			if(!areInConflict(node,i) && !isExcluded[i])
				degree++;
		}
		return degree;
	}

	/**
	 * Sets the node invalid for schedule (invalid == true)
	 * 
	 * @param index corresponds to the index of the node in matrix
	 * that supposed to become invalid
	 */
	private void setNodeInvalid(int node) {
		isExcluded[node] = true;
		degrees[node]=-1;
	}

	/**
	 * Creates an array to choose combinations from
	 * @return int[] array of indexes to choose schedules from
	 */
	public int[] validNodes()
	{
		int[] chooseFrom = new int[countValidSubj(new int[2])];
		int currentIndex = 0;
		for(int i = 0; i<isExcluded.length; i++)
		{
			if(!isExcluded[i])
			{
				chooseFrom[currentIndex]=i;
				currentIndex++;
			}
		}
		return chooseFrom;

	}

	/**
	 * Counts subjects that are valid for making a schedule 
	 * @param array - int [] array containing degrees of subjects
	 * @return int number of valid subjects
	 */
	int countValidSubj(int[] array)
	{
		int validSubj = 0;
		for(int i = 0; i<isExcluded.length; i++)
		{
			if(!isExcluded[i])
				validSubj++;
		}
		return validSubj;
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
	 * Adds an edge between two nodes
	 * edge stands for conflict between two nodes 
	 * 
	 * @param oneNode
	 * @param anotherNode
	 */
	private void addEdge(int oneNode, int anotherNode) {

	}

	/**
	 * Checks if two nodes are in conflict
	 * 
	 * @param oneNode
	 * @param anotherNode
	 */
	public boolean areInConflict(int oneNode, int anotherNode) {
		return false;
	}

	public boolean[] getNodeConnections(int node)
	{
		return matrix[node];
	}

}
