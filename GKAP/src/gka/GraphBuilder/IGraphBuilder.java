package gka.GraphBuilder;


import edu.uci.ics.jung.graph.Graph;
import gka.Exceptions.GraphBuildException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.Collection;
import java.util.List;

public interface IGraphBuilder {


	/**
	 * Build graph by List<String> input
	 * @param list
	 * @return
	 * 		Graph Interface with specific Graph Class
	 * 		The specific Graph decides based with list content
	 * 
	 * @throws GraphBuildException
	 */
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) throws GraphBuildException;
	
	/**
	 * Add Vertex
	 * Add Vertex to the Graph
	 * 
	 * @param vertex
	 * @return true if Vertex does not exist in Graph
	 */
	public boolean addVertex(String vertex);
	
	/**
	 * Add Vertex with Attribute
	 * Add Vertex to the Graph
	 * 
	 * @param vertex
	 * @return true if Vertex does not exist in Graph
	 */
	public boolean addVertex(String vertex, int attribute);
	
	/**
	 * Remove Vertex
	 * Remove Vertex from Graph
	 * 
	 * @param vertex
	 * @return true if Vertex exists in Graph
	 */
	public boolean removeVertex(String vertex);
	
	/**
	 * Add Edge
	 * Add Edge to the Graph
	 * 
	 * @param edge
	 * @param v1
	 * @param v2
	 * 
	 * @return true if both Vertices exists
	 * 
	 * ************************************
	 * NOTE: If weight value equals 0, then default Edge
	 */
	public boolean addEdge(int weight, String v1, String v2);
	
	/**
	 * Remove Edge
	 * Removes Edge from Graph
	 * 
	 * @param edgeID
	 * @return true if Edge found By ID / Edge exists in Graph
	 */
	public boolean removeEdge(long edgeID);
	
	/**
	 * Get All Vertices 
	 * Get a List with all Vertices from Graph
	 * 
	 * @return List with Vertices
	 * 
	 * If Graph is empty, returns empty List
	 */
	public Collection<OwnVertex> getAllVertices();
	
	/**
	 * Get All Vertices 
	 * Get a List with all Vertices from Graph
	 * 
	 * @return List with Vertices
	 * 
	 * If Graph is empty, returns empty List
	 */
	public List<String> getAllVerticesAsString();
	
	/**
	 * Get All Edges
	 * Get a List with all Edges from Graph
	 * 
	 * @return List with all Edges
	 * 
	 * If Graph has no Edges, returns empty List
	 */
	public Collection<OwnEdge> getAllEdges();
	
	/**
	 * Get All Edges
	 * Get a List with all Edges from Graph
	 * 
	 * @return List with all Edges
	 * 
	 * If Graph has no Edges, returns empty List
	 */
	public List<String> getAllEdgesAsString();
	
	/**
	 * Get Vertex By Name
	 * Try to search a Vertex by Name
	 * 
	 * @param name
	 * @return Vertex or null 
	 */
	public OwnVertex getVertexByName(String name);
	
	/**
	 * Get Graph Type
	 * Get a Graph Type from loaded Graph.
	 * @require loaded Graph
	 * @return GraphType
	 */
	public String getGraphType();
	
	/**
	 * Get Saveable Graph
	 * Convert a Graph to a List
	 * 
	 * @return List with Graph content
	 */
	public List<String> getSaveableGraph();
}
