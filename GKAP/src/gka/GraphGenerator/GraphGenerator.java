package gka.GraphGenerator;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class GraphGenerator implements IGraphGenerator{
	
	// Edge Length default settings
	public static final int DEAULT_MIN_EDGE_LENGTH 	= 1;
	public static final int DEFAULT_MAX_EDGE_LENGTH = 200;
	
	// Edge spread in Graph
	public static final int PERFECT_SPREAD = 1;
	public static final int NORMAL_SPREAD  = 2;
	public static final int BAD_SPREAD     = 5;
	

	@Override
	public void generateUndirectedWeightedGraph(Graph<OwnVertex, OwnEdge> emptyGraph, 
			int vertices, int edges,int minEdgeLength, int maxEdgeLenth, int verteilungsFaktor) {

		UndirectedWeightedGenerator gen = new UndirectedWeightedGenerator(emptyGraph);
		gen.generate(vertices, edges, minEdgeLength, maxEdgeLenth, verteilungsFaktor);
		
	}

	@Override
	public void generateGraphHeuristic(Graph<OwnVertex, OwnEdge> filledGraph, OwnVertex target) {
		
		HeuristicGenerator hg = new HeuristicGenerator();
		// todo ...
	}
}
