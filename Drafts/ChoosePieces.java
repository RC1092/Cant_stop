import javax.swing.*;
import java.awt.*;

public class ChoosePieces extends JFrame {
    public ChoosePieces(int numPlayers){
        this.setLayout(new BorderLayout());
        JPanel header = new JPanel(new FlowLayout());
        header.setBackground(Color.red);
        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Calibre",Font.BOLD,20));
        startGame.setOpaque(true);
        startGame.setBackground(Color.white);
        startGame.setForeground(Color.red);
        header.add(startGame);

        JPanel selection = new JPanel();
        if (numPlayers == 2) {
            this.setSize(new Dimension(800, 500));
            selection.setLayout(new GridLayout(1,2));}
        else if (numPlayers == 3){
            this.setSize(new Dimension(1200,500));
            selection.setLayout(new GridLayout(1,3));}
        else if (numPlayers == 4){
            this.setSize(800,900);
            selection.setLayout(new GridLayout(2,2));}

        for (int i=0; i<numPlayers; i++) {
            JPanel player = new JPanel(new GridLayout(3, 2));
            player.setBorder(BorderFactory.createLineBorder(Color.white));
            JLabel color = new JLabel("Color: ");
            JLabel shape = new JLabel("Shape: ");
            JLabel name = new JLabel("Name: ");
            formatLabel(color);
            player.add(color);
            formatLabel(shape);
            player.add(shape);
            formatLabel(name);
            player.add(name);
            selection.add(player);}

        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(selection, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);

        }
        private void formatLabel(JLabel label){
            label.setFont(new Font("Calibre",Font.BOLD,25));
            label.setOpaque(true);
            label.setBackground(Color.red);
            label.setForeground(Color.white);
            label.setBorder(BorderFactory.createLineBorder(Color.white));
        }
    }


