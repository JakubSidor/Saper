import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements MyWindow {

    private JButton start;
    private JButton exit;
    private JTextField numberOfBombs;
    private JTextField sizeOfMap;
    private Settings it;

    public Settings(){
        init();
    }

    @Override
    public void init() {
        it = this;
        setSize(250,220);
        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Start");
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    @Override
    public void initComponents() {
        start = new JButton("Start");
        start.setSize(100,20);
        start.setLocation(this.getWidth()/2 - start.getWidth()/2-8, 20);
        start.addActionListener(e -> {
            try {

                if(Integer.parseInt(sizeOfMap.getText()) < 6)
                {
                    JOptionPane.showMessageDialog(it, "Wielkość mapy musi być równa lub większa 6!","Błąd wprowadzania", 0);
                    return;
                }
                if(Integer.parseInt(numberOfBombs.getText()) >= Integer.parseInt(sizeOfMap.getText()) * Integer.parseInt(sizeOfMap.getText()))
                {
                    JOptionPane.showMessageDialog(it, "Ilość bomb nie może być większa niż ilość dostępnych pól!","Błąd wprowadzania", 0);
                    return;
                }

                new Game(it,
                        Integer.parseInt(numberOfBombs.getText()),
                        Integer.parseInt(sizeOfMap.getText())
                );
                it.setVisible(false);
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(it,"Wprowadzono błędne wartości!\nProszę wprowadzić same liczby!","Błąd wprowadzania", 0);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(it, "Napotkano nieznany bląd!","Błąd", 0);
            }
        });
        add(start);

        exit = new JButton("Wyjście");
        exit.setSize(100,20);
        exit.setLocation(this.getWidth()/2 - exit.getWidth()/2-8, 140);
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        numberOfBombs = new JTextField("Ilość bomb");
        numberOfBombs.setSize(100,20);
        numberOfBombs.setLocation(this.getWidth()/2 - numberOfBombs.getWidth()/2-8, 60);
        numberOfBombs.setBorder(border);
        numberOfBombs.setHorizontalAlignment(JTextField.CENTER);
        add(numberOfBombs);

        sizeOfMap = new JTextField("Wielkość planszy");
        sizeOfMap.setSize(100,20);
        sizeOfMap.setLocation(this.getWidth()/2 - sizeOfMap.getWidth()/2-8, 100);
        sizeOfMap.setBorder(border);
        sizeOfMap.setHorizontalAlignment(JTextField.CENTER);
        add(sizeOfMap);
    }
}
