package gka.GraphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphBuilder.Extension.VertexNameGenerator;

public class UndirectedCoherentlyEvenGenerator {

	private Graph<OwnVertex, OwnEdge> _graph;
	private List<OwnVertex> _verticesList;

	public UndirectedCoherentlyEvenGenerator(Graph<OwnVertex, OwnEdge> graph) {
		_graph = graph;
		_verticesList = new ArrayList<OwnVertex>();
		// TODO Auto-generated constructor stub

	}

	public void generate(int vertices, int edges) {
		System.out.println(vertices);
		System.out.println(edges);

		if(vertices <= 0) return;

		// create Vertices	
		createVertices(vertices);	
		
		// create Edges
		createCoherentlyEdges(edges);
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
			if(!_graph.addVertex(v)){
				while(!_graph.addVertex(v)){
					v = new OwnVertex(VertexNameGenerator.getInstance().getNext());	
				}
			}
		}
	}
	
	private void createAEdge(OwnVertex source, OwnVertex target){
		
		int max = 1000;
		Random rand = new Random();
		
		OwnEdge e = new OwnEdge(rand.nextInt(max));
		
		if(source == null || target == null)
		{	
			do{
			source = getRandomVertex(_graph);
			target = getRandomVertex(_graph);	
			} while(source.equals(target) && (_graph.getVertexCount() > 1));
		}
				
		_graph.addEdge(e, source, target);
		
	}

	
	private void createCoherentlyEdges(int edges)
	{
		if(edges < _graph.getVertexCount()) edges +=  (_graph.getVertexCount()+1)*2;
		
		OwnVertex lastVertex = getRandomVertex(_graph);
		
		// connect minimum
		for(OwnVertex v : _graph.getVertices()){
			
			if(v.equals(lastVertex)) continue;
			
			createAEdge(lastVertex, v);
			
			lastVertex = v;
			
		}
		
		int edgesToAdd = (edges - _graph.getEdgeCount());
		
		for(int i = 0; i < edgesToAdd; i++){
			createAEdge(null, null);
		}
	}

}
