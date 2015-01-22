package onyx;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;

public class Display {

	private Doc document;
	private final Screen term;
	private final int polling;
	private TerminalSize sz;
	private int cursorRow;
	private int cursorCol;

	public Display() {
		polling = 10;
		term = new Screen(TerminalFacade.
			createTerminal(System.in, System.out, Charset.
						   forName("UTF8")));

	}

	public void run() {
		setup();
		takedown();
	}

	public void setup() {
		term.startScreen();
		printIntro();
		readLoop();
	}

	public void takedown() {
		term.stopScreen();
	}

	private void printIntro() {
		String intro = StringUtils.center("Onyx 0.1", term.getTerminalSize().getColumns());
		term.putString(0, 0, intro, Terminal.Color.WHITE, Terminal.Color.BLACK);

	}

	private Key readLoop() {
		Key k = null;
		try {
			do {
				Thread.sleep(polling);

				k = term.readInput();
				if (k != null)
					handleKey(k);

			} while (k != null ? k.getKind() != Key.Kind.Escape : true);
		} catch (InterruptedException ex) {
		}
		return k;
	}

	private void handleKey(final Key k) {
		switch (k.getKind()) {
			case NormalKey:
				addChar(k);

				break;
		}
	}

	private void addChar(Key k) {
		if (cursorCol >= sz.getColumns() - 1) {
			cursorCol = 0;
			cursorRow++;
		}

		if (cursorRow >= sz.getRows() - 1)
			panDown(); //give us some space
		term.putString(cursorCol, cursorRow, k.toString(), Terminal.Color.YELLOW, Terminal.Color.BLACK);
	}

	private void panDown() {

	}

}
