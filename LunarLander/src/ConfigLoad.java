import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Arrays;



/**
 Klasa wczytujaca pliki konfiguracyjne
 */
class ConfigLoad {

    /**
     * Metoda wczytujaca dane z pliku konfiguracyjnego i zapisujace je do pol w ConfigData
     * @throws IOException
     */
    static void configStart() throws IOException
    {
        //Uzyskujemy aktualny adres sciezki dostepu do szukanego pliku, który jest podany względem tej klasy. Jest to metoda
        //class
        URL url=ConfigLoad.class.getResource("config/config_start.txt");
        System.out.println(url);
        InputStream file = url.openStream();
        Properties config = new Properties();
        config.load(file);

        ConfigData.windowWidth = Integer.parseInt(config.getProperty("WindowWidth"));
        ConfigData.windowHeight = Integer.parseInt(config.getProperty("WindowHeight"));
        ConfigData.startFuelValue = Integer.parseInt(config.getProperty("StartFuelValue"));
        ConfigData.startSpeed[0] = Integer.parseInt(config.getProperty("StartVX"));
        ConfigData.startSpeed[1] = Integer.parseInt(config.getProperty("StartVY"));
        ConfigData.startPoint[0] = Integer.parseInt(config.getProperty("StartXPoint"));
        ConfigData.startPoint[1] = Integer.parseInt(config.getProperty("StartYPoint"));
        ConfigData.landingSpeed = Double.parseDouble(config.getProperty("LandingV"));
        ConfigData.numberOfLevelsHard = Integer.parseInt(config.getProperty("LevelsHard"));
        ConfigData.numberOfLevelsEasy = Integer.parseInt(config.getProperty("LevelsEasy"));

        file.close();

    }

    /**
     Metoda wczytujaca poziom w zaleznosci od poziomu trudnosci
     @param lvlOfDifficulty poziom trudnosci
     @throws IOException
     */
     static void configMap(String lvlOfDifficulty, int mapNumber) throws IOException
     {
         URL url=ConfigLoad.class.getResource("config/config_map.txt");
         InputStream file = url.openStream();
         Properties configMap = new Properties();
         configMap.load(file);

         ConfigData.pointsX1 = Arrays.stream(configMap.getProperty("PointsX" + lvlOfDifficulty+mapNumber).split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigData.pointsY1 = Arrays.stream(configMap.getProperty("PointsY" + lvlOfDifficulty+mapNumber).split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigData.landingX11 = Arrays.stream(configMap.getProperty("LandingX" + lvlOfDifficulty+mapNumber+"1").split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigData.landingY11 = Arrays.stream(configMap.getProperty("LandingY" + lvlOfDifficulty+mapNumber+"1").split(" ")).mapToInt(Integer::parseInt).toArray();

         ConfigData.landingX12 = Arrays.stream(configMap.getProperty("LandingX" + lvlOfDifficulty+mapNumber+"2").split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigData.landingY12 = Arrays.stream(configMap.getProperty("LandingY" + lvlOfDifficulty+mapNumber+"2").split(" ")).mapToInt(Integer::parseInt).toArray();

         ConfigData.landingvXmultiplier = Double.parseDouble(configMap.getProperty("LandingVXMultiplier" + lvlOfDifficulty));
         ConfigData.landingvYmultiplier = Double.parseDouble(configMap.getProperty("LandingVYMultiplier" + lvlOfDifficulty));
         ConfigData.FuelLoss = Integer.parseInt(configMap.getProperty("FuelLoss"+ lvlOfDifficulty));

         file.close();
     }



 }