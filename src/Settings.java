import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements MyWindow {

    private JButton start;
    private JTextField numberOfBombs;
    private JTextField sizeOfMap;
    private JFrame it;

    public Settings(){
        init();
    }

    @Override
    public void init() {
        it = this;
        setSize(250,200);
        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Saper - Opcje");
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    @Override
    public void initComponents() {
        start = new JButton("Start");
        start.setSize(100,20);
        start.setLocation(this.getWidth()/2 - start.getWidth()/2-8, 20);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                it.dispose();
                new Game();
            }
        });
        add(start);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        numberOfBombs = new JTextField("Ilość bomb");
        numberOfBombs.setSize(100,20);
        numberOfBombs.setLocation(this.getWidth()/2 - numberOfBombs.getWidth()/2-8, 70);
        numberOfBombs.setBorder(border);
        numberOfBombs.setHorizontalAlignment(JTextField.CENTER);
        add(numberOfBombs);

        sizeOfMap = new JTextField("Wielkość planszy");
        sizeOfMap.setSize(100,20);
        sizeOfMap.setLocation(this.getWidth()/2 - sizeOfMap.getWidth()/2-8, 110);
        sizeOfMap.setBorder(border);
        sizeOfMap.setHorizontalAlignment(JTextField.CENTER);
        add(sizeOfMap);
    }
}
