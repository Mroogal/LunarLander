
import java.io.IOException;
import javax.swing.*;


class Main {
        public static Client client;

        public static void main(String[] args) throws IOException {

                client = new Client();

                        Object[] options = {"Lokalne", "Serwer"};
                        int x = JOptionPane.showOptionDialog(null, "Wybierz skąd załadować pliki kofiguracyjne",
                                "Wczytanie plików",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                        if (x == 0) {
                                ConfigLoad.configStart();
                                client.Connected = false;
                        } else if (x == 1) {

                                if (client.SetPort()==false)
                                {
                                        System.exit(3);
                                }
                                try {
                                        client.CheckIfConnected();
                                } catch (Exception h) {

                                }
                                if (client.Connected == true) {
                                        try {
                                                client.getConfig();
                                        } catch (IOException e) {

                                        }
                                } else if (client.Connected == false) {
                                        ConfigLoad.configStart();
                                        int odp = JOptionPane.showConfirmDialog(null, "Serwer niedostępny, użyto lokalnej konfiguracji", "Serwer niedostępny", JOptionPane.DEFAULT_OPTION);
                                }
                                System.out.println(6);
                        } else {
                                System.exit(2);
                        }

                        System.out.println(client.Connected);


                        MainWindow mainwindow = new MainWindow();
                        mainwindow.setVisible(true);
                }

}
