import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 Klasa ktora reprezentująca guziki okna glownego
 */
public class MenuOptions extends JPanel implements ActionListener {
    /**
     Panel do ktorego dodawane sa guziki
     */
    public static JPanel subpanelRight;
    /**
     Guziki w menu glownym
     */
    public JButton buttonSterowanie,buttonExit,buttonCheck;

    /**
     Konstruktor klasy MenuOptions
     */
    public MenuOptions() {
        subpanelRight = new JPanel( new GridLayout(20,1));
        subpanelRight.setOpaque(false);
        subpanelRight.setFocusable(false);
        subpanelRight.setLayout(new BoxLayout(subpanelRight,BoxLayout.Y_AXIS));
        subpanelRight.add(Box.createVerticalStrut(200));

        buttonSterowanie = new JButton("Sterowanie");
        buttonSterowanie.setFocusable(false);
        buttonSterowanie.setBounds(50,50,100,50);

        subpanelRight.add(buttonSterowanie,2,1);
        buttonSterowanie.setPreferredSize(new Dimension(150,30));
        buttonSterowanie.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonCheck = new JButton("Połącz z serwerem");
        buttonCheck.setFocusable(false);
        subpanelRight.add(buttonCheck,BorderLayout.PAGE_END);
        buttonCheck.setPreferredSize(new Dimension(150,30));
        buttonCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonExit = new JButton("EXIT");
        buttonExit.setFocusable(false);
        subpanelRight.add(buttonExit,BorderLayout.PAGE_END);
        buttonExit.setPreferredSize(new Dimension(150,30));
        buttonExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonSterowanie.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonCheck.addActionListener(this);
        add(subpanelRight, BorderLayout.LINE_END);
        this.setOpaque(false);
        this.setVisible(false);
    }
    /**
     Metoda odpowiadajaca za wykonywanie akcji przez przyciski
     @param e Reprezentuje akcje wykonane przez guziki
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonSterowanie) {
            Object[] options = {"Strzałki", "AWSD"};
            int x = JOptionPane.showOptionDialog(null, "Wybierz sposob sterowania",
                    "Sterowanie",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (x == 0) {
                System.out.println("Strzałki");
                MainWindow.game.boardgame.spaceShip.control=0;

            } else if(x==1) {
                System.out.println("AWSD");
                MainWindow.game.boardgame.spaceShip.control=1;
            }
        }
        else if (source == buttonExit)
        {
            this.setVisible(false);
            MainWindow.mainmenu.menu.setVisible(true);
        }
        else if (source == buttonCheck)
        {
            try {
                boolean value = Main.client.SetPort();

            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            }
            try{
            Main.client.CheckIfConnected();
            } catch(Exception m){
            }
            if(Main.client.Connected==false){
                JOptionPane.showMessageDialog(this, "Nie udało się połączyć z serwerem","Połączenie",JOptionPane.INFORMATION_MESSAGE);
            } else if(Main.client.Connected==true){

                JOptionPane.showMessageDialog(this, "Udało się nawiązać połączenie","Połączenie",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
