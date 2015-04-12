package gka.AlgorithmManager;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class AlgorithmManager implements IAlgorithManager{

	private Graph<OwnVertex, OwnEdge> _graph;
	
	public AlgorithmManager(Graph<OwnVertex, OwnEdge> graph) {
		this._graph = graph;
	}
	
	@Override
	public AlgoReport startBFS(OwnVertex start_node, OwnVertex goal_node) {
		
		AlgoReport reporter = new AlgoReport("Breadth First Search");
		BFS bfs = new BFS(_graph, start_node, goal_node, reporter);
		
		reporter.startTimer();
		bfs.startbreadthSearch();
		System.out.println(reporter.stopTimer());
		
		return reporter;
	}

}
