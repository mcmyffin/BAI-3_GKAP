package gka.GraphGenerator;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public interface IGraphGenerator {

	
	/**
	 * Generate undirected weighted Graph
	 * @require Empty undirected weighted Graph
	 * 
	 * @param emptyGraph
	 * @param vertices
	 * @param edges
	 * @param minEdgeLength
	 * @param maxEdgeLenth
	 * @param verteilungsFaktor
	 * @return true if generate procedure success
	 */
	public void generateUndirectedWeightedGraph(Graph<OwnVertex,OwnEdge> emptyGraph,
						int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int spread);
	
	
	/**
	 * Generate undirected weighted attributed Graph
	 * generate a Heuristic 
	 * 
	 * @require Empty undirected weighted attributed Graph
	 * @param emptyGraph
	 * @param vertices
	 * @param edges
	 * @param minEdgeLength
	 * @param maxEdgeLenth
	 * @param spread
	 */
	public void generateUndirectedWeightedAttributedGraph(Graph<OwnVertex,OwnEdge> emptyGraph,
			int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int spread);
}