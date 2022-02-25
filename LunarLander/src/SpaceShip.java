import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Klasa reprezentujaca ladownik, ktorym steruje gracz
 *
 */
public class SpaceShip extends Engine2D.MovingObject{

    /**
     * obiekt reprezentujaca sile grawitacji
     */
    public Engine2D.Gravity gravity = new Engine2D.Gravity(-0.00005);
    /**
     * wysokosc statku
     */
    private int w;
    /**
     * szerokosc statku
     */
    private int h;
    /**
     * obiekty typu image przechowijace odpowiednie obrazki rakiety
     */
    public static Image image1,image,imageL,imageP,imageG;
    /**
     * zmienna reprezentujaca kierunek ruchu rakiety
     */
    private int move;
    /**
     * zmienna reprezentujaca wciskany przez gracza klawisz
     */
    public int key;
    /**
     * zmienna reprezentujaca sposob sterowania
     */
    public int control;

    /**
     * konstruktor klasy
     */
    public SpaceShip() {
        super(new Engine2D.Vector2D(ConfigData.startSpeed[0],ConfigData.startSpeed[1]), new Engine2D.Vector2D(0, 0));
        loadImage();
        position = new Engine2D.Vector2D(ConfigData.startPoint[0], ConfigData.startPoint[1]);
        image = image1;
        fuel=ConfigData.startFuelValue;
        move=0;
        control=0;
    }

    /**
     * metoda wczytujaca grafike ladownika
     */
    public void loadImage() {
        URL urlii=MenuBackground.class.getResource("img/rakieta1.png");
        ImageIcon ii = new ImageIcon(urlii);
        image1 = ii.getImage();

        URL urliii=MenuBackground.class.getResource("img/rakietagora.png");
        ImageIcon iii = new ImageIcon(urliii);
        imageG = iii.getImage();

        URL urliiii=MenuBackground.class.getResource("img/rakietalewa.png");
        ImageIcon iiii = new ImageIcon(urliiii);
        imageL = iiii.getImage();

        URL urliiiii=MenuBackground.class.getResource("img/rakietaprawa.png");
        ImageIcon iiiii = new ImageIcon(urliiiii);
        imageP = iiiii.getImage();



        w = image1.getWidth(null);
        h = image1.getHeight(null);
    }

    /**
     * getter pozycji X
     * @return pozycja X
     */
    public int getX() {

        return (int) Math.round(position.x);
    }
    /**
     * getter pozycji Y
     * @return pozycja Y
     */
    public int getY() {

        return (int) Math.round(position.y);
    }
    /**
     * getter szerokosci
     * @return szerokosc
     */
    public int getWidth() {

        return w;
    }
    /**
     * getter wysokosci
     * @return wysokosc
     */
    public int getHeight() {

        return h;
    }

    /**
     * getter grafiki
     * @return grafika
     */
    public Image getImage() {

        return image;
    }

    /**
     * metoda reagujaca na wciskanie pzez gracza klawiszy
     * @param e obiekt typu KeyEvent obslugujacy wciskanie klawiszy
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(control==0) {
            if (key == KeyEvent.VK_LEFT && key == KeyEvent.VK_RIGHT) {
                move = 3;
            } else if (key == KeyEvent.VK_RIGHT) {
                move = 2;
            } else if (key == KeyEvent.VK_UP) {
                move = 3;
            } else if (key == KeyEvent.VK_LEFT) {
                move = 1;
            } else if (key == KeyEvent.VK_DOWN) {
                move = 4;
            } else if(key==KeyEvent.VK_P&&GameBoard.started==true){GameBoard.started=false;
                Game.pausemenu.setVisible(true);
                Game.boardgame.repaint();
                Game.boardgame.revalidate();
            }
        }
        if(control==1){
            if (key == KeyEvent.VK_A && key == KeyEvent.VK_D) {
                move = 3;
            } else if (key == KeyEvent.VK_D) {
                move = 2;
            } else if (key == KeyEvent.VK_W) {
                move = 3;
            } else if (key == KeyEvent.VK_A) {
                move = 1;
            } else if (key == KeyEvent.VK_S) {
                move = 4;
            } else if(key==KeyEvent.VK_P&&GameBoard.started==true){GameBoard.started=false;
                Game.pausemenu.setVisible(true);
                Game.boardgame.repaint();
                Game.boardgame.revalidate();
            }
        }
        movement();
    }

    /**
     * metoda reagujaca na puszczanie pzez gracza klawiszy
     * @param e obiekt typu KeyEvent obslugujacy puszczanie klawiszy
     */
    public void keyReleased(KeyEvent e) {

        key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            move = 0;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D || key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            move = 0;
        }
        movement();
    }



    /**
     * Metoda resetujaca polozenie statku
     */
    public void cnt()
    {
        position.x = ConfigData.startPoint[0];
        position.y = ConfigData.startPoint[1];
        speed.x = ConfigData.startSpeed[0];
        speed.y = ConfigData.startSpeed[1];
        acceleration.x = 0;
        acceleration.y = 0;
    }

    /**
     * metoda resetujaca polozenie rakiety oraz przywracajaca startowa wartosc paliwa
     */
    public void reset() {
        cnt();
        fuel = ConfigData.startFuelValue;
    }

    /**
     * metoda odpowiadajaca za ruch rakiety
     */
    private void movement() {
        if (fuel > 0) {
            if (move == 1) {
                ForceChange(new Engine2D.Vector2D(-0.005, 0 + gravity.value.y));
                fuel-=1;
                image=imageP;
            }
            if (move == 2) {
                ForceChange(new Engine2D.Vector2D(0.005, 0 + gravity.value.y));
                fuel-=1;
                image=imageL;
            }
            if (move == 3) {
                ForceChange(new Engine2D.Vector2D(0, -0.01 + gravity.value.y));
                fuel-=1;
                image=imageG;
            }
            if (move == 4) {
                ForceChange(new Engine2D.Vector2D(0, 0.01 + gravity.value.y));
                fuel-=1;
                image=image1;
            }
        }
        if (move == 0) {
            ForceChange(gravity.value);
            image = image1;
        }
    }
}
