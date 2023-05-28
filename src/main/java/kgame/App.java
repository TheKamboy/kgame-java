package kgame;

// IO shit
import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
// Terminal shit
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class App {
    public int kx = 1;
    public int ky = 1;
    public int kbx = kx;
    public int kby = ky;

    public static void main(String[] args) throws IOException {
        // Screen Clear
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        TerminalSize ts = terminal.getTerminalSize();

        if (ts.getColumns() < 80 || ts.getRows() < 24) {
            System.out.println(
                    "ERROR: Your terminal size does not meet the requirements (80x24). Please change your terminal size to play.");
            System.exit(1);
        }

        App app = new App();

        try {
            app.test();
        } finally {
            System.exit(0);
        }
    }

    public void test() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics tg = terminal.newTextGraphics();
        terminal.setCursorVisible(false);
        screen.clear();

        String hudtext = "";

        while (true) {
            terminal.clearScreen();

            tg.putString(10, 0, String.format("X: %s, Y: %s", kx, ky));
            tg.putString(kx, ky, "K");
            tg.putString(10, 23, hudtext);

            screen.refresh();

            KeyStroke input = screen.readInput();

            kbx = kx;
            kby = ky;
            hudtext = "";

            if (input != null) {
                // hudtext = String.format("Character Info: %s", input.toString());
                if (input.getCharacter() == 'w') {
                    ky--;
                } else if (input.getCharacter() == 's') {
                    ky++;
                } else if (input.getCharacter() == 'a') {
                    kx--;
                } else if (input.getCharacter() == 'd') {
                    kx++;
                }
            }

            if (kx >= 80 || kx < 0) {
                move_keegan_back();
            }

            if (ky < 0 || ky >= 23) {
                move_keegan_back();
            }

        }
        // screen.close();
    }

    public boolean at_point(int x, int y) {
        if (kx == x && ky == y) {
            return true;
        }

        return false;
    }

    public void move_keegan_back() {
        kx = kbx;
        ky = kby;
    }
}
