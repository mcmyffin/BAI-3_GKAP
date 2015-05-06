package gka.AlgorithmManager;

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
	 * Start ASternchen Algorithm
	 * @param start_node
	 * @param goal_node
	 * @return AlgoReport with result Information
	 */
	public IAlgoReport startAStar(OwnVertex start_node, OwnVertex goal_node);
}
