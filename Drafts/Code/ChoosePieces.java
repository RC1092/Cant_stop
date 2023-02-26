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
            JPanel background = new JPanel(new BorderLayout());
            JPanel choices = new JPanel(new GridLayout(3, 2));
            JPanel player = new JPanel(new FlowLayout());
            JLabel playerNumber = new JLabel("Player ".concat(Integer.toString(i+1)));

            playerNumber.setFont(new Font("Calibre",Font.BOLD,25));
            playerNumber.setOpaque(true);
            playerNumber.setBackground(Color.white);
            playerNumber.setForeground(Color.red);
            playerNumber.setBorder(BorderFactory.createLineBorder(Color.white));
            player.add(playerNumber);

            choices.setBorder(BorderFactory.createLineBorder(Color.white));
            JLabel color = new JLabel("Color: ");
            JLabel shape = new JLabel("Shape: ");
            JLabel name = new JLabel("Name: ");
            formatLabel(color);
            choices.add(color);
            formatLabel(shape);
            choices.add(shape);
            formatLabel(name);
            choices.add(name);
            background.add(choices, BorderLayout.CENTER);
            background.add(player, BorderLayout.NORTH);
            selection.add(background);}
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


