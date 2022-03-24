import javax.swing.*;
import java.awt.*;

public class Block extends JButton {

    private Game game;
    private boolean showed = false;
    private boolean bomb = false;
    private static Icon bombIcon;
    public static final int SIZE = 40;

    public Block(int posX, int posY, Game game){

        if(bombIcon == null)
            bombIcon = new ImageIcon("bomb.jpg");
        this.game = game;
        init(posX, posY);
    }

    public void init(int posX, int posY){
        setSize(SIZE,SIZE);
        setLocation(posX, posY);
        setFont(new Font(null,Font.BOLD,10));

        this.addActionListener(e -> {
            expose();
        });
    }

    public void expose(){

        if(showed) return;

        showed = true;


        int bombsNear;
        if(!bomb)
        {
            this.setEnabled(false);
            bombsNear = game.checkNearBlocks(this);
            if(bombsNear == 0)
            {
                game.exposeNearBlocks(this);
            }
            this.setText(bombsNear > 0 ? Integer.toString(bombsNear) : "");
        }
        else
        {
            this.setIcon(bombIcon);
            game.showAllBombs();
        }
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }
}
