package tester;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import  Logic.SchedSolver;
import org.junit.jupiter.api.Test;

class SchedSolverTest {

	private static boolean [][] matrix = new boolean[9][9];
	private static SchedSolver testSched;
	
	@Before
	public static void setMatrix() {

		for(int i = 0; i < matrix.length;i++)
		{
			for(int j = 0;  j < matrix[0].length;j++)
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
	void mainLogicTest()
	{
		//setMatrix();
				
		testSched = new SchedSolver(matrix, 5);
		
		//testSched.mainLogic();
		
		/*for(int i = 0;i<testSched.idSubjs.size();i++)
		{
			int [] arr = testSched.idSubjs.get(i);
			for(int i1 = 0;i1<arr.length;i1++ )
				System.out.print(arr[i1]);
			System.out.print("\n");
		
		}*/

	}

}
