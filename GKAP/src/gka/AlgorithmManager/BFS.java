package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javafx.util.Pair;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
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
		
		Stack<OwnVertex> path = new Stack<OwnVertex>();
		
		boolean hasFound = breadthSearch(path);

		if(hasFound){
			
			OwnVertex lastDepthVertex = path.pop();
			reporter.addPathNode(lastDepthVertex);
			
			while(!path.isEmpty()){
				
				OwnVertex aVertex = path.pop();
				
				if(aVertex.get_level() == (lastDepthVertex.get_level()-1))
				{					
					lastDepthVertex = aVertex;
					reporter.addPathNode(lastDepthVertex);
				}
			}
		}
	}
	
	private boolean breadthSearch(Stack<OwnVertex> path){
		
		Queue<OwnVertex> queue = new ArrayDeque<OwnVertex>();
		List<OwnVertex> visited = new ArrayList<OwnVertex>();
		
		
		queue.offer(_start);
		path.push(_start);
		
		_start.set_level(0);
		
		while(!queue.isEmpty()){

		
			OwnVertex currentVertex = queue.poll();
			visited.add(currentVertex);
			reporter.addVisitedNode(currentVertex);
			Collection<OwnVertex> childVertices = _graph.getNeighbors(currentVertex);
			
			if(!childVertices.isEmpty()){
				
				for(OwnVertex childVertex : childVertices){
					
					if(visited.contains(childVertex)) continue;
					
					childVertex.set_level(currentVertex.get_level()+1);
					path.push(childVertex);
					
					if(childVertex.equals(_goal)) {
						
						return true;
					}
					
					queue.offer(childVertex);
				}
			}
			
		}
		return false;
	}
	
	
//	public static void main(String[] args) {
//		
//		Graph<OwnVertex, OwnEdge> g = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
//		
//		OwnVertex v1 = new OwnVertex("v1");
//		OwnVertex v2 = new OwnVertex("v2");
//		OwnVertex v3 = new OwnVertex("v3");
//		OwnVertex v4 = new OwnVertex("v4");
//		OwnVertex v5 = new OwnVertex("v5");
//		OwnVertex v6 = new OwnVertex("v6");
//		OwnVertex v7 = new OwnVertex("v7");
//		
//		OwnEdge e1 = new OwnEdge();
//		OwnEdge e2 = new OwnEdge();
//		OwnEdge e3 = new OwnEdge();
//		OwnEdge e4 = new OwnEdge();
//		OwnEdge e5 = new OwnEdge();
//		OwnEdge e6 = new OwnEdge();
//		
//		g.addVertex(v1);
//		g.addVertex(v2);
//		g.addVertex(v3);
//		g.addVertex(v4);
//		g.addVertex(v5);
//		g.addVertex(v6);
//		g.addVertex(v7);
//		
//		g.addEdge(e1, v1, v2);
//		g.addEdge(e2, v1, v3);
//		g.addEdge(e3, v3, v4);
//		g.addEdge(e4, v3, v5);
//		g.addEdge(e5, v5, v6);
//		g.addEdge(e6, v6, v7);
//		
//		System.out.println(g.toString());
//		
//		BFS bfs = new BFS(g, v1, v6);
//		System.out.println(bfs.startbreadthSearch());
//		
//	}
}
