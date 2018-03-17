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
		//creates an empty list for schedule combinations
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
		//creates an empty list for schedule combinations
		schLists = new ArrayList<int[]>();

		createValidSchedules(numS);
	}


	/**
	 * Creates all possible combinations of valid subjects and adds them to the list of schedules
	 * 
	 * @param sched 
	 * @param numS the number of subjects that are yet to be added
	 * @param chFrom array that keeps all valid and unused subjects
	 * @param gen the round of recursion
	 */
	private void traverseGraph(int[] sched, int leftToAdd, int[] chFrom, int gen) {

		/*
		 * As long as there are enough valid subjects to finish the combination
		 * And the subjects need to be added
		 * And there exists at least one valid subject 
		 */
		while(matrixJ.countValidSubj(chFrom) >= leftToAdd && leftToAdd!=0 && findFirstValid(chFrom) != -1)
		{
			/*
			 * side note: pass startFrom instead of checking firstValid =================================== 
			 */

			// set the current elt of the combination to the first valid subject 
			// from array of the valid subjects
			sched[gen] = chFrom[findFirstValid(chFrom)];

			// set it == -1 (invalid for further use in this combination)
			// in the array of the valid subjects 
			chFrom[findFirstValid(chFrom)] = -1;

			// copy the array of the valid subjects to pass through recursion
			int[] newChFrom = chFrom.clone();

			// if the combination is complete - add to the list of combinations
			if(leftToAdd == 1)
			{
				// VERIFY HERE? ===========================================================================
				schLists.add(sched.clone());
			}

			//recursion; the number of subj to add decrease, the gen - increases 
			traverseGraph(sched, leftToAdd - 1, newChFrom, gen + 1);
		}
	}

	private void traverseGraphNew(int[] sched, int leftToAdd, int[] chFrom, int gen) {

		/*
		 * As long as there are enough valid subjects to finish the combination
		 * And the subjects need to be added
		 * And there exists at least one valid subject 
		 */
		while(matrixJ.countValidSubj(chFrom) >= leftToAdd && leftToAdd!=0 && findFirstValid(chFrom) != -1)
		{
			/*
			 * side note: pass startFrom instead of checking firstValid =================================== 
			 */

			// set the current elt of the combination to the first valid subject 
			// from array of the valid subjects
			sched[gen] = chFrom[findFirstValid(chFrom)];

			// set it == -1 (invalid for further use in this combination)
			// in the array of the valid subjects 
			chFrom[findFirstValid(chFrom)] = -1;

			// copy the array of the valid subjects to pass through recursion
			int[] newChFrom = chFrom.clone();

			// NEW STEP IN THE ALGORITHM
			int[] newPrototype = sched.clone();

			// if the combination is complete - add to the list of combinations
			if(leftToAdd == 1)
			{
				if (isInterconnected(newPrototype, 0))// VERIFY HERE? ===========================================================================
					schLists.add(newPrototype);
			}

			//recursion; the number of subj to add decrease, the gen - increases 
			traverseGraph(sched, leftToAdd - 1, newChFrom, gen + 1);
		}
	}

	private void traverseGraphNewNew(int[] sched, int leftToAdd, int[] chFrom, int gen) {

		/*
		 * As long as there are enough valid subjects to finish the combination
		 * And the subjects need to be added
		 * And there exists at least one valid subject 
		 */
		while(matrixJ.countValidSubj(chFrom) >= leftToAdd && leftToAdd!=0 && findFirstValid(chFrom) != -1)
		{
			/*
			 * side note: pass startFrom instead of checking firstValid =================================== 
			 */

			// set the current elt of the combination to the first valid subject 
			// from array of the valid subjects
			sched[gen] = chFrom[findFirstValid(chFrom)];

			// set it == -1 (invalid for further use in this combination)
			// in the array of the valid subjects 
			chFrom[findFirstValid(chFrom)] = -1;

			// copy the array of the valid subjects to pass through recursion
			int[] newChFrom = chFrom.clone();

			// NEW STEP IN THE ALGORITHM
			int[] newPrototype = sched.clone();

			// if the combination is complete - add to the list of combinations
			if(leftToAdd == 1)
			{
				System.out.println(123);
				boolean hui = isInterconnectedNew(newPrototype, 0);
				if (hui)// VERIFY HERE? ===========================================================================
					schLists.add(newPrototype);
			}

			//recursion; the number of subj to add decrease, the gen - increases 
			traverseGraph(sched, leftToAdd - 1, newChFrom, gen + 1);
		}
	}


	/**
	 * Finds first valid element in the passed array 
	 * Valid means != -1
	 * 
	 * @param validChoiceArr the array to find a valid element in
	 * @return the index of the first valid in the passed array
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
			if(!isInterconnected(schLists.get(i),0))
			{
				schLists.remove(i);
				i--;
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
	private boolean isInterconnected(int[] combination, int gen)
	{
		/*
		 * copies a row from adj matrix that
		 * corresponds to the connections of the element with others
		 * combination[gen] = index of the row in the matrix
		 */
		boolean[] compareTo = matrixJ.getNodeConnections(combination[gen]);

		//gen +1 - in order not to compare to itself
		for(int i = gen + 1; i < combination.length; i++)
		{
			//if there is a conflict - the combination id not valid, return false 
			if(compareTo[combination[i]] == true)
				return false;
		}
		//if the method hasn't checked connections between all the elements 
		// recursion
		if( gen < combination.length - 1)
			return isInterconnected(combination, gen + 1);

		return true;
	}

	private boolean isInterconnectedNew(int[] combination, int gen)
	{
		/*
		 * copies a row from adj matrix that
		 * corresponds to the connections of the element with others
		 * combination[gen] = index of the row in the matrix
		 */
		boolean[] compareTo = matrixJ.getNodeConnections(combination[gen]);

		//gen +1 - in order not to compare to itself
		for(int i = gen + 1; i < combination.length; i++)
		{
			//if there is a conflict - the combination id not valid, return false 
			if(matrixJ.areInConflict(gen, i))
				return false;
		}
		//if the method hasn't checked connections between all the elements 
		// recursion
		if( gen < combination.length - 1)
			return isInterconnected(combination, gen + 1);

		return true;
	}
	
	/**
	 * Traverses the graph to find every combination possible
	 * and verifies the schedules 
	 *  
	 * @param numS 
	 */
	private void createValidSchedules(int numS)
	{
		long start;
		
		/*System.out.println("Old traverse, Old verification");		
		traverseGraph(new int[numS], numS, matrixJ.validNodes(), 0);
		verifySchedules();
		System.out.println("Time: " + (System.nanoTime() - start) + ", " + schLists.size());
		System.out.println();*/

		System.out.println("New traverse, Old verification");
		schLists = new ArrayList<int[]>();
		start = System.nanoTime();
		traverseGraphNew(new int[numS], numS, matrixJ.validNodes(), 0);
		System.out.println("Time: " + (System.nanoTime() - start) + ", " + schLists.size());
		System.out.println();
		
		System.out.println("New traverse, new verification");
		schLists = new ArrayList<int[]>();
		start = System.nanoTime();
		traverseGraphNewNew(new int[numS], numS, matrixJ.validNodes(), 0);
		System.out.println("Time: " + (System.nanoTime() - start) + ", " + schLists.size());
	}


	private void classesToNodes(ArrayList<Subject> classes) {

	}

}
