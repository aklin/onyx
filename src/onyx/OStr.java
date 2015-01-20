package onyx;

import java.util.Arrays;

public class OStr implements CharSequence, Comparable<OStr> {

	private char content[];
	private int fill = 0;
	public static final int INIT_SIZE = 32;
	private boolean empty = true;

	public OStr() {
		this(null);
	}

	public OStr(final CharSequence seq) {
		int sz = INIT_SIZE; //initial size

		if (seq != null) //make sure the array is large enough
			while (sz < seq.length())
				sz += INIT_SIZE;
		this.content = new char[sz];

		if (seq != null) {
			for (int i = 0; i < seq.length(); i++)
				content[i] = seq.charAt(i);
//			System.arraycopy(seq, 0, content, 0, seq.length());
			this.fill = seq.length() - 1;
			this.empty = false;
		} else
			this.fill = 0;

	}

	public boolean isEmpty() {
		return this.empty;
	}

	@Override
	public int length() {
		return this.fill + 1;
	}

	public int size() {
		return this.content.length;
	}

	@Override
	public char charAt(final int index) {
		return this.content[index];
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		if (end - start <= 0)
			return null;
		return String.valueOf(Arrays.copyOfRange(content, start, end));
	}

	@Override
	public int compareTo(OStr comp) {
		final int min;

		if (comp == null)
			return 1;

		min = Math.min(this.length(), comp.length());
		for (int i = 0; i < min; i++)
			if (this.content[i] == comp.content[i])
				continue;
			else if (this.content[i] > comp.content[i])
				return 1;
			else
				return -1;

		//At this point both strings are equal. Let the longest win.
		return this.length() == comp.length() ? 0
			   : this.length() > min ? 1 //This string is longer
				 : -1;  //comp is longer
	}

	/**
	 * Appends a character at the end of this string. If the internal array is
	 * not large enough, it will be reallocated according to
	 * <code>expand()</code>
	 * @param e
	 */
	public void append(final char e) {
		putAt(e, this.length());
	}

	public boolean equals(final CharSequence comp) {
		if (comp == null)
			return false;

		if (comp.length() != this.length())
			return false;

		for (int i = 0; i < this.length(); i++)
			if (this.charAt(i) != comp.charAt(i))
				return false;

		return true;
	}

	public void putAt(final char e, final int pos) {
		if (this.length() == this.size())
			this.expand();

		//Push all elements 1 position to the right
		shift(pos, 1);
		content[pos] = e;
		fill++;
	}

	public void putChunk(final CharSequence e, final int pos) {
		while (e.length() + this.length() > this.size()) 
			expand(); //make sure we have enough room
		

		//move everything `dist` to the right
		shift(pos, e.length());

		//fill the gap
		for (int i = pos; i < e.length(); i++)
			content[i] = e.charAt(i - pos);

		this.fill += e.length();
	}

	/**
	 * Expands the size of the internal array by 1/2.
	 */
	private void expand() {
		char[] old = this.content;
		this.content = new char[content.length + (content.length / 2)];
		System.arraycopy(old, 0, this.content, 0, old.length);
	}

	@Override
	public String toString() {
		return String.valueOf(this.content, 0, this.length());
	}

	/**
	 * Reallocates the internal array to house just enough content. Basically
	 * the new <code>content.length</code> will be equal to
	 * <code>this.fill</code>
	 */
	public void trimToSize() {
		char[] newc;
		if (this.length() == this.size())
			return;
		newc = new char[this.fill];
		System.arraycopy(this.content, 0, newc, 0, this.fill);
		this.content = newc;
	}

	/**
	 * Removes <code>range</code> characters, starting at <code>pos</code>.
	 * @param pos
	 * @param range
	 */
	public void remove(final int pos, final int range) {
		shift(pos, -range);
		content[pos-range]='\0';
		fill-=range;
	}

	/**
	 * Moves <code>step</code> characters left, starting at <code>start</code>.
	 * Does not check array bounds. Does not handle potential leftovers. Does
	 * not update <code>fill</code>. These are the responsibility of the caller.
	 * @param start The position to start moving
	 * @param step How many positions left it should go
	 */
//	private void moveLeft(final int start, final int step) {
//
//		for (int i = 0; i < step; i++)
//			content[start - step] = content[start];
//
//	}

	/**
	 * Create a gap in the internal array. Pushes all elements
	 * <code>width</code> positions to the right, starting at
	 * <code>start</code>. This method assumes there is sufficient space in the
	 * array to shuffle things around; caller must ensure that this is true. The
	 * caller must also ensure the correct <code>fill</code> value has been set
	 * after everything is done.
	 * @param start Where to start the gap
	 * @param width How wide to make it
	 */
//	private void moveRight(final int start, final int width) {
//		if (width != 0) //or should it be >0?
//			for (int move = this.length(); move >= start; move--) {
//				System.out.
//					println("Moving " + move + " to pos " + (move + width));
//				content[move + width] = content[move];
//			}
//	}

	/**
	 * Moves <code>step</code> characters, starting at <code>start</code>. If
	 * <code>step</code> is positive, everything is moved to the right. If it's
	 * negative, everything is moved left. Does not check array bounds. Does not
	 * handle potential leftovers. Does not allocate or free any resources. Does
	 * not update <code>fill</code>. These tasks are the responsibility of the
	 * caller, and should be taken care of, lest the instance is left in a
	 * broken state.
	 * @param start
	 * @param step
	 */
	private void shift(final int start, int step) {
		if (step == 0)
			return; //don't bother

		if (step > 0) //execute right shift
			for (int i = this.length(); i >= start; i--)
				content[i + step] = content[i];
		else { //execute left shift
			step = Math.abs(step);
			for (int i = 0; i < step; i++)
				content[start - step] = content[start];
		}
	}

}
