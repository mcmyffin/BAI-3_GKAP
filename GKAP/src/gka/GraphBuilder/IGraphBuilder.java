package gka.GraphBuilder;


import edu.uci.ics.jung.graph.Graph;
import gka.Exceptions.GraphBuildException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.List;
import java.util.Map;

public interface IGraphBuilder {


	/**
	 * Build graph by List<String> input
	 * @param list
	 * @return
	 * 		Graph Interface with specific Graph Class
	 * 		The specific Graph decides based with list content
	 */
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) throws GraphBuildException;
	
	public boolean addVertex(OwnVertex vertex);
	public boolean removeVertex(OwnVertex vertex);
	
	public boolean addEdge(OwnEdge edge, OwnVertex v1, OwnVertex v2);
	public boolean removeEdge(long edgeID);
	
	public List<OwnVertex> getAllVertices();
	public List<OwnEdge> getAllEdges();
	
	public OwnVertex getVertexByName(String name);
	
	public String getGraphType();
}
