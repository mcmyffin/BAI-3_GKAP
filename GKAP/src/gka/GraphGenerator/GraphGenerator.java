package gka.GraphGenerator;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class GraphGenerator implements IGraphGenerator{
	
	
	@Override
	public void generateUndirectedWeightedGraph(Graph<OwnVertex, OwnEdge> emptyGraph, 
			int vertices, int edges,boolean coherently) {

		UndirectedWeightedGenerator gen = new UndirectedWeightedGenerator(emptyGraph);
		gen.generate(vertices, edges, coherently);
		
	}


	@Override
	public void generateUndirectedWeightedAttributedGraph(Graph<OwnVertex, OwnEdge> emptyGraph, 
			int vertices, int edges, boolean coherently) {
		
		UndirectedWeightedAttributedGenerator gen = new UndirectedWeightedAttributedGenerator(emptyGraph);
		gen.generate(vertices, edges, coherently);
	}

}
