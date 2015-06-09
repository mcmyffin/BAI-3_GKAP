package gka.AlgorithmManager;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import es.usc.citius.lab.hipster.collections.adapter.PriorityEvaluator;
import es.usc.citius.lab.hipster.collections.adapter.PriorityFibonacciQueue;
import gka.AlgorithmManager.Extension.ComparatorLength;
import gka.AlgorithmManager.Extension.Kruskal_Prim_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Prim {

	private Graph<OwnVertex,OwnEdge> graph;
	private Graph<OwnVertex,OwnEdge> resultGraph;
	private Kruskal_Prim_Report reporter;

	private Queue<OwnVertex> priorityQueue;
	private Map<OwnVertex,OwnVertex> pred;
	private Map<OwnVertex,Integer> dist;
	private Set<OwnVertex> selectedVertices;
	
	public Prim(Graph<OwnVertex,OwnEdge> graph, Kruskal_Prim_Report reporter){
		
		this.graph = graph;
		this.reporter = reporter;
		
		this.resultGraph = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
		this.dist = new LinkedHashMap<OwnVertex,Integer>();
		this.pred = new LinkedHashMap<OwnVertex,OwnVertex>();
		this.selectedVertices = new LinkedHashSet<OwnVertex>();
		
		this.priorityQueue = new PriorityQueue<OwnVertex>(new ComparatorLength(dist));
		
	}
	
	void startPrim(){
		
		reporter.startTimer();
		
		// if the precondition not violated, then start Algorithm
		if(defaultPrecondition(graph))
		{
			// initialize start Vertex
			reporter.countGraphAccess();
			OwnVertex start = getRandomVertex();
			
			dist.put(start, 0);
			pred.put(start, start);
			priorityQueue.offer(start);
			
			
			while(!priorityQueue.isEmpty()){
			
				// get minimal Vertex
				OwnVertex next = priorityQueue.poll();
				
				selectedVertices.add(next);
				
				// look at all neighbors of next
				for(OwnVertex neighbor : graph.getSuccessors(next)){
					
					reporter.countGraphAccess();
				
					if(selectedVertices.contains(neighbor)) continue;

					// get an Edge between both
					reporter.countGraphAccess();
					OwnEdge edge = getEdgeBetween(next, neighbor);
					
					// get Edge weight
					int weight = edge.getWeight();
					
					// if Edge-weight < distance[neighbor], then save it in distance[neighbor] = Edge-weight
					if(!dist.containsKey(neighbor) || dist.get(neighbor) > weight){
						dist.put(neighbor, weight);
						pred.put(neighbor, next);
					}
					
					if(!priorityQueue.contains(neighbor)) priorityQueue.offer(neighbor);
					else{
						priorityQueue.remove(neighbor);// get an Edge between both

						priorityQueue.offer(neighbor);
					}
				}
			}
		}

		buildMinimalSpanningTree();
		
		// stop Timer
		reporter.stopTimer();
		
		// count pathLength
		int totalPathLength = 0;
		for(OwnEdge e : resultGraph.getEdges()){
			totalPathLength+=e.getWeight();
		}
		// document total Path Length 
		reporter.setPathLength(totalPathLength);
		
		// document minimalSpannTree
		reporter.addMinimalSpanningTree(resultGraph);
	}
	
	/*** private helper Methods ***/
	
	// Get Random Vertex
	private OwnVertex getRandomVertex(){
		
		
		OwnVertex vertex = null;
		
		Random rand = new Random();
		final int max = graph.getVertexCount();
		final int randomIntexPos = rand.nextInt(max);
		int i = 0;
		
		for(OwnVertex v : graph.getVertices()){
			
			if(i == randomIntexPos){
				vertex = v;
				break;
			}else{
				vertex = v;
			}
			i++;
		}
		
		return vertex;
	}
	
	/**
	 * Default Precondition
	 * checks before algorithm start
	 * @param graph
	 * @return false if precondition fails
	 */
	private boolean defaultPrecondition(Graph<OwnVertex,OwnEdge> graph){

		// precondition fails if Vertex-count < 1
		boolean NumberOfNodesToSmall = ( graph.getVertexCount() >= 1 );
		// precondition fails if Edge-count < (Vertex-count -1)
		boolean NumberOfEdgesToSmall = ( graph.getEdgeCount() >= (graph.getVertexCount() -1) );
		
		return (NumberOfNodesToSmall && NumberOfEdgesToSmall);
	}
	
	// search the smallest Edge between two Vertices
	private OwnEdge getEdgeBetween(OwnVertex v1, OwnVertex v2){
		
		OwnEdge smallestEdge = null;
		Collection<OwnEdge> edgeset = graph.findEdgeSet(v1, v2);
		
		for(OwnEdge aEdge : edgeset){
			
			if(smallestEdge == null){
				smallestEdge = aEdge;
			}else if(aEdge.getWeight() < smallestEdge.getWeight()){
				smallestEdge = aEdge;
			}
		}
		return smallestEdge;
	}
	
	private void buildMinimalSpanningTree(){

		for(OwnVertex v : selectedVertices){
			
			resultGraph.addVertex(v);

			if(pred.get(v).equals(v)) continue;
			
			OwnVertex predecessor = pred.get(v);
			int distance = dist.get(v);
			
			resultGraph.addVertex(predecessor);
			OwnEdge e = new OwnEdge(distance);
			
			resultGraph.addEdge(e, predecessor, v);
		}
		
	}

}
