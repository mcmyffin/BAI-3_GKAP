package gka.GraphBuilder;

import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.List;

import org.jgrapht.Graph;

public interface IGraphBuilder {

	
	public Graph<OwnVertex,OwnEdge> buildGraph(List<String> list);
}
