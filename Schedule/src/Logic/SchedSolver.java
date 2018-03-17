package Logic;
import java.util.ArrayList;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 */
public class SchedSolver {

	// The adjacentcy matrix
	private boolean [][] matrix;

	//Holds the degree of vertex in the adjacentcy matrix
	private int[] degrees;

	// Holds the schedule options: indexes of the nodes
	private ArrayList<int[]> schLists;
	
	// Holds validity of the subjects (degree + 1 >= minNumSubj)
	private boolean[] isValidSubject[];

	//Holds the number of the subjects a student wants to take
	private int minNumSubj;

	/**
	 * Constructor 
	 * 
	 * @param matrixNew boolean[][] the adjacency matrix
	 * @param numS int the number of the subjects the student wants to take
	 */
	//needs to be checking for the validity of passed args - 
	//and throw a mistake/message if they are not valid ?
	public SchedSolver(boolean [][] matrixNew, int numS) {
		matrix = matrixNew;
		minNumSubj = numS;
		//creates array with length == the number of the nodes
		degrees = new int[matrix.length];
		//fills the array with degrees of each node in the adjacentcy matrix
		degreeMatrix(minNumSubj);
		//creates an empty list for schedule combinations
		schLists = new ArrayList<int[]>();
	}

	//заполняет и сразу чекает - valid or not
	/**
	 * Fills out the array degrees (instant variable) with the degrees. 
	 * The index of an elt of the array corresponds to the indexes of the nodes in the matrix
	 * @param numSubj - int - the number of subjects that the student wants to take
	 */
	private void degreeMatrix(int numSubj)
	{
		//counter of the invalid nodes
		//change of node validity triggers recursion 

		int invCounter = 0;

		for(int i = 0; i<matrix.length;i++)
		{
			//finds degree of node with index i
			int degree=vertexDegree(i);
			//if the node is valid, but the degree is less then n-1
			//set invalid
			if(matrix[i][matrix.length]==false&&degree<(numSubj-1))
			{
				setNodeInvalid(i);
				invCounter++;
			}
			degrees[i]=degree;
		}
		//the recursion happens
		if(invCounter!=0)
			degreeMatrix(numSubj);
	}

	/**
	 * Calculates the degree of a node from matrix
	 * @param row - int row - the number of the node in the matrix
	 * @return int the degree of the node
	 */
	private int vertexDegree(int row)
	{
		//the degree
		int degree = 0;
		//for each 
		for(int i = 0; i<matrix.length;i++)
		{
			//if the node is invalid (the degree is < numberOfSubjects-1)
			//then we don't care for the degree and set it to -1
			//the last elt of the row == true if the node is invalid
			if(matrix[row][matrix.length]==true)
				return -1;
			//if the node is valid and there is no conflict with an elt - increase the degree by 1
			if(matrix[row][i]==false&&matrix[i][matrix.length]==false)
				degree++;
		}
		return degree;
	}

	/**
	 * Sets the row invalid for schedule 
	 * (invalid == true)
	 * @param row - int row represents the number of the row 
	 * that supposed to become invalid
	 */
	private void setNodeInvalid(int row) {
		matrix[row][matrix.length] = true;
	}

	/**
	 * Finds all the combinations of the classes that fit together
	 * 
	 * @param numSubj - int - the number of subjects that the student wants to take
	 */
	private void createScheduleList(int numSubj) {

		int [] sched = new int[numSubj];
		traverseGraph(sched, numSubj, validChoiceArr(),0);

	}

	/**
	 * Creates all possible combinations of valid subjects and adds them to the list of schedules
	 * @param sched int[] that keeps current combination
	 * @param numS int the number of subjects that are yet to be added
	 * @param chFrom int[] that keeps all valid and unused subjects
	 * @param gen - int the round of recursion
	 */
	private void traverseGraph(int[] sched, int numS,
			int[] chFrom, int gen) {

		/*
		 * As long as there are enough valid subjects to finish the combination
		 * And the subjects needed to be added
		 * And there exists at least one valid subject 
		 */
		while(countValidSubj(chFrom)>=numS&& numS!=0 &&findFirstValid(chFrom)!=-1)
		{
			//set the current elt of the combination to the first valid subject from array of the valid subjects
			sched[gen] = chFrom[findFirstValid(chFrom)];
			//set it == -1 (invalid for further use in this combination) in the array of the valid subjects 
			chFrom[findFirstValid(chFrom)] = -1;
			//copy the array of the valid subjects to pass through recursion
			int[] newChFrom = chFrom.clone();

			//if the combination is complete - add to the list of combinations
			if(numS==1)
			{
				schLists.add(sched.clone());
			}

			//recursion; the number of subj to add decrease, the gen - increases 
			traverseGraph(sched, numS-1,newChFrom,gen+1);

		}
	}

	/**
	 * Creates an array to choose combinations from
	 * @return int[] array of indexes to choose schedules from
	 */
	private int[] validChoiceArr()
	{
		int[] chooseFrom = new int[countValidSubj(degrees)];

		for(int i = 0; i<degrees.length; i++)
		{
			if(degrees[i]!=-1)
				chooseFrom[i]=i;
		}
		return chooseFrom;

	}

	/**
	 * Counts subjects that are valid for making a schedule 
	 * @param array - int [] array containing degrees of subjects
	 * @return int number of valid subjects
	 */
	private int countValidSubj(int [] array)
	{
		int validSubj = 0;
		for(int i = 0; i<array.length; i++)
			
		{
			if(array[i]!=-1)
				validSubj++;
		}
		return validSubj;

	}

	/**
	 * Finds first valid element in the passed array 
	 * Valid means != -1
	 * @param validChoiceArr - the array to find a valid element in
	 * @return  the index of tne first valid in the passed array
	 */
	private int findFirstValid(int[] validChoiceArr) {
		for(int i = 0; i<validChoiceArr.length; i++)
		{
			if(validChoiceArr[i]!=-1)
				return i;
		}
		return -1;
	}

	/**
	 * verifySchedules - verifies that all the combinations of subjects 
	 * in idSubjs are interconnected
	 * If not - deletes such a combination
	 */
	private void verifySchedules()
	{
		for(int i = 0; i<schLists.size(); i++)
		{
			if(false==isInterconnected(schLists.get(i),0))
			{
				schLists.remove(i);
				i--;
			}

		}
	}


	/**
	 * Checks if the graph is complete
	 * Employs recursion to compare subject with all subsequent ones
	 * @param combination - int[] a combination of sched nodes (vertexes of a graph) 
	 * @param gen - the round of recursion
	 * @return true if graph is complete
	 * false if it isn't
	 */
	private boolean isInterconnected(int[] combination, int gen)
	{
		//copies a row from adj matrix that
		//corresponds to the connections of the element with others
		boolean[] compareTo = new boolean [matrix[0].length];
		//combination[gen] = index of the row in the matrix
		compareTo=matrix[combination[gen]];

		//gen +1 - in order not to compare to itself
		for(int i = gen+1; i<combination.length; i++)
		{
			//if there is a conflict - the combination id not valid, return falsr 
			if(compareTo[combination[i]] == true)
				return false;
		}
		//if the method hasn't checked connections between all the elements 
		// recursion
		if( gen<combination.length-1)
			return isInterconnected(combination, gen+1);

		return true;
	}

	/**
	 * The Logic class
	 * Calls all function that needed to be called
	 *  for scheds construction and veryfiing their validity 
	 */
	public void mainLogic()
	{
		
		createScheduleList(minNumSubj);
		verifySchedules();
	}

}
