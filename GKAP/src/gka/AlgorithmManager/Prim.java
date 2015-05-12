package gka.AlgorithmManager;

import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Prim {

	private Graph<OwnVertex,OwnEdge> graph;
	private Kruskal_Prim_Report reporter;
	private OwnVertex start;
	
	public Prim(Graph<OwnVertex,OwnEdge> graph, OwnVertex start, boolean withFibHeap, Kruskal_Prim_Report reporter){
		
		this.graph = graph;
		this.start = start;
		this.reporter = reporter;
		
		if(withFibHeap){
			// TODO Fibonacci Heap
		}else{
			// TODO default Priority Queue
		}
	}
	
	void startPrim(){
		reporter.startTimer();
		
		reporter.stopTimer();
	}
}
