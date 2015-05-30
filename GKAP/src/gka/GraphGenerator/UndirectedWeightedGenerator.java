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

	public void generate(int vertices, int edges, boolean coherently){
		
		if(vertices <= 0) return;

		// create Vertices	
		createVertices(vertices);	
		
		
		// create Edges
		
		if(coherently){
			createCoherentlyEdges(edges);
		}else{
			createRandomEdges(edges);
		}
		
		
		
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
	
	
	private void createVertices(int vertices)
	{
		for(int i = 0; i < vertices; i++){
			
			OwnVertex v = new OwnVertex(VertexNameGenerator.getInstance().getNext());	
			if(!graph.addVertex(v)){
				while(!graph.addVertex(v)){
					v = new OwnVertex(VertexNameGenerator.getInstance().getNext());	
				}
			}
		}
	}
	
	private void createARandomEdge(OwnVertex source, OwnVertex target, boolean withSelfEdge){
		
		int min = 10;
		int max = 1000;
		Random rand = new Random();
		
		OwnEdge e = new OwnEdge((rand.nextInt(max - min))+min);
		
		if(source == null || target == null){
		
			source = getRandomVertex(graph);
			target = getRandomVertex(graph);
			
			if(!withSelfEdge){
				
				while(source.equals(target) && (graph.getVertexCount() > 1)){
					
					source = getRandomVertex(graph);
					target = getRandomVertex(graph);
				}
			}
		}
		
		graph.addEdge(e, source, target);
		
	}
	
	private void createRandomEdges(int edges){
		
		for(int i = 0; i < edges; i++){
			createARandomEdge(null, null,true);
		}
	}
	
	
	private void createCoherentlyEdges(int edges)
	{
		if(edges < graph.getVertexCount()) edges +=  (graph.getVertexCount()+1)*2;
		
		OwnVertex lastVertex = getRandomVertex(graph);
		
		// connect minimum
		for(OwnVertex v : graph.getVertices()){
			
			if(v.equals(lastVertex)) continue;
			
			createARandomEdge(lastVertex, v, false);
			
			lastVertex = v;
			
		}
		
		int edgesToAdd = (edges - graph.getEdgeCount());
		
		for(int i = 0; i < edgesToAdd; i++){
			createARandomEdge(null, null, false);
		}
	}

	
	
}
