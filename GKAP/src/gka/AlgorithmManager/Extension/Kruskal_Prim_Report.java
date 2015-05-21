package gka.AlgorithmManager.Extension;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kruskal_Prim_Report implements IAlgoReport{


	private String algName;
	private int pathLength = 0;
	private int numberOfGraphAccess = 0;
	
	private long   startInMillSec = 0L;
	private double totalTimeInSec = 0.0;
	
	private Set<OwnVertex>  visitedVertices;
	private List<OwnVertex>  path;
	
	private Graph<OwnVertex,OwnEdge> graph;
	
	public Kruskal_Prim_Report(String name) {
		
		this.algName = name;
		this.visitedVertices = new HashSet();
		this.path = new ArrayList<OwnVertex>();
	}
	
	public void addMinimalSpanningTree(Graph<OwnVertex,OwnEdge> g){
		graph = g;
	}
	
	public Graph<OwnVertex,OwnEdge> getMinimalSpanningTree(){
		return graph;
	}
	
	@Override
	public void addPathNode(OwnVertex v) {
		path.add(v);
	}

	@Override
	public void addVisitedNode(OwnVertex v) {
		visitedVertices.add(v);
	}

	@Override
	public void startTimer() {
		this.startInMillSec = System.currentTimeMillis();
	}

	@Override
	public void stopTimer() {
		this.totalTimeInSec = (System.currentTimeMillis() - startInMillSec)/1000.0 ;
	}

	@Override
	public List<String> getPathNodes() {
		
		List<String> result = new ArrayList<String>();
		
		for(int i = path.size()-1; i >= 0 ; i--){
			result.add(path.get(i).toString());
		}
		return result;
	}

	@Override
	public List<String> getVisitedNodes() {
		
		List<String> result = new ArrayList<String>();
		
		for(OwnVertex v : visitedVertices){
			result.add(v.toString());
		}
		
		return result;
	}

	@Override
	public int getPathLength() {
		if(this.pathLength == 0){
			return (path.size() >= 2 ? (path.size()-1) : 0);
		}
		return this.pathLength;
	}

	@Override
	public double getTotalTimeInSec() {
		return totalTimeInSec;
	}

	@Override
	public String getAlgName() {
		return algName;
	}

	@Override
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

	@Override
	public String toString() {

		String line1  = "Algorithm: "+algName;
		String line2 = "Time: "+totalTimeInSec+" Sec";
		String line3  = "Number of Graph-Access: "+this.numberOfGraphAccess;
		String line4  = "Number of Vertices: "+getMinimalSpanningTree().getVertexCount();
		String line5  = "Number of Edges: "+getMinimalSpanningTree().getEdgeCount();
		
		String line6  = "Total Path: "+getPathLength();
		
		String result = line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n\n"+line6;
		return result;
	}

	@Override
	public void countGraphAccess() {
		this.numberOfGraphAccess++;
	}

	@Override
	public int getGraphAccessCounter() {
		return this.numberOfGraphAccess;
	}
	
}
