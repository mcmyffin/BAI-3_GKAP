package AlgorithmManagerTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gka.AlgorithmManager.BFS_Report;
import gka.AlgorithmManager.AlgorithmManager;
import gka.AlgorithmManager.IAlgoReport;
import gka.AlgorithmManager.IAlgorithManager;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.FileManager.FileManager;
import gka.FileManager.IFileManager;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.IGraphBuilder;
import gka.GraphBuilder.Extension.OwnVertex;

import org.junit.Test;

public class BFSTest {

	IAlgorithManager am;
	IFileManager fm;
	IGraphBuilder gb;
	String testFilePath = "/home/dima/Studium/4_Semester_SoSe205/Git/BAI-3_GKAP/testFiles/";
	
	
	
	// DIRECTED TESTS *****************************
	
	/**
	 * Test BFS with DIRECTED Graph
	 * Test part 1
	 * File 'directedTest.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBFSDirected1() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException {
		
		fm = new FileManager();
		gb = new GraphBuilder();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"directedTest.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		am = new AlgorithmManager(gb.buildGraph(directedGraphContent));
		
		// create start & goal Nodes
		OwnVertex start_node = gb.getVertexByName("v1");
		OwnVertex goal_node = gb.getVertexByName("v9");
		
		// start BFS
		IAlgoReport report = am.startBFS(start_node, goal_node);
		
		// test for path count
		assertTrue(report.getPathLength() == 4);
	}
	
	/**
	 * Test BFS with DIRECTED Graph
	 * Test part 2
	 * File 'directedTest.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBFSDirected2() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		fm = new FileManager();
		gb = new GraphBuilder();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"directedTest.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		am = new AlgorithmManager(gb.buildGraph(directedGraphContent));
		
		// create start & goal Nodes
		OwnVertex start_node = gb.getVertexByName("v3");
		OwnVertex goal_node = gb.getVertexByName("v5");
		
		// start BFS
		IAlgoReport report = am.startBFS(start_node, goal_node);
		
		// Create expected Result
		OwnVertex v3 = gb.getVertexByName("v3");
		OwnVertex v1 = gb.getVertexByName("v1");
		OwnVertex v2 = gb.getVertexByName("v2");
		OwnVertex v6 = gb.getVertexByName("v6");
		OwnVertex v5 = gb.getVertexByName("v5");
		
		OwnVertex[] list = {v3, v1, v2, v6, v5};
		List<OwnVertex> expectedPath = new ArrayList<OwnVertex>(Arrays.asList(list));

		// test for path count
		assertTrue(report.getPathLength() == 4);
	}
	
	// ********************************************
	// UNDIRECTED TESTS ***************************
	
	
	/**
	 * Test BFS with UNDIRECTED Graph
	 * Test part 1
	 * File 'undirectedTest.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBFSUndirected1() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		fm = new FileManager();
		gb = new GraphBuilder();
		
		File undirectedGraphFile = new File(testFilePath+File.separatorChar+"undirectedTest.graph");
		List<String> undirectedGraphContent = fm.loadFile(undirectedGraphFile);
		
		am = new AlgorithmManager(gb.buildGraph(undirectedGraphContent));
		
		// create start & goal Nodes
		OwnVertex start_node = gb.getVertexByName("v3");
		OwnVertex goal_node = gb.getVertexByName("v8");
		
		// start BFS
		IAlgoReport report = am.startBFS(start_node, goal_node);
		
		// test for path count
		assertTrue(report.getPathLength() == 3);
	}

	
	/**
	 * Test BFS with UNDIRECTED Graph
	 * Test part 2
	 * File 'undirectedTest.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBFSUndirected2() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		fm = new FileManager();
		gb = new GraphBuilder();
		
		File undirectedGraphFile = new File(testFilePath+File.separatorChar+"undirectedTest.graph");
		List<String> undirectedGraphContent = fm.loadFile(undirectedGraphFile);
		
		am = new AlgorithmManager(gb.buildGraph(undirectedGraphContent));
		
		// create start & goal Nodes
		OwnVertex start_node = gb.getVertexByName("v1");
		OwnVertex goal_node = gb.getVertexByName("v6");
		
		// start BFS
		IAlgoReport report = am.startBFS(start_node, goal_node);
		
		// test for path count
		assertTrue(report.getPathLength() == 4);
	}
}
