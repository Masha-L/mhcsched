package Logic;
import java.util.ArrayList;
import Database.Subject;

/**
 * The main logic class that creates the schedule options
 */
public class SchedSolver {


	// The adjacentcy matrix
	private boolean [][] matrix;
	// Holds the schedule options: indexes of the nodes
	private ArrayList<int[]> schLists;

	// The node matrix
	private SchedMatrix matrixJ;
	// Schedule nodes
	private SchedNode[] schNodes;
	// Holds the schedule options
	private ArrayList<ArrayList<Subject>> schedules; 

	//Holds the number of the subjects a student wants to take
	//private int minNumSubj;

	/**
	 * Constructor 
	 * 
	 * @param classes - list of classes chosen by user
	 * @param numS - int the number of the subjects the student wants to take
	 */
	public SchedSolver(ArrayList<Subject> classes, int numS) {
		classesToNodes(classes);
		matrixJ = new SchedMatrix(schNodes);
		matrixJ.assignValidity(numS);	
		//creates an empty list for schedule combinations
		schLists = new ArrayList<int[]>();
		mainLogic(numS);
	}




	/**
	 * Creates all possible combinations of valid subjects and adds them to the list of schedules
	 * @param sched 
	 * @param numS int the number of subjects that are yet to be added
	 * @param chFrom int[] that keeps all valid and unused subjects
	 * @param gen - int the round of recursion
	 */
	private void traverseGraph(int[] sched, int leftToAdd, int[] chFrom, int gen) {

		/*
		 * As long as there are enough valid subjects to finish the combination
		 * And the subjects needed to be added
		 * And there exists at least one valid subject 
		 */
		while(matrixJ.countValidSubj(chFrom)>=leftToAdd && leftToAdd!=0 &&findFirstValid(chFrom)!=-1)
		{
			//set the current elt of the combination to the first valid subject from array of the valid subjects
			sched[gen] = chFrom[findFirstValid(chFrom)];
			
			//set it == -1 (invalid for further use in this combination) in the array of the valid subjects 
			chFrom[findFirstValid(chFrom)] = -1;
			
			//copy the array of the valid subjects to pass through recursion
			int[] newChFrom = chFrom.clone();

			//if the combination is complete - add to the list of combinations
			if(leftToAdd==1)
			{
				schLists.add(sched.clone());
			}

			//recursion; the number of subj to add decrease, the gen - increases 
			traverseGraph(sched, leftToAdd-1,newChFrom,gen+1);

		}
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
	 * @param numS 
	 */
	public void mainLogic(int numS)
	{

		int [] sched = new int[numS];
		traverseGraph(sched, numS, matrixJ.validNodes(), 0);
		verifySchedules();
	}


	private void classesToNodes(ArrayList<Subject> classes) {

	}

}
