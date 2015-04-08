package gka.Exceptions;

public class GraphFormatException extends Exception{

	public GraphFormatException() throws GraphFormatException{
		
	}

	@Override
	public String getMessage() {
		String message;
		message = "File contains wrong Graph Format or has wrong content \n Please check your File content";
		return message;
	}
	
	
}
