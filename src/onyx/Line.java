package onyx;

import onyx.util.Pair;
import java.io.IOException;
import java.util.ArrayList;

public class Line implements StrIface {

	private final ArrayList<Str> list;
	private int length;

	public Line() {
		list = new ArrayList<>();
		length = 0;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public char charAt(int index) {
		int clen = 0;
		Str sub = null;

		if (index > length || index < 0)
			throw new IndexOutOfBoundsException();

		for (Str s : list)
			if (clen + s.length() < index)
				clen += s.length();
			else
				sub = s;

		return sub.charAt(clen - index);
	}

	@Override
	public CharSequence subSequence(int begin, int end) {
		final Pair<Integer, Integer> startPair;
		final Pair<Integer, Integer> endPair;
		final StringBuilder b;

		final Str startNode;
		final Str endNode;

		if (Math.min(begin, end) < 0 || Math.max(begin, end) > this.length())
			throw new IndexOutOfBoundsException();

		startPair = findStr(begin, 0);

		startNode = list.get(startPair.getRight());

		/*
		 It's possible that the first node contains both begin and end. If so
		 make startPair and endPair tautonymous; else go find the endPair.
		 */
		if (startNode.length() + startPair.getLeft() >= end)
			endPair = startPair;
		else
			endPair = findStr(end, startPair.getRight() + 1);

		endNode = list.get(endPair.getRight());

		b = new StringBuilder(end - begin);

		begin = begin - startPair.getLeft();
		end = end - endPair.getLeft();

		b.append(startNode.subSequence(begin));

		for (int i = startPair.getRight() + 1; i < endPair.getRight(); i++)
			b.append(list.get(i).subSequence(0));

		b.append(endNode.subSequence(0, end));

		return b.toString();
	}

	/**
	 * Returns the index of the Str that contains the point in question.
	 * @param index
	 * @param startAt
	 * @return
	 */
	private Pair<Integer, Integer> findStr(final int index, final int startAt) {
		int clen = 0;
		Str tmp;

		for (int i = startAt; i < list.size(); i++) {
			tmp = list.get(i);
			if (clen + tmp.length() >= index)
				return new Pair<>(clen, i);
			else
				clen += tmp.length();
		}

		return null;
	}

	private void checkLength() {
		length = 0;
		for (Str s : list)
			length += s.length();
	}

	@Override
	public int insert(int pos, CharSequence s) {
		if (pos < 0 || pos > length())
			throw new IndexOutOfBoundsException();

		/*
		 TODO: Implement cache
		 */
		this.length += s.length();
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int compareTo(CharSequence o) {
		int res;
		for (Str s : list)
			if ((res = s.compareTo(o)) != 0)
				return res;
		return 0;
	}

	@Override
	public boolean equals(StrIface o) {
		if (o == null)
			return false;
		if (o.length() != this.length())
			return false;

		return list.stream().noneMatch((s) -> (!s.equals(o)));
	}

}
