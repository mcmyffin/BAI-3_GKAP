package gka.GraphBuilder.Extension;


public class OwnVertex implements Comparable<OwnVertex>{
	
	private String 	_name;
	private int	 	_attribute = -1;
	private int		_level = 0;
	
	
	public OwnVertex(String name){
		set_name(name);
	}
	
	public OwnVertex(String name, int attribute){
		
		this(name);
		this._attribute = attribute;
	}
	
	public String get_name() {
		return _name;
	}

	public int get_attribute() {
		return _attribute;
	}

	public void set_name(String name) {
		if(name == null) name = "V"+System.currentTimeMillis();
		
		this._name = name;
	}

	public void set_attribute(int attribute) {
		this._attribute = attribute;
	}

	public void set_level(int lvl){
		this._level = lvl;
	}
	
	public int get_level(){
		return this._level;
	}
	@Override
	public String toString() {
		return (get_attribute() > -1? _name+" ["+get_attribute()+"]" : get_name());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == this){
			return true;			
		}
		else if(!(obj instanceof OwnVertex)) {
			return false;
		} else {
			OwnVertex other = (OwnVertex) obj;
			return get_name().equals(other.get_name());
		}
	}

	@Override
	public int compareTo(OwnVertex o) {
		return Integer.compare(get_level(), o.get_level());
	}

}
