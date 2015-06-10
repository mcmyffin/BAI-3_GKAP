package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.Extension.Fleury_Hierholzer_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Fleury {
	
	private Graph<OwnVertex,OwnEdge> graph;
	private Fleury_Hierholzer_Report reporter;
	private Set<OwnEdge> delEdges;
	private Queue<OwnVertex> queue;
	private OwnVertex start_node;
	
	Fleury(Graph<OwnVertex,OwnEdge> graph, OwnVertex start_node, Fleury_Hierholzer_Report reporter){
		
		this.start_node = start_node;
		this.graph 	  = graph;
		this.reporter = reporter;
		this.delEdges = new LinkedHashSet();
		this.queue 	  = new LinkedList();
	}
	
	
	void startFleury(){
		
		reporter.startTimer();
		
		// TODO precondition
		queue.offer(start_node);
		
		while(!queue.isEmpty()){
			
			OwnVertex v = queue.poll();
			
			reporter.addPathNode(v);
			
			Collection<OwnEdge> outgouingEdges = new LinkedHashSet(graph.getOutEdges(v));
			outgouingEdges.removeAll(delEdges);
			
			OwnEdge e = null;
			if(outgouingEdges.size() > 1){
				for(OwnEdge aEdge : outgouingEdges){
					
					if(advancedBFS(v, start_node, aEdge)){
						e = aEdge;
						break;
					}
				}
			}else{
				
				for(OwnEdge aEdge : outgouingEdges){
					e = aEdge;
				}
			}

			if(e == null) break;
			else reporter.countPath();
			
			delEdges.add(e);
			OwnVertex vertexOnTheOppesite = graph.getOpposite(v, e);
			queue.offer(vertexOnTheOppesite);

		}
		reporter.stopTimer();
	}
	
	private boolean advancedBFS(OwnVertex startnode, OwnVertex target, OwnEdge edgeToIgnore){
		
		if(startnode.equals(target)) return true;
		
		
		Queue<OwnVertex> queue = new ArrayDeque<OwnVertex>();
		Set<OwnVertex> visited = new HashSet<OwnVertex>();
		
		queue.offer(startnode);
		
		while(!queue.isEmpty()){

			OwnVertex currentVertex = queue.poll();
			visited.add(currentVertex);
			
			for(OwnVertex successor : graph.getSuccessors(currentVertex)){
				
				// advanced step
				Collection<OwnEdge> edgesBetween = graph.findEdgeSet(currentVertex, successor);
				
				if(edgesBetween.contains(edgeToIgnore) || delEdges.containsAll(edgesBetween)){
					continue;
				}
				
				if(visited.contains(successor)) continue;
				
				if(successor.equals(target)) return true;
				
				
				if(!queue.contains(successor)) queue.offer(successor);
			}
		}
		return false;
	}

}
