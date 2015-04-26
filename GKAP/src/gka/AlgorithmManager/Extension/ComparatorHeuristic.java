package gka.AlgorithmManager.Extension;

import java.util.Comparator;
import gka.GraphBuilder.Extension.*;

public class ComparatorHeuristic implements Comparator<OwnVertex>{

	@Override
	public int compare(OwnVertex o1, OwnVertex o2) {
		
		int v1 = (o1.get_level()+o1.get_attribute());
		int v2 = (o2.get_level()+o2.get_attribute());
		
		return  Integer.compare(v1, v2);
	}

	
}
