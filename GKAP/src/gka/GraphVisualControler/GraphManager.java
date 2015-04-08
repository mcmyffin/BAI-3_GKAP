package gka.GraphVisualControler;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.vecmath.Point2d;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileContentException;
import gka.Exceptions.WrongFileTypeException;
import gka.FileManager.FileManager;
import gka.FileManager.IFileManager;
import gka.GraphBuilder.GraphBuilder;
import gka.GraphBuilder.IGraphBuilder;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public class GraphManager implements IGraphManager{

	
	private IFileManager fileManager;
	private IGraphBuilder graphBuilder;
	private Graph<OwnVertex,OwnEdge> adtGraph;
	private Layout<OwnVertex,OwnEdge> layout;
	private VisualizationViewer<OwnVertex,OwnEdge> vv;
	
	
	public GraphManager(){
		
		this.fileManager = new FileManager();
		this.graphBuilder = new GraphBuilder();
	}
	
	@Override
	public VisualizationViewer<OwnVertex, OwnEdge> loadGraph(String path) throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException{
		
		List<String> listContent = fileManager.loadFile(new File(path));
		adtGraph= graphBuilder.buildGraph(listContent);
		
		return setUpGraphiew(adtGraph);
	}
	
	private VisualizationViewer<OwnVertex, OwnEdge> setUpGraphiew(Graph g){
		
		// Layout<V, E>, VisualizationComponent<V,E>
		 layout = new CircleLayout(g);
		 
		 layout.setSize(new Dimension(600,600));
		 
		 vv = new VisualizationViewer<OwnVertex,OwnEdge>(layout);
//		 vv.setPreferredSize(new Dimension(350,350));
		 
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
	public void addVertexAt(OwnVertex vertex, int x, int y) {

		
		adtGraph.addVertex(vertex);
		
		layout.setLocation(vertex, new Point2D.Double(x,y));
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

	@Override
	public void addEdge(OwnVertex v1, OwnVertex v2) {

		// todo
	}

	@Override
	public boolean removeVertex(OwnVertex vertex) {
		
		// todo
		return false;
	}

	@Override
	public boolean removeEdge(OwnEdge edge) {
		
		// todo
		return false;
	}
	
	
}
