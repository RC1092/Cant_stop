import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;


public class diceImage extends JButton{
    int number;

    public diceImage(int number){
        this.number = number;
        this.setPreferredSize(new Dimension(60,60));
        this.setBackground(Color.RED);
        this.setForeground(Color.BLACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D CustomShape = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        CustomShape.setRenderingHints(hints);
        switch(this.number){
            case 1:
                CustomShape.fillOval(25,25,10,10);
            break;
                
            case 2:    
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
            break; 

            case 3:
                CustomShape.fillOval(25,25,10,10);
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
            break;    
                
            case 4:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);    
            break;

            case 5:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);
                CustomShape.fillOval(25,25,10,10);
            break;

            case 6:
                CustomShape.fillOval(10,10,10,10); 
                CustomShape.fillOval(40,40,10,10);
                CustomShape.fillOval(10,25,10,10); 
                CustomShape.fillOval(40,25,10,10);
                CustomShape.fillOval(10,40,10,10); 
                CustomShape.fillOval(40,10,10,10);    
            break;

            }
    }

/*     public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new FlowLayout());
        diceImage d1 = new diceImage(1);
        diceImage d2 = new diceImage(2);
        diceImage d3 = new diceImage(3);
        diceImage d4 = new diceImage(4);
        diceImage d5 = new diceImage(5);
        diceImage d6 = new diceImage(6);
        panel.add(d1);
        panel.add(d2);
        panel.add(d3);
        panel.add(d4);
        panel.add(d5);
        panel.add(d6);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    } */
}
