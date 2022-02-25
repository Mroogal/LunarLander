import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.io.IOException;

/**
 Klasa ktora reprezentujaca panel z gra
 */
public class GameBoard extends JPanel implements ActionListener {
    /**
     Klasa Timer odpowiedzialna jest za generowanie akcji z zadanym delayem
     */
    private Timer timer;
    /**
     Klasa reprezentujaca statek
     */
    public SpaceShip spaceShip;
    /**
     Informuje czy gra ma sie dziac czy nie
     */
    public static boolean started=false;
    /**
     Rozmiary okna
     */
    public int currentScreenWidth, currentScreenHeight;
    /**
     Obiekt reprezentujacy ziemie
     */
    Shape ground;
    /**
     Klasa reprezentujaca ladowisko
     */
    Shape landing;
    Shape landing2;
    /**
     Ilosc poziomow
     */
    int mapNum;
    /**
     * zdobyte punkty
     */
    int score=0;
    /**
     * Ilosc poziomow
     */
    int numberOfLevels;


     /**
     * poziom trudnosci
     */
    static String DiffLvl;

    /**
     * skalowanie
     */
        float xScale,yScale;
        boolean changed_size;
    /**
     *
     Kontruktor klasy GameBoard
     @param x Rozmiar width tworzonego okna
     @param y Rozmiar height tworzonego okna
     */
    public GameBoard(int x, int y)
    {
        changed_size=false;
        currentScreenWidth = x;
        currentScreenHeight = y;
        addKeyListener(new GameBoard.TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        spaceShip = new SpaceShip();


        timer = new Timer(2, this);
        timer.start();
        mapNum = 1;
    }
    /**
    Klasa odpowiedzialna za rysowanie pola gry
    @param graphics Klasa reprezentujaca rysowana grafike
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setPreferredSize(new Dimension(currentScreenWidth,currentScreenHeight));

        xScale = getSize().width/(float)ConfigData.windowWidth;
        yScale = getSize().height/(float)ConfigData.windowHeight;

        drawLvl(ConfigData.pointsX1, ConfigData.pointsY1, ConfigData.landingX11, ConfigData.landingY11,ConfigData.landingX12, ConfigData.landingY12);

        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(Color.gray);
        g2d.fill(ground);
        g2d.setColor(Color.CYAN);
        g2d.fill(landing);
        g2d.setColor(Color.GREEN);
        g2d.fill(landing2);


        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(),(int)(spaceShip.getWidth()*xScale),(int)(yScale*spaceShip.getHeight()), this);
        g2d.setColor(Color.red);
        g2d.draw(new Line2D.Double(spaceShip.getX()+xScale*spaceShip.getWidth()/2, spaceShip.getY()+yScale*spaceShip.getHeight()/2,spaceShip.getX()+xScale*spaceShip.getWidth()/2+xScale*10000*spaceShip.acceleration.x , spaceShip.getY()+yScale*spaceShip.getHeight()/2+yScale*10000*spaceShip.acceleration.y));
        Rectangle2D lander = new Rectangle2D.Float(spaceShip.getX(), spaceShip.getY(), xScale*spaceShip.getWidth(), yScale*spaceShip.getHeight());

        Collision(ground, landing, lander);

        Rectangle2D info = new Rectangle2D.Float(0, 0, (float)currentScreenWidth, 40);
        g2d.setColor(Color.gray);
        g2d.fill(info);
        g2d.setColor(Color.red);
        g2d.drawString("Fuel= " + Double.toString(spaceShip.fuel), xScale*300, yScale*25);
        g2d.drawString("SCORE:"+score,xScale*500,yScale*25);
        if(Client.Connected==true){g2d.setColor(Color.green);} else if(Client.Connected==false){g2d.setColor(Color.red);}
        g2d.fillOval((int)(750*xScale),(int)(5*yScale),(int)(30*xScale),(int)(30*yScale));
        g2d.scale(xScale,yScale);

        graphics.dispose();
    }
    /**
    Klasa odpowiedzialna za wczytywanie poziomu do obiektu
    @param pointsx punkty x ziemi
     @param pointsy punkty y ziemi
     @param landingx punkty x ladowiska
     @param landingy punkty y ladowiska
     */
    public void drawLvl(int[] pointsx, int[] pointsy, int[] landingx, int[] landingy,int[] landingx2, int[] landingy2)
    {
        AffineTransform tx=new AffineTransform();
        tx.scale(xScale,yScale);
        ground = tx.createTransformedShape(new Polygon(pointsx,pointsy,pointsx.length));
        landing = tx.createTransformedShape(new Polygon(landingx,landingy,landingx.length));
        landing2 = tx.createTransformedShape(new Polygon(landingx2,landingy2,landingx2.length));

    }

    /**
     Metoda odpowiadajaca za wykonywanie akcji przez przyciski
     @param e Reprezentuje akcje wykonane przez timera
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (started==true||changed_size==true) {
            step();
        }
    }
    /**
     Metoda odpowiadajaca za rysowanie i akutalizowanie danych
     */
    private void step() {

        if (started==true&&changed_size==false) {
            spaceShip.Update();
            spaceShip.ForceChange(spaceShip.gravity.value);
        }

        if(spaceShip.getX()>this.getWidth())
        {
            Engine2D.Vector2D temp = new Engine2D.Vector2D(0,spaceShip.getY());
            spaceShip.position = temp;
        }
        else if (spaceShip.getX()<0)
        {
            Engine2D.Vector2D temp = new Engine2D.Vector2D(this.getWidth(),spaceShip.getY());
            spaceShip.position = temp;
        }
        else if(spaceShip.getY()<10)
        {
            Engine2D.Vector2D temp = new Engine2D.Vector2D(spaceShip.getX(), 10);

            spaceShip.position = temp;
        }
        this.repaint();
        this.revalidate();
        changed_size=false;
    }

    /**
     Metoda odpowiadajaca za sprawdzanie kolizji obiektow
     @param ground ziemia
     @param landing ladowisko
     @param lander ladownik
     */
    private void Collision(Shape ground, Shape landing, Rectangle2D lander)
    {
            if (ground.intersects(lander)) {

                System.out.println("Rozbiles sie");
                System.out.println(score);
                spaceShip.fuel -= ConfigData.FuelLoss;
                if(spaceShip.fuel>=0) {
                    this.continueGame();
                }
                else {
                    endGame();
                }
            }
            else if (landing.intersects(lander) && (spaceShip.speed.length() < ConfigData.landingSpeed)) {

                System.out.println("Pomyslne ladowanie ladowisko x2");
                score=score+200+(int) Math.round(spaceShip.fuel);
                System.out.println(score);


                if (mapNum < numberOfLevels )
                {
                    mapNum++;
                }
                else
                {
                    mapNum = 1;
                }

                if(spaceShip.fuel>=0) {
                    this.continueGame();
                }
                else {
                    endGame();
                }
            }
            else if (landing2.intersects(lander) && (spaceShip.speed.length() < ConfigData.landingSpeed)) {

                System.out.println("Pomyslne ladowanie ladowisko x1");
                score=score+100+(int) Math.round(spaceShip.fuel);
                System.out.println(score);

                //elegancki if na poziomie 3ciej gimnazjum
                if (mapNum < numberOfLevels )
                {
                    mapNum++;
                }
                else
                {
                    mapNum = 1;
                }

                if(spaceShip.fuel>=0) {
                    this.continueGame();
                }
                else {
                    endGame();
                }
            }
            else if(landing.intersects(lander)||landing2.intersects(lander))
            {
                System.out.println("Rozbiles sie o ladowisko");

                spaceShip.fuel -= ConfigData.FuelLoss;
                if (spaceShip.fuel >= 0) {
                    this.continueGame();
                }
                else
                    {
                    endGame();
                    }
            }
    }

    /**
     Metoda odpowiadajaca za zakonczenie gry oraz zapisanie wyniku na liscie wyników
     */
    public void endGame()
    {
        started = false;
        MainWindow.mainmenu.setVisible(true);
        MainWindow.game.setVisible(false);
        String nick = JOptionPane.showInputDialog(this, "Wprowadź swój nick aby zapisac wynik", "Wyniki", JOptionPane.INFORMATION_MESSAGE);
        if (nick != null) {
            try {
               String out = Main.client.saveScore(nick, score);
               if( out != null) {
                   JOptionPane.showMessageDialog(this, out);
               }
            } catch (IOException e){
                System.out.println("Nie udalo sie zapisac wyniku w serwerze");

            }

            MainWindow.mainmenu.requestFocus();
        }
    }

    /**
     Klasa odpowiadajaca zczytywanie wejsc z klawiatury
     */
    private class TAdapter extends KeyAdapter {
        /**
         Metoda odpowiadajaca za przekazywanie puszczenia klawisza do spaceship
         @param e Reprezentuje akcje wykonane przez klawiature
         */
        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        /**
         Metoda odpowiadajaca za przekazywanie nacisniecia klawisza do spaceship
         @param e Reprezentuje akcje wykonane przez klawiature
         */
        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }
    /**
     Metoda odpowiadajaca przygotwanie planczy pod nowa gre
     */
    public void NewGame(){
        if(DiffLvl=="Easy"){
            numberOfLevels=ConfigData.numberOfLevelsEasy;
        }else if(DiffLvl=="Hard"){
            numberOfLevels=ConfigData.numberOfLevelsHard;
        }
        CheckAndLoad();

        spaceShip.reset();
        score=0;
        started=true;
        this.requestFocus();
        repaint();
    }
    /**
     Metoda odpowiadajaca przygotwanie planszy pod nowa plansze
     */
    public void continueGame(){

        CheckAndLoad();

        spaceShip.cnt();
        started=true;
        this.requestFocus();
    }
    /**
     Metoda odpowiadajaca za update rozmiarow okna
     */
    public void RSize(int xn, int yn){
        currentScreenWidth = xn;
        currentScreenHeight = yn;
        changed_size=true;
    }

    /**
     * Metoda sprawdzajaca polaczenie i ladujaca mape z plikow lokalnych lub z serwera w zaleznosci od stanu polacznia
     */

    void CheckAndLoad()
    {

        if (Main.client.Connected==true)
        {
            try {
                Main.client.getMapConfig(DiffLvl,mapNum);
            } catch (IOException ee) {
                try {
                    ConfigLoad.configMap(DiffLvl,mapNum);
                } catch (IOException e) {

                }
            }
        }
        else if (Main.client.Connected==false)
        {
            try {
                ConfigLoad.configMap(DiffLvl,mapNum);
            } catch (IOException e) {

            }
        }


    }
}