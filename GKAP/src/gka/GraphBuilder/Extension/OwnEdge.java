package gka.GraphBuilder.Extension;



public class OwnEdge implements Comparable<OwnEdge>{

	private long _id;
	private int _weight;
	
	public OwnEdge(){
		this._id = EdgeCounter.getInstance().next();
		this._weight = 0;
	}
	
	public OwnEdge(long id){
		this._id =id;
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
		return ("E "+_id+" ["+getWeight()+"]");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		return result;
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

	@Override
	public int compareTo(OwnEdge o) {
		return Integer.compare(getWeight(), o.getWeight());
	}

	
	

	
}
