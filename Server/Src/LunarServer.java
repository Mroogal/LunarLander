import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  Reprezentuje okno glowne servera, oraz petle dzialania serwera
 */

public class LunarServer extends JFrame {

    /**
     * Domyslne wymiary okna serwera
     */
    public int currentScreenWidth, currentScreenHeight;
    /**
     *Przechowuje text wyswietlany na ekranie
     */
    static String commands;
    /**
     * Panel, w korym wyswietlany jest tekst
     */
    static JTextArea text;

    /**
     * Zmienna przechowujaca nr portu
     */
    int port;

    /**
     * Zmienna reprezentujaca socket serwera
     */
    public static ServerSocket Ssocket;

    /**
     * konstruktor klasy LunarServer, pobiera z pliku dane konfiguracyjne, tworzy okno serwera, wyswietla pole tekstowe JTextArea,
     * wyswietla adres IP serwera oraz port
     * @throws IOException
     */
    public LunarServer() throws IOException {
        ServerConfigLoad.loadServerData();
        setTitle("Serwer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        currentScreenWidth = ServerConfigData.windowWidth;
        currentScreenHeight = ServerConfigData.windowHeight;
        setVisible(true);
        this.setSize(this.currentScreenWidth, this.currentScreenHeight);

        text=new JTextArea();
        text.setSize(this.currentScreenWidth, this.currentScreenHeight);
        this.add(text);

        port=ServerConfigData.port;
        commands="IP Address: " + InetAddress.getLocalHost()+"\n";
        commands=commands+"Port: " + this.port+"\n \n";
        text.setText(LunarServer.commands);
    }

    /**
     * Metoda implementujaca podstawowe funkcje serwera
     * @throws IOException
     */
    public void Server() throws IOException{
        Ssocket = new ServerSocket(port);
        //petla czeka na linijce socketaccept na komende, gdy ja dostanie tworzy nowy Thread
        while(true){
            Socket socket = Ssocket.accept();
            new Thread(new Contact(socket)).start();
        }
    }
}
