package gka.AlgorithmManager;

import javafx.util.Pair;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.GraphBuilder.Extension.OwnVertex;

public interface IAlgorithManager{

	/**
	 * Start Breadth First Search Algorithm
	 *
	 * @param start_node
	 * @param goal_node
	 * @return AlgoReport with result Information
	 */
	public IAlgoReport startBFS(OwnVertex start_node, OwnVertex goal_node);
	
	
	/**
	 * Start Dijkstra Algorithm
	 * @param start_node
	 * @param goal_node
	 * @return AlgoReport with result Information
	 */
	public IAlgoReport startDijkstra(OwnVertex start_node, OwnVertex goal_node);
	
	
	/**
	 * Start AStar Algorithm
	 * 
	 * @param start_node
	 * @param goal_node
	 * @return AlgoReport with result Information
	 */
	public IAlgoReport startAStar(OwnVertex start_node, OwnVertex goal_node);
	
	
	/**
	 * Start Kruskal Algorithm
	 * 
	 * @return AlgoReport with result Information
	 */
	public Pair<IAlgoReport,Graph> startKruksal();
	
	
	/**
	 * Start Prim Algorithm
	 *  
	 * @param start_node
	 * @return AlgoReport with result Information
	 */
	public Pair<IAlgoReport,Graph> startPrim(boolean withFibHeap);
}
