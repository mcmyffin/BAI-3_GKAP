package gka.FileManager;

import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.WrongFileTypeException;

import java.io.File;
import java.util.List;

/**
 * 
 * @author dima
 *
 */
public class FileManager implements IFileManager{

	FileLoader loader;
	
	public FileManager() {
		loader = new FileLoader();
	}
	
	
	@Override
	public List<String> loadFile(File path) throws FileNotFoundException, WrongFileTypeException, AccessException {
		return loader.loadFile(path);
	}

	@Override
	public boolean saveFile(File path, boolean ifExistsOverride, List<String> content) {
		return false;
	}

}
