package gka.GraphBuilder;

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
}
