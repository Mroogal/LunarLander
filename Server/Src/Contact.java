import java.io.*;
import java.net.Socket;

/**
 * Klasa odpowiada za logike odpowiedzi serwera, na zadania klineta
 */
public class Contact implements Runnable{
    /**
     * socket używany do komunikacji
     */
    private Socket socket;

    /**
     * Konstruktor klasy
     * @param socket
     */
    public Contact(Socket socket){
        this.socket=socket;
    }

    /**
     * Metoda obslugiwana przez Thread
     * przetwarza zadanie klienta i w zaleznosci od niego wysyla odpowiedz
     */
    @Override
    public void run(){
        try {
            //wczytywanie żądania klienta i rzutowanie go na obiekt klasy String
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = br.readLine();

            //tworzenie Stream do odsylania danych
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);

            //dzieli żądanie klienta na pojedyncze elementy
            String[] inputinfo;
            System.out.println(data);
            inputinfo = data.split(";");
            System.out.println(inputinfo);

            // dodawanie do tablicy commands kolejnych komend
            LunarServer.commands = LunarServer.commands + inputinfo[0] + "\n";
            LunarServer.text.setText(LunarServer.commands);

            switch (inputinfo[0]) {
                case "getConfig": {
                    System.out.println(inputinfo[0]);
                    ConfigLoadGame loader = new ConfigLoadGame();
                    try {
                        loader.configStart();
                    } catch (IOException e) {
                        System.out.println("Nie udalo załadować konfiguracji");
                    }

                    String sent = ConfigDataServer.windowWidth + ";" +
                            ConfigDataServer.windowHeight + ";" +
                            ConfigDataServer.startFuelValue + ";" +
                            ConfigDataServer.startSpeed[0] + ";" +
                            ConfigDataServer.startSpeed[1] + ";" +
                            ConfigDataServer.startPoint[0] + ";" +
                            ConfigDataServer.startPoint[1] + ";" +
                            ConfigDataServer.landingSpeed + ";" +
                            ConfigDataServer.numberOfLevelsHard +";"+
                            ConfigDataServer.numberOfLevelsEasy+"\n";
                    pw.println(sent);
                    pw.flush();
                    break;
                }
                case "getScore": {
                    System.out.println(inputinfo[0]);
                    Scoreboard2 scoreboard = new Scoreboard2();
                    scoreboard.loadScores();
                    pw.println(scoreboard.show());
                    pw.flush();
                    break;
                }
                case "getMapConfig": {
                    System.out.println(inputinfo[0]);
                    ConfigLoadGame loader = new ConfigLoadGame();
                    try {
                        loader.configMap(inputinfo[1], Integer.parseInt(inputinfo[2]));
                    } catch (IOException e) {
                        System.out.println("Nie udalo załadować konfiguracji map");
                    }
                    String sent = getDataInString(ConfigDataServer.pointsX1) + ";" +
                            getDataInString(ConfigDataServer.pointsY1) + ";" +
                            getDataInString(ConfigDataServer.landingX11) + ";" +
                            getDataInString(ConfigDataServer.landingY11) + ";" +
                            getDataInString(ConfigDataServer.landingX12) + ";" +
                            getDataInString(ConfigDataServer.landingY12) + ";" +
                            ConfigDataServer.landingvXmultiplier + ";" +
                            ConfigDataServer.landingvYmultiplier + ";" +
                            ConfigDataServer.FuelLoss;
                    pw.println(sent);
                    pw.flush();
                    break;
                }
                case "saveScore": {
                    System.out.println(inputinfo[0]);
                    Scoreboard2 scoreboard = new Scoreboard2();
                    scoreboard.loadScores();
                    scoreboard.addScores(inputinfo[1], Integer.parseInt(inputinfo[2]));
                    scoreboard.sort();
                    scoreboard.saveScores();
                    LunarServer.commands = LunarServer.commands + "Zapisano score" + "\n";
                    LunarServer.text.setText(LunarServer.commands);
                    break;
                }
                case "CheckIfConnected": {
                    System.out.println(inputinfo[0]);
                    pw.println(1);
                    pw.flush();
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Wyjatek");
        }

    }

    /**
     * Metoda konwertujaca typ tablica int na typ String
     * @param data tablica typu int
     * @return obiekt typu String
     */
    static String getDataInString(int[] data){
        StringBuilder text = new StringBuilder();
        text.append(data[0]);
        for(int i =1;i<data.length;i++){
            text.append(","+data[i]);
        }
        return text.toString();
    }
}
