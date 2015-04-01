package gka.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

	public final String fileType = ".graph";
	
	
	public List<String> loadFile(String path){
		
		
		File loadedFile = new File(path);
		
		// pre check
		if(!loadedFile.exists()) return null; // todo exception
		if(!loadedFile.isFile()) return null; // todo exception
		if(!loadedFile.getName().endsWith(fileType)) return null; // todo exception
		
		if(!loadedFile.canRead()) return null;
		
		List<String> 	resultList = new ArrayList<String>();
		FileReader  	fr;
		BufferedReader 	br;
		
		try{
			
			fr = new FileReader(loadedFile);
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null){
				resultList.add(line);
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
