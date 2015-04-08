package gka.GraphBuilder.Extension;

import edu.uci.ics.jung.graph.event.GraphEvent.Edge;


public class OwnEdge{

	private long _id;
	private int _weight;
	
	public OwnEdge(){
		this._id = EdgeCounter.getInstance().next();
		this._weight = 0;
	}
	
	public OwnEdge(int weight){
		this();
		this._weight = weight;
	}
	
	public int getWeight(){
		return this._weight;
	}
	
	public void setWeight(int weight){
		this._weight = weight;
	}
	
	@Override
	public String toString(){
		
		return (_weight == 0 ? "E_"+_id : "W_"+_weight);
	}

	
}
