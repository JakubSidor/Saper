import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements MyWindow {

    public Game(){
        init();
    }

    @Override
    public void init() {
        setSize(450,300);
        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Saper");
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    @Override
    public void initComponents() {

    }

}
