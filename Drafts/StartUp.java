import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUp extends JFrame implements ActionListener {
    private String[] numberOptions;
    private JComboBox<String> dropDown;
    public StartUp(){
        this.setLayout(new GridLayout(3,1));
        this.setSize(new Dimension(600,600));
        numberOptions = new String[]{" ","2","3","4"};
        JPanel title = new JPanel(new GridBagLayout());
        title.setBackground(Color.red);
        JPanel select = new JPanel(new FlowLayout());
        select.setBackground(Color.red);
        JPanel start = new JPanel(new FlowLayout());
        start.setBackground(Color.red);

        JLabel cantStop = new JLabel("Can't Stop!");
        cantStop.setFont(new Font("Calibre",Font.BOLD,50));
        cantStop.setOpaque(true);
        cantStop.setBackground(Color.red);
        cantStop.setForeground(Color.white);

        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Calibre",Font.BOLD,20));
        startGame.setOpaque(true);
        startGame.setBackground(Color.white);
        startGame.setForeground(Color.red);
        startGame.addActionListener(this);

        JLabel numPlayers = new JLabel("Number of Players: ");
        numPlayers.setFont(new Font("Calibre",Font.BOLD,25));
        numPlayers.setOpaque(true);
        numPlayers.setBackground(Color.red);
        numPlayers.setForeground(Color.white);

        dropDown = new JComboBox<>(numberOptions);
        dropDown.setFont(new Font("Calibre",Font.BOLD,25));
        dropDown.setBackground(Color.white);

        getContentPane().add(title);
        getContentPane().add(select);
        getContentPane().add(start);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        title.add(cantStop, c);
        select.add(numPlayers);
        select.add(dropDown);
        start.add(startGame);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dropDown.getSelectedItem().equals(" ")){
            System.out.println(dropDown.getSelectedItem());}
    }
}
