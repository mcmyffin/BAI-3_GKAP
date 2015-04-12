package gka.AlgorithmManager;

import gka.GraphBuilder.Extension.OwnVertex;

public interface IAlgorithManager {

	/**
	 * Start Breadth First Search Algorithm
	 *
	 * @param start_node
	 * @param goal_node
	 * @return AlgoReport with result Information
	 */
	public AlgoReport startBFS(OwnVertex start_node, OwnVertex goal_node);
}
