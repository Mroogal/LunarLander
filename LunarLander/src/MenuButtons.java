import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 Klasa ktora reprezentująca guziki okna glownego
 */
public class MenuButtons extends JPanel implements ActionListener {
    /**
     Panel do ktorego dodawane sa guziki
     */
    public static JPanel subpanelRight;
    /**
     Guziki w menu glownym
     */
    public JButton buttonStart, buttonTworcy, buttonWyniki, buttonExit, buttonOpcje;

    /**
     Konstruktor klasy MenuButtons
     */
    public MenuButtons() {
        subpanelRight = new JPanel( new GridLayout(20,1));
        subpanelRight.setOpaque(false);
        subpanelRight.setFocusable(false);
        subpanelRight.setLayout(new BoxLayout(subpanelRight,BoxLayout.Y_AXIS));
        subpanelRight.add(Box.createVerticalStrut(200));

        buttonStart = new JButton("START");
        buttonStart.setFocusable(false);
        buttonStart.setBounds(50,50,100,50);

        subpanelRight.add(buttonStart,2,1);
        buttonStart.setPreferredSize(new Dimension(150,30));
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonWyniki = new JButton("WYNIKI");
        subpanelRight.add(buttonWyniki);
        buttonWyniki.setPreferredSize(new Dimension(150,30));
        buttonWyniki.setFocusable(false);
        buttonWyniki.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonOpcje = new JButton("Opcje");
        subpanelRight.add(buttonOpcje);
        buttonOpcje.setPreferredSize(new Dimension(150,30));
        buttonOpcje.setFocusable(false);
        buttonOpcje.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonTworcy = new JButton("TWORCY");
        buttonTworcy.setFocusable(false);
        subpanelRight.add(buttonTworcy,BorderLayout.LINE_END);
        buttonTworcy.setPreferredSize(new Dimension(150,30));
        buttonTworcy.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelRight.add(Box.createVerticalStrut(40));

        buttonExit = new JButton("EXIT");
        buttonExit.setFocusable(false);
        subpanelRight.add(buttonExit,BorderLayout.PAGE_END);
        buttonExit.setPreferredSize(new Dimension(150,30));
        buttonExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonStart.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonTworcy.addActionListener(this);
        buttonWyniki.addActionListener(this);
        buttonOpcje.addActionListener(this);
        add(subpanelRight, BorderLayout.LINE_END);
        this.setOpaque(false);
    }
    /**
     Metoda odpowiadajaca za wykonywanie akcji przez przyciski
     @param e Reprezentuje akcje wykonane przez guziki
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonStart) {
            Object[] options = {"Easy", "Hard"};
            int x = JOptionPane.showOptionDialog(this, "Wybierz poziom trudności:",
                    "Poziom trudności",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (x == 0) {

                GameBoard.DiffLvl = "Easy";

                EventQueue.invokeLater(() -> {
                    MainWindow.game.setVisible(true);
                    MainWindow.game.boardgame.NewGame();
                    MainWindow.mainmenu.setVisible(false);

                });
            } else if (x==1) {

                GameBoard.DiffLvl = "Hard";
                EventQueue.invokeLater(() -> {
                    MainWindow.game.setVisible(true);
                    MainWindow.game.boardgame.NewGame();
                    MainWindow.mainmenu.setVisible(false);

                });
            }
        }

        else if (source == buttonExit)
        {
            int odp = JOptionPane.showConfirmDialog(this,"Czy na pewno chcesz wyjsc?","EXIT",JOptionPane.YES_NO_OPTION);
            {
                if(odp ==JOptionPane.YES_OPTION)
                {
                    System.exit(23);
                }
            }
        }
        else if (source == buttonTworcy)
        {
            JOptionPane.showMessageDialog(this, "Autorzy:\n Bartosz Iwaniuk\n Maciej Rogaliński \n\n v2.0 All rights reserved","TWORCY",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (source == buttonWyniki){
            try {
                JOptionPane.showMessageDialog(this,Main.client.getScore(),"Wyniki",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException k){
            }
        }
        else if(source ==buttonOpcje){
            this.setVisible(false);
            MainWindow.mainmenu.options.setVisible(true);

        }
    }
}
