package AlgorithmManagerTest;

import static org.junit.Assert.*;
import edu.uci.ics.jung.graph.Graph;
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
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {

	IAlgorithManager am;
	static IFileManager fm;
	static IGraphBuilder gb;
	String testFilePath = "/home/dima/Studium/4_Semester_SoSe205/Git/BAI-3_GKAP/testFiles/";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fm = new FileManager();
		gb = new GraphBuilder();
	}

	@Test
	public void testDijkstraUndirected() throws GraphBuildException, FileNotFoundException, WrongFileTypeException, AccessException {
		File undirectedGraphFile = new File(testFilePath+File.separatorChar+"dijkstraTest.graph");
		Graph<OwnVertex,OwnEdge> g = gb.buildGraph(fm.loadFile(undirectedGraphFile));
		am = new AlgorithmManager(g);
		
		OwnVertex target = gb.getVertexByName("v8");
		
		OwnVertex start1 = gb.getVertexByName("v1"); // 60
		OwnVertex start2 = gb.getVertexByName("v2"); // 20
		OwnVertex start3 = gb.getVertexByName("v3"); // 20
		
		assertEquals(am.startAStar(start1, target).getPathLength(), 60);
		assertEquals(am.startAStar(start2, target).getPathLength(), 20);
		assertEquals(am.startAStar(start3, target).getPathLength(), 20);
	}
	
	@Test
	public void testDijkstraDirected() throws GraphBuildException, FileNotFoundException, WrongFileTypeException, AccessException{
		File directedGraphFile = new File(testFilePath+File.separatorChar+"dijkstraDirectedTest.graph");
		Graph<OwnVertex,OwnEdge> g = gb.buildGraph(fm.loadFile(directedGraphFile));
		am = new AlgorithmManager(g);
		
		OwnVertex target = gb.getVertexByName("v8");
		
		OwnVertex start1 = gb.getVertexByName("v7"); // 31
		OwnVertex start2 = gb.getVertexByName("v1"); // 0
		OwnVertex start3 = gb.getVertexByName("v5"); // 13
		
		assertEquals(am.startDijkstra(start1, target).getPathLength(), 31);
		assertEquals(am.startDijkstra(start2, target).getPathLength(), 0);
		assertEquals(am.startDijkstra(start3, target).getPathLength(), 13);
		
	}


}
