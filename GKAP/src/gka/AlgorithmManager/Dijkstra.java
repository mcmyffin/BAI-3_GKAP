package gka.AlgorithmManager;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Dijkstra {

	private OwnVertex start;
	private OwnVertex goal;
	private Graph<OwnVertex,OwnEdge> graph;
	private AlgoReport reporter;
	private PriorityQueue<OwnVertex> priorityQueue;
	
	public Dijkstra(Graph<OwnVertex, OwnEdge> g, OwnVertex start, OwnVertex goal, AlgoReport reporter){
		
		this.graph = g;
		this.start = start;
		this.goal  = goal;
		this.reporter = reporter;
		this.priorityQueue = new PriorityQueue<OwnVertex>();
	}
	
	public void startDijkstra(){

		// if the precondition fails, then interrupt procedure
		if(defaultPrecondition(graph, start, goal)) return;
		
		
		// todo
		throw new NotImplementedException();
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
