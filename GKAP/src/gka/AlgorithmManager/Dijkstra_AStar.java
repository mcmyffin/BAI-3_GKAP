package gka.AlgorithmManager;

import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.Extension.ComparatorHeuristic;
import gka.AlgorithmManager.Extension.ComparatorLength;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Dijkstra_AStar{

	public static final String DIJKSTRA = "DIJKSTRA";
	public static final String ASTAR 	= "ASTAR";
	
	private OwnVertex _start;
	private OwnVertex _goal;
	private Graph<OwnVertex,OwnEdge> _graph;
	private IAlgoReport _reporter;
	
	private Queue<OwnVertex> _openList;
	private Set<OwnVertex> _closedList;
	private Map<OwnVertex,OwnVertex> _path;
	private Map<OwnVertex, Integer> _vertexLength;

	
	Dijkstra_AStar(Graph<OwnVertex,OwnEdge> g, OwnVertex start, OwnVertex goal, 
											IAlgoReport reporter, String algorithmType){
		
		this._graph 	  = g;
		this._start 	  = start;
		this._goal  	  = goal;
		this._reporter = reporter;						
		
		this._vertexLength = new HashMap<OwnVertex, Integer>();
		this._path     	  = new HashMap<OwnVertex, OwnVertex>();
		this._closedList   = new HashSet<OwnVertex>();
		
		if(algorithmType.equals(DIJKSTRA))
		{
			this._openList 	= new PriorityQueue<OwnVertex>(new ComparatorLength(this._vertexLength));
		}
		else
		{
			this._openList 	= new PriorityQueue<OwnVertex>(new ComparatorHeuristic(this._vertexLength));
		}
	}
	

	void startAlgorithm(){

		_reporter.startTimer();
		
		// if the precondition fails, then interrupt procedure
		if(defaultPrecondition(_graph, _start, _goal)) return;

		// if algorithm find target Vertex then calculate Path
		if(findTarget()){
			
			// The target Vertex is a part of path
			_reporter.addPathNode(_goal);
			_reporter.setPathLength(_vertexLength.get(_goal));
			
			// Set up Pointer for loop
			OwnVertex currentV = _goal;
			
			// Repeat until pointer "currentV" equals "start" Vertex
			while(!currentV.equals(_start)){
			
				// save Successor pointer instartASternchen "tmpV" variable
				OwnVertex tmpV = _path.get(currentV);
				
				// then add the Successor as part of Path in reporter Class 
				_reporter.addPathNode(tmpV);
				
				// set new Pointer for next loop repeat
				currentV = tmpV;
			}
			
			// add visited Vertices to Reporter Class
			for(OwnVertex v : _vertexLength.keySet()){
				_reporter.addVisitedNode(v);
			}
		}
		_reporter.stopTimer();
	}
	
	/**
	 * Part 1 Searching
	 * @return
	 */
	private boolean findTarget(){

		// add start Vertex to priority Queue
		_vertexLength.put(_start, 0);
		_openList.offer(_start);
		
		// Start Vertex is the predecessor to himself
		_path.put(_start, _start);
		
		while(!_openList.isEmpty()){
			
			// Get Vertex with lowest priority
			OwnVertex currentVertex = _openList.poll();
			
			// Set current Vertex as closed
			_closedList.add(currentVertex);
			
			// target found
			if(_goal.equals(currentVertex)) return true;
			
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
		
		for(OwnVertex successor : _graph.getSuccessors(currentNode)){
			_reporter.countGraphAccess();
			// if successor Vertex in closed list, then do nothing
			if(_closedList.contains(successor)) continue;
			
			// calculate temporary path value
			int tentative_g = getPathWeight(currentNode, successor);
			
			// if successor already in openList and the new pathValue >= current pathValue, then do nothing
			if(_openList.contains(successor) && tentative_g >= _vertexLength.get(successor)) continue;
				
			// Set new Predecessor
			_path.put(successor, currentNode);
			
			// set new path value
			_vertexLength.put(successor, tentative_g);
			
			// add Child Vertex to openList if not in OpenList
			if(!_openList.contains(successor)) _openList.offer(successor);
		}
	}
	
	
	/**************** Helper Methods *******************/
	
	private int getPathWeight(OwnVertex currentNode, OwnVertex successor){
		
		int path = _vertexLength.get(currentNode) + _graph.findEdge(currentNode, successor).getWeight();
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
