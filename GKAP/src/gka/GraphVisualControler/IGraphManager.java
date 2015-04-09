package gka.GraphVisualControler;

import java.util.List;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import gka.Exceptions.AccessException;
import gka.Exceptions.FileNotFoundException;
import gka.Exceptions.GraphBuildException;
import gka.Exceptions.WrongFileTypeException;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

public interface IGraphManager {

	
	public VisualizationViewer<OwnVertex, OwnEdge>  loadGraph(String path) throws FileNotFoundException, WrongFileTypeException, AccessException, GraphBuildException;
	public String getGraphType();
	
	public boolean addVertexAt(OwnVertex vertex, int x ,int y);
	public boolean addVertex(OwnVertex vertex);
	public boolean addEdge(OwnEdge edge, OwnVertex v1, OwnVertex v2);
	
	public boolean removeVertex(OwnVertex vertex);
	public boolean removeEdge(long edgeID);
	
	public List<OwnVertex> getAllVertices();
	public List<OwnEdge> getAllEdges();
	public OwnVertex getVertexByName(String name);
	
	public void setTrasformMode();
	public void setPicMode();
	
}
