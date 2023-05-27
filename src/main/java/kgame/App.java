package kgame;

// IO shit
import java.io.IOException;

// Terminal shit
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class App
{
    public static void main( String[] args ) throws IOException
    {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        terminal.clearScreen();

        try {
            TextGraphics tg = screen.newTextGraphics();

            screen.startScreen();
            screen.clear();

            tg.putString(10, 10, "hi");
            screen.refresh();

            screen.readInput();
        }
        finally {
            screen.stopScreen();
            screen.close();
        }
    }
}
