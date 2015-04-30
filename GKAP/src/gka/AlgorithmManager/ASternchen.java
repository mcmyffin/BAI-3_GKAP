package gka.AlgorithmManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.PriorityBlockingQueue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.AlgorithmManager.Extension.ComparatorHeuristic;
import gka.AlgorithmManager.Extension.ComparatorLength;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class ASternchen {

	private OwnVertex start;
	private OwnVertex goal;
	private Graph<OwnVertex,OwnEdge> graph;
	private BFS_Report reporter;
	
	private Queue<OwnVertex> openList;
	private Set<OwnVertex> closedList;
	private Map<OwnVertex,OwnVertex> path;
	private Map<OwnVertex, Integer> vertexLength;

	
	public ASternchen(Graph<OwnVertex,OwnEdge> g, OwnVertex start, OwnVertex goal, BFS_Report reporter){
		
		this.graph = g;
		this.start = start;
		this.goal  = goal;
		this.reporter = reporter;						
		
		this.vertexLength = new HashMap<OwnVertex, Integer>();
		this.openList 	= new PriorityQueue<OwnVertex>(new ComparatorLength(this.vertexLength));
		this.path     	= new HashMap<OwnVertex, OwnVertex>();
		this.closedList = new HashSet<OwnVertex>();
	}
	
	
	void startASternchen(){

		// if the precondition fails, then interrupt procedure
		if(defaultPrecondition(graph, start, goal)) return;

		// if algorithm find target Vertex then calculate Path
		if(searchASternchen()){
			
			// The target Vertex is a part of path
			reporter.addPathNode(goal);
			
			// Set up Pointer for loop
			OwnVertex currentV = goal;
			
			// Repeat until pointer "currentV" equals "start" Vertex
			while(!currentV.equals(start)){
			
				// save Successor pointer instartASternchen "tmpV" variable
				OwnVertex tmpV = path.get(currentV);
				
				// then add the Successor as part of Path in reporter Class 
				reporter.addPathNode(tmpV);
				
				// set new Pointer for next loop repeat
				currentV = tmpV;
			}
		}
	}
	
	/**
	 * Part 1 Searching
	 * @return
	 */
	private boolean searchASternchen(){

		// add start Vertex to priority Queue
		vertexLength.put(start, 0);
		openList.offer(start);
		
		// Start Vertex is the predecessor to himself
		path.put(start, start);
		
		while(!openList.isEmpty()){
			
			// Get Vertex with lowest priority
			OwnVertex currentVertex = openList.poll();
			
			// Set current Vertex as closed
			closedList.add(currentVertex);
			
			// target found
			if(goal.equals(currentVertex)) return true;
			
			// If target not found
			// look at child Vertices and add these to openList
			expandNode(currentVertex);
		}
		return false;
	}

	/**
	 * Helper Method from A* Part 1
	 * @param currentNode
	 */
	private void expandNode(OwnVertex currentNode){
		
		for(OwnVertex successor : graph.getSuccessors(currentNode)){
			
			// if successor Vertex in closed list, then do nothing
			if(closedList.contains(successor)) continue;
			
			// calculate temporary path value
			int tentative_g = getPathWeight(currentNode, successor);
			
			// if successor already in openList and the new pathValue >= current pathValue, then do nothing
			if(openList.contains(successor) && tentative_g >= vertexLength.get(successor)) continue;
				
			// Set new Predecessor
			path.put(successor, currentNode);
			
			// set new path value
			vertexLength.put(successor, tentative_g);
			
			// add Child Vertex to openList if not in OpenList
			if(!openList.contains(successor)) openList.offer(successor);
		}
	}
	
	
	/**************** Helper Methods *******************/
	
	private int getPathWeight(OwnVertex currentNode, OwnVertex successor){
		
		int path = vertexLength.get(currentNode) + graph.findEdge(currentNode, successor).getWeight();
		return path;
	}
	
	/**
	 * Default Precondition
	 * checks before algorithm start
	 * @param g
	 * @param start
	 * @param goal
	 * @return true if precondition fails
	 */
	private boolean defaultPrecondition(Graph<OwnVertex,OwnEdge> g,OwnVertex start, OwnVertex goal){
		// precondition Terminate by non existing Vertex
		boolean nonExistingVertex = (!g.containsVertex(start) || !g.containsVertex(goal));
		// precondition Terminate by same Vertex
		boolean isSameVertex = (start.equals(goal));
		// precondition Terminate by unreachable Vertex
		boolean isUnreachable = g.getOutEdges(start).isEmpty() || g.getInEdges(goal).isEmpty();
		
		return (nonExistingVertex || isSameVertex || isUnreachable);
	}

}
