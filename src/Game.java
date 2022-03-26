import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame implements MyWindow {

    private Settings settings;

    private int numberOfBombs;
    private int sizeOfMap;

    private static final int topPanelSize = 90;
    private JTextField jtfScore;
    private JTextField jtfBombs;
    private JTextField jtfTimer;
    private JButton menu;
    private int score;
    private int numberOfIndicators = 10;
    private boolean gameover = false;

    private Block[][] blocks_arr;

    public Game(Settings settings, int numberOfBombs, int sizeOfMap){
        this.numberOfBombs = numberOfBombs;
        this.sizeOfMap = sizeOfMap;
        this.settings = settings;
        init();
    }

    @Override
    public void init() {
        setSize((sizeOfMap) * Block.SIZE + 14,(sizeOfMap + 1) * Block.SIZE + topPanelSize);
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

        blocks_arr = new Block[sizeOfMap][sizeOfMap];

        for(int i = 0; i < sizeOfMap; i++)
        {
            for(int j = 0; j < sizeOfMap; j++)
            {
                Block newBlock = new Block(i * Block.SIZE, j * Block.SIZE + topPanelSize, this);
                blocks_arr[i][j] = newBlock;
                this.add(newBlock);
            }
        }

        for(int i = 0 ; i < numberOfBombs; i++)
        {
            Block block = blocks_arr[new Random().nextInt(Integer.MAX_VALUE) % sizeOfMap][new Random().nextInt(Integer.MAX_VALUE) % sizeOfMap];
            if(block.isBomb())
                i--;
            else
                block.setBomb(true);
        }

        jtfScore = new JTextField("Punkty:"+ score);
        jtfScore.setSize(100,20);
        jtfScore.setLocation(20,10);
        jtfScore.setEditable(false);
        add(jtfScore);

        jtfBombs = new JTextField("Bomby:"+ numberOfBombs);
        jtfBombs.setSize(100,20);
        jtfBombs.setLocation(20,35);
        jtfBombs.setEditable(false);
        add(jtfBombs);

        jtfTimer = new JTextField("Czas:");
        jtfTimer.setSize(100,20);
        jtfTimer.setLocation(20,60);
        jtfTimer.setEditable(false);
        new Thread(() -> {
            int timer = 0;
            while (true && !gameover) { timer++; jtfTimer.setText("Czas: " + timer + " s.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}).start();
        add(jtfTimer);

        menu = new JButton("Menu");
        menu.setSize(80, 20);
        menu.setLocation(150,35);
        menu.addActionListener(e->{
            this.dispose();
            settings.setVisible(true);
        });
        add(menu);
    }

    //zwraca ilość bomb dookoła
    public int checkNearBlocks(Block block){
        int counter = 0;
        for(int i = 0; i < sizeOfMap; i++)
        {
            for(int j = 0; j < sizeOfMap; j++)
            {
                if(blocks_arr[i][j] == block)
                {

                    //nad aktualnym blokiem
                    if(i > 0 && blocks_arr[i - 1][j].isBomb())
                    {
                        counter ++;
                    }
                    //pod aktualnym blokiem
                    if(i + 1 < sizeOfMap && blocks_arr[i + 1][j].isBomb())
                    {
                        counter ++;
                    }
                    //po lewej od aktualnego bloku
                    if(j > 0 && blocks_arr[i][j - 1].isBomb())
                    {
                        counter ++;
                    }
                    //po prawej od aktualnego bloku
                    if(j + 1 < sizeOfMap && blocks_arr[i][j + 1].isBomb())
                    {
                        counter ++;
                    }
                }
            }
        }
        return counter;
    }

    //sprawdze pozostałe bloki dookoła takiego bez bomby
    public void exposeNearBlocks(Block block){
        for(int i = 0; i < sizeOfMap; i++)
        {
            for(int j = 0; j < sizeOfMap; j++)
            {
                if(blocks_arr[i][j] == block)
                {

                    //nad aktualnym blokiem
                    if(i > 0)
                    {
                        blocks_arr[i - 1][j].expose();
                    }
                    //pod aktualnym blokiem
                    if(i + 1 < sizeOfMap)
                    {
                        blocks_arr[i + 1][j].expose();
                    }
                    //po lewej od aktualnego bloku
                    if(j > 0)
                    {
                        blocks_arr[i][j - 1].expose();
                    }
                    //po prawej od aktualnego bloku
                    if(j + 1 < sizeOfMap)
                    {
                        blocks_arr[i][j + 1].expose();
                    }
                }
            }
        }
    }

    public void showAllBombs() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if(blocks_arr[i][j].isBomb())
                blocks_arr[i][j].expose();
                else blocks_arr[i][j].setEnabled(false);
            }
        }
    }

    public boolean isWin(){
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if(!blocks_arr[i][j].isBomb() && !blocks_arr[i][j].isShowed())
                    return false;
            }
        }
        return true;
    }

    public void addToPoints(int add){
        score += add;
        jtfScore.setText("Punkty:" + score);
    }

    public int getScore(){
        return score;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public Settings getSettings() {
        return settings;
    }

    public void useIndicator(){
        numberOfIndicators --;
    }
}
