package gka.AlgorithmManager.Extension;

import java.util.Comparator;
import java.util.Map;

import gka.GraphBuilder.Extension.*;

public class ComparatorHeuristic implements Comparator<OwnVertex>{

	private Map<OwnVertex, Integer> vertexLength;
	
	public ComparatorHeuristic(Map<OwnVertex, Integer> vertexLenght) {
		this.vertexLength = vertexLenght;
	}
	
	@Override
	public int compare(OwnVertex o1, OwnVertex o2) {
		
		int v1 = vertexLength.get(o1) + o1.get_attribute();
		int v2 = vertexLength.get(o2) + o2.get_attribute();
		
		return  Integer.compare(v1, v2);
	}

	
}
