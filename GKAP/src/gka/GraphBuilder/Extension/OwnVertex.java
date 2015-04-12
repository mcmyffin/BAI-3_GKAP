package gka.GraphBuilder.Extension;

public class OwnVertex{
	
	private String 	_name;
	private int	 	_attribute = 0;
	
	private int		_level = 0;
	
	
	public OwnVertex(String name){
		super();
		this._name = name;
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

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_attribute(int _attribute) {
		this._attribute = _attribute;
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
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		} else if(!(obj instanceof OwnVertex)) {
			return false;
		} else {
			OwnVertex other = (OwnVertex) obj;
			return get_name().equals(other.get_name());
		}
	}
}
