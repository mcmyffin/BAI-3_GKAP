package gka.Exceptions;

public class WrongFileContentException extends Exception {

	public WrongFileContentException() throws WrongFileContentException{
		super();
	}

	@Override
	public String getMessage() {
		String message;
		message = "File content is wrong!";
		return message;
	}
	
	
}
