import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Klasa ktora reprezentująca pause menu
 */
public class PauseMenu extends JPanel implements ActionListener {
    /**
     Panel do ktorego dodawane sa guziki
     */
    public static JPanel subpanelPause;
    /**
     Guziki w pause menu
     */
    public static JButton buttonContinue,buttonBack,buttonOption;

    /**
     Konstruktor klasy PauseMenu
     */
    public PauseMenu(){
            subpanelPause = new JPanel( new GridLayout(20,1));
            subpanelPause.setOpaque(false);
            subpanelPause.setFocusable(false);
            subpanelPause.setLayout(new BoxLayout(subpanelPause,BoxLayout.Y_AXIS));
            subpanelPause.add(Box.createVerticalStrut(200));

            buttonContinue = new JButton("Kontynuuj");
            buttonContinue.setFocusable(false);
            buttonContinue.setBounds(50,50,100,50);

            subpanelPause.add(buttonContinue,2,1);
            buttonContinue.setPreferredSize(new Dimension(150,30));
            buttonContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
            subpanelPause.add(Box.createVerticalStrut(40));

            buttonOption = new JButton("Sterowanie");
            buttonOption.setFocusable(false);
            subpanelPause.add(buttonOption,BorderLayout.PAGE_END);
            buttonOption.setPreferredSize(new Dimension(150,30));
            buttonOption.setAlignmentX(Component.CENTER_ALIGNMENT);
            subpanelPause.add(Box.createVerticalStrut(40));

            buttonBack = new JButton("Wyjście");
            buttonBack.setFocusable(false);
            subpanelPause.add(buttonBack,BorderLayout.PAGE_END);
            buttonBack.setPreferredSize(new Dimension(150,30));
            buttonBack.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonContinue.addActionListener(this);
            buttonBack.addActionListener(this);
            buttonOption.addActionListener(this);

            add(subpanelPause, BorderLayout.LINE_END);
            setOpaque(false);
    }

    /**
     Metoda odpowiadajaca za wykonywanie akcji przez przyciski
     @param e Reprezentuje akcje wykonane przez guziki
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == buttonBack){
            GameBoard.started=false;
            MainWindow.mainmenu.setVisible(true);
            setVisible(false);
        }
        else if(source == buttonContinue){
            GameBoard.started=true;
            this.setVisible(false);
            this.setFocusable(true);
        }
        else if(source == buttonOption){
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
    }
}
