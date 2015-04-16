package gka.GraphBuilder.Extension;

public class OwnVertex implements Comparable<OwnVertex>{
	
	private String 	_name;
	private int	 	_attribute = 0;
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
		
		if(name == null) return;
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
		return _name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = _name.hashCode();
		return result;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) return false;
		if(obj == this) return true;
		else if(!(obj instanceof OwnVertex)) {
			return false;
		} else {
			OwnVertex other = (OwnVertex) obj;
			return get_name().equals(other.get_name());
		}
	}

	@Override
	public int compareTo(OwnVertex o) {
		System.out.println(Integer.compare(get_level(), o.get_level()));
		return Integer.compare(get_level(), o.get_level());
//		return 0;
	}
}
