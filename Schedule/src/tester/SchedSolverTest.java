package tester;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import  Logic.SchedSolver;
import org.junit.jupiter.api.Test;

class SchedSolverTest {

	private boolean [][] matrix;

	

	//fill it with random data
	//test if it assigns the vertexes correctly 
	
	@Test
	void test() {
		SchedSolver.setMatrix();
		SchedSolver.degreeMatrix(5);
		assertEquals(5,SchedSolver.getDegrees()[2]);
	}

}
