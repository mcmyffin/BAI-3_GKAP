package AlgorithmManagerTest;

import static org.junit.Assert.*;
import javafx.util.Pair;
import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.AlgorithmManager;
import gka.AlgorithmManager.IAlgorithManager;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.Exceptions.GraphBuildException;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.GraphType;
import gka.GraphBuilder.IGraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphGenerator.GraphGenerator;
import gka.GraphGenerator.IGraphGenerator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Kruskal_Prim_Test {
	
	private static IAlgorithManager algorithmManager;
	private static IGraphBuilder graphBuilder;
	private static IGraphGenerator generator;

	@Before
	public void setUpBefore() throws Exception {
		graphBuilder = new GraphBuilder();
		generator = new GraphGenerator();
	}

	@Test
	public void testKruskalPrim_10_100V() throws GraphBuildException {
		
		int vertices = 10;
		
		for(;vertices < 100; vertices++){
			
			// create empty undirected weighted graph
			Graph<OwnVertex,OwnEdge> graph = graphBuilder.createNewGraph(GraphType.WEIGHTED);
			generator.generateUndirectedWeightedGraph(graph, vertices, 0, true);
			
			algorithmManager = new AlgorithmManager(graph);
			
			Kruskal_Prim_Report kruskalReport = (Kruskal_Prim_Report) algorithmManager.startKruksal().getKey();
			Kruskal_Prim_Report primReport 	  = (Kruskal_Prim_Report) algorithmManager.startPrim(false).getKey(); 
			
			assertEquals(primReport.getPathLength(),kruskalReport.getPathLength());
		}
	}

	@Test
	public void testKruskalPrim_100_300V() throws GraphBuildException {
		
		int vertices = 100;
		
		for(;vertices < 300; vertices++){
			
			// create empty undirected weighted graph
			Graph<OwnVertex,OwnEdge> graph = graphBuilder.createNewGraph(GraphType.WEIGHTED);
			generator.generateUndirectedWeightedGraph(graph, vertices, 0, true);
			
			algorithmManager = new AlgorithmManager(graph);
			
			Kruskal_Prim_Report kruskalReport = (Kruskal_Prim_Report) algorithmManager.startKruksal().getKey();
			Kruskal_Prim_Report primReport 	  = (Kruskal_Prim_Report) algorithmManager.startPrim(false).getKey(); 
			
			assertEquals(primReport.getPathLength(),kruskalReport.getPathLength());
		}
	}

	
	
	@Test
	public void testKruskalPrim_1000_1020V() throws GraphBuildException {
		
		int vertices = 1000;
		
		for(;vertices < 1020; vertices++){
			
			// create empty undirected weighted graph
			Graph<OwnVertex,OwnEdge> graph = graphBuilder.createNewGraph(GraphType.WEIGHTED);
			generator.generateUndirectedWeightedGraph(graph, vertices, 0, true);
			
			algorithmManager = new AlgorithmManager(graph);
			
			Kruskal_Prim_Report kruskalReport = (Kruskal_Prim_Report) algorithmManager.startKruksal().getKey();
			Kruskal_Prim_Report primReport 	  = (Kruskal_Prim_Report) algorithmManager.startPrim(false).getKey(); 
			
			assertEquals(primReport.getPathLength(),kruskalReport.getPathLength());
		}
	}
	
	
	@Test
	public void testRuntimePrim() throws GraphBuildException{
		
		Graph<OwnVertex,OwnEdge> graph = graphBuilder.createNewGraph(GraphType.WEIGHTED);
		generator.generateUndirectedWeightedGraph(graph, 20000, 40000, true);
		
		algorithmManager = new AlgorithmManager(graph);
		
		IAlgoReport primReport 	  = algorithmManager.startPrim(false).getKey();
		
		System.out.println(primReport.toString());
	}
}
