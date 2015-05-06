package gka.GraphVisualControler;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import gka.AlgorithmManager.BFS_Report;
import gka.AlgorithmManager.AlgorithmManager;
import gka.AlgorithmManager.IAlgoReport;
import gka.AlgorithmManager.IAlgorithManager;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.FileManager.FileManager;
import gka.FileManager.IFileManager;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.GraphType;
import gka.GraphBuilder.IGraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;
import gka.GraphGenerator.GraphGenerator;
import gka.GraphGenerator.IGraphGenerator;
import gka.GraphicalView.Generator.GraphCreator;

public class GraphManager implements IGraphManager{

	
	private IFileManager fileManager;
	private IGraphBuilder graphBuilder;
	private Graph<OwnVertex,OwnEdge> adtGraph;
	private IAlgorithManager algoManager;
	
	private Layout<OwnVertex,OwnEdge> layout;
	private VisualizationViewer<OwnVertex,OwnEdge> vv;
	
	
	public GraphManager() {
		this.fileManager = new FileManager();
	}
	
	/********Implemented********/
	@Override
	public VisualizationViewer<OwnVertex, OwnEdge> loadGraph(String path) throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		this.graphBuilder = new GraphBuilder();
		
		List<String> listContent = fileManager.loadFile(new File(path));
		adtGraph= graphBuilder.buildGraph(listContent);
		
		return setUpGraphiew(adtGraph);
	}
	
	@Override
	public boolean saveGraph(File path) {
		
		List<String> g = graphBuilder.getSaveableGraph();
		return fileManager.saveFile(path, g);
	}
	
	@Override
	public boolean addVertex(String vertex, int attribute) {
		return graphBuilder.addVertex(vertex,attribute);
	}

	@Override
	public boolean addVertex(String vertex) {
		return graphBuilder.addVertex(vertex);	
	}	
	
	@Override
	public boolean removeVertex(String vertex) {
		return graphBuilder.removeVertex(vertex);
	}
	
	@Override
	public boolean addEdge(int weight, String v1, String v2) {
		return graphBuilder.addEdge(weight, v1, v2);
	}

	@Override
	public boolean removeEdge(long id) {
		return graphBuilder.removeEdge(id);
	}
	
	@Override
	public String getGraphType() {
		return graphBuilder.getGraphType();
	}

	@Override
	public List<String> getAllVerticesAsString() {
		return graphBuilder.getAllVerticesAsString();
	}

	@Override
	public List<String> getAllEdgesAsString() {
		return graphBuilder.getAllEdgesAsString();
	}
	
	@Override
	public void setTrasformMode() {
	
		GraphMouse gm = vv.getGraphMouse();
		((ModalGraphMouse) gm).setMode(ModalGraphMouse.Mode.TRANSFORMING);
	}

	@Override
	public void setPicMode() {
		
		GraphMouse gm = vv.getGraphMouse();
		((ModalGraphMouse) gm).setMode(ModalGraphMouse.Mode.PICKING);
	}
	
	// Algorithm BFS implementation
	@Override
	public IAlgoReport startBreadthFirstSearch(String start, String target) {
		OwnVertex v1 = graphBuilder.getVertexByName(start);
		OwnVertex v2 = graphBuilder.getVertexByName(target);
		
		algoManager = new AlgorithmManager(adtGraph);
		
		return algoManager.startBFS(v1, v2);
	}
	
	// Algorithm Dijkstra implementation
	@Override
	public IAlgoReport startDijkstra(String start, String target){
		OwnVertex v1 = graphBuilder.getVertexByName(start);
		OwnVertex v2 = graphBuilder.getVertexByName(target);
		
		algoManager = new AlgorithmManager(adtGraph);
		return algoManager.startDijkstra(v1, v2);
	}
	
	// Algorithm A* implementation
	@Override
	public IAlgoReport startASternchen(String start, String target){
		OwnVertex v1 = graphBuilder.getVertexByName(start);
		OwnVertex v2 = graphBuilder.getVertexByName(target);
		
		algoManager = new AlgorithmManager(adtGraph);
		return algoManager.startAStar(v1, v2);
	}
	
	
	/*********Helper Method from loadGraph(String path)**********/
	/**
	 * Set up the Graphical Component "VisualizationViewer"
	 * @param g
	 * @return VisualizationViewer
	 */
	private VisualizationViewer<OwnVertex, OwnEdge> setUpGraphiew(Graph<OwnVertex, OwnEdge> g){
		
		 // Layout<V, E>, VisualizationComponent<V,E>
		 layout = new CircleLayout<OwnVertex,OwnEdge>(g);
		 
		 layout.setSize(new Dimension(600,600));
		 
		 vv = new VisualizationViewer<OwnVertex,OwnEdge>(layout);
		 
		 // Show vertex and edge labels
		 vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		 vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		 
		 // Create a graph mouse and add it to the visualization component
		 ModalGraphMouse gm = new DefaultModalGraphMouse<OwnVertex, OwnEdge>();
		 gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		 vv.setGraphMouse(gm);
		 
		 return vv;
	}

	@Override
	public VisualizationViewer<OwnVertex, OwnEdge> generateNewGraph(int vertices, int edges, int spread, 
			int edgeWeightMin, int edgeWeightMax, GraphType...type) throws GraphBuildException {

		this.graphBuilder = new GraphBuilder();
		this.adtGraph = graphBuilder.createNewGraph(type);
		
		IGraphGenerator generator = new GraphGenerator();
		String header = GraphType.createHeader(type);
		
		if(header.contains(GraphType.ATTRIBUTED.getValue()) && header.contains(GraphType.WEIGHTED.getValue())){
			
			generator.generateUndirectedWeightedAttributedGraph(adtGraph, vertices, edges, edgeWeightMin,edgeWeightMax, spread);
			
		}else if(header.contains(GraphType.WEIGHTED.getValue())){
			generator.generateUndirectedWeightedGraph(adtGraph, vertices, edges, edgeWeightMin, edgeWeightMax, spread);
			
		}else{
			throw new GraphBuildException();
		}
		
		return setUpGraphiew(adtGraph);
	}

}
