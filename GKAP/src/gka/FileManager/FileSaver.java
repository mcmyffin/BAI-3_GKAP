package gka.FileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver {

	public final String fileType = ".graph";
	
	
	
	public boolean saveFile(List<String> aList, String path, String name){
		
		File fileToSave = new File(path+ File.separatorChar +name+fileType);
		File dir		= new File(path);
		
		// precheck
		if(fileToSave.exists()) return false; // todo exception
		if(!dir.canWrite()) return false;
		
		FileWriter fw;
		BufferedWriter bw;
		
		try{
			
			fw = new FileWriter(fileToSave);
			bw = new BufferedWriter(fw);
			
			for(String line : aList){
				
				if(line == null) continue;
				bw.write(line);
				bw.newLine();
			}
			
			bw.close();
			fw.close();

			return true;
		}catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
		
		
	}
}
