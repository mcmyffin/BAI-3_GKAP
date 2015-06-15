package AlgorithmManagerTest;

import static org.junit.Assert.*;
import gka.AlgorithmManager.Extension.Fleury_Hierholzer_Report;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphVisualControler.GraphManager;
import gka.GraphVisualControler.IGraphManager;

import org.junit.Test;

public class FleuryTest {
	
	private static IGraphManager gmanager = new GraphManager();;
	private final static String simpleEulerkreisGraphPfad = "/home/dima/Studium/4_Semester_SoSe205/Git/BAI-3_GKAP/testFiles/testEulerkreis.graph";


	@Test
	public void testFleurySimple() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		// load graph from File
		final int eulerCirclePathLength = 15;
		gmanager.loadGraph(simpleEulerkreisGraphPfad);
		String[] startNodes = {"a","b","c","d","e","f","g","h","i"};
		
		for(String aStartNode : startNodes){
			
			Fleury_Hierholzer_Report reporter = (Fleury_Hierholzer_Report) gmanager.startFleury(aStartNode);
			assertEquals(reporter.getPathLength(), eulerCirclePathLength);

		}
	}

}
