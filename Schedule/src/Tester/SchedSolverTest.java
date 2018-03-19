package Tester;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import Logic.SchedSolver;

public class SchedSolverTest {

	private boolean [][] matrix = new boolean[9][9];;
	private SchedSolver testSched;
	
	@Before
	public void setMatrix() {
		
		matrix[0][0]=true;
		matrix[1][1]=true;
		matrix[2][2]=true;
		matrix[3][3]=true;
		matrix[4][4]=true;
		matrix[5][5]=true;
		matrix[6][6]=true;
		matrix[7][7]=true;
		matrix[8][8]=true;	
		
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

	@Test
	public void algorithmTest() {
		
		setMatrix();
				
		SchedSolver testSched = new SchedSolver(matrix, 5);	

	}
	
	/*
	private void createValidSchedules(int numS)
	{
		long start;
		long finish;

		System.out.println("Initial version");
		start = System.nanoTime();
		traverseGraph(new int[numS], numS, matrixJ.validNodes(), 0);
		verifySchedules();
		finish = System.nanoTime();
		System.out.println("Time: " + (finish - start));
		//System.out.println("size: " + schLists.size());

		System.out.println();

		System.out.println("Updated version");
		schLists = new ArrayList<int[]>();
		start = System.nanoTime();
		traverseGraphNew(new int[numS], numS, matrixJ.validNodes(), 0);
		finish = System.nanoTime();
		System.out.println("Time: " + (finish - start));
		//System.out.println("size: " + schLists.size());

		System.out.println();

		System.out.println("Satisfying version");
		schLists = new ArrayList<int[]>();
		start = System.nanoTime();
		traverseGraphNewNew(new int[numS], matrixJ.validNodes(), numS, 0, 0);
		finish = System.nanoTime();
		System.out.println("Time: " + (finish - start));	
		//System.out.println("size: " + schLists.size());
	}*/

}
