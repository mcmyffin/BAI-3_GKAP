package gka.GraphBuilder;

import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.List;
import org.jgrapht.Graph;


public class GraphBuilder implements IGraphBuilder{

	private Graph<OwnVertex,OwnEdge> udir;
	private Graph<OwnVertex,OwnEdge> dir;
	
	private Graph<OwnVertex,OwnEdge> udir_weight;
	private Graph<OwnVertex,OwnEdge> dir_weight;
	
	public final static String UNDIRECTED = "UNDIRECTED";
	public final static String DIRECTED = "DIRECTED";
	public final static String UNDIRECTED_WEIGHTED = "UNDIRECTED_WEIGHTED";
	public final static String DIRECTED_WEIGHTED = "DIRECTED_WEIGHTED";
	
	
	
	@Override
	public Graph<OwnVertex, OwnEdge> buildGraph(List<String> list) {
		
		switch(witchTypOfGraph(list)){
			case(GraphBuilder.UNDIRECTED):
				break;
			case(GraphBuilder.DIRECTED):
				break;
			case(GraphBuilder.DIRECTED_WEIGHTED):
				break;
			case(GraphBuilder.UNDIRECTED_WEIGHTED):
				break;
		}
		
		
		return null;
	}
	
	private String witchTypOfGraph(List<String> list){
		
		if(list == null) return null;
		if(list.isEmpty()) return null;
		
		for(String line: list){

			if(line.length() != 0){
				if(line.contains("#")){
					
					
					
				}else return GraphBuilder.UNDIRECTED;
			}
			
		}
		
		
	}
	
	
	private String witchTyp()
	public static void main(String[] args) {
		
	}

}
