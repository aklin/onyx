package onyx;

public class Str implements StrIface {

	private final StringBuilder b;
	private String lastImage;

	public Str() {
		this(null);
	}

	public Str(CharSequence s) {
		b = new StringBuilder(s);
		lastImage=null;
	}

	@Override
	public int length() {
		return b.length();
	}

	@Override
	public char charAt(int index) {
		return b.charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		return b.subSequence(start, end);
	}

	public CharSequence subSequence(final int start) {
		return subSequence(start, this.length());
	}

	@Override
	public int insert(int pos, final CharSequence s) {
		if (s == null)
			return 0;
		b.insert(pos, s);
		
		lastImage=null;
		
		return s.length();
	}

	@Override
	public int compareTo(CharSequence o) {
		//probably needs to be cached
		if(lastImage==null)
			lastImage=b.toString();
		
		return lastImage.compareTo(String.valueOf(o));
	}

}
