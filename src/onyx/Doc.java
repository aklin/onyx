package onyx;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Doc {

	private final ArrayList<StringBuilder> lines;

	public Doc() {
		lines = new ArrayList<>();
	}

	public void put(final Doc d, final int col, final int row) {
		
	}

	public void put(CharSequence s, int x, int y) {

	}

	private ArrayList<StringBuilder> collection() {
		return this.lines;
	}

	public static Doc read(final Scanner sc) {
		final Doc ret = new Doc();
		final ArrayList<StringBuilder> l = ret.collection();

		while (sc.hasNext())
			l.add(new StringBuilder(sc.nextLine()));

		return ret;
	}

	public static Doc clone(final Doc proto) {
		final Doc ret = new Doc();
		ret.collection().addAll(proto.collection());
		return ret;
	}

	@Override
	public String toString() {
		final StringBuilder ret = new StringBuilder();

		lines.stream().forEach((m) -> {
			ret.append(m);
		});

		return ret.toString();
	}

	public void flush(final Writer writer) throws IOException {
		for (StringBuilder line : lines)
			writer.write(line.toString());

	}
}
