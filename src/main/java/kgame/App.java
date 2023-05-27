package kgame;

// IO shit
import java.io.IOException;

// Terminal shit
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class App
{
    public int kx  = 1;
    public int ky  = 1;
    public int kbx = kx;
    public int kby = ky;

    public static void main( String[] args ) throws IOException
    {
        // Screen Clear
        System.out.print("\033[H\033[2J");
        System.out.flush();

        App app = new App();

        try {
            app.test();
        }
        finally {
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

            tg.putString(kx, ky, "K");
            tg.putString(10, 20, hudtext);

            screen.refresh();

            KeyStroke input = screen.readInput();

            if (input != null) {
                //hudtext = String.format("Character Info: %s", input.toString());
                if (input.getCharacter() == 'w') {
                    ky--;
                }
                else if (input.getCharacter() == 's') {
                    ky++;
                }
                else if (input.getCharacter() == 'a') {
                    kx--;
                }
                else if (input.getCharacter() == 'd') {
                    kx++;
                }
            }
        }
        // screen.close();
    }
}
