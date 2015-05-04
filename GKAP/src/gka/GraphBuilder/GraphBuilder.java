package gka.GraphBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import gka.Exceptions.GraphBuildException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;




public class GraphBuilder implements IGraphBuilder{

	private Scanner scanner;
	private Graph<OwnVertex,OwnEdge> graph;
	private String graphType;
	String header;
	
	// UNDIRECTED
	public final static String UNDIRECTED = "UNDIRECTED";
	public final static String UNDIRECTED_WEIGHTED 	= "UNDIRECTED_WEIGHTED";
	public final static String UNDIRECTED_ATTRIBUTED= "UNDIRECTED_ATTRIBUTED";
	public final static String UNDIRECTED_WEIGHTED_ATTRIBUTED = "UNDIRECTED_WEIGHTED_ATTRIBUTED";
	
	// DIRECTED
	public final static String DIRECTED   = "DIRECTED";
	public final static String DIRECTED_WEIGHTED   	= "DIRECTED_WEIGHTED";
	public final static String DIRECTED_ATTRIBUTED  = "DIRECTED_ATTRIBUTED";
	public final static String DIRECTED_WEIGHTED_ATTRIBUTED   = "DIRECTED_WEIGHTED_ATTRIBUTED";
	
	public final static String WEIGHTED 	= "WEIGHTED";
	public final static String ATTRIBUTED 	= "ATTRIBUTED";
	
//	private List<OwnVertex> vertices = new ArrayList();
//	private List<OwnEdge>   edges    = new ArrayList();
	
	
	/*** private Class methods ***/
	
	private OwnEdge getEdgeByID(long id){
		
		OwnEdge edge = new OwnEdge(id);
		if(graph.containsEdge(edge)){
			
			for(OwnEdge aEdge : graph.getEdges()){
				
				if(aEdge.equals(edge)) return aEdge;
			}
		}
		return null;
	}
	
	
	
	/*** Implemented Interface methods ***/
	
	@Override
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) throws GraphBuildException{
		
		GraphScanner scanner = new GraphScanner(this);
		switch(scanner.whichTypOfGraph(list)){
			
			case(GraphBuilder.UNDIRECTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED;
				
				System.out.println("undr");
				GraphAssembler.fillGraph(graph,list);

				return graph;
				
			case(GraphBuilder.UNDIRECTED_ATTRIBUTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_ATTRIBUTED;
			
				System.out.println("undr_attr");
				GraphAssembler.fillAttributedGraph(graph,list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED):
			
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED;
			
				System.out.println("undr_weight");
				GraphAssembler.fillWeightedGraph(graph,list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED;
			
				System.out.println("undr_weight_attr");
				GraphAssembler.fillWeightedAttributedGraph(graph,list);
			
				return graph;
				
			case(GraphBuilder.DIRECTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED;
				
				System.out.println("dir");
				GraphAssembler.fillGraph(graph,list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_WEIGHTED;
			
				System.out.println("dir_weight");
				GraphAssembler.fillWeightedGraph(graph,list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_ATTRIBUTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_ATTRIBUTED;
			
				System.out.println("dir_attr");
				GraphAssembler.fillAttributedGraph(graph,list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED):
			
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED;
			
				System.out.println("dir_weight_attr");
				GraphAssembler.fillWeightedAttributedGraph(graph,list);
				
				return graph;
				
		}
		throw new GraphBuildException();
	}
	
	@Override
	public String getGraphType() {
		return graphType;
	}

	@Override
	public Collection<OwnVertex> getAllVertices() {
		return this.graph.getVertices();
	}
	
	@Override
	public List<String> getAllVerticesAsString(){
		
		List<String> vertices = new ArrayList<>();
		for(OwnVertex v : graph.getVertices()){
			
			vertices.add(v.get_name());
		}
		return vertices;
	}

	@Override
	public boolean addVertex(String vertex){
		
		return this.graph.addVertex(new OwnVertex(vertex));
	}
	
	@Override
	public boolean addVertex(String vertex, int attribute){
		
		return this.graph.addVertex(new OwnVertex(vertex, attribute));
	}
	
	@Override
	public OwnVertex getVertexByName(String v){
		
		OwnVertex vertex = new OwnVertex(v);
		if(this.graph.containsVertex(vertex)){
		
			for(OwnVertex aVertex : graph.getVertices()){
				
				if(aVertex.equals(vertex)) return aVertex;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean removeVertex(String vertex) {
		
		OwnVertex v = getVertexByName(vertex);
		
		if(v == null) return false;
		
		return graph.removeVertex(v);
	}
	
	@Override
	public boolean addEdge(int weight, String vertex1, String vertex2) {

		OwnVertex v1 = new OwnVertex(vertex1);
		OwnVertex v2 = new OwnVertex(vertex2);
		OwnEdge e = new OwnEdge(weight);
		
		if(graph.containsVertex(v1) & graph.containsVertex(v2)){
			return graph.addEdge(e, v1,v2);
		}
		
		return false;
	}

	@Override
	public boolean removeEdge(long edgeID) {

		OwnEdge e = getEdgeByID(edgeID);
		if(e == null) return false;
		
		return graph.removeEdge(e);
	}

	@Override
	public Collection<OwnEdge> getAllEdges() {
		return graph.getEdges();
	}
	
	@Override
	public List<String> getAllEdgesAsString(){
		
		List<String> edges = new ArrayList<>();
		
		for(OwnEdge e : graph.getEdges()){
			edges.add(Long.toString(e.getID()));
		}
		return edges;
	}
	
	@Override
	public List<String> getSaveableGraph() {
		
		List<String> res = new ArrayList<String>();
		List<OwnVertex> vertexWithEdge = new ArrayList<OwnVertex>();
		res.add(header);
		
		for(OwnEdge edge : graph.getEdges()){
			
			Pair<OwnVertex> pair = graph.getEndpoints(edge);
			
			OwnVertex s_v = pair.getFirst();
			OwnVertex t_v = pair.getSecond();
			
			String v1 = s_v.get_name();
			String a1 = s_v.get_attribute()+"";
			
			String v2 = t_v.get_name();
			String a2 = t_v.get_attribute()+"";
			
			String weight = edge.getWeight()+"";
			String line;
			
			if(graphType.contains(GraphBuilder.WEIGHTED) && graphType.contains(GraphBuilder.ATTRIBUTED))
			{
				line = v1+":"+a1+","+v2+":"+a2+"::"+weight;
			}
			else if(graphType.contains(GraphBuilder.WEIGHTED))
			{
				line = v1+","+v2+"::"+weight;
			}
			else if(graphType.contains(GraphBuilder.ATTRIBUTED))
			{
				line = v1+":"+a1+","+v2+":"+a2;
			}else
			{
				line = v1+","+v2;
			}
			
			vertexWithEdge.add(s_v);
			vertexWithEdge.add(t_v);
			res.add(line);
		}
		
		if(vertexWithEdge.size() != graph.getVertexCount()){
			for(OwnVertex v : graph.getVertices()){
				if(!vertexWithEdge.contains(v)){
					
					String line = v.get_name();
					if(graphType.contains(GraphBuilder.ATTRIBUTED))
					{
						line = v+":"+v.get_attribute();
					}
					res.add(line);
				}
			}
		}
		return res;
	}

	
	@Override
	public Graph<OwnVertex, OwnEdge> createNewGraph(GraphType... type) throws GraphBuildException {
		
		String tmpHeader = GraphType.createHeader(type);
		GraphScanner scanner = new GraphScanner(this);
		
		List<String> emptyGraphContent = new ArrayList<String>();
		emptyGraphContent.add(tmpHeader);
		
		
		switch(scanner.whichTypOfGraph(emptyGraphContent)){
			
			case(GraphBuilder.UNDIRECTED_WEIGHTED):
			
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				this.graphType = UNDIRECTED_WEIGHTED;
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				this.graphType = UNDIRECTED_WEIGHTED_ATTRIBUTED;
				return graph;
		}
		throw new GraphBuildException();
	}

}
