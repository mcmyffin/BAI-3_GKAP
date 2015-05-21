package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.AlgorithmManager.Extension.IAlgoReport;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class BFS {

	IAlgoReport reporter;
	
	OwnVertex _start;
	OwnVertex _goal;
	Graph<OwnVertex, OwnEdge> _graph;
	
	Stack<OwnVertex> path;
	Map<OwnVertex,Integer> vertexLength;
	
	public BFS(Graph<OwnVertex, OwnEdge> graph, OwnVertex start_node, OwnVertex goal_node, IAlgoReport reporter){
		
		this.reporter 	= reporter;
		this._start 	= start_node;
		this._goal 		= goal_node;
		this._graph 	= graph;
		
		this.path 		= new Stack<OwnVertex>();
		this.vertexLength = new HashMap<OwnVertex, Integer>();
	}
	
	
	void startbreadthSearch(){
		reporter.startTimer();
		// if the precondition fails, then interrupt procedure
		if(defaultPrecondition(_graph, _start, _goal)) return;

		// First part of BFS, that method returns true if target Vertex found
		if(breadthSearch()){
			
			// Second part of BFS
			// These procedure find out, which shortest path to choose 
			OwnVertex lastDepthVertex = path.pop();
			
			reporter.addPathNode(lastDepthVertex);
			reporter.addVisitedNode(lastDepthVertex);
			
			while(!path.isEmpty()){
				
				OwnVertex aVertex = path.pop();
				reporter.addVisitedNode(aVertex);
				
				if(vertexLength.get(aVertex) == (vertexLength.get(lastDepthVertex)-1))
				{					
					if(_graph.isPredecessor(lastDepthVertex, aVertex) || _graph.isSuccessor(lastDepthVertex, aVertex))
					{
						lastDepthVertex = aVertex;
						reporter.addPathNode(lastDepthVertex);
					}
				}
			}
		}
		reporter.stopTimer();
	}
	
	// Helper method from startbreadthSearch()
	// First algorithm part
	private boolean breadthSearch(){
		
		Queue<OwnVertex> queue = new ArrayDeque<OwnVertex>();
		List<OwnVertex> visited = new ArrayList<OwnVertex>();
		
		queue.offer(_start);
		path.push(_start);
		vertexLength.put(_start, 0);
		
		while(!queue.isEmpty()){

			OwnVertex currentVertex = queue.poll();
			visited.add(currentVertex);
			
			
			for(OwnVertex successor : _graph.getSuccessors(currentVertex)){
				
				if(visited.contains(successor)) continue;
				
				
				vertexLength.put(successor, vertexLength.get(currentVertex)+1);
				path.push(successor);						
				
				if(successor.equals(_goal)) return true;
				
				if(!queue.contains(successor)) queue.offer(successor);
			}
			
		}
		return false;
	}
	
/**************** Helper Methods *******************/
	
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
