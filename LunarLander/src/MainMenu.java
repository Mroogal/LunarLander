import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 Klasa ktora reprezentująca menu główne
 */
public class MainMenu extends JPanel {
    /**
     Tło menu glownego
     */
    public MenuBackground background;
    /**
     Panel z guzikami
     */
    public MenuButtons menu;
    /**
     * Panel z opcjami
     */
    public MenuOptions options;
    /**
     Rozmiary okna
     */
    int currentScreenWidth, currentScreenHeight;
    /**
     Obiekt pozwalajacy wyswietlac panele na sobie
     */
    public JLayeredPane layers;

    /**
     Kontruktor klasy MainMenu
     @param x Rozmiar width tworzonego okna
     @param y Rozmiar height tworzonego okna
     */
    public MainMenu(int x, int y){
        currentScreenWidth = x;
        currentScreenHeight = y;
        setSize(new Dimension(currentScreenWidth, currentScreenHeight));
        layers = new JLayeredPane();
        layers.setLayout(new OverlayLayout(layers));
        layers.setSize(new Dimension(currentScreenWidth,currentScreenHeight));
        add(layers);
        setVisible(true);
        setOpaque(true);

        background = new MenuBackground(currentScreenWidth, currentScreenHeight);
        background.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        menu = new MenuButtons();
        menu.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        options = new MenuOptions();
        options.setBounds(0,0,currentScreenWidth,currentScreenHeight);

        layers.setLayer(background,0);
        layers.setLayer(menu,100);
        layers.setLayer(options,100);

        layers.add(background);
        layers.add(menu);
        layers.add(options);
    }
    /**
     Metoda odpowiadajaca za update rozmiarow okna
     */
    public void RSize(int xn, int yn){
        currentScreenWidth = xn;
        currentScreenHeight = yn;
        background.RSize(currentScreenWidth,currentScreenHeight);

        setBounds(0,0,currentScreenWidth,currentScreenHeight);
        layers.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        background.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        menu.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        menu.subpanelRight.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        options.setBounds(0,0,currentScreenWidth,currentScreenHeight);
        options.subpanelRight.setBounds(0,0,currentScreenWidth,currentScreenHeight);

        repaint();
        layers.repaint();
        background.repaint();
        menu.repaint();
        menu.subpanelRight.repaint();
        options.repaint();
        options.subpanelRight.repaint();

        revalidate();
        layers.revalidate();
        background.revalidate();
        menu.revalidate();
        menu.subpanelRight.revalidate();
        options.revalidate();
        options.subpanelRight.revalidate();
    }
}
