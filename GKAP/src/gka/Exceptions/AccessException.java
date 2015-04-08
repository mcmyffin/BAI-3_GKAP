package gka.Exceptions;

public class AccessException extends Exception{
	
	public AccessException() throws AccessException{
		super();
	}


	@Override
	public String getMessage() {
		String message;
		message = "No Permission to read / write File";
		return message;
	}
	
	
}
