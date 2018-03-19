package Tester;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import Database.Section;
import Database.Subject;
import Database.TimePeriod;
import Logic.SchedSolver;

public class SchedSolverTest {

	private boolean [][] matrix;
	private SchedSolver testSched;
	
	private ArrayList<Subject> classesList;
	private Subject physics;
	private Subject computerScience;
	private Subject geography;		
	
	public void setMatrix() {	
		
		matrix = new boolean[9][9];
			
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
	
	public void initClasses() {
		// 2 nodes
		physics = new Subject();
		TimePeriod[] physLecture1 = {new TimePeriod(), null, new TimePeriod(), null, new TimePeriod()}; 	
		TimePeriod[] physLab1 = {new TimePeriod(), null, null, null, null}; 	
		TimePeriod[] physLab2 = {null, new TimePeriod(), null, null, null};
		physics.addLectureSection(new Section(physics, physLecture1, false)); 	
		physics.addLabSection(new Section(physics, physLab1, true));
		physics.addLabSection(new Section(physics, physLab2, true));
		
		// 8 nodes
		computerScience = new Subject();
		TimePeriod[] comSciLecture1 = {new TimePeriod(), null, new TimePeriod(), null, new TimePeriod()}; 	
		TimePeriod[] comSciLecture2 = {null, new TimePeriod(), null, new TimePeriod(), new TimePeriod()}; 	
		TimePeriod[] comSciLab1 = {new TimePeriod(), null, null, null, null}; 	
		TimePeriod[] comSciLab2 = {null, new TimePeriod(), null, null, null};
		TimePeriod[] comSciLab3 = {null, null, new TimePeriod(), null, null}; 	
		TimePeriod[] comSciLab4 = {null, null, null, new TimePeriod(), null};
		computerScience.addLectureSection(new Section(computerScience, comSciLecture1, false));
		computerScience.addLectureSection(new Section(computerScience, comSciLecture2, false));
		computerScience.addLabSection(new Section(computerScience, comSciLab1, true));
		computerScience.addLabSection(new Section(computerScience, comSciLab2, true));
		computerScience.addLabSection(new Section(computerScience, comSciLab3, true));
		computerScience.addLabSection(new Section(computerScience, comSciLab4, true));
		
		// 1 node
		geography = new Subject();
		TimePeriod[] geographyLecture1 = {null, new TimePeriod(), null, new TimePeriod(), null}; 	
		geography.addLectureSection(new Section(geography, geographyLecture1, false));
		
		classesList = new ArrayList<Subject>();
		classesList.add(physics);
		classesList.add(computerScience);
		classesList.add(geography);
	}

	@Test
	public void algorithmTest() {
		
		setMatrix();			
		testSched = new SchedSolver(matrix, 5);	
		assertEquals(testSched.getSchLists().size(), 6);

	}
	
	@Test 
	public void classesToNodesTest() {
		
		initClasses();
		testSched = new SchedSolver(classesList, 5);
		assertEquals(testSched.getNumNodes(), 11);
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
