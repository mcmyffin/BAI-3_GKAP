package gka.GraphBuilder.Extension;

public class OwnVertex{
	
	private String 	_name;
	private int	 	_attribute = 0;
	
	private int		_xPos;
	private int		_yPos;
	
	
	public OwnVertex(String name){
		super();
		this._name = name;
	}
	
	public OwnVertex(String name, int attribute){
		
		this(name);
		this._attribute = attribute;
	}
	
	public OwnVertex(String name, int attribute, int x, int y){
		
		this(name,attribute);
		this._xPos = x;
		this._yPos = y;
	}

	
	public String get_name() {
		return _name;
	}

	public int get_attribute() {
		return _attribute;
	}

	public int get_xPos() {
		return _xPos;
	}

	public int get_yPos() {
		return _yPos;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_attribute(int _attribute) {
		this._attribute = _attribute;
	}

	public void set_xPos(int _xPos) {
		this._xPos = _xPos;
	}

	public void set_yPos(int _yPos) {
		this._yPos = _yPos;
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
