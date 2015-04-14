package GraphBuilderTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import gka.StartUp;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.FileManager.FileManager;
import gka.FileManager.IFileManager;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.IGraphBuilder;

import org.junit.Test;

public class GraphBuilderTest {

	IGraphBuilder gb;
	IFileManager  fm;
	String testFilePath = "/home/dima/Studium/4_Semester_SoSe205/Git/BAI-3_GKAP/testFiles/";
	
	
	// DIRECTED TESTS ***************************
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * DIRECTED Graph from File 'bsp1.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphDirected() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp1.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.DIRECTED);
	}
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * DIRECTED ATTRIBUTED Graph from File 'bsp8.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphDirectedAttributed() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp8.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.DIRECTED_ATTRIBUTED);
	}
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * DIRECTED WEIGHTED Graph from File 'bsp5.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphDirectedWeighted() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp5.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.DIRECTED_WEIGHTED);
	}
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * DIRECTED WEIGHTED ATTRIBUTED Graph from File 'bsp9.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphDirectedWeightedAttributed() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp9.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.DIRECTED_WEIGHTED_ATTRIBUTED);
	}
	
	// ********************************************
	// UNDIRECTED TESTS ***************************
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * UNDIRECTED Graph from File 'bsp2.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphUndirected() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException {
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File undirectedGraphFile = new File(testFilePath+File.separatorChar+"bsp2.graph");
		List<String> undirectedGraphContent = fm.loadFile(undirectedGraphFile);
		
		gb.buildGraph(undirectedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.UNDIRECTED);
	}
	
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * UNDIRECTED ATTRIBUTED Graph from File 'bsp7.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphUndirectedAttributed() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp7.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.UNDIRECTED_ATTRIBUTED);
	}
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * UNDIRECTED WEIGHTED Graph from File 'bsp4.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphUndirectedWeighted() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp4.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.UNDIRECTED_WEIGHTED);
	}
	
	/**
	 * Test loaded Graph-Type and Graph-Content 
	 * UNDIRECTED WEIGHTED ATTRIBUTED Graph from File 'bsp3.graph'
	 * 
	 * @throws AccessException 
	 * @throws WrongFileTypeException 
	 * @throws FileNotFoundException 
	 * @throws GraphBuildException 
	 */
	@Test
	public void testBuildGraphUndirectedWeightedAttributed() throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		gb = new GraphBuilder();
		fm = new FileManager();
		
		File directedGraphFile = new File(testFilePath+File.separatorChar+"bsp3.graph");
		List<String> directedGraphContent = fm.loadFile(directedGraphFile);
		
		gb.buildGraph(directedGraphContent);
		
		assertEquals(gb.getGraphType(), GraphBuilder.UNDIRECTED_WEIGHTED_ATTRIBUTED);
	}
	
	// **************************************
	

}
