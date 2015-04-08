package gka.Exceptions;

public class WrongFileTypeException extends Exception {

	public WrongFileTypeException() throws WrongFileTypeException{
		
	}

	@Override
	public String getMessage() {
		String message;
		message = "Wrong Filetype has been detected, please check your Filetype";
		return message;
	}
	
	
}
