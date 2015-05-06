package gka.GraphGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphBuilder.Extension.VertexNameGenerator;

public class UndirectedWeightedGenerator extends Thread{

	private Graph<OwnVertex,OwnEdge> graph;


	public UndirectedWeightedGenerator(Graph<OwnVertex,OwnEdge> graph){
		this.graph = graph;
	}

	public void generate(int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int spread){
		
		if(vertices <= 0) return;

		// create Vertices
		for(int i = 0; i < vertices; i++){
			createVertex();	
		}
		
		// create Edges
		for(int i = 0; i < edges; i++){
			createEdge(minEdgeLength, maxEdgeLenth);
		}
		
	}	

	void createEdge(int min, int max){
		
		Random rand = new Random();
		
		OwnEdge e = new OwnEdge((rand.nextInt(max - min))+min);
		OwnVertex source = getRandomVertex(graph);
		OwnVertex target = getRandomVertex(graph);
		
		graph.addEdge(e, source, target);
	}

	private OwnVertex getRandomVertex(Graph<OwnVertex,OwnEdge> g){
		
		Random r = new Random();
		
		int j = r.nextInt(g.getVertexCount());
		
		for(OwnVertex v: g.getVertices()){
			
			if(j <= 0){
				return v;
			}
			j--;
		}
		return null;
	}
	
	
	private void createVertex()
	{
		OwnVertex v = new OwnVertex(VertexNameGenerator.getInstance().getNext());	
		if(!graph.addVertex(v)){
			while(!graph.addVertex(v)){
				v = new OwnVertex(VertexNameGenerator.getInstance().getNext());	
			}
		}
	}

	
	
}
