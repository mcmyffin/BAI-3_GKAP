package gka.GraphBuilder;

import java.util.List;
import java.util.Scanner;

public class GraphScanner {

	
	private GraphBuilder parent;
	
	public GraphScanner(GraphBuilder parent){
		
		this.parent = parent;
	}
	
	
	/*
	 * Which Type of Graph
	 * 
	 * Private helper method from buildGraph(List<String> list)
	 * It decides witch type of Graph the "list" contains
	 */
	public String whichTypOfGraph(List<String> list){
		
		// precondition
		if(list == null) return null;
		if(list.isEmpty()) return null;

		String firstLine = list.get(0);
		if(firstLine.contains("#"))
		{
			// remove header
			list.remove(0);
			
			// save header
			parent.header = firstLine;
			
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

}
