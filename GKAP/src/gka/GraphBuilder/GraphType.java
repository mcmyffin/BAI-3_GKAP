package gka.GraphBuilder;

import java.util.ArrayList;
import java.util.List;

public enum GraphType {

	DIRECTED("#directed"),
	WEIGHTED("#weighted"),
	ATTRIBUTED("#attributed");
	
	private String type;
	
	GraphType(String valType){
		
		this.type = valType;
	}
	
	
	public String getValue(){
		
		return this.type;
	}
	
	public int getLength(){
		
		return this.type.length();
	}
	
	public static String createHeader(GraphType...types){
		
		List<GraphType> graphTypes = new ArrayList<GraphType>();
		String header = "";
		for(int i = 0; i < types.length; i++){
			graphTypes.add(types[i]);
		}
		
		// directed
		if(graphTypes.contains(GraphType.DIRECTED)){
			header = GraphType.DIRECTED.getValue()+" ";
			
			// weighted
			if(graphTypes.contains(GraphType.WEIGHTED)){
				header += GraphType.WEIGHTED.getValue()+" ";
			}
			
			// attributed
			if(graphTypes.contains(GraphType.ATTRIBUTED)){
				header += GraphType.ATTRIBUTED.getValue();
			}
		}
		// undirected
		else
		{
			// weighted
			if(graphTypes.contains(GraphType.WEIGHTED)){
				header += GraphType.WEIGHTED.getValue()+" ";
			}
			
			// attributed
			if(graphTypes.contains(GraphType.ATTRIBUTED)){
				header += GraphType.ATTRIBUTED.getValue();
			}
		}
		
		return header;
	}
}
