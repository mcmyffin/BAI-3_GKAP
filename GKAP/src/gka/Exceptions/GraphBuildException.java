package gka.Exceptions;

public class GraphBuildException extends Exception {
	
	public GraphBuildException() throws GraphBuildException{
		super();
	}

	@Override
	public String getMessage() {
		String message;
		message = "Graph can not be build";
		return message;
	}
	
	

}
