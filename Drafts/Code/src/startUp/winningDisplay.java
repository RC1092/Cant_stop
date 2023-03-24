

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class winningDisplay extends JFrame{
    JLabel winningMessage;
    JButton restart;
    JPanel panel;


    public winningDisplay(JFrame board,Player winner){
        this.setSize(new Dimension(500,300));
        this.setBackground(Color.RED);
        panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(240, 0, 0));

        GridBagConstraints c = new GridBagConstraints();

        winningMessage = new JLabel(winner.getName()+" Won !");
        winningMessage.setHorizontalAlignment(JLabel.CENTER);
        winningMessage.setFont(new Font("Goudy Stout", ABORT, 20));
        winningMessage.setForeground(Color.yellow);
        

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 50;
        c.insets = new Insets(50, 0, 0, 0);
        panel.add(winningMessage,c);

        JPanel footer = new JPanel(new FlowLayout());
        footer.setBackground(Color.red);
        restart = new JButton();
        restart.setText("New Game");
        restart.setPreferredSize(new Dimension(120,40));
        restart.setFont(new Font("Arial", Font.BOLD, 14));
        restart.setBackground(new Color(144, 238,144 ));
        
        
      /*   c.gridy = 5;
        c.gridx = 1;
        c.ipady = 0;
        
        c.insets= new Insets(10,20,150,10);
        c.gridwidth = 2;
         */
        
        
        restart.addActionListener((ActionEvent e) -> {startNewGame(board);});
        footer.add(restart,c);
        this.getContentPane().add(panel);
        this.getContentPane().add(footer,BorderLayout.SOUTH);
        setLocationRelativeTo(board);
        setVisible(true);
        setResizable(false);
    }

    public void startNewGame(JFrame parent){
        StartUp newGame = new StartUp();
        parent.dispose();
        this.dispose();    
    }
    
    public static void main(String [] argv){
        Player p1 = new Player("square", "red", "adbc");
        JFrame temp = new JFrame();
        winningDisplay w1 = new winningDisplay(temp,p1);
    }
}