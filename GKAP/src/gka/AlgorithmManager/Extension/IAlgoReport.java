package gka.AlgorithmManager.Extension;

import java.util.List;

import gka.GraphBuilder.Extension.OwnVertex;

public interface IAlgoReport {

	
	public void addPathNode(OwnVertex v);
	
	public void addVisitedNode(OwnVertex v);
	
	public void setPathLength(int pathLength);
	
	public void countGraphAccess();
	
	public int getGraphAccessCounter();
	
	public void startTimer();
	
	public void stopTimer();
	
	public List<String> getPathNodes();
	
	public List<String> getVisitedNodes();
	
	public int getPathLength();
	
	public double getTotalTimeInSec();
	
	public String getAlgName();
}
