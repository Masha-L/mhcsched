package Logic;
import java.util.ArrayList;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 */
public class SchedSolver {
	//why do we need the whole thing? we need just the matrix, no?
	/*// The node matrix
	private SchedMatrix matrix;*/

	private static boolean [][] matrix = new boolean[9][10];
	//Holds the degree of vertex in the adjacentcy matrix
	private static  int[] degrees = new int[9];
	// Holds the schedule options
	private ArrayList<ArrayList<Subject>> schedules;
	
/**
 * Creates a sample matrix
 */
	public static void setMatrix() {

		for(int i = 0; i<matrix.length;i++)
		{
			for(int j = 0;  j<matrix[0].length;j++)
			{
				matrix[i][j]=false;
			}
		}

		matrix[0][0]=true;
		matrix[1][1]=true;
		matrix[2][2]=true;
		matrix[3][3]=true;
		matrix[4][4]=true;
		matrix[5][5]=true;
		matrix[6][6]=true;
		matrix[7][7]=true;
		matrix[8][8]=true;

		//
		//matrix[2][9]=true;
		//
		
		matrix[0][6]=true;
		matrix[6][0]=true;

		matrix[1][6]=true;
		matrix[6][1]=true;
		matrix[1][7]=true;
		matrix[7][1]=true;
		matrix[1][8]=true;
		matrix[8][1]=true;

		matrix[2][7]=true;
		matrix[7][2]=true;
		matrix[2][8]=true;
		matrix[8][2]=true;

		matrix[3][6]=true;
		matrix[6][3]=true;
		matrix[3][7]=true;
		matrix[7][3]=true;
		matrix[3][8]=true;
		matrix[8][3]=true;

		matrix[4][6]=true;
		matrix[6][4]=true;
		matrix[4][8]=true;
		matrix[8][4]=true;

		matrix[5][6]=true;
		matrix[6][5]=true;
	}


	/**
	 * Constructor
	 * 
	 * @param matrix the node matrix
	 */
	public SchedSolver(boolean [][] matrix) {
		SchedSolver.matrix = matrix;

		//creates array with length == the number of the nodes (in height)
		setDegrees(new int[matrix.length]);
	}



	public static int vertexDegree(int row)
	{
		int degree = 0;
		for(int i = 0; i<matrix.length;i++)
		{
			if(matrix[row][matrix.length]==true)
				return -1;
			if(matrix[row][i]==false&&matrix[i][matrix.length]==false)
				degree++;
		}
		return degree;
	}

	//заполняет и сразу чекает - valid or not
	public static void degreeMatrix(int numSubj)
	{
		//тест, что не null!!!! - constructor's fault,
		//but since wew never call it in the first place...
		
		int invCounter = 0;
		for(int i = 0; i<matrix.length;i++)
		{
			//counter of invalid 
			int degree=vertexDegree(i);
			if(matrix[i][matrix.length]==false&&degree<(numSubj-1))
			{
				setNodeInvalid(i);
				invCounter++;
			}
			getDegrees()[i]=degree;
		}
		if(invCounter!=0)
			degreeMatrix(numSubj);
	}
	/**
	 * Sets the row invalid for schedule 
	 * (invalid == true)
	 * @param row - int row represents the number of the row 
	 * that supposed to become invalid
	 */
	private static void setNodeInvalid(int row) {
		matrix[row][matrix.length] = true;
	}


	/**
	 * Builds the graph for the selected classes
	 */
	public void buildGraph() {
		//do we need to do the graph? I would use our matrix as a graph
	}

	/**
	 * Traverses the graph (requires helper methods)
	 */
	public void traverseGraph() {

	}


	/**
	 * Finds all the combinations of the classes that fit together
	 */
	public void createScheduleList() {

	}


	public static int[] getDegrees() {
		return degrees;
	}


	public static void setDegrees(int[] degrees) {
		SchedSolver.degrees = degrees;
	}

}
