import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 Class which represents Main Menu Window (extends JFrame)
 */
public class MainWindow extends JFrame {

    public int currentScreenWidth, currentScreenHeight;
    public static Game game;
    public static MainMenu mainmenu;

    /**
     MainWindow class constructor
     */
    public MainWindow()
    {
        setTitle("LunarLander v3.0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        currentScreenWidth = ConfigData.windowWidth;
        currentScreenHeight = ConfigData.windowHeight;
        setVisible(true);

        mainmenu=new MainMenu(currentScreenWidth, currentScreenHeight);
        add(mainmenu);
        setSize(currentScreenWidth,currentScreenHeight);

        game= new Game(currentScreenWidth,currentScreenHeight);
        add(game);
        game.setVisible(false);
        this.setFocusable(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                currentScreenWidth=getWidth();
                currentScreenHeight=getHeight();

                mainmenu.RSize(currentScreenWidth,currentScreenHeight);
                game.RSize(currentScreenWidth,currentScreenHeight);

                repaint();
            }
        });
    }
}
