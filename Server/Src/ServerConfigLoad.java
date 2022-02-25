import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Klasa ladujaca dane konfiguracyjne serwera (wymiary okna serwera i nr portu)
 */
public class ServerConfigLoad{
    /**
     * Metoda ladujaca dane konfiguracyjne serwera z pliku textowego
     * @throws IOException
     */
    public static void loadServerData() throws IOException {
        URL url=ConfigLoad.class.getResource("configserver/server_config.txt");
        System.out.println(url);
        InputStream file = url.openStream();
        Properties ServerConfig = new Properties();
        ServerConfig.load(file);

        ServerConfigData.port = Integer.parseInt(ServerConfig.getProperty("Port"));
        ServerConfigData.windowWidth = Integer.parseInt(ServerConfig.getProperty("WindowWidth"));
        ServerConfigData.windowHeight = Integer.parseInt(ServerConfig.getProperty("WindowHeight"));

        file.close();
    }
}
