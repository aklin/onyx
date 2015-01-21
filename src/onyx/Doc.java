package onyx;

import java.util.ArrayList;
import java.util.Scanner;

public class Doc {

	private final ArrayList<StringBuilder> lines;

	public Doc() {
		lines = new ArrayList<>();
	}
	
	
	public void put(Doc d, int x, int y){
		
	}
	
	public void put(CharSequence s, int x, int y){
		
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
}
