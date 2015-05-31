package gka.GraphVisualControler;

import java.io.File;
import java.util.List;

import javafx.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.Extension.BFS_Report;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.GraphType;
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
	 * Add Vertex
	 * Add a Vertex to the graph
	 * 
	 * @param vertex
	 * @return true if Vertex does not exist
	 */
	public boolean addVertex(String vertex);
	
	/**
	 * Add Vertex
	 * Add a Vertex to the graph
	 * 
	 * @param vertex
	 * @param attribute
	 * @return true if Vertex does not exist
	 */
	public boolean addVertex(String vertex, int attribute);
	
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
	public boolean addEdge(int weight, String v1, String v2);
	
	/**
	 * Remove Vertex
	 * Remove a Vertex from graph
	 * If some Edges connected, then they are also removed
	 * 
	 * @param vertex
	 * @return true if Vertex exists
	 */
	public boolean removeVertex(String vertex);
	
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
	public List<String> getAllVerticesAsString();
	
	/**
	 * Get All Edges
	 * Get List<OwnEde> from graph, if Edges contains some
	 * If graph does not have Edges, return empty List
	 * @return List<OwnEdge>
	 */
	public List<String> getAllEdgesAsString();
	
	/**
	 * Get Vertex By Name
	 * Try to Search a Vertex by unique Name.
	 * @param name
	 * 
	 * @return OwnVertex if Vertex found by Name
	 * 		   else null.
	 */
//	public OwnVertex getVertexByName(String name);

	
	/**
	 * Set Transform Mode
	 * Set the VisualizationViewer Component in Transform Mode
	 * Mouse can move Background in Component
	 * Mouse can zoom in/out in Component
	 * @require loaded Graph
	 */
	public void setTrasformMode(VisualizationViewer viewer);
	
	/**
	 * Set Piking Mode
	 * Set the VisualizationViewer Component in Piking Mode
	 * Mouse can move/select Vertex/Vertices in Component
	 * Mouse can zoom in/out in Component
	 */
	public void setPicMode(VisualizationViewer viewer);
	
	
	/**
	 * Breadth First Search Algorithm
	 * Start BFS Algorithm in Graph
	 * 
	 * @param start
	 * @param target
	 * 
	 * @return AlgoReport
	 */
	public IAlgoReport startBreadthFirstSearch(String start, String target);
	
	/**
	 * Dijkstra Algorithm
	 * Start Dijkstra Algorithm in Graph
	 * 
	 * @param start
	 * @param target
	 * 
	 * @return AlgoReport
	 */
	public IAlgoReport startDijkstra(String start, String target);
	
	/**
	 * A* Algorithm
	 * Start A* Algorithm in Graph
	 * 
	 * @param start
	 * @param target
	 * 
	 * @return AlgoReport
	 */
	public IAlgoReport startAStar(String start, String target);
	
	/**
	 * Kruskal Algorithm
	 * Start Kruskal Algorithm in Graph
	 * 
	 * @return AlgoReport
	 */
	public Pair<IAlgoReport,VisualizationViewer> startKruskal();
	
	/**
	 * Prim Algorithm
	 * Start Prim Algorithm in Graph
	 * 
	 * @param start
	 * @param withFibHeap
	 * @return Pair<AlgoReport,VisualizationView>
	 */
	public Pair<IAlgoReport,VisualizationViewer> startPrim(boolean withFibHeap);
	
	/**
	 * Generate new Graph
	 * 
	 * @param graphType
	 * @return
	 * @throws GraphBuildException
	 */
	public VisualizationViewer<OwnVertex, OwnEdge>  generateNewGraph(int vertices, int edges, 
								boolean coherently, GraphType...graphTypes) throws GraphBuildException;
}
