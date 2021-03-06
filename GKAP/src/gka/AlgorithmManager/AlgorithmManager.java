package gka.AlgorithmManager;

import javafx.util.Pair;
import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.Extension.BFS_Report;
import gka.AlgorithmManager.Extension.Dijkstra_AStar_Report;
import gka.AlgorithmManager.Extension.Fleury_Hierholzer_Report;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class AlgorithmManager implements IAlgorithManager{

	private Graph<OwnVertex, OwnEdge> graph;
	
	public AlgorithmManager(Graph<OwnVertex, OwnEdge> graph) {
		this.graph = graph;
	}
	
	@Override
	public IAlgoReport startBFS(OwnVertex start_node, OwnVertex goal_node) {
		
		IAlgoReport reporter = new BFS_Report("Breadth First Search");
		BFS bfs = new BFS(graph, start_node, goal_node, reporter);
		
		bfs.startbreadthSearch();
		
		return reporter;
	}

	@Override
	public IAlgoReport startDijkstra(OwnVertex start_node, OwnVertex goal_node) {
		
		IAlgoReport reporter = new Dijkstra_AStar_Report("Dijkstra");
		Dijkstra_AStar dijkstra = new Dijkstra_AStar(graph, start_node, goal_node, 
															reporter, Dijkstra_AStar.DIJKSTRA);
		dijkstra.startAlgorithm();
		
		return reporter;
	}

	@Override
	public IAlgoReport startAStar(OwnVertex start_node, OwnVertex goal_node) {

		IAlgoReport reporter = new Dijkstra_AStar_Report("A*");
		Dijkstra_AStar astar = new Dijkstra_AStar(graph, start_node, goal_node, 
															reporter, Dijkstra_AStar.ASTAR);
		astar.startAlgorithm();
		
		return reporter; 
	}

	
	@Override
	public Pair<IAlgoReport,Graph<OwnVertex, OwnEdge>> startKruksal() {
		
		Kruskal_Prim_Report reporter = new Kruskal_Prim_Report("Kruskal");
		Kruskal algorithm = new Kruskal(graph,reporter);
		
		algorithm.startKruskal();
		
		
		return new Pair<IAlgoReport, Graph<OwnVertex, OwnEdge>>((IAlgoReport) reporter,reporter.getMinimalSpanningTree());
	}

	
	@Override
	public Pair<IAlgoReport,Graph<OwnVertex, OwnEdge>> startPrim(boolean withFibHeap) {
		
		Kruskal_Prim_Report reporter; 
		
		if(withFibHeap){
			reporter = new Kruskal_Prim_Report("Prim FibHeap");
			PrimFib algorithm = new PrimFib(graph, reporter);
			algorithm.startPrim();
		}else{
			reporter = new Kruskal_Prim_Report("Prim");
			Prim algorithm = new Prim(graph, reporter);
			algorithm.startPrim();
		}

		return new Pair<IAlgoReport,Graph<OwnVertex, OwnEdge>>((IAlgoReport) reporter,reporter.getMinimalSpanningTree());
	}

	@Override
	public IAlgoReport startHierholzer(OwnVertex start_node) {

		Fleury_Hierholzer_Report reporter = new Fleury_Hierholzer_Report("Hierholzer");
	
		Hierholzer algortihm = new Hierholzer(graph, start_node, reporter);
		algortihm.startHierholzer();
		
		return (IAlgoReport) reporter;
	}

	@Override
	public IAlgoReport startFleury(OwnVertex start_node) {
		
		Fleury_Hierholzer_Report reporter = new Fleury_Hierholzer_Report("Fleury");
		
		Fleury algorithm = new Fleury(graph,start_node, reporter);
		algorithm.startFleury();

		return (IAlgoReport) reporter;
	}

}
