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
public interface IFileManager {

	/**
	 * Load File from directory
	 * @param path
	 * @return
	 * 		true if:
	 * 			"path" is File and not Directory,
	 * 			Read Permission available.
	 */
	public List<String> loadFile(File path) throws FileNotFoundException, WrongFileTypeException, AccessException;
	
	/**
	 * Save File to directory
	 * @param path
	 * @param ifExistsOverride
	 * @return
	 * 		true if:
	 * 			"path" exists and "ifExistsOverride" = true,
	 * 				OR
	 * 			"path" not exists,
	 * 			Write Permission available.
	 */
	public boolean saveFile(File path, boolean ifExistsOverride, List<String> content);
}
