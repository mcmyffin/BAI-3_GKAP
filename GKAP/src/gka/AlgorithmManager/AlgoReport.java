package gka.AlgorithmManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gka.GraphBuilder.Extension.OwnVertex;

public class AlgoReport {
	
	private String algName;
	
	private long startInMillSec;
	private double totalTimeInSec = 0.0;
	
	private Set<OwnVertex>  visitedVertices;
	private List<OwnVertex>  path;
	
	
	public AlgoReport(String algName){
		
		this.algName = algName;
		visitedVertices = new HashSet<OwnVertex>();
		path = new ArrayList<OwnVertex>();
	}
	
	public void addPathNode(OwnVertex v){
		this.path.add(v);
	}
	
	public void addVisitedNode(OwnVertex v){
		this.visitedVertices.add(v);
	}
	
	public void startTimer(){
		this.startInMillSec = System.currentTimeMillis();
	}
	
	public double stopTimer(){
		
		totalTimeInSec = ((System.currentTimeMillis() - startInMillSec) / 1000.0);
		return totalTimeInSec;
	}
	
	public double getTotalTime(){
		return totalTimeInSec;
	}
	
	public String getAlgName(){
		return this.algName;
	}
	
	public List<OwnVertex> getPath(){
		List<OwnVertex> result = new ArrayList<OwnVertex>();
		
		for(int i = path.size()-1; i >= 0 ; i--){
			result.add(path.get(i));
		}
		return result;
	}
	
	@Override
	public String toString(){
		String line1  = "Algorithm: "+algName;
		String line12 = "Time: "+totalTimeInSec+" Sec";
		String line2  = "Target found: "+(!path.isEmpty());
		String line3  = "Edges count: "
				+(path.size() >= 2 ? (path.size()-1) : 0);
		
		String line4  = "Vertices count: "+path.size();
		String line5  = "Total Visited: "+visitedVertices.size();
		String line6  = "Path: "+getPath().toString();
		String line7  = "Visited : "+visitedVertices.toString();
		
		String result = line1+"\n"+line12+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5+"\n\n"+line6+"\n\n"+line7;
		return result;
	}
}
