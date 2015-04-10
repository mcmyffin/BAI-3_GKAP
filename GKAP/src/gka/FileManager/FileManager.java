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
	FileSaver  saver;
	
	public FileManager() {
		loader = new FileLoader();
		saver = new FileSaver();
	}
	
	
	@Override
	public List<String> loadFile(File path) throws FileNotFoundException, WrongFileTypeException, AccessException {
		return loader.loadFile(path);
	}

	@Override
	public boolean saveFile(File path, List<String> content) {
		
		String fileName = path.getName();
		String pathFolder = (path.getPath()).replace(fileName, "");
		
		
		boolean saveResult = saver.saveFile(content, pathFolder, fileName);
		for(int i = 1; !saveResult && i < 21; i++){
			
			saveResult = saver.saveFile(content, pathFolder, fileName+"_"+i);
		}
		
		return saveResult;
	}

}
