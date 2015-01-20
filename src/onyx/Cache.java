package onyx;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;


public class Cache {
	private final ArrayDeque<Str> cache;
	
	public Cache(){
		cache=new ArrayDeque<>();
	}
	
	public void load(final Scanner sc){
		while(sc.hasNext())
			cache.add(new Str(sc.nextLine()));
	}
	
	public String find(final Str s){
		
		
		return null;
	}
	
}
