package gka.AlgorithmManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Kruskal {
	
	private Graph<OwnVertex,OwnEdge> graph;
	private Kruskal_Prim_Report reporter;
	
	private Queue<OwnEdge> edges;
	
	private Graph<OwnVertex,OwnEdge> resultGraph;
	
	public Kruskal(Graph<OwnVertex,OwnEdge> graph, Kruskal_Prim_Report reporter){
		
		this.graph = graph;
		this.reporter = reporter;
		
		this.edges = getEdgeQueue();
		this.resultGraph = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
		
	}
	
	/**
	 * Start Kruskal Algorithm
	 * 
	 * @require undirected weighted and coherently Graph
	 * @ensure minimal Spanning Tree
	 */
	void startKruskal(){
		// start Timer
		reporter.startTimer();
		
		while(resultGraph.getVertexCount() != graph.getVertexCount()){
			
			// get edge with minimal Length
			OwnEdge minimalEdge = edges.poll();
			
			// get edge connection
			Pair<OwnVertex> endpoints = graph.getEndpoints(minimalEdge);
			OwnVertex v1 = endpoints.getFirst();
			OwnVertex v2 = endpoints.getSecond();
			reporter.countGraphAccess();
			
			// if both Vertices contains resultGraph then check for cycle
			if(resultGraph.containsVertex(v1) && resultGraph.containsVertex(v2)){
				int vertexCount = resultGraph.getVertexCount();
				int edgeCount = resultGraph.getEdgeCount();
				
				if((edgeCount+1) >= vertexCount) continue;
			}
			
			resultGraph.addVertex(v1);
			resultGraph.addVertex(v2);
			
			resultGraph.addEdge(minimalEdge, v1, v2);
		}
		
		// count pathLength
		int totalPathLength = 0;
		for(OwnEdge e : resultGraph.getEdges()){
			totalPathLength+=e.getWeight();
		}
		// document total Path Length 
		reporter.setPathLength(totalPathLength);
		
		// document minimalSpannTree
		reporter.addMinimalSpanningTree(resultGraph);

		// stop Timer
		reporter.stopTimer();
	}
	
	/**** private helper Methods ****/
	// Queue sort Edges by Weight
	private Queue<OwnEdge> getEdgeQueue(){
		
		Queue<OwnEdge> edgeQueue = new PriorityQueue<OwnEdge>();
		
		for(OwnEdge e : graph.getEdges()){
			reporter.countGraphAccess();
			edgeQueue.offer(e);
		}
		return edgeQueue;
	}

}
