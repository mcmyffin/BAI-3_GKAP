package AlgorithmManagerTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gka.AlgorithmManager.AlgoReport;
import gka.AlgorithmManager.AlgorithmManager;
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
		AlgoReport report = am.startBFS(start_node, goal_node);
		
		// Create expected Result
		OwnVertex v1 = gb.getVertexByName("v1");
		OwnVertex v2 = gb.getVertexByName("v2");
		OwnVertex v6 = gb.getVertexByName("v6");
		OwnVertex v3 = gb.getVertexByName("v3");
		OwnVertex v9 = gb.getVertexByName("v9");
		
		OwnVertex[] list = {v1,v2,v6,v3,v9};
		List<OwnVertex> expectedPath = new ArrayList<OwnVertex>(Arrays.asList(list));

		// test for path count
		assertTrue(report.getPath().size() == expectedPath.size());
		
		// test for path
		assertTrue(report.getPath().equals(expectedPath));
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
		AlgoReport report = am.startBFS(start_node, goal_node);
		
		// Create expected Result
		OwnVertex v3 = gb.getVertexByName("v3");
		OwnVertex v1 = gb.getVertexByName("v1");
		OwnVertex v2 = gb.getVertexByName("v2");
		OwnVertex v6 = gb.getVertexByName("v6");
		OwnVertex v5 = gb.getVertexByName("v5");
		
		OwnVertex[] list = {v3, v1, v2, v6, v5};
		List<OwnVertex> expectedPath = new ArrayList<OwnVertex>(Arrays.asList(list));

		// test for path count
		assertTrue(report.getPath().size() == expectedPath.size());
		
		// test for path
		assertTrue(report.getPath().equals(expectedPath));
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
		AlgoReport report = am.startBFS(start_node, goal_node);
		
		// Create expected Result
		OwnVertex v3 = gb.getVertexByName("v3");
		OwnVertex v7 = gb.getVertexByName("v7");
		OwnVertex v4 = gb.getVertexByName("v4");
		OwnVertex v8 = gb.getVertexByName("v8");
		
		
		OwnVertex[] list = {v3, v7, v4, v8};
		List<OwnVertex> expectedPath = new ArrayList<OwnVertex>(Arrays.asList(list));

		// test for path count
		assertTrue(report.getPath().size() == expectedPath.size());
		
		// test for path
		assertTrue(report.getPath().equals(expectedPath));
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
		AlgoReport report = am.startBFS(start_node, goal_node);
		
		// Create expected Result
		OwnVertex v1 = gb.getVertexByName("v1");
		OwnVertex v8 = gb.getVertexByName("v8");
		OwnVertex v4 = gb.getVertexByName("v4");
		OwnVertex v7 = gb.getVertexByName("v7");
		OwnVertex v6 = gb.getVertexByName("v6");
		
		OwnVertex[] list = {v1, v8, v4, v7, v6};
		List<OwnVertex> expectedPath = new ArrayList<OwnVertex>(Arrays.asList(list));

		// test for path count
		assertTrue(report.getPath().size() == expectedPath.size());
		
		// test for path
		assertTrue(report.getPath().equals(expectedPath));
	}
}
