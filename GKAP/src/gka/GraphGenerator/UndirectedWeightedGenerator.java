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

	public void generate(int vertices, int edges, int minEdgeLength, int maxEdgeLenth, int verteilungsFaktor){
		
		if(vertices <= 0) return;
		
		int edgesPerVertex = Math.round(edges/vertices);
		edgesPerVertex = ((edgesPerVertex >= 1 ? edgesPerVertex : 1)) * verteilungsFaktor;
		
		System.out.println("EdgePerVertex: "+edgesPerVertex);

		// create Vertices
		for(int i = 0; i < vertices; i++){
			createVertex();	
		}
		
		// create Edges
		for(int i = 0; i < edges; i++){
			createEdge(minEdgeLength, maxEdgeLenth, edgesPerVertex);
		}
		
	}	

	void createEdge(int min, int max, int edgesPerVertex){
		
		Random rand = new Random();
		
		OwnEdge e = new OwnEdge((rand.nextInt(max - min))+min);
		OwnVertex source = getRandomVertex(graph);
		OwnVertex target = getRandomVertex(graph);

		int s_outgoing = graph.getSuccessorCount(source);
		int t_ingoing  = graph.getPredecessorCount(target);
		
		graph.addEdge(e, source, target);
		
//		if (s_outgoing < edgesPerVertex &&  t_ingoing < edgesPerVertex)
//		{
//			graph.addEdge(e , source , target);
//			
//		}
//		else if(s_outgoing < edgesPerVertex)
//		{
//			for(OwnVertex v : graph.getVertices())
//			{
//				if(graph.getSuccessorCount(v) < edgesPerVertex)
//				{
//					e = new OwnEdge(((rand.nextInt(max - min))+min));
//					graph.addEdge(e , source , v);
//				}			
//			}
//		}
//		else if(t_ingoing < edgesPerVertex)
//		{
//			for(OwnVertex v : graph.getVertices())
//			{
//				if(graph.getPredecessorCount(v) < edgesPerVertex)
//				{
//					e = new OwnEdge(((rand.nextInt(max - min))+min));
//					graph.addEdge(e , v , target);
//				}					
//			}
//		}
//		else
//		{
//			createEdge(min,max,edgesPerVertex);	
//		}

	}

	private OwnVertex getRandomVertex(Graph<OwnVertex,OwnEdge> g){
		
		Random r = new Random();
		
		int j = r.nextInt(g.getVertexCount()-1);
		
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
