package gka.GraphGenerator;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class GraphGenerator implements IGraphGenerator{
	
	
	@Override
	public void generateUndirectedWeightedGraph(Graph<OwnVertex, OwnEdge> emptyGraph, 
			int vertices, int edges,int minEdgeLength, int maxEdgeLenth, int verteilungsFaktor) {

		UndirectedWeightedGenerator gen = new UndirectedWeightedGenerator(emptyGraph);
		gen.generate(vertices, edges, minEdgeLength, maxEdgeLenth, verteilungsFaktor);
		
	}


	@Override
	public void generateUndirectedWeightedAttributedGraph(Graph<OwnVertex, OwnEdge> emptyGraph, 
			int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int spread) {
		
		UndirectedWeightedAttributedGenerator gen = new UndirectedWeightedAttributedGenerator(emptyGraph);
		gen.generate(vertices, edges, minEdgeLength, maxEdgeLenth, spread);
	}

}
