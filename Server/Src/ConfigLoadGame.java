import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Arrays;


/**
 Klasa wczytujaca pliki konfiguracyjne
 */
 class ConfigLoadGame {

    /**
     * Metoda wczytujaca dane z pliku konfiguracyjnego i zapisujace je do pol w ConfigData
     * @throws IOException
     */
    static void configStart() throws IOException
    {
        URL url=ConfigLoad.class.getResource("configserver/config_start.txt");
        InputStream file = url.openStream();
        Properties config = new Properties();
        config.load(file);

        ConfigDataServer.windowWidth = Integer.parseInt(config.getProperty("WindowWidth"));
        ConfigDataServer.windowHeight = Integer.parseInt(config.getProperty("WindowHeight"));
        ConfigDataServer.startFuelValue = Integer.parseInt(config.getProperty("StartFuelValue"));
        ConfigDataServer.startSpeed[0] = Integer.parseInt(config.getProperty("StartVX"));
        ConfigDataServer.startSpeed[1] = Integer.parseInt(config.getProperty("StartVY"));
        ConfigDataServer.startPoint[0] = Integer.parseInt(config.getProperty("StartXPoint"));
        ConfigDataServer.startPoint[1] = Integer.parseInt(config.getProperty("StartYPoint"));
        ConfigDataServer.landingSpeed = Double.parseDouble(config.getProperty("LandingV"));
        ConfigDataServer.numberOfLevelsHard = Integer.parseInt(config.getProperty("LevelsHard"));
        ConfigDataServer.numberOfLevelsEasy = Integer.parseInt(config.getProperty("LevelsEasy"));


        file.close();

    }

     /**
      Metoda wczytujaca poziom w zaleznosci od poziomu trudnosci
     @param lvlOfDifficulty poziom trudnosci
      @throws IOException
     */
     static void configMap(String lvlOfDifficulty, int mapNumber) throws IOException
     {
         URL url=ConfigLoad.class.getResource("configserver/config_map.txt");
         System.out.println(url);
         InputStream file = url.openStream();
         Properties configMap = new Properties();
         configMap.load(file);

         ConfigDataServer.pointsX1 = Arrays.stream(configMap.getProperty("PointsX" + lvlOfDifficulty+mapNumber).split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigDataServer.pointsY1 = Arrays.stream(configMap.getProperty("PointsY" + lvlOfDifficulty+mapNumber).split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigDataServer.landingX11 = Arrays.stream(configMap.getProperty("LandingX" + lvlOfDifficulty+mapNumber+"1").split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigDataServer.landingY11 = Arrays.stream(configMap.getProperty("LandingY" + lvlOfDifficulty+mapNumber+"1").split(" ")).mapToInt(Integer::parseInt).toArray();

         ConfigDataServer.landingX12 = Arrays.stream(configMap.getProperty("LandingX" + lvlOfDifficulty+mapNumber+"2").split(" ")).mapToInt(Integer::parseInt).toArray();
         ConfigDataServer.landingY12 = Arrays.stream(configMap.getProperty("LandingY" + lvlOfDifficulty+mapNumber+"2").split(" ")).mapToInt(Integer::parseInt).toArray();

         ConfigDataServer.landingvXmultiplier = Double.parseDouble(configMap.getProperty("LandingVXMultiplier" + lvlOfDifficulty));
         ConfigDataServer.landingvYmultiplier = Double.parseDouble(configMap.getProperty("LandingVYMultiplier" + lvlOfDifficulty));
         ConfigDataServer.FuelLoss = Integer.parseInt(configMap.getProperty("FuelLoss"+ lvlOfDifficulty));

         file.close();
     }




 }