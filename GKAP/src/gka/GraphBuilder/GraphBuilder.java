package gka.GraphBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import gka.Exceptions.GraphBuildException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;



public class GraphBuilder implements IGraphBuilder{

	private Graph<OwnVertex,OwnEdge> graph;
	private String graphType;
	
	public final static String UNDIRECTED = "UNDIRECTED";
	public final static String UNDIRECTED_WEIGHTED = "UNDIRECTED_WEIGHTED";
	public final static String UNDIRECTED_ATTRIBUTED = "UNDIRECTED_ATTRIBUTED";
	public final static String UNDIRECTED_WEIGHTED_ATTRIBUTED = "UNDIRECTED_WEIGHTED_ATTRIBUTED";
	public final static String DIRECTED   = "DIRECTED";
	public final static String DIRECTED_WEIGHTED   = "DIRECTED_WEIGHTED";
	public final static String DIRECTED_ATTRIBUTED   = "DIRECTED_ATTRIBUTED";
	public final static String DIRECTED_WEIGHTED_ATTRIBUTED   = "DIRECTED_WEIGHTED_ATTRIBUTED";
	
	public final static String WEIGHTED = "WEIGHTED";
	public final static String ATTRIBUTED = "ATTRIBUTED";
	
	private List<OwnVertex> vertices = new ArrayList();
	private List<OwnEdge>   edges = new ArrayList();
	
	/**Implemented Interface
	 * @throws GraphBuildException **/
	
	@Override
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) throws GraphBuildException{
		
		
		switch(witchTypOfGraph(list)){
			
			case(GraphBuilder.UNDIRECTED):
				
				graph  = new UndirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED;
				
				System.out.println("undr");
				fillGraph(list);

				return graph;
				
			case(GraphBuilder.UNDIRECTED_ATTRIBUTED):
				
				graph  = new UndirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_ATTRIBUTED;
			
				System.out.println("undr_attr");
				fillAttributedGraph(list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED):
			
				graph  = new UndirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED;
			
				System.out.println("undr_weight");
				fillWeightedGraph(list);
				
				return graph;
				
			case(GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED):
				
				graph  = new UndirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED;
			
				System.out.println("undr_weight_attr");
				fillWeightedAttributedGraph(list);
			
				return graph;
				
			case(GraphBuilder.DIRECTED):
				
				graph = new DirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED;
				
				System.out.println("dir");
				fillGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED):
				
				graph = new DirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_WEIGHTED;
			
				System.out.println("dir_weight");
				fillGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_ATTRIBUTED):
				
				graph = new DirectedSparseGraph<OwnVertex, OwnEdge>();
				graphType = GraphBuilder.DIRECTED_ATTRIBUTED;
			
				System.out.println("dir_attr");
				fillGraph(list);
				
				return graph;
				
			case(GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED):
			
				graph = new DirectedSparseGraph<OwnVertex, OwnEdge>();
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
				
				if(!addVertex(vertex1)){
					vertex1 = getVertexByName(vertex1.get_name());
				}
				
				if(!addVertex(vertex2)){
					vertex2 = getVertexByName(vertex2.get_name());
				}
				
				addEdge(edge,vertex1, vertex2);
				
			}
			else
			{
				// only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				addVertex(vertex1);
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
				
				if(!addVertex(vertex1)){
					vertex1 = getVertexByName(vertex1.get_name());
				}
				
				if(!addVertex(vertex2)){
					vertex2 = getVertexByName(vertex2.get_name());
				}
				
				addEdge(edge,vertex1, vertex2);
				
			}else{

				// Only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				addVertex(vertex1);
				
			}
		}
	}
	
	private void fillWeightedGraph(List<String> list){
		
		
		for(String line : list){
			
			String lineSpaceFree = line.replaceAll("\\s+", "");
			
			if(lineSpaceFree.contains(",") && lineSpaceFree.contains("::"))
			{
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
				
				if(!addVertex(vertex1)){
					vertex1 = getVertexByName(vertex1.get_name());
				}
				
				if(!addVertex(vertex2)){
					vertex2 = getVertexByName(vertex2.get_name());
				}
				
				addEdge(edge,vertex1, vertex2);
				
			}
			else
			{
				// only a Vertex
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				addVertex(vertex1);
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
				
				if(!addVertex(vertex1)){
					vertex1 = getVertexByName(vertex1.get_name());
				}
				
				if(!addVertex(vertex2)){
					vertex2 = getVertexByName(vertex2.get_name());
				}
				
				addEdge(edge,vertex1, vertex2);
			}
			
			else
			{
				// only a Vertex
				
				// todo to check at attribute
				OwnVertex vertex1 = new OwnVertex(lineSpaceFree);
				addVertex(vertex1);
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
	public List<OwnVertex> getAllVertices() {
		return this.vertices;
	}

	@Override
	public boolean addVertex(OwnVertex vertex){
		
		if(!vertices.contains(vertex))
		{
			
			return graph.addVertex(vertex) && vertices.add(vertex);
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public OwnVertex getVertexByName(String v){
		
		for(OwnVertex vertex1 : graph.getVertices()){
			
			if(vertex1.get_name().equals(v)){
				return vertex1;
			}
		}
		return null;
	}

	private OwnEdge getEdgeByID(long id){
		
		for(OwnEdge e : edges){
		
			if(e.getID() == id) return e;
		}
		return null;
	}
	
	@Override
	public boolean removeVertex(OwnVertex vertex) {
		
		OwnVertex v = getVertexByName(vertex.get_name());
		if(v == null) return false;
		
		boolean res = graph.removeVertex(v) && vertices.remove(v);
		reloadEdges();
		
		return res;
	}

	@Override
	public boolean addEdge(OwnEdge edge, OwnVertex vertex1, OwnVertex vertex2) {

		if(!edges.contains(edge))
		{
			return graph.addEdge(edge, vertex1, vertex2) && edges.add(edge);
		}
		
		return false;
	}

	@Override
	public boolean removeEdge(long edgeID) {

		OwnEdge e = getEdgeByID(edgeID);
		if(e == null) return false;
		
		boolean res = graph.removeEdge(e) && edges.remove(e);
		reloadEdges();
		
		return res;
	}

	@Override
	public List<OwnEdge> getAllEdges() {
		return edges;
	}
	
	
	private void reloadEdges(){

		if(edges.size() == graph.getEdgeCount()) return;
		
		List<OwnEdge> res = new ArrayList<OwnEdge>();
		for(OwnEdge edge : graph.getEdges()){
			
			res.add(edge);
		}
		edges = res;
	}
	
	private void reloadVertices(){
		
		if(vertices.size() == graph.getVertexCount()) return;
		
		List<OwnVertex> res = new ArrayList<OwnVertex>();
		for(OwnVertex vertex : graph.getVertices()){
			
			res.add(vertex);
		}
		vertices = res;
	}

}
