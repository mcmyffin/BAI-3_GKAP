package gka.GraphVisualControler;

import java.io.File;
import java.util.List;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.AlgoReport;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public interface IGraphManager {

	/**
	 * Load Graph
	 * Load a graph from "path" an returns VisulalizationViewr (Component).
	 * That Component draw graph.
	 * 
	 * @param path
	 * @return VisualizationViewer<OwnVertex, OwnEdge>
	 * OR
	 * @throws FileNotFoundException
	 * @throws WrongFileTypeException
	 * @throws AccessException
	 * @throws GraphBuildException
	 */
	public VisualizationViewer<OwnVertex, OwnEdge>  loadGraph(String path) throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException;
	
	
	/**
	 * Save Graph
	 * Try to save Graph at file
	 * 
	 * @param path
	 * @return true if graph saved
	 */
	public boolean saveGraph(File path);
	
	/**
	 * Get Graph Type
	 * Gives graph Type from loaded Graph
	 * @return String
	 * 
	 * Example:
	 * 		DIRECTED
	 * 		UNDIRECTED
	 * 		...
	 */
	public String getGraphType();
	
	/**
	 * Add Vertex At
	 * Add a Vertex to the graph at specific position (x,y)
	 * 
	 * @param vertex
	 * @param x
	 * @param y
	 * 
	 * @return true if Vertex does not exist
	 */
	public boolean addVertexAt(OwnVertex vertex, int x ,int y);
	
	/**
	 * Add Vertex
	 * Add a Vertex to the graph
	 * 
	 * @param vertex
	 * @return true if Vertex does not exist
	 */
	public boolean addVertex(OwnVertex vertex);
	
	/**
	 * Add Edge
	 * Add a Edge to the graph
	 * 
	 * @param edge
	 * @param v1
	 * @param v2
	 * 
	 * @return true if Edge does not exist
	 */
	public boolean addEdge(OwnEdge edge, OwnVertex v1, OwnVertex v2);
	
	/**
	 * Remove Vertex
	 * Remove a Vertex from graph
	 * If some Edges connected, then they are also removed
	 * 
	 * @param vertex
	 * @return true if Vertex exists
	 */
	public boolean removeVertex(OwnVertex vertex);
	
	/**
	 * Remove Edge
	 * Remove a Edge by EdgeID
	 * 
	 * @param edgeID
	 * @return true if Edge found by id
	 */
	public boolean removeEdge(long edgeID);
	
	/**
	 * Get All Vertices
	 * Get List<OwnVertex> from Graph if graph not empty
	 * If graph is empty, return empty List
	 * @return List<OwnVertex>
	 */
	public List<OwnVertex> getAllVertices();
	
	/**
	 * Get All Edges
	 * Get List<OwnEde> from graph, if Edges contains some
	 * If graph does not have Edges, return empty List
	 * @return List<OwnEdge>
	 */
	public List<OwnEdge> getAllEdges();
	
	/**
	 * Get Vertex By Name
	 * Try to Search a Vertex by unique Name.
	 * @param name
	 * 
	 * @return OwnVertex if Vertex found by Name
	 * 		   else null.
	 */
	public OwnVertex getVertexByName(String name);

	
	/**
	 * Set Transform Mode
	 * Set the VisualizationViewer Component in Transform Mode
	 * Mouse can move Background in Component
	 * Mouse can zoom in/out in Component
	 * @require loaded Graph
	 */
	public void setTrasformMode();
	
	/**
	 * Set Pic Mode
	 * Set the VisualizationViewer Component in Pic Mode
	 * Mouse can move/select Vertex/Vertices in Component
	 * Mouse can zoom in/out in Component
	 */
	public void setPicMode();
	
	
	/**
	 * Breadth First Search Algorithm
	 * Start BFS Algorithm in Graph
	 * 
	 * @param start
	 * @param goal
	 * 
	 * @return AlgoReport
	 */
	public AlgoReport breadthFirstSearch(OwnVertex start, OwnVertex goal);
	
}
