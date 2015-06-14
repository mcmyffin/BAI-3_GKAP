package gka.AlgorithmManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
import gka.AlgorithmManager.Extension.Fleury_Hierholzer_Report;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class Hierholzer {

	private Graph<OwnVertex,OwnEdge> graph;
	private OwnVertex startNode;
	private Fleury_Hierholzer_Report reporter;
	
	private Set<OwnEdge> visitedEdges;
	private Set<OwnVertex> visitedVertices;
	private List<List<OwnVertex>> disjointCircles;
	private Queue<OwnVertex> workingQueue;
	
	Hierholzer(Graph<OwnVertex,OwnEdge> graph, OwnVertex start, Fleury_Hierholzer_Report reporter){

		this.graph 		= graph;
		this.startNode 	= start;
		this.reporter 	= reporter;
		
		this.visitedEdges = new HashSet();
		this.visitedVertices = new HashSet();
		this.disjointCircles = new LinkedList<List<OwnVertex>>();
		this.workingQueue = new ArrayDeque();
	}
	
	
	void startHierholzer(){
		
		reporter.startTimer();
		
		if(!precondition()) return;
		
		while(visitedEdges.size() < graph.getEdgeCount()){

			// initialize temporary-startnode and add to queue
			OwnVertex tmpstartNode = chooseNextVertex(); 			
			workingQueue.offer(tmpstartNode);
			
			List<OwnVertex> oneCircle = new LinkedList<OwnVertex>();
			while(!workingQueue.isEmpty()){
				
				// get next Vertex from Queue
				OwnVertex vertex = workingQueue.poll();
				
				// add Vertex to Circle
				oneCircle.add(vertex);
				visitedVertices.add(vertex);
				
				// Outgoing Edge-Set
				Collection<OwnEdge> outgouingEdges = new LinkedHashSet(graph.getOutEdges(vertex));
				// Unvisited Edge-Set = {Outgoing Edge-Set} \ {Deleted Edge-Set}
				outgouingEdges.removeAll(visitedEdges);
				
				
				OwnEdge nextEdge = null;
				// Select Edge from Unvisited Edge-Set
				for(OwnEdge anEdge : outgouingEdges){
					nextEdge = anEdge;
					break;
				}
				
				// Note the Edge as visited
				visitedEdges.add(nextEdge);
				reporter.countPath();

				// Get the Vertex on the opposite 
				OwnVertex vertexOnTheOpposite = graph.getOpposite(vertex, nextEdge);
				
				// if circle close
				if(tmpstartNode.equals(vertexOnTheOpposite))
				{
					// add Vertex no the opposite to circle and save circle
					oneCircle.add(vertexOnTheOpposite);
					disjointCircles.add(oneCircle);
				}
				else{
					// add next Vertex to queue
					workingQueue.offer(vertexOnTheOpposite);
				}
			}
		}
		
		// build EulerianPath Recursive
		List<OwnVertex> eulerianPath = new ArrayList();
		buildEulerianCircuit(eulerianPath,0);

		reporter.addEulerianPath(eulerianPath);
		reporter.stopTimer();
	}
	
	
	private OwnVertex chooseNextVertex(){
		
		if(visitedEdges.isEmpty()) return startNode;
		
		for(OwnVertex vertex : visitedVertices){
			
			Set<OwnEdge> outgouingEdges = new HashSet(graph.getOutEdges(vertex));
			outgouingEdges.removeAll(visitedEdges);
			
			if(!outgouingEdges.isEmpty()) return vertex;
			
		}
		return null;
	}
	
	private List<OwnVertex> buildEulerianCircuit(List<OwnVertex> eulerianPath, int i){
		
		// if index i >= totalCircles
		if(i >= disjointCircles.size()) return eulerianPath;
		
		// if last Circle
		if(i+1 >= disjointCircles.size())
		{
			List<OwnVertex> oneCircle = disjointCircles.get(i);
			for(OwnVertex vertex: oneCircle)
			{
				eulerianPath.add(vertex);
			}
			return eulerianPath;
		}
		
		OwnVertex nextCircleCut = disjointCircles.get(i+1).get(0);
		
		List<OwnVertex>     oneCircle = disjointCircles.get(i);
		Iterator<OwnVertex> oneCircleIterator = oneCircle.iterator();
		eulerianPath.add(oneCircleIterator.next());
		
		while(oneCircleIterator.hasNext())
		{
			// get Next Vertex
			OwnVertex vertex = oneCircleIterator.next();
			
			// if Circle cutting with next Circle
			if(vertex.equals(nextCircleCut))
			{
				buildEulerianCircuit(eulerianPath, i+1);
			}
			else
			{
				eulerianPath.add(vertex);
			}
		}
		
		return eulerianPath;
	}
	
	private boolean precondition(){
		boolean c1 = (graph.getEdgeCount() > graph.getVertexCount()-1);
		return c1;
	}
}
