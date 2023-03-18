import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    private Turn turn;
    private ArrayList<Player> players;
    private JPanel gridPanel;
    public FileManager (){

    }

    public void writeSave(Turn turn, ArrayList<Player> players){
        this.turn = turn;
        this.players = players;
        try {
            String userDirectory = System.getProperty("user.dir");
            String fileDir = userDirectory + "\\CantStopSave.txt";
            File saveFile = new File(fileDir);
            FileWriter saveWriter = new FileWriter(fileDir);
            for (Player player: players){
                String saveData = player.getName() + ":" + player.getShape() + ":" + player.getColor() + ":";
                // for (pieces piece: player.getPieces()){
                //     saveData += piece.getTile().getPosition();
                // }
                saveWriter.write(saveData + System.getProperty( "line.separator" ));
            }
            saveWriter.close();
            saveFile.createNewFile();

        } catch (IOException e) {
            System.out.println("File IO Error Occurred");
        }
    }


    public void loadSave() {
        fileWindow(tempBuildPlayerLst());
    }

    private void fileWindow(ArrayList<Player> playerLst){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800,200+100*playerLst.size()));
        JPanel panel = new JPanel(new BorderLayout());
        JLabel gameSummary = new JLabel("Game Summary");
        gameSummary.setFont(new Font("Calibre",Font.BOLD,25));
        gameSummary.setOpaque(true);
        gameSummary.setBackground(Color.white);
        gameSummary.setForeground(Color.red);
        gridPanel = new JPanel(new GridLayout(playerLst.size()+1,4));

        buildHeader();
        for(Player player : playerLst){
            readInfo(player);
        }
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.red);
        JButton loadGame = new JButton("Load Game");
        loadGame.setFont(new Font("Calibre",Font.BOLD,20));
        loadGame.setOpaque(true);
        loadGame.setBackground(Color.white);
        loadGame.setForeground(Color.red);
        loadGame.addActionListener(e-> loadGame());

        JButton back = new JButton("<- Back");
        back.setFont(new Font("Calibre",Font.BOLD,20));
        back.setOpaque(true);
        back.setBackground(Color.white);
        back.setForeground(Color.red);
        back.addActionListener(e->newGame());
        buttonPanel.add(back);
        buttonPanel.add(loadGame);

        panel.add(gameSummary, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.setBackground(Color.red);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void newGame() {
    }

    private void loadGame() {
    }

    private void buildHeader() {
        JLabel names = new JLabel("Player Name");
        JLabel colors = new JLabel("Colors");
        JLabel shapes = new JLabel("Shapes");
        JLabel scores = new JLabel("Scores");
        formatHeaders(names);
        formatHeaders(colors);
        formatHeaders(shapes);
        formatHeaders(scores);
    }

    private void formatHeaders(JLabel label) {
        label.setFont(new Font("Calibre",Font.BOLD,25));
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setForeground(Color.red);
        label.setBorder(BorderFactory.createLineBorder(Color.red,10));
        gridPanel.add(label);
    }

    private void readInfo(Player player) {
        JLabel name = new JLabel(player.getName());
        JLabel color = new JLabel(player.getColor());
        JLabel shape = new JLabel(player.getShape());
        JLabel score = new JLabel(Integer.toString(player.getScore()));
        formatLabel(name);
        formatLabel(color);
        formatLabel(shape);
        formatLabel(score);
    }
    private void formatLabel(JLabel label){
        label.setFont(new Font("Calibre",Font.BOLD,25));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.white,4));
        gridPanel.add(label);}

    private ArrayList<Player> tempBuildPlayerLst(){
        ArrayList<Player> playerLst = new ArrayList<>();
        playerLst.add(new Player("Circle","Yellow","Emily"));
        playerLst.add(new Player("Square", "Blue", "Rushi"));
        return playerLst;

    }
}