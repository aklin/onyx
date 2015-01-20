package onyx.util;


public final class Pair<LEFT, RIGHT> {
	private final LEFT left;
	private final RIGHT right;
	
	public Pair(final LEFT l, final RIGHT r){
		left=l;
		right=r;
	}
	
	public LEFT getLeft(){
		return this.left;
	}
	
	public RIGHT getRight(){
		return this.right;
	}
	
}
