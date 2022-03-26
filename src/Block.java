import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Block extends JButton {

    private Game game;
    private boolean showed = false;
    private boolean bomb = false;
    private boolean indicated = false;
    private static Icon bombIcon;
    private static Icon indicatorIcon;
    public static final int SIZE = 40;

    public Block(int posX, int posY, Game game){

        if(bombIcon == null)
            bombIcon = new ImageIcon("bomb.jpg");

        if(indicatorIcon == null)
            indicatorIcon = new ImageIcon("indicator.jpg");

        this.game = game;
        init(posX, posY);
    }

    public void init(int posX, int posY){
        setSize(SIZE,SIZE);
        setLocation(posX, posY);
        setFont(new Font(null,Font.BOLD,10));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && !indicated)
                {
                    expose();
                }
                else if (e.getButton() == MouseEvent.BUTTON3)
                {
                    placeIndicator();
                }
            }
        });
    }

    public void placeIndicator(){
        if(indicated) {
            indicated = false;
            this.setIcon(null);
        }
        else
        {
            //game.useIndicator();
            indicated = true;
            this.setIcon(indicatorIcon);
        }
    }

    public void expose(){

        if(showed) return;

        showed = true;

        int bombsNear;
        if(!bomb)
        {
            game.addToPoints(10);
            this.setEnabled(false);
            bombsNear = game.checkNearBlocks(this);
            if(bombsNear == 0)
            {
                game.exposeNearBlocks(this);
            }
            this.setText(bombsNear > 0 ? Integer.toString(bombsNear) : "");

            if(game.isWin() && !game.isGameover())
            {
                game.setGameover(true);
                game.showAllBombs();
                JOptionPane.showMessageDialog(this, "Gratulacje! Odsłoniłeś wszystkie pola bez zdetonowania bomby\nIlość zdobytych punktów: " + game.getScore(),"Wygrana!",1);
                game.dispose();
                game.getSettings().setVisible(true);
            }
        }
        else
        {
            this.setIcon(bombIcon);

            if(!game.isGameover()) {
                game.setGameover(true);
                game.showAllBombs();
                JOptionPane.showMessageDialog(this, "Zdentonowałeś bombę!\nIlość zdobytych punktów: " + game.getScore(),"Przegrana!",1);
                game.dispose();
                game.getSettings().setVisible(true);
            }
        }
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isShowed() {
        return showed;
    }
}
