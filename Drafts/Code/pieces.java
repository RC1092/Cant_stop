import javax.swing.*;
import java.awt.*;



public class pieces extends JButton{
    private String shape;
    private String color;
    public pieces(String shape, String color){
        this.setPreferredSize(new Dimension(120,120));
        this.shape = shape;
        this.color = color;
        this.setText(color);
        this.setBackground(Color.white);
        this.setForeground(Color.getColor(color,Color.BLACK));
        setColor();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D CustomShape = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        CustomShape.setRenderingHints(hints);
        switch(this.shape){
            case "Square":
                CustomShape.fillRect(0,0, this.getWidth(), this.getHeight());
            break;
                
            case "Triangle":    
                int[] XCoords1 = {0, this.getWidth()/2, this.getWidth()}; 
                int[] YCoords1 = {this.getHeight(), 0, this.getHeight()}; 
                CustomShape.fillPolygon(XCoords1, YCoords1, 3);    
                break; 

            case "Circle":
                CustomShape.fillOval(0,0,this.getWidth()-2,this.getHeight()-2);
                break;    
                
            case "Star":
                int[] XCoords2 = {0,                  6*this.getWidth()/14,     this.getWidth()/2, 8*this.getWidth()/14,  this.getWidth(),    8*this.getWidth()/14, this.getWidth()/2, 6*this.getWidth()/14,};
                int[] YCoords2 = {this.getHeight()/2,8*this.getHeight()/14,  this.getHeight(),  8*this.getHeight()/14, this.getHeight()/2, 6*this.getHeight()/14,   0,                 6*this.getHeight()/14};
                CustomShape.fillPolygon(XCoords2, YCoords2, 8);
                break;
            }
    }

    private void setColor(){
        switch(this.color){
            case "Blue":
                this.setForeground(Color.BLUE);
                break;
            case "Yellow":
                this.setForeground(Color.YELLOW); 
                break;
            case "Green":
                this.setForeground(Color.GREEN);
                break;
            case "Pink":
                this.setForeground(Color.PINK);
                break;
            case "Orange":
                this.setForeground(Color.ORANGE);
                break;
            case "Purple":
                this.setForeground(new Color(255,0,255));
                break;
            

        }
    }

}