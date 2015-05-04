package gka.GraphBuilder;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.List;

public class GraphAssembler {

	
	private GraphAssembler(){};
	
	/**
	 * Fill Graph
	 * Fill a Directed or Undirected Graph without attribute and weight
	 * 
	 * @param graph
	 * @param list
	 */
	static void fillGraph(Graph<OwnVertex,OwnEdge> graph, List<String> list){
		
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
	
	
	/**
	 * Fill attributed Graph 
	 * Fill a Directed or Undirected Graph with attribute and without weight
	 * 
	 * @param graph
	 * @param list
	 */
	static void fillAttributedGraph(Graph<OwnVertex,OwnEdge> graph, List<String> list){

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
	
	/**
	 * Fill weighted Graph
	 * Fill a Directed or Undirected Graph with weight and without attribute
	 * @param graph
	 * @param list
	 */
	static void fillWeightedGraph(Graph<OwnVertex,OwnEdge> graph, List<String> list){
		
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
	
	
	/**
	 * Fill weighted attributed Graph
	 * Fill a Directed or Undirected Graph with weight and attribute
	 * @param graph
	 * @param list
	 */
	static void fillWeightedAttributedGraph(Graph<OwnVertex,OwnEdge> graph, List<String> list){
		
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
				OwnVertex vertex1;
				if(lineSpaceFree.contains(":")){
					
					String[] splited = lineSpaceFree.split(":");
					vertex1 = new OwnVertex(splited[0],Integer.parseInt(splited[1]));
					
				}else{
					vertex1 = new OwnVertex(lineSpaceFree);
				}
				graph.addVertex(vertex1);
			}
		}
	}
	
}
