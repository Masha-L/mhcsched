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
	
	/**
	 * Constructor
	 * Takes the list of chosen classes from controller
	 */
	public SchedMatrix(ArrayList<Subject> classes) {
		
	}
	
	/**
	 * Adds an edge between two nodes
	 *
	 * @param oneNode
	 * @param anotherNode
	 */
	public void addEdge(int oneNode, int anotherNode) {
		
	}
	
	/**
	 * Removes an edge between two nodes
	 *
	 * @param oneNode
	 * @param anotherNode
	 */
	public void removeEdge(int oneNode, int anotherNode) {
		
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
	
	public boolean[][] getMatrix()
	{
		return matrix;
	}
}
