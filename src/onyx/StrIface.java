package onyx;

/**
 *
 */
public interface StrIface extends CharSequence, Comparable<CharSequence> {

	public int insert(final int pos, final CharSequence s);
	
	default public boolean isEmpty(){
		return this.length()==0;
	}
	
	default public boolean equals(final StrIface o){
		if(o==null)
			return false;
		
		if(this.length()!=o.length())
			return false;
		
		for(int i=0; i<this.length(); i++)
			if(this.charAt(i)!=o.charAt(i))
				return false;
		
		return true;
	}
	
}
