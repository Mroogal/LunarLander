import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 Klasa odpowiadajaca za obsluge wynikow
 */
public class Scoreboard {
    /**
     Lista wynikow
     */
    public ArrayList<Pair<String, Integer>> scorelist = new ArrayList<>();
    /**
     Wczytytywacz
     */
    private BufferedReader br;

    /**
     Dodawanie wyniku do scoreboard
     @param name nick gracza
     @param score wynik gracza
     * @throws IOException
     */
    public void addScores(String name, int score) {
        Pair<String, Integer> pair = new Pair(name, score);
        scorelist.add(pair);
    }

    /**
     * Metoda wczytujaca dane z pliku
     *
     * @throws IOException
     */
    public void loadScores() {
        try {
            br = new BufferedReader(new FileReader("LunarLander/scoreboard.csv"));
            scorelist.clear();
            String line = br.readLine();
            while (line != null) {
                String[] text = line.split(",");
                addScores(text[0], Integer.parseInt(text[1]));
                line = br.readLine();
            }
            br.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Metoda zpisujaca wyniki do pliku
     *
     * @throws IOException
     */
    public void saveScores() {
        try {
            FileWriter writer = new FileWriter("LunarLander/scoreboard.csv");
            for (Pair<String, Integer> element : scorelist) {
                writer.write(element.o1 + "," + element.o2 + '\n');
            }
            writer.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    /**
     * Metoda sortujaca wyniki
     *
     * @throws IOException
     */
    public void sort(){
        scorelist.sort(Comparator.comparing(thing -> -thing.o2));
    }
    /**
     * Metoda pokazujaca dane
     *
     * @throws IOException
     */
    public String show(){
        StringBuilder text = new StringBuilder();
        text.append("Name:         Score:\n");
        sort();
        for(Pair<String,Integer> element:scorelist){
            text.append(element.o1+"         "+element.o2+'\n');
        }
        return text.toString();
    }

    /**
     * Klasa ktora reprezentuje dwa obiekty
     */
    public static class Pair<O1, O2> {
        public O1 o1;
        public O2 o2;
        /**
         * Konstruktor klasy Pair
         * @param o1 obiekt 1
         * @param o2 obiekt 2
         */
        public Pair(O1 o1, O2 o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }
}