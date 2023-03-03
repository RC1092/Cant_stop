import javax.swing.*;
import java.awt.*;
import java.awt.GradientPaint;



public class Tile extends JButton {
    private int x, y;

    public Tile(int x, int y, boolean gameTile){
        this.x = x;
        this.y = y;
        setOpaque(true);
        if (gameTile){
            this.setBackground(Color.red);
            this.setBorder(BorderFactory.createLineBorder(Color.white));
        }
        else {
            this.setVisible(false);
            }
    }
}
