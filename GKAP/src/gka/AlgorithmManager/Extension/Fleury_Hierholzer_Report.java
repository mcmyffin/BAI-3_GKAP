package gka.AlgorithmManager.Extension;

import gka.GraphBuilder.Extension.OwnVertex;

import java.util.ArrayList;
import java.util.List;

public class Fleury_Hierholzer_Report implements IAlgoReport{

	private String algName;
	private int pathLength = 0;
	
	private long   startInMillSec = 0L;
	private double totalTimeInSec = 0.0;
	
	private List<OwnVertex>  path;
	public Fleury_Hierholzer_Report(String name) {
		
		this.algName = name;
		this.path = new ArrayList<OwnVertex>();
	}
	
	public void countPath(){
		pathLength++;
	}
	
	public void addEulerianPath(List<OwnVertex> eulerianPath){
		this.path = eulerianPath;
	}
	
	public List<OwnVertex> getEulerianCircuit(){
		return this.path;
	}
	
	@Override
	public void addPathNode(OwnVertex v) {
		path.add(v);
	}

	@Override
	public void addVisitedNode(OwnVertex v) {}

	@Override
	public void startTimer() {
		this.startInMillSec = System.currentTimeMillis();
	}

	@Override
	public void stopTimer() {
		this.totalTimeInSec = (System.currentTimeMillis() - startInMillSec)/1000.0 ;
	}

	@Override
	public List<String> getPathNodes() {return null;}

	@Override
	public List<String> getVisitedNodes() {return null;}

	@Override
	public int getPathLength() {
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
		String line2  = "Time: "+getTotalTimeInSec()+" Sec";
		String line3  = "Number of Edge in Eulerian circuit "+getPathLength();
		String line4  = "Eulerian circuit "+getEulerianCircuit();
		
		String result = line1+"\n"+line2+"\n"+line3+"\n\n\n"+line4+"\n";
		return result;
	}

	@Override
	public void countGraphAccess() {}

	@Override
	public int getGraphAccessCounter() {
		return 0;
	}
	
}
