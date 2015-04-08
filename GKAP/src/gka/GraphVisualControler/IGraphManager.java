package gka.GraphVisualControler;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import org.jgraph.JGraph;

public interface IGraphManager {

	
	public VisualizationViewer<OwnVertex, OwnEdge>  loadGraph(String path) throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException;
	
	public void addVertexAt(OwnVertex vertex, int x ,int y);
	public void addEdge(OwnVertex v1, OwnVertex v2);
	
	public boolean removeVertex(OwnVertex vertex);
	public boolean removeEdge(OwnEdge edge);
	
	public void setTrasformMode();
	public void setPicMode();
	
}
