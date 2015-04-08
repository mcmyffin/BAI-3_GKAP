package gka.Exceptions;

public class FileNotFoundException extends Exception{

	public FileNotFoundException() throws FileNotFoundException{
		
	}

	@Override
	public String getMessage() {
		String message;
		message = "Can't find File in Path";
		return message;
	}
	
}
