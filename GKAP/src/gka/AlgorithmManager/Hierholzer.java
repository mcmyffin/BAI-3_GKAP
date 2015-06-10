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
	private List<List<OwnVertex>> disjunkteEulerKreise;
	private Queue<OwnVertex> workingQueue;
	
	Hierholzer(Graph<OwnVertex,OwnEdge> graph, OwnVertex start, Fleury_Hierholzer_Report reporter){

		this.graph 		= graph;
		this.startNode 	= start;
		this.reporter 	= reporter;
		
		this.visitedEdges = new HashSet();
		this.visitedVertices = new HashSet();
		this.disjunkteEulerKreise = new LinkedList<List<OwnVertex>>();
		this.workingQueue = new ArrayDeque();
	}
	
	
	void startHierholzer(){
		
		// TODO Preconditon
		
		reporter.startTimer();
		while(visitedEdges.size() < graph.getEdgeCount()){

			// initialisiere queue mit startVertex
			OwnVertex tmpstartNode = chooseNextVertex(); // TODO;			
			workingQueue.offer(tmpstartNode);
			
			List<OwnVertex> einDisjunkterKreis = new LinkedList<OwnVertex>();
			while(!workingQueue.isEmpty()){
				
				OwnVertex v = workingQueue.poll();
				einDisjunkterKreis.add(v);
				visitedVertices.add(v);
				
				Collection<OwnEdge> outgouingEdges = new LinkedHashSet(graph.getOutEdges(v));
				outgouingEdges.removeAll(visitedEdges);
				
				OwnEdge e = null;
				for(OwnEdge aEdge : outgouingEdges){
					e = aEdge;
					break;
				}
				
				visitedEdges.add(e);
				reporter.countPath();
				OwnVertex vertexOnTheOppesite = graph.getOpposite(v, e);
				
				
				if(tmpstartNode.equals(vertexOnTheOppesite)){
					einDisjunkterKreis.add(vertexOnTheOppesite);
					disjunkteEulerKreise.add(einDisjunkterKreis);
				}else{
					workingQueue.offer(vertexOnTheOppesite);
				}
			}
			
			
		}
		
		List<OwnVertex> eulerkreis = new ArrayList();
		buildEulerKreis(eulerkreis,0);

		reporter.addEulerPfad(eulerkreis);
		reporter.stopTimer();
	}
	
	
	private OwnVertex chooseNextVertex(){
		
		if(visitedEdges.isEmpty()) return startNode;
		
		for(OwnVertex vertex : visitedVertices){
			// WARNING LANGE LAUFZEIT!!!
			Set<OwnEdge> outgouingEdges = new LinkedHashSet(graph.getOutEdges(vertex));
			outgouingEdges.removeAll(visitedEdges);
			
			if(!outgouingEdges.isEmpty()){
				return vertex;
			}
		}
		return null;
	}
	
	private List<OwnVertex> buildEulerKreis(List<OwnVertex> eulerKreis, int i){
		
		if(i >= disjunkteEulerKreise.size()){
			return eulerKreis;
		}
		
		if(i+1 >= disjunkteEulerKreise.size()){
			List<OwnVertex> einDisjunkterKreis = disjunkteEulerKreise.get(i);
			for(OwnVertex v: einDisjunkterKreis){
				eulerKreis.add(v);
			}
			return eulerKreis;
		}
		
		OwnVertex naechsterSchnitt = disjunkteEulerKreise.get(i+1).get(0);
		List<OwnVertex>     einDisjunkterKreisListe = disjunkteEulerKreise.get(i);
		Iterator<OwnVertex> einDisjunkterKreisIterator= einDisjunkterKreisListe.iterator();
		eulerKreis.add(einDisjunkterKreisIterator.next());
		
		while(einDisjunkterKreisIterator.hasNext()){
			
			OwnVertex v = einDisjunkterKreisIterator.next();
			
			if(v.equals(naechsterSchnitt)){
				buildEulerKreis(eulerKreis, i+1);
			}else{
				eulerKreis.add(v);
			}
		}
		
		return eulerKreis;
	}
	
	
	
	/**
	 * 
	 * {Knotengrad von v1} \ {Visited edges}
	 * 
	 */
}
