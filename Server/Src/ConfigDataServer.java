/**
 Klasa przechowujaca dane wczytywania
 */
public  class ConfigDataServer
{
    /**
     * informacja o liczbie poziomow Easy
     */
    public static int numberOfLevelsEasy;
    /**
     * informacja o liczbie poziomow Hard
     */
    public static int numberOfLevelsHard;
    /**
     Informacja o wysokosci okienka
     */
    static int windowHeight;
    /**
     Informacja o szerokosci okienka
     */
    static int windowWidth;
    /**
     Wartosc poczatkowa paliwa
     */
    static int startFuelValue;
    /**
     Wartosc poczatkowa predkosci
     */
    static int[] startSpeed = new int[2];
    /**
     Punkt startowy
     */
    static int[] startPoint = new int[2];
    /**
     Dozwolona predkosc ladowania
     */
    static double landingSpeed;
    /**
     Punkty x mapy
     */
    static int[] pointsX1= new int[15];
    /**
     Punkty y mapy
     */
    static int[] pointsY1= new int[15];
    /**
     Miejsce ladowania x
     */
    static int[] landingX11= new int[4];
    /**
     Miejsce ladowania y
     */
    static int[] landingY11= new int[4];
    /**
     Miejsce ladowania x
     */
    static int[] landingX12= new int[4];
    /**
     Miejsce ladowania y
     */
    static int[] landingY12= new int[4];

    /**
     Mnoznik X predkosci
     */
    static double landingvXmultiplier;
    /**
     Mnoznik Y predkosci
     */
    static double landingvYmultiplier;
    /**
     Ilosc paliwa stracona podczas ladowania
     */
    static int FuelLoss;

}