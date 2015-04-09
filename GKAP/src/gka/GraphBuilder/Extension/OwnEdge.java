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
	
	public long getID(){
		return _id;
	}
	
	@Override
	public String toString(){
		
		return ("E "+_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		} else if(!(obj instanceof OwnEdge)) {
			return false;
		} else {
			OwnEdge other = (OwnEdge) obj;
			return _id == other.getID(); 
		}
	}

	
}
