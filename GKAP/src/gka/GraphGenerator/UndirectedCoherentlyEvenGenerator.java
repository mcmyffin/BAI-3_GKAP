package gka.GraphGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphBuilder.Extension.VertexNameGenerator;

public class UndirectedCoherentlyEvenGenerator {

	private Graph<OwnVertex, OwnEdge> graph;
	private List<OwnVertex> verticesList;
	private List<List<OwnVertex>> disjointCircles;
	
	private Random rand;

	public UndirectedCoherentlyEvenGenerator(Graph<OwnVertex, OwnEdge> graph) {
		this.graph = graph;
		this.verticesList = new ArrayList<OwnVertex>();
		this.disjointCircles = new LinkedList();
		
		this.rand = new Random();
	}

	public void generate(int vertices, int edges) {
		
		// preconditions vertices and edges
		if(!defaultPrecondition(vertices,edges)) return;
		// create vertices
		List<OwnVertex> verts = createVertices(vertices);
		
		// create disjunkte kreise
		createCircles(verts);
		
		// connect disjunkte kreise
		connectCircles();
		
		// korrektur auf ungeraden Knotengrad
		correctGraph();
		
	}	

	private List<OwnVertex> createVertices(int vertices){
		
		List<OwnVertex> createdVertices = new LinkedList();
		
		for(int i = 0; i < vertices; i++){
		
			String vertexName = VertexNameGenerator.getInstance().getNext();
			OwnVertex v = new OwnVertex(vertexName);
			createdVertices.add(v);
		}
		
		return createdVertices;
	}
	
	private void createCircles(List<OwnVertex> createdVertices){
		
		final int minimalCircleSize = 3;
		
		int tmpMaxSize = createdVertices.size()/4;
		final int maximalCircleSize = (tmpMaxSize < 3 ? 4 : tmpMaxSize);
		
		
		while(!createdVertices.isEmpty() && createdVertices.size() >= minimalCircleSize){

			List<OwnVertex> disjointCircle = new LinkedList();
			int numberOfCircles = rand.nextInt((maximalCircleSize-minimalCircleSize))+minimalCircleSize;
			
			for(int i = 0; i <= numberOfCircles && !createdVertices.isEmpty(); i++){
				
				disjointCircle.add(createdVertices.remove(0));
			}
			disjointCircles.add(disjointCircle);
		}
		
		disjointCircles.get(0).addAll(createdVertices);
	}
	
	
	private void connectCircles(){
		
		for(List<OwnVertex> circle : disjointCircles){
			
			OwnVertex start  = circle.get(0);
			OwnVertex before = start;
			for(int i = 1; i < circle.size(); i++){
				
				OwnVertex v = circle.get(i);
				
				// connect together
				connectVerts(before, v);
				before = v;
			}
			connectVerts(before, start);
		}
		
		
		final int minConnections = disjointCircles.size();
		final int maxConnections = (graph.getVertexCount()+1)*10;
		
		for(int i = 0; i < (rand.nextInt(maxConnections-minConnections)+minConnections); i++){
			
			int index1 = i % disjointCircles.size();
			int index2 = (i+1) % disjointCircles.size();
			
			connectTwoCircles(index1, index2);
		}
	}
	
	private void correctGraph(){
		
		List<OwnVertex> vertsToConnect = getVeticesWitUngeradenKnotengrad();
		
		for(int i = 0; !vertsToConnect.isEmpty(); i++){
			
			int index1 = i % vertsToConnect.size();
			OwnVertex v1 = vertsToConnect.remove(index1);
			
			int index2 = (i+1) % vertsToConnect.size();
			OwnVertex v2 = vertsToConnect.remove(index2);
			
			connectVerts(v1, v2);
		}
	}
	
	
	private List<OwnVertex> getVeticesWitUngeradenKnotengrad(){
		
		List<OwnVertex> vertices = new ArrayList();
		
		for(OwnVertex v : graph.getVertices()){
			
			if((graph.getOutEdges(v).size() % 2) != 0){
				vertices.add(v);
			}
		}
		
		return vertices;
	}
	
	
	private void connectTwoCircles(int circle1, int circle2){
		
		OwnVertex v1 = getRandomVertexFromList(disjointCircles.get(circle1));
		OwnVertex v2 = getRandomVertexFromList(disjointCircles.get(circle2));
		
		OwnEdge e = new OwnEdge();
		graph.addEdge(e, v1, v2);
	}
	
	
	
	private OwnVertex getRandomVertexFromList(List<OwnVertex> vertexList){
		
		int index = rand.nextInt(vertexList.size());
		OwnVertex v = vertexList.get(index);
		
		return v;
	}
	
	private void connectVerts(OwnVertex v1, OwnVertex v2){
		
		OwnEdge e = new OwnEdge();
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(e, v1,v2);
	}
	
	
	private boolean defaultPrecondition(int vertices, int edges){
		
		if(vertices == 0) return false;
		if(vertices < 3) return false; 
		else return true;
	}
}
