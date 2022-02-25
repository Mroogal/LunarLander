import javax.swing.*;
import java.awt.*;

/**
 Klasa ktora reprezentująca okno gry
 */
public class Game extends JPanel {
    /**
     Panel reprezentujacy panel z gra
     */
    public static GameBoard boardgame;
    /**
     Panel reprezentujacy menu pausy w czasie trwania gry
     */
    public static PauseMenu pausemenu;
    /**
     Rozmiary okna
     */
    int currentScreenWidth, currentScreenHeight;
    /**
     Obiekt pozwalajacy wyswietlac panele na sobie
     */
    public JLayeredPane layers;

    /**
    Kontruktor klasy Game
    @param x Rozmiar width tworzonego okna
    @param y Rozmiar height tworzonego okna
     */
    public Game(int x, int y){
        currentScreenWidth = x;
        currentScreenHeight = y;
        layers = new JLayeredPane();
        layers.setLayout(new OverlayLayout(layers));
        layers.setSize(new Dimension(currentScreenWidth,currentScreenHeight));
        add(layers);
        setVisible(true);
        setOpaque(true);

        boardgame=new GameBoard(currentScreenWidth, currentScreenHeight);
        boardgame.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        layers.setLayer(boardgame,0);

        pausemenu=new PauseMenu();
        layers.setLayer(pausemenu,100);

        layers.add(boardgame);
        layers.add(pausemenu);
        pausemenu.setVisible(false);
    }
    /**
     Metoda odpowiadajaca za update rozmiarow okna
     */
    public void RSize(int xn, int yn){
        currentScreenWidth = xn;
        currentScreenHeight = yn;
        boardgame.RSize(currentScreenWidth,currentScreenHeight);

        setBounds(0,0,currentScreenWidth,currentScreenHeight);
        layers.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        boardgame.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        pausemenu.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        pausemenu.subpanelPause.setBounds(0,0,currentScreenWidth,currentScreenHeight);

        repaint();
        layers.repaint();
        boardgame.repaint();
        pausemenu.repaint();
        pausemenu.subpanelPause.repaint();

        revalidate();
        layers.revalidate();
        boardgame.revalidate();
        pausemenu.revalidate();
        pausemenu.subpanelPause.revalidate();
    }
}
