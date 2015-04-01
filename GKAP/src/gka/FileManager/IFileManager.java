package gka.FileManager;

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
	public List<String> loadFile(File path);
	
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
