package gka.AlgorithmManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gka.GraphBuilder.Extension.OwnVertex;

public class BFS_Report implements IAlgoReport{
	
	private String algName;
	private int pathLength = 0;
	
	private long startInMillSec = 0L;
	private double totalTimeInSec = 0.0;
	
	private Set<OwnVertex>  visitedVertices;
	private List<OwnVertex>  path;
	
	
	public BFS_Report(String algName){
		
		this.algName = algName;
		visitedVertices = new HashSet<OwnVertex>();
		path = new ArrayList<OwnVertex>();
	}
	
	@Override
	public void addPathNode(OwnVertex v){
		this.path.add(v);
	}
	
	@Override
	public void addVisitedNode(OwnVertex v){
		this.visitedVertices.add(v);
	}
	
	@Override
	public void startTimer(){
		this.startInMillSec = System.currentTimeMillis();
	}
	
	@Override
	public void stopTimer(){
		this.totalTimeInSec = ((System.currentTimeMillis() - startInMillSec) / 1000.0);
	}
	
	@Override
	public double getTotalTimeInSec(){
		return totalTimeInSec;
	}
	
	@Override
	public String getAlgName(){
		return this.algName;
	}
	
	@Override
	public List<String> getPathNodes(){
		List<String> result = new ArrayList<String>();
		
		for(int i = path.size()-1; i >= 0 ; i--){
			result.add(path.get(i).toString());
		}
		return result;
	}
	
	@Override
	public List<String> getVisitedNodes(){
		List<String> result = new ArrayList<String>();
		
		for(OwnVertex v : visitedVertices){
			result.add(v.toString());
		}
		return result;
	}
	
	@Override
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	
	@Override
	public int getPathLength() {
		if(this.pathLength == 0){
			return (path.size() >= 2 ? (path.size()-1) : 0);
		}
		return this.pathLength;
	}

	@Override
	public String toString(){
		String line1  = "Algorithm: "+algName;
		String line12 = "Time: "+totalTimeInSec+" Sec";
		String line2  = "Target found: "+(!path.isEmpty());
		String line3  = "Path length: "+getPathLength();
		
		String line4  = "Path Vertices: "+path.size();
		String line5  = "Visited Vertices: "+visitedVertices.size();
		String line6  = "Path: "+getPathNodes().toString();
		String line7  = "Visited : "+visitedVertices.toString();
		
		String result = line1+"\n"+line12+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n\n"+line6+"\n\n"+line7;
		return result;
	}

	@Override
	public void countGraphAccess() {
		// do nothing
	}

	@Override
	public int getGraphAccessCounter() {
		// do nothing
		return 0;
	}

}
