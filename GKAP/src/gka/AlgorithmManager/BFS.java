package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class BFS {

	AlgoReport reporter;
	
	OwnVertex _start;
	OwnVertex _goal;
	Graph<OwnVertex, OwnEdge> _graph;
	
	public BFS(Graph<OwnVertex, OwnEdge> graph, OwnVertex start_node, OwnVertex goal_node, AlgoReport reporter){
		
		this.reporter = reporter;
		this._start = start_node;
		this._goal = goal_node;
		this._graph = graph;
	}
	
	
	void startbreadthSearch(){
		
		// if the precondition fails, then interrupt procedure
		if(defaultPrecondition(_graph, _start, _goal)) return;
		
		// Stack to save visited Vertices
		Stack<OwnVertex> path = new Stack<OwnVertex>();
		
		// First part of BFS, that method returns true if target Vertex found
		boolean hasFound = breadthSearch(path);

		if(hasFound){
			
			// Second part of BFS
			// These procedure find out, which shortest path to choose 
			OwnVertex lastDepthVertex = path.pop();
			reporter.addPathNode(lastDepthVertex);
			reporter.addVisitedNode(lastDepthVertex);
			
			while(!path.isEmpty()){
				
				OwnVertex aVertex = path.pop();
				reporter.addVisitedNode(aVertex);
				
				if(aVertex.get_level() == (lastDepthVertex.get_level()-1))
				{					

					if(_graph.isPredecessor(lastDepthVertex, aVertex) || _graph.isSuccessor(lastDepthVertex, aVertex))
					{
						lastDepthVertex.set_level(0);
						lastDepthVertex = aVertex;
						reporter.addPathNode(lastDepthVertex);
					}
					else
					{
						aVertex.set_level(0);
					}
					
				}else{
					aVertex.set_level(0);					
				}
				
			}
		}
	}
	
	// Helper method from startbreadthSearch()
	// First algorithm part
	private boolean breadthSearch(Stack<OwnVertex> path){
		
		Queue<OwnVertex> queue = new ArrayDeque<OwnVertex>();
		List<OwnVertex> visited = new ArrayList<OwnVertex>();
		
		queue.offer(_start);
		path.push(_start);
		_start.set_level(0);
		
		while(!queue.isEmpty()){

			OwnVertex currentVertex = queue.poll();
			visited.add(currentVertex);
			
			List<OwnVertex> childVertices = getChildren(_graph,currentVertex);
			
			if(!childVertices.isEmpty()){
				for(OwnVertex childVertex : childVertices){
					
					if(visited.contains(childVertex)) continue;
					
					if(childVertex.get_level() == 0){
						childVertex.set_level(currentVertex.get_level()+1);						
						path.push(childVertex);						
					}
					
					if(childVertex.equals(_goal)) {
						return true;
					}
					
					if(!queue.contains(childVertex)) queue.offer(childVertex);
				}
			}
			
		}
		return false;
	}
	
/**************** Helper Methods *******************/
	
	/**
	 * Get Children
	 * 
	 * @param vertex
	 * @return a List<OwnVertex> with Children Vertices
	 * 
	 *  if vertex hasn't any Children, then return empty List<OwnVertex>
	 */
	private List<OwnVertex> getChildren(Graph<OwnVertex, OwnEdge> g, OwnVertex vertex){
		
		List<OwnVertex> result = new ArrayList<OwnVertex>();
		for(OwnEdge e : g.getOutEdges(vertex)){
			
			Pair<OwnVertex> pair = g.getEndpoints(e);
			
			// IF source Vertex equals pair.left
			if(pair.getFirst().equals(vertex))
			{
				// take the right Vertex to result
				result.add(pair.getSecond());
			}
			else
			{	
				// else take the left Vertex to result
				result.add(pair.getFirst());
			}
		}
		return result;
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
