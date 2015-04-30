package gka.AlgorithmManager.Extension;

import gka.GraphBuilder.Extension.OwnVertex;

import java.util.Comparator;
import java.util.Map;

public class ComparatorLength implements Comparator<OwnVertex>{

	private Map<OwnVertex,Integer> vertexLength;
	
	public ComparatorLength(Map<OwnVertex,Integer> vertexLength) {
		this.vertexLength = vertexLength; 
	}
	
	@Override
	public int compare(OwnVertex o1, OwnVertex o2) {
		
		int v1 = vertexLength.get(o1);
		int v2 = vertexLength.get(o2);
	
		return  Integer.compare(v1, v2);
	}

	
}
