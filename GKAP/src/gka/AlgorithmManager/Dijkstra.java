package gka.AlgorithmManager;

import gka.GraphBuilder.Extension.OwnVertex;

import java.util.PriorityQueue;

public class Dijkstra {

	
	public static void main(String[] args) {
		
		PriorityQueue<OwnVertex> aaa = new PriorityQueue<OwnVertex>();
		
		OwnVertex v1 = new OwnVertex("v1");
		v1.set_level(1);
		OwnVertex v2 = new OwnVertex("v2");
		v2.set_level(2);
		OwnVertex v3 = new OwnVertex("v3");
		v3.set_level(3);
		OwnVertex v4 = new OwnVertex("v4");
		v4.set_level(4);
		OwnVertex v5 = new OwnVertex("v5");
		v5.set_level(5);
		
		aaa.add(v1);
		aaa.add(v5);
		aaa.add(v2);
		aaa.add(v3);
		aaa.add(v4);
		
		
		System.out.println(aaa);
	}
}
