package gka.AlgorithmManager;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class AlgorithmManager implements IAlgorithManager{

	private Graph<OwnVertex, OwnEdge> graph;
	
	public AlgorithmManager(Graph<OwnVertex, OwnEdge> graph) {
		this.graph = graph;
	}
	
	@Override
	public IAlgoReport startBFS(OwnVertex start_node, OwnVertex goal_node) {
		
		BFS_Report reporter = new BFS_Report("Breadth First Search");
		BFS bfs = new BFS(graph, start_node, goal_node, reporter);
		
		reporter.startTimer();
		bfs.startbreadthSearch();
		reporter.stopTimer();
		
		return reporter;
	}

	@Override
	public IAlgoReport startDijkstra(OwnVertex start_node, OwnVertex goal_node) {
		
		IAlgoReport reporter = new Dijkstra_AStar_Report("Dijkstra");
		Dijkstra_AStar dijkstra = new Dijkstra_AStar(graph, start_node, goal_node, 
															reporter, Dijkstra_AStar.DIJKSTRA);
		
		reporter.startTimer();
		dijkstra.startAlgorithm();
		reporter.stopTimer();
		
		return reporter;
	}

	@Override
	public IAlgoReport startASternchen(OwnVertex start_node, OwnVertex goal_node) {

		IAlgoReport reporter = new BFS_Report("A*");
		Dijkstra_AStar astar = new Dijkstra_AStar(graph, start_node, goal_node, 
															reporter, Dijkstra_AStar.ASTAR);
		
		reporter.startTimer();
		astar.startAlgorithm();
		reporter.startTimer();
		
		return reporter; 
	}

}
