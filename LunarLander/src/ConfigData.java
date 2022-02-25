/**
 Klasa przechowujaca dane wczytywania
 */
public  class ConfigData
{
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
     * informacja o liczbie poziomow Easy
     */
    static int numberOfLevelsEasy;
    /**
     * informacja o liczbie poziomow Hard
     */
    static int numberOfLevelsHard;
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
    static int[] pointsX1;
    /**
     Punkty y mapy
     */
    static int[] pointsY1;
    /**
     Miejsce ladowania x
     */
    static int[] landingX11;
    /**
     Miejsce ladowania y
     */
    static int[] landingY11;

    /**
     Miejsce ladowania x
     */
    static int[] landingX12;
    /**
     Miejsce ladowania y
     */
    static int[] landingY12;

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