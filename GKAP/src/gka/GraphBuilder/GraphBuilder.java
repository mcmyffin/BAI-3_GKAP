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
	private String header;
	
	public final static String UNDIRECTED = "UNDIRECTED";
	public final static String UNDIRECTED_WEIGHTED 	= "UNDIRECTED_WEIGHTED";
	public final static String UNDIRECTED_ATTRIBUTED= "UNDIRECTED_ATTRIBUTED";
	public final static String UNDIRECTED_WEIGHTED_ATTRIBUTED = "UNDIRECTED_WEIGHTED_ATTRIBUTED";
	public final static String DIRECTED   = "DIRECTED";
	public final static String DIRECTED_WEIGHTED   	= "DIRECTED_WEIGHTED";
	public final static String DIRECTED_ATTRIBUTED  = "DIRECTED_ATTRIBUTED";
	public final static String DIRECTED_WEIGHTED_ATTRIBUTED   = "DIRECTED_WEIGHTED_ATTRIBUTED";
	
	public final static String WEIGHTED 	= "WEIGHTED";
	public final static String ATTRIBUTED 	= "ATTRIBUTED";
	
//	private List<OwnVertex> vertices = new ArrayList();
//	private List<OwnEdge>   edges    = new ArrayList();
	
	/**Implemented Interface
	 * @throws GraphBuildException **/
	
	@Override
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) throws GraphBuildException{
		
		
		switch(witchTypOfGraph(list)){
			
			case(GraphBuilder.UNDIRECTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED;
				
				System.out.println("undr");
				fillGraph(list);

				return graph;
				
			case(GraphBuilder.UNDIRECTED_ATTRIBUTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_ATTRIBUTED;
			
				System.out.println("undr_attr");
				fillAttributedGraph(list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED):
			
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED;
			
				System.out.println("undr_weight");
				fillWeightedGraph(list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED):
				
				graph  = new UndirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED;
			
				System.out.println("undr_weight_attr");
				fillWeightedAttributedGraph(list);
			
				return graph;
				
			case(GraphBuilder.DIRECTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED;
				
				System.out.println("dir");
				fillGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_WEIGHTED;
			
				System.out.println("dir_weight");
				fillWeightedGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_ATTRIBUTED):
				
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_ATTRIBUTED;
			
				System.out.println("dir_attr");
				fillGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED):
			
				graph = new DirectedOrderedSparseMultigraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED;
			
				System.out.println("dir_weight_attr");
				fillGraph(list);
				
				return graph;
				
		}
		throw new GraphBuildException();
	}
	
	private void fillGraph(List<String> list){
	
		
		for(String line : list){
			
			String lineSpaceFree = line.replaceAll("\\s+", "");
			
			if(lineSpaceFree.contains(","))
			{
				// normal Graph(Directed/ Undirected)
				// split by ","
				String[] splitInTwo = lineSpaceFree.split(",");
				
				String v1 = splitInTwo[0];
				String v2 = splitInTwo[1];
				
				OwnVertex vertex1 = new OwnVertex(v1);
				OwnVertex vertex2 = new OwnVertex(v2);
				OwnEdge   edge = new OwnEdge();
				
				graph.addEdge(edge,vertex1, vertex2);					
				
				
			}
			else
			{
				// only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				graph.addVertex(vertex1);
			}
		}
		
	}
	
	private void fillAttributedGraph(List<String> list){

		for(String line : list)
		{
			
			String lineSpaceFree = line.replaceAll("\\s+", "");
			
			if(lineSpaceFree.contains(",")){
				
				// attributed Graph (Directed/ Undirected)
				// split by ","
				String[] splitInTwo = lineSpaceFree.split(",");
				
				String v1 		= "";
				String v2 		= "";
				int    v1_attr 	= 0;
				int    v2_attr 	= 0;

				// Vertex1
				if(splitInTwo[0].contains(":"))
				{
					// split by ":" to get Attribute
					String[] vertex1Content = splitInTwo[0].split(":");
					
					v1 = vertex1Content[0];
					v1_attr = Integer.parseInt(vertex1Content[1]); // todo catch Exception
				}
				else
				{
					// if Vertex without Attribute
					v1 = splitInTwo[0];
				}
				
				// Vertex2
				if(splitInTwo[1].contains(":"))
				{
					// split by ":" to get Attribute
					String[] vertex2Content = splitInTwo[1].split(":");
					
					v2 = vertex2Content[0];
					v2_attr = Integer.parseInt(vertex2Content[1]); // todo catch Exception
				}
				else
				{
					// if Vertex without Attribute
					v2 = splitInTwo[1];
				}
				
				OwnVertex vertex1 = new OwnVertex(v1, v1_attr);
				OwnVertex vertex2 = new OwnVertex(v2, v2_attr);
				OwnEdge edge = new OwnEdge();
				
				graph.addEdge(edge,vertex1, vertex2);
				
				
			}else{

				// Only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				graph.addVertex(vertex1);
				
			}
		}
	}
	
	private void fillWeightedGraph(List<String> list){
		
		
		for(String line : list){
			
			String lineSpaceFree = line.replaceAll("\\s+", "");
			
			if(lineSpaceFree.contains(","))
			{
				if(lineSpaceFree.contains("::")){
					
					// weighted Graph (Directed/ Undirected)
					// split by "::"
					String[] splitInTwo = lineSpaceFree.split("::");
					String[] vertexInfo = splitInTwo[0].split(",");
					String v1 		= vertexInfo[0];
					String v2 		= vertexInfo[1];
					int weight		= Integer.parseInt(splitInTwo[1]);
					
					OwnVertex vertex1 = new OwnVertex(v1);
					OwnVertex vertex2 = new OwnVertex(v2);
					OwnEdge edge = new OwnEdge(weight);

					graph.addEdge(edge,vertex1, vertex2);					
				}
				
			}
			else
			{
				// only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				graph.addVertex(vertex1);
			}
		}
	}
	
	private void fillWeightedAttributedGraph(List<String> list){
		
		
		for(String line : list){
			
			String lineSpaceFree = line.replaceAll("\\s+", "");
			
			if(lineSpaceFree.contains(","))
			{
				// weighted Graph (Directed/ Undirected)
				// split by "::"
				String v1 = "";
				String v2 = ""; 
				int	   a1 = 0;
				int	   a2 = 0;
				int weight = 0;
				
				if(lineSpaceFree.contains("::")){
					
					String[] splitInTwo = lineSpaceFree.split("::");
					lineSpaceFree = splitInTwo[0];
					
					weight = Integer.parseInt(splitInTwo[1]);
				}
				
				String[] vertexSplit = lineSpaceFree.split(",");
				
				// Vertex1
				if(vertexSplit[0].contains(":"))
				{
					String[] splitInTwo = vertexSplit[0].split(":");
					
					v1 = splitInTwo[0];
					a1 = Integer.parseInt(splitInTwo[1]); // todo catch Exception
				}
				else
				{
					v1 = vertexSplit[0];
				}
				
				// Vertex2
				if(vertexSplit[1].contains(":"))
				{
					String[] splitInTwo = vertexSplit[1].split(":");
					
					v2 = splitInTwo[0];
					a2 = Integer.parseInt(splitInTwo[1]); // todo catch Exception
				}
				else
				{
					v2 = vertexSplit[1];
				}
				
				
				OwnVertex vertex1 = new OwnVertex(v1, a1);
				OwnVertex vertex2 = new OwnVertex(v2, a2);
				OwnEdge edge = new OwnEdge(weight);
				
				graph.addEdge(edge, vertex1, vertex2);					
			}
			else
			{
				// only a Vertex
				// todo to check at attribute
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				graph.addVertex(vertex1);
			}
		}
	}
	
	/*
	 * Witch Type of Graph
	 * 
	 * Private helper method from buildGraph(List<String> list)
	 * It decides witch type of Graph the "list" contains
	 */
	private String witchTypOfGraph(List<String> list){
		
		// precondition
		if(list == null) return null;
		if(list.isEmpty()) return null;

		String firstLine = list.get(0);
		if(firstLine.contains("#"))
		{
			// remove header
			list.remove(0);
			
			// save header
			header = firstLine;
			
			// delete Whitespace
			// safety lower Case
			String line = (firstLine.replaceAll("\\s+", "")).toLowerCase();
			System.out.println("HEADER ->: "+line);
			
			// DIRECTED
			if(line.contains(GraphType.DIRECTED.getValue()) && line.length() >= GraphType.DIRECTED.getLength())
			{
				// DIRECTED WEIGHTED ATTRIBUTED
				if(line.contains(GraphType.WEIGHTED.getValue()) && line.contains(GraphType.ATTRIBUTED.getValue()))
				{
					return GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED;
				}
				// DIRECTED ATTRIBUTED
				else if(line.contains(GraphType.ATTRIBUTED.getValue()))
				{
					return GraphBuilder.DIRECTED_ATTRIBUTED;
				}
				// DIRECTED WEIGHTED
				else if(line.contains(GraphType.WEIGHTED.getValue()))
				{
					return GraphBuilder.DIRECTED_WEIGHTED;
				}
				// DIRECTED DEFAULT
				else
				{
					return GraphBuilder.DIRECTED;
				}
			
			}
			else
			{
				
				// UNDIRECTED WEIGHTED ATTRIBUTED
				if(line.contains(GraphType.WEIGHTED.getValue()) && line.contains(GraphType.ATTRIBUTED.getValue()))
				{
					return GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED;
				}
				// UNDIRECTED ATTRIBUTED
				else if(line.contains(GraphType.ATTRIBUTED.getValue()))
				{
					return GraphBuilder.UNDIRECTED_ATTRIBUTED;
				}
				// DIRECTED WEIGHTED
				else if(line.contains(GraphType.WEIGHTED.getValue()))
				{
					return GraphBuilder.UNDIRECTED_WEIGHTED;
				}
				// UNDIRECTED DEFAULT
				else
				{
					return GraphBuilder.UNDIRECTED;
				}
			}
		}
		else
		{
			// DEFAULT RETURN
			return GraphBuilder.UNDIRECTED;			
		}
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

	private OwnEdge getEdgeByID(long id){
		
		OwnEdge edge = new OwnEdge(id);
		if(graph.containsEdge(edge)){
			
			for(OwnEdge aEdge : graph.getEdges()){
				
				if(aEdge.equals(edge)) return aEdge;
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
		
		List<String> res = new ArrayList();
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
					res.add(v.get_name());
				}
			}
		}
		
		return res;
	}

}
