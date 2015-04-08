package gka.GraphBuilder.Extension;

public class EdgeCounter {

	
	private static EdgeCounter instance = null;
	private long id = 0L;
	
	private EdgeCounter(){
		
	}
	
	public static EdgeCounter getInstance(){
		
		if(EdgeCounter.instance == null)
		{
			EdgeCounter.instance = new EdgeCounter();
		}
		
		return EdgeCounter.instance;
	}
	
	public long next(){
		return this.id++;
	}
	
}
