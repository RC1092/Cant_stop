import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {
    private ArrayList<Player> players;
    private JPanel gridPanel;
    ArrayList<String> lines;
    public FileManager (){

    }


    //Save in following Format

    /*  currentTurn (single integer)
        turnOrder (0,firstPlayer:1,secondPlayer:...)
        player1 (name:shape:color:score:)
        player1PieceLocations (x,y:x,y:....)
        player2 (name:shape:color:score)
        player2PieceLocations (x,y:x,y:....)
        player3 (name:shape:color:score:)
        player3PieceLocations (x,y:x,y:....)
        player4 (name:shape:color:score:)
        player4PieceLocations (x,y:x,y:....)
    */

    public void writeSave(Turn turn, ArrayList<Player> players){
        this.players = players;
        try {
            String userDirectory = System.getProperty("user.dir");
            String fileDir = userDirectory + "\\CantStopSave.txt";
            File saveFile = new File(fileDir);
            FileWriter saveWriter = new FileWriter(fileDir);
            saveWriter.write(turn.getCurrentPlayerKey() + System.getProperty("line.separator"));
            String turnOrderString = "";
            for (int i = 1; i<players.size() + 1; i++){
                turnOrderString += i + "," + turn.getTurnOrder().get(i).getName() + ":";
            }
            saveWriter.write(turnOrderString + System.getProperty( "line.separator" ));
            for (Player player: players){
                String saveData = player.getName() + ":" + player.getShape() + ":" + player.getColor() + ":" + player.getScore() + ":" + System.getProperty( "line.separator" );
                for (pieces piece: player.getPieces()){
                    saveData += piece.getTile().getPosition() + ":";
                }
                saveWriter.write(saveData + System.getProperty( "line.separator" ));
            }
            saveWriter.close();
            saveFile.createNewFile();

        } catch (IOException e) {
            System.out.println("File IO Error Occurred");
        }
    }


    public boolean loadSave() {
        String userDirectory = System.getProperty("user.dir");
        File saveFile = new File(userDirectory + "\\CantStopSave.txt");
        boolean isFile = false;
        ArrayList<String> scores = new ArrayList<>();
        if (saveFile.exists()){
            try{
                lines = new ArrayList<>();
                Scanner myReader = new Scanner(saveFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    lines.add(data);
                }
                myReader.close();
            } catch (IOException e) {
                System.out.println("IOException reading file");
            }
            if (lines.size()>0) {
                isFile = true;
                players = new ArrayList<>();
                String[] p1 = lines.get(2).split(":");
                String[] p2 = lines.get(4).split(":");
                players.add(new Player(p1[1], p1[2], p1[0]));
                scores.add(p1[3]);
                players.add(new Player(p2[1], p2[2], p2[0]));
                scores.add(p2[3]);
                if (lines.size() > 6) {
                    String[] p3 = lines.get(6).split(":");
                    players.add(new Player(p3[1], p3[2], p3[0]));
                    scores.add(p3[3]);
                }
                if (lines.size() > 8) {
                    String[] p4 = lines.get(8).split(":");
                    players.add(new Player(p4[1], p4[2], p4[0]));
                    scores.add(p4[3]);
                }
                fileWindow(players, scores);
            }     else{
                    players = new ArrayList<>();
                    fileWindow(players, scores);;
        }
    }return isFile;}

    private void fileWindow(ArrayList<Player> playerLst, ArrayList<String>scores){
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
        for(int i = 0; i<playerLst.size(); i++){
            readInfo(playerLst.get(i), scores.get(i));
        }
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.red);
        JButton loadGame = new JButton("Load Game");
        loadGame.setFont(new Font("Calibre",Font.BOLD,20));
        loadGame.setOpaque(true);
        loadGame.setBackground(Color.white);
        loadGame.setForeground(Color.red);
        loadGame.addActionListener(e-> loadGame(frame));

        JButton back = new JButton("<- Back");
        back.setFont(new Font("Calibre",Font.BOLD,20));
        back.setOpaque(true);
        back.setBackground(Color.white);
        back.setForeground(Color.red);
        back.addActionListener(e->newGame(frame));
        buttonPanel.add(back);
        buttonPanel.add(loadGame);

        panel.add(gameSummary, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.setBackground(Color.red);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void newGame(JFrame frame) {
        new StartUp();
        frame.setVisible(false);
    }

    private void loadGame(JFrame frame) {
        Game game = new Game(players);
        ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations = new ArrayList<>();
        ArrayList<String[]> tempPlayerPieces = new ArrayList<>();
        tempPlayerPieces.add(lines.get(3).split(":"));
        tempPlayerPieces.add(lines.get(5).split(":"));
        if (lines.size() > 6){
            tempPlayerPieces.add(lines.get(7).split(":"));
        }
        if (lines.size() > 8){
            tempPlayerPieces.add(lines.get(9).split(":"));
        }
        for (String[] playerPieces: tempPlayerPieces){
            ArrayList<ArrayList<Integer>> individualPieceLocations = new ArrayList<>();
            for (int i = 0; i<playerPieces.length; i++){
                String[] strLoc = playerPieces[i].split(",");
                ArrayList<Integer> intLoc = new ArrayList<>();
                for (int j = 0; j<strLoc.length;j++){
                    intLoc.add(Integer.parseInt(strLoc[j]));
                }
                individualPieceLocations.add(intLoc);
            }
            pieceLocations.add(individualPieceLocations);
        }
        HashMap<Integer, Player> playOrder = new HashMap<>();
        String[] playOrderLst = lines.get(1).split(":");
        ArrayList<String[]> tempPlayParser = new ArrayList<>();
        for (String item: playOrderLst){
            tempPlayParser.add(item.split(","));
        }

        for (int i=0; i < tempPlayParser.size(); i++){
            for (Player player: players){
                if (player.getName().equals(tempPlayParser.get(i)[1])){
                    playOrder.put(i+1,player);
                }
            }
        }


        game.loadGame(pieceLocations, Integer.parseInt(lines.get(0)), playOrder);

        frame.dispose();
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

    private void readInfo(Player player, String playerScore) {
        JLabel name = new JLabel(player.getName());
        JLabel color = new JLabel(player.getColor());
        JLabel shape = new JLabel(player.getShape());
        JLabel score = new JLabel(playerScore);
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
}