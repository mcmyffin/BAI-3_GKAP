package gka.GraphGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphBuilder.Extension.VertexNameGenerator;

public class UndirectedWeightedAttributedGenerator {

	private Map<OwnVertex,Integer> vertexValueMap;
	private List<OwnVertex> verticesList;
	private List<Integer> vertexValueHistoryList;
	private Graph<OwnVertex,OwnEdge> graph;
	
	public UndirectedWeightedAttributedGenerator(Graph<OwnVertex,OwnEdge> graph){
		this.graph = graph;
		this.verticesList = new ArrayList<OwnVertex>();
		this.vertexValueHistoryList = new ArrayList<Integer>();
		this.vertexValueMap = new HashMap<OwnVertex, Integer>();
	}
	
	public void generate(int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int spread){
		
		// precondition
		if(vertices <= 0) return;

		// create Vertices
		createVertex(vertices);
		
		// set Random Vertex values
		setRandomValues(vertices);
		
		// calculate Heuristic
		calculateHeuristic();
		
		// add Vertices to Graph
		addVertices();
		
		// insert and calculate edges
		addEdges(edges);
		
	}
	
	
	private void addEdges(int edges){
		
		Random rand = new Random();
		for(int i = 0; i < edges ; i++){
			
			OwnVertex v1 = getRandomVertex(verticesList);
			OwnVertex v2 = getRandomVertex(verticesList);
			
			int minEdgeLength = Math.abs(v1.get_attribute() - v2.get_attribute());
			int maxEdgeLength = (minEdgeLength+1)*20;
			
			OwnEdge e = new OwnEdge((rand.nextInt(maxEdgeLength - minEdgeLength))+minEdgeLength);
			graph.addEdge(e,v1,v2);
		}
	}
	
	
	private void addVertices(){
		
		for(OwnVertex v : verticesList){
			graph.addVertex(v);
		}
	}
	
	private void calculateHeuristic(){
		
		verticesList.sort(new ValueComperator(vertexValueMap));
		OwnVertex target = verticesList.get(verticesList.size()-1);
		
		target.set_attribute(0);
		int target_val = vertexValueMap.get(target);
		
		for(OwnVertex v : verticesList){
			
			int v_value = vertexValueMap.get(v);
			int attribute = Math.abs(target_val - v_value);				

			if(!v.equals(target)) v.set_attribute(attribute);;
		}
	}
	
	private void setRandomValues(int vertices){
		
		Random rand = new Random();
		int tmpMaxVal = 20 * vertices;
		
		for(OwnVertex v : this.verticesList){
			
			int vertexVal = rand.nextInt(tmpMaxVal);
			if(vertexValueHistoryList.contains(vertexVal)){
				while(vertexValueHistoryList.contains(vertexVal)){
					vertexVal = rand.nextInt(tmpMaxVal);
				}
			}
			
			this.vertexValueMap.put(v, vertexVal);
		}
		
	}
	
	
	private void createVertex(int vertices)
	{
		for(int i = 0; i < vertices; i++){
			OwnVertex v = new OwnVertex(VertexNameGenerator.getInstance().getNext());
			this.verticesList.add(v);
		}
	}
	
	private OwnVertex getRandomVertex(List<OwnVertex> g){
		
		Random r = new Random();
		int j = r.nextInt(g.size());
		j = (j < 0 ? 0 : j);
		
		return g.get(j);
	}
	
}

class ValueComperator implements Comparator<OwnVertex>{

	private Map<OwnVertex,Integer> vertexValue;
	
	public ValueComperator(Map<OwnVertex,Integer> vertexValue) {
		this.vertexValue = vertexValue;
	}
	
	@Override
	public int compare(OwnVertex o1, OwnVertex o2) {
		int v1 = vertexValue.get(o1);
		int v2 = vertexValue.get(o2);
		return Integer.compare(v1, v2);
	}
	
}
