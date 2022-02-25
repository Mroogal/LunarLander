import java.io.*;
import java.net.InetAddress;

/**
 * Klasa Main serwera
 */
public class ServerMain {
    /**
     * metoda main tworzy obiekt LunarServer i wywoluje jego metode Server
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)  throws IOException {
        LunarServer server = new LunarServer();
        System.out.println("IP Address: " + InetAddress.getLocalHost());
        System.out.println("Port: " + server.port);
        server.Server();
        }
    }

