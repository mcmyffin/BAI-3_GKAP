package gka.AlgorithmManager.Extension;

import edu.uci.ics.jung.graph.Graph;
import gka.GraphBuilder.Extension.OwnEdge;
import gka.GraphBuilder.Extension.OwnVertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public void addVertexToEulerPfad(OwnVertex v){
		this.path.add(v);
	}
	
	public void addEulerPfad(List<OwnVertex> eulerpfad){
		this.path = eulerpfad;
	}
	
	public List<OwnVertex> getEulerKreis(){
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
		String line3  = "Number of Edge in EulerTour "+getPathLength();
		String line4  = "Eulerkreis "+getEulerKreis();
		
		String result = line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n";
		return result;
	}

	@Override
	public void countGraphAccess() {}

	@Override
	public int getGraphAccessCounter() {
		return 0;
	}
	
}
