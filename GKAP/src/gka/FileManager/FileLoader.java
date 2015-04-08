package gka.FileManager;

import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.WrongFileTypeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

	public final String fileType = ".graph";
	
	
	public List<String> loadFile(File loadedFile) throws FileNotFoundException, WrongFileTypeException, AccessException{
		
		
		// precondition
		if(!loadedFile.exists()) throw new FileNotFoundException();
		if(!loadedFile.isFile()) throw new FileNotFoundException();
		if(!loadedFile.getName().endsWith(fileType)) throw new WrongFileTypeException();
		if(!loadedFile.canRead()) throw new AccessException();
		
		List<String> 	resultList = new ArrayList<String>();
		FileReader  	fr;
		BufferedReader 	br;
		
		try{
			
			fr = new FileReader(loadedFile);
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null){
				
				// delete empty lines
				if(!line.isEmpty()) resultList.add(line);

			}
			
			br.close();
			fr.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
			// todo exception
		
		}
		return resultList;
	}
	
	
}
