package Logic;

/**
 * Manages holding the adjacency matrix for the schedule nodes
 *
 */
public class SchedMatrix {
	// The adjacency matrix 
	private boolean[][] matrix;
	// Holds validity of the subjects (degree + 1 >= minNumSubj)
	private boolean[] isExcluded;
	//Holds the degree of vertex in the adjacency matrix
	private int[] degrees;


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
	 * 
	 * @param numS the number of subjects the student wants to take
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
	 * 
	 * @param the number of the node in the matrix
	 * @return the degree of the node
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
	 * @param node corresponds to the index of the node in matrix
	 * that is supposed to become invalid
	 */
	private void setNodeInvalid(int node) {
		isExcluded[node] = true;
		degrees[node]=-1;
	}

	/**
	 * Creates an array to choose combinations from
	 * 
	 * @return array of indexes to choose schedules from
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
	 * 
	 * @param array containing degrees of subjects
	 * @return number of valid subjects
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
		
		// if index row == index column, add edge
		// if times are in conflict, add edge 

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

	public boolean[][] getMatrix()
	{
		return matrix;
	}

}
