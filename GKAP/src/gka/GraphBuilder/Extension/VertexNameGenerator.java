package gka.GraphBuilder.Extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VertexNameGenerator {

	private static VertexNameGenerator instance = null;
	private List<List<Character>> alphabet;
	private Counter c;
	
	static Character[] abcArray1 = null;
	static Character[] abcArray2 = null;
	
	private VertexNameGenerator(){
		c = new Counter();
		initAlphabet();
	}
	
	public static VertexNameGenerator getInstance(){
		
		if(VertexNameGenerator.instance == null){
			VertexNameGenerator.instance = new VertexNameGenerator();
		}
		return VertexNameGenerator.instance;
	}
	
	public String getNext(){
		return c.getNext(alphabet);
	}
	
	private void initAlphabet(){
		
		Character[] tmp1 = {'a','b','c','d','e','f','g','h','i','j',
					   		'k','l','m','n','o','p','q',
					   		'r','s','t','u','v','w','x','y','z'};
		abcArray1 = tmp1;
		
		Character[] tmp2 = {' ','a','b','c','d','e','f','g','h','i','j',
						   		'k','l','m','n','o','p','q',
						   		'r','s','t','u','v','w','x','y','z'};
		
		abcArray2 = tmp2;
		
		List<Character> abc1 = new ArrayList<Character>(Arrays.asList(abcArray1));
		List<Character> abc2 = new ArrayList<Character>(Arrays.asList(abcArray2));
		
		alphabet = new ArrayList<List<Character>>();
		alphabet.add(abc1);
		alphabet.add(abc2);
		alphabet.add(abc2);
		alphabet.add(abc2);
		alphabet.add(abc2);
	}
}


class Counter{
	
	private int c1 = 0;
	private int c2 = 0;
	private int c3 = 0;
	private int c4 = 0;
	private int c5 = 0;
	
	
	String getNext(List<List<Character>> chars){
		
		checkCounter();
		char s1 = chars.get(0).get(c1);
		char s2 = chars.get(1).get(c2);
		char s3 = chars.get(2).get(c3);
		char s4 = chars.get(3).get(c4);
		char s5 = chars.get(4).get(c5);
		c1++;
		return (""+s1+s2+s3+s4+s5).replaceAll("\\s+", "");
	}
	
	private void checkCounter(){
		
		if(c1 >= VertexNameGenerator.abcArray1.length){		
			c1 = 0;
			c2++;
		}
		
		if(c2 >= VertexNameGenerator.abcArray2.length){
			c2 = 1;
			c3++;
		}
		
		if(c3 >= VertexNameGenerator.abcArray2.length){
			c3 = 1;
			c4++;
		}
			
		if(c4 >= VertexNameGenerator.abcArray2.length){
			c4 = 1;
			c5++;
		}
		
		// reset
		if(c5 >= VertexNameGenerator.abcArray2.length){
			c1 = 0;
			c2 = 0;
			c3 = 0;
			c4 = 0;
			c5 = 0;
		}
			
	}
	
}
