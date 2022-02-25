import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Klasa odpowiadajaca za komunikacje gry z serwerem
 */
public class Client{
    /**
     * Klasa reprezentujaca socket
     */
    public Socket socket;
    /**
     * zmienna przechowujaca stan polaczenia z serwerem
     */
    static boolean Connected;

    InetAddress IPaddress;

    /**
     * numer portu
     */
    int port;

    /**
     * konstruktor klasy
     */
    public Client(){

    }

    /**
     * metoda wysylajaca zadanie o wczytanie plikow konfiguracyjnych, oraz otrzymujaca dane z serwera i zapisujaca je w pamieci programu
     * @throws IOException
     */
    void getConfig() throws IOException {
        try {
            socket = new Socket(IPaddress, port);
            Connected=true;
        } catch (Exception e){
            System.out.println("Serwer offline");
            Connected=false;
        }
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println("getConfig\n");
        pw.flush();

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String data = br.readLine();

        String[] inputinfo;
        inputinfo=data.split(";");

        ConfigData.windowWidth = Integer.parseInt(inputinfo[0]);
        System.out.println(ConfigData.windowWidth);
        ConfigData.windowHeight = Integer.parseInt(inputinfo[1]);
        ConfigData.startFuelValue = Integer.parseInt(inputinfo[2]);
        ConfigData.startSpeed[0] = Integer.parseInt(inputinfo[3]);
        ConfigData.startSpeed[1] = Integer.parseInt(inputinfo[4]);
        ConfigData.startPoint[0] = Integer.parseInt(inputinfo[5]);
        ConfigData.startPoint[1] = Integer.parseInt(inputinfo[6]);
        ConfigData.landingSpeed = Double.parseDouble(inputinfo[7]);
        ConfigData.numberOfLevelsHard = Integer.parseInt(inputinfo[8]);
        ConfigData.numberOfLevelsEasy = Integer.parseInt(inputinfo[9]);
        socket.close();
    }

    /**
     * metoda wysylajaca zadanie o wczytanie listy wynikow i po przeslaniu danych przez serwer i zapisujaca je w tablicy
     * @return tablica wyników w postaci tablicy stringow
     * @throws IOException
     */
    String getScore() throws IOException {
        try {
            socket = new Socket(IPaddress, port);
            Connected=true;
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("getScore\n");
            pw.flush();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = br.readLine();

            String[] inputinfo;
            inputinfo=data.split(";");

            StringBuilder text = new StringBuilder();
            text.append("Name:         Score:\n");
            for(int i=0;(i/2)<inputinfo.length/2;i=i+2){
                text.append(inputinfo[i]+"         "+inputinfo[i+1]+'\n');
            }
            socket.close();
            return text.toString();
        } catch (Exception e){
            System.out.println("Serwer offline");
            Connected=false;
            return "Nie udało się połączyć z serwerem";
        }


    }

    /**
     * metoda wysylajaca zadanie o wczytanie mapy z serwera po otrzymaniu  danych z serwera zapisuje je w pamieci programu
     * @param DiffLvl poziom trudnosci
     * @param mapNum numer kolejnej mapy ktora ma byc wczytana
     * @throws IOException
     */
    void getMapConfig(String DiffLvl,int mapNum) throws IOException {
        try {
            socket = new Socket(IPaddress, port);
            Connected=true;
        } catch (Exception e){
            System.out.println("Serwer offline");
            Connected=false;
        }
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println("getMapConfig;"+DiffLvl+";"+mapNum+"\n");
        pw.flush();

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String data = br.readLine();

        String[] inputinfo1;
        inputinfo1=data.split(";");
        ConfigData.pointsX1 = getDataInInt(inputinfo1[0]);
        ConfigData.pointsY1 = getDataInInt(inputinfo1[1]);
        ConfigData.landingX11 = getDataInInt(inputinfo1[2]);
        ConfigData.landingY11 = getDataInInt(inputinfo1[3]);
        ConfigData.landingX12 = getDataInInt(inputinfo1[4]);
        ConfigData.landingY12 = getDataInInt(inputinfo1[5]);
        //ConfigData.landingvXmultiplier = Integer.parseInt(inputinfo1[6]);
        //ConfigData.landingvYmultiplier = Integer.parseInt(inputinfo1[7]);
        ConfigData.FuelLoss = Integer.parseInt(inputinfo1[8]);
        socket.close();
    }

    /**
     * metoda wysylajaca na serwer zadanie zapisania wyniku oraz nick gracza i wynik ktory ma byc zapisany
     * @param name nick gracza
     * @param score wynik gracza
     * @return
     * @throws IOException
     */
    String saveScore(String name, int score) throws IOException {
        try {
            socket = new Socket(IPaddress, port);
            Connected=true;
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("saveScore;"+name+";"+score+"\n");
            pw.flush();
            socket.close();
            return null;
        } catch (Exception e){
            System.out.println("Serwer offline");
            Connected=false;
            return "Nie udało się zapisać wyniku, serwer offline";
        }

    }

    /**
     * metoda sprawzajaca polaczenie z serwerem
     */
    void CheckIfConnected() {
        try {
            socket = new Socket(IPaddress, port);
            Connected=true;
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("CheckIfConnected\n");
            pw.flush();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = br.readLine();
            if(Integer.parseInt(data)==1){
                Connected=true;
            } else{
                Connected=false;
            }
            socket.close();
        } catch (Exception e){
            System.out.println("Serwer offline");
            Connected=false;
        }

    }

    /**
     * metoda pobierajaca adres serwera od uzytkownika
     */
    boolean SetPort() throws UnknownHostException {
        JTextField IP = new JTextField(10);
        JTextField portnumber = new JTextField(5);

       portnumber.setText("10000");
       IP.setText("127.0.0.1");


        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Adres IP: "));
        myPanel.add(IP);
        myPanel.add(Box.createHorizontalStrut(10));
        myPanel.add(new JLabel("numer portu:"));
        myPanel.add(portnumber);

        int x = JOptionPane.showConfirmDialog(null, myPanel,
                "Podaj adres serwera", JOptionPane.OK_CANCEL_OPTION);
        if (x == JOptionPane.OK_OPTION) {
            port= Integer.parseInt(portnumber.getText());
            IPaddress = InetAddress.getByName(IP.getText());
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * metoda konwertujaca dane typu String na tablice typu int
     * @param data tablica typu string
     * @return tablica int
     */
    static int[] getDataInInt(String data) {
        String[] ss;
        ss = data.split(",");
        int[] number = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            number[i] = Integer.parseInt(ss[i]);
        }
        return number;
    }



}
