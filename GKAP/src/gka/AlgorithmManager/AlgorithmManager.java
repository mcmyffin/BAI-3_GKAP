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
		reporter.stopTimer();
		
		return reporter;
	}

	@Override
	public AlgoReport startDijkstra(OwnVertex start_node, OwnVertex goal_node) {
		
		AlgoReport reporter = new AlgoReport("Dijkstra");
		Dijkstra dijkstra = new Dijkstra(_graph, start_node, goal_node, reporter);
		
		reporter.startTimer();
		dijkstra.startDijkstra();
		reporter.stopTimer();
		
		return reporter;
	}

	@Override
	public AlgoReport startASternchen(OwnVertex start_node, OwnVertex goal_node) {

		AlgoReport reporter = new AlgoReport("A*");
		ASternchen asternchen = new ASternchen(_graph, start_node, goal_node, reporter);
		
		reporter.startTimer();
		asternchen.startASternchen();
		reporter.startTimer();
		
		return reporter; 
	}

}
