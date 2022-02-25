import static java.lang.Math.sqrt;

/**
 * Silnik obliczajacy predkoscoraz przyspieszenie statku
 */
public class Engine2D {
    /**
     * Klasa reprezentujaca pare punktow
     */
    public static class Vector2D{
        /**
         * skladowa kierunku poziomym
         */
        public double x;
        /**
         * skladowa kierunku pionowym
         */
        public double y;

        /**
         * konstruktor klasy
         * @param X poczatkowa wartosc X
         * @param Y poczatkowa wartosc Y
         */
        public Vector2D(double X,double Y){
            x=X;
            y=Y;
        }

        /**
         * Konstruktor kopiujacy
         * @param V obiekt klasy Vector 2D
         */
        public Vector2D(Vector2D V){
            x=V.x;
            y=V.y;
        }


        /**
         * metoda dodajaca wartosci X, Y do pol wektora
         * @param X przyrost wartosci X
         * @param Y przyrost wartosci Y
         */
        public void add(double X,double Y){
            x+=X;
            y+=Y;
        }

        /**
         * metoda dodajaca wartosci pol wektora V do pol wektora
         * @param V wektor ktorego wartosci sa dodawane
         */
        public void add(Vector2D V){
            x+=V.x;
            y+=V.y;
        }

        /**
         * metoda odemujaca wartosci X, Y do pol wektora
         * @param X spadek wartosci X
         * @param Y spadek wartosci Y
         */
        public void minus(double X,double Y){
            x-=X;
            y-=Y;
        }

        /**
         * metoda odejmujaca wartosci pol wektora V do pol wektora
         * @param V wektor ktorego wartosci sa odejmowane
         */
        public void minus(Vector2D V){
            x-=V.x;
            y-=V.y;
        }

        /**
         * klasa zwracajaca aktualna  dlugosc wektora
         * @return dlugosc wektora
         */
        public double length(){
            return sqrt(x*x+y*y);
        }
    }

    /**
     * klasa reprezentujaca poruszajacy sie obiekt
     */
    public static class MovingObject{
        /**
         * pozycja obiektu
         */
        public Vector2D position;
        /**
         * predkosc obiektu
         */
        public Vector2D speed;
        /**
         * przyspieszenie obiektu
         */
        public Vector2D acceleration;
        /**
         * paliwo obiektu
         */
        public double fuel;

        /**
         * konstruktor obiektu
         * @param SPEED poczatkowa predkosc
         * @param ACCELERATION poczatkowe przyspieszenie
         */
        public MovingObject(Vector2D SPEED,Vector2D ACCELERATION){
            speed = new Vector2D(SPEED);
            acceleration = new Vector2D(ACCELERATION);
        }

        /**
         * metoda aktualizuaca predkosc i polozenie obiektu
         */
        public void Update(){
            speed.add(acceleration);
            position.add(speed);
        }

        /**
         * metoda zmieniajaca przyspieszenie obiektu
         * @param ACCELERATION wektor przyspieszenia, o ktory zmienia sie przyspieszenie
         */
        public void ForceChange(Vector2D ACCELERATION){
            acceleration.add(ACCELERATION);
        }
    }

    /**
     *  klasa symulujaca grawitacje planety
     */
    public static class Gravity{
        /**
         * wartosc grawitacji
         */
        public Vector2D value;

        /**
         * konstruktor
         * @param num wartosc grawitacji w kierunku pionowym
         */
        public Gravity(double num){
            value = new Vector2D(0,-num);
        }
    }
}
