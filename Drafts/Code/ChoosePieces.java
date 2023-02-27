import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoosePieces extends JFrame implements ActionListener {
    private int numPlayers;
    private JPanel selection;
    private final String[] shapes, colors;
    private ArrayList<JComboBox<String>> shapeLst, colorLst;
    private ArrayList<JTextField> nameLst;
    private Game game;
    public ChoosePieces(int numPlayers){
        game = new Game();
        this.numPlayers = numPlayers;
        this.setLayout(new BorderLayout());
        this.shapes = new String[]{" ","Circle","Square","Triangle","Star"};
        this.colors = new String[]{" ", "Blue","Red","Yellow","Green","Orange","Purple"};
        shapeLst = new ArrayList<>();
        colorLst = new ArrayList<>();
        nameLst = new ArrayList<>();
        JPanel header = new JPanel(new FlowLayout());
        header.setBackground(Color.red);
        JButton startGame = new JButton("Start Game");
        startGame.setFont(new Font("Calibre",Font.BOLD,20));
        startGame.setOpaque(true);
        startGame.setBackground(Color.white);
        startGame.setForeground(Color.red);
        header.add(startGame);
        startGame.addActionListener(this);

        selection = new JPanel();
        if (numPlayers == 2) {
            this.setSize(new Dimension(800, 500));
            selection.setLayout(new GridLayout(1,2));}
        else if (numPlayers == 3){
            this.setSize(new Dimension(1200,500));
            selection.setLayout(new GridLayout(1,3));}
        else if (numPlayers == 4){
            this.setSize(800,900);
            selection.setLayout(new GridLayout(2,2));}

        buildChoicePanel();

        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(selection, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

    private void formatLabel(JLabel label){
            label.setFont(new Font("Calibre",Font.BOLD,25));
            label.setOpaque(true);
            label.setBackground(Color.red);
            label.setForeground(Color.white);
            //label.setBorder(BorderFactory.createLineBorder(Color.white));
        }
        private void buildChoicePanel(){
            for (int i=0; i<numPlayers; i++) {
                JPanel background = new JPanel(new BorderLayout());
                JPanel choices = new JPanel(new GridLayout(3, 1));
                JPanel player = new JPanel(new FlowLayout());
                JLabel playerNumber = new JLabel("Player ".concat(Integer.toString(i+1)));

                playerNumber.setFont(new Font("Calibre",Font.BOLD,25));
                playerNumber.setOpaque(true);
                playerNumber.setBackground(Color.white);
                playerNumber.setForeground(Color.red);
                playerNumber.setBorder(BorderFactory.createLineBorder(Color.white));
                player.add(playerNumber);
                player.setBackground(Color.white);

                choices.setBorder(BorderFactory.createLineBorder(Color.white));
                JLabel color = new JLabel("Color: ");
                JLabel shape = new JLabel("Shape: ");
                JLabel name = new JLabel("Name: ");
                formatLabel(color);
                formatLabel(shape);
                formatLabel(name);

                background.add(choices, BorderLayout.CENTER);
                background.add(player, BorderLayout.NORTH);
                selection.add(background);

                JComboBox<String> chooseShape = new JComboBox<>(shapes);
                chooseShape.setFont(new Font("Calibre",Font.BOLD,20));
                chooseShape.setBackground(Color.white);
                shapeLst.add(chooseShape);
                JComboBox<String> chooseColor = new JComboBox<>(colors);
                chooseColor.setFont(new Font("Calibre",Font.BOLD,20));
                chooseColor.setBackground(Color.white);
                colorLst.add(chooseColor);
                JTextField chooseName = new JTextField("Type your name here");
                chooseName.setFont(new Font("Calibre",Font.PLAIN,20));
                chooseName.setBackground(Color.white);
                nameLst.add(chooseName);

                JPanel panel = new JPanel(new FlowLayout());
                panel.setBackground(Color.red);
                choices.add(panel);
                panel.add(shape);
                panel.add(chooseShape);

                JPanel panel2 = new JPanel(new FlowLayout());
                panel2.setBackground(Color.red);
                choices.add(panel2);
                panel2.add(color);
                panel2.add(chooseColor);

                JPanel panel3 = new JPanel(new FlowLayout());
                panel3.setBackground(Color.red);
                choices.add(panel3);
                panel3.add(name);
                panel3.add(chooseName);
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<numPlayers; i++){
            game.addPlayer(new Player((String) shapeLst.get(i).getSelectedItem(), (String) colorLst.get(i).getSelectedItem()
            , nameLst.get(i).getText()));

        }
    }
}


