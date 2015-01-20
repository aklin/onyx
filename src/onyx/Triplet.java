package onyx;


public final class Triplet<LEFT, MIDDLE, RIGHT> {
	private final LEFT left;
	private final MIDDLE middle;
	private final RIGHT right;
	
	public Triplet(final LEFT l, final MIDDLE m, final RIGHT r){
		left=l;
		middle=m;
		right=r;
	}
	
	public LEFT getLeft(){
		return this.left;
	}
	
	public RIGHT getRight(){
		return this.right;
	}
	
	public MIDDLE getMiddle(){
		return this.middle;
	}
}
