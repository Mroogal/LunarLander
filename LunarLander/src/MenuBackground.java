import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;

/**
 Klasa ktora reprezentujaca tlo menu glownego
 */
public class MenuBackground extends JPanel implements ActionListener{
    /**
     Rozmiary okna
     */
    public int currentScreenWidth, currentScreenHeight;
    /**
     Klasa Timer odpowiedzialna jest za generowanie akcji z zadanym delayem
     */
    private Timer timer;
    /**
     Obrazek tla
     */
    public Image img;

    /**
    Kontruktor klasy MenuBackground
    @param x Rozmiar width tworzonego okna
    @param y Rozmiar height tworzonego okna
     */
    public MenuBackground(int x, int y){
        currentScreenWidth = x;
        currentScreenHeight = y;
        timer = new Timer(2, this);
        timer.start();
        this.repaint();
        this.revalidate();
        System.out.println("testv0 , "+x+" , "+y);
    }
    /**
    Klasa odpowiedzialna za rysowanie tla
    @param graphics Klasa reprezentujaca rysowana grafike
     */
    @Override
    public void paintComponent(Graphics graphics) {
        setPreferredSize(new Dimension(currentScreenWidth,currentScreenHeight));
        super.paintComponent(graphics);

        float xScale = getSize().width/(float)ConfigData.windowWidth;
        float yScale = getSize().height/(float)ConfigData.windowHeight;
        Graphics2D g2d = (Graphics2D) graphics;

        URL url=MenuBackground.class.getResource("img/LLtlo3.png");
        ImageIcon imag = new ImageIcon(url);
        img=imag.getImage();
        int w = img.getWidth(this);
        int h = img.getHeight(this);
        g2d.drawImage(img,0,0,currentScreenWidth,currentScreenHeight,this);
        if(Client.Connected==true){g2d.setColor(Color.green);} else if(Client.Connected==false){g2d.setColor(Color.red);}
        g2d.fillOval((int)(750*xScale),(int)(25*yScale),(int)(30*xScale),(int)(30*yScale));
        g2d.scale(xScale,yScale);
        graphics.dispose();
    }

    /**
     Metoda odpowiadajaca za wykonywanie akcji przez przyciski
     @param e Reprezentuje akcje wykonane przez timera
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
        this.revalidate();
    }
    /**
     Metoda odpowiadajaca za update rozmiarow okna
     */
    public void RSize(int xn, int yn){
        currentScreenWidth = xn;
        currentScreenHeight = yn;
    }
}
