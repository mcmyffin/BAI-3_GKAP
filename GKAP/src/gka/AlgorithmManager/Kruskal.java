package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
		
		while(resultGraph.getEdgeCount() < graph.getVertexCount()-1 && !edges.isEmpty()){
			
			// get edge with minimal Length
			OwnEdge minimalEdge = edges.poll();
			
			// get edge connection
			Pair<OwnVertex> endpoints = graph.getEndpoints(minimalEdge);
			OwnVertex v1 = endpoints.getFirst();
			OwnVertex v2 = endpoints.getSecond();
			reporter.countGraphAccess();
			
			// if both Vertices contains resultGraph then check for cycle
			if(resultGraph.containsVertex(v1) && resultGraph.containsVertex(v2)){
				
				if(checkForCycle(v1,v2,resultGraph)) continue;
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
	
	private boolean checkForCycle(OwnVertex v1,OwnVertex v2, Graph<OwnVertex,OwnEdge> g){
		
		Queue<OwnVertex> queue = new ArrayDeque<OwnVertex>();
		Set<OwnVertex> visited = new HashSet<OwnVertex>();
		
		queue.offer(v1);
		
		while(!queue.isEmpty()){

			OwnVertex currentVertex = queue.poll();
			visited.add(currentVertex);
			
			for(OwnVertex successor : g.getSuccessors(currentVertex)){
				
				if(visited.contains(successor)) continue;
				
				if(successor.equals(v2)) return true;
				
				if(!queue.contains(successor)) queue.offer(successor);
			}
		}
		
		return false;
	}

}
