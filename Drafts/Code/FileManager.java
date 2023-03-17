import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    private Turn turn;
    private ArrayList<Player> players;
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
        fileWindow(null);
    }
    //Idk how we want this to work
    //}
    private void fileWindow(ArrayList<Player> playerLst){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(600,600));
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.red);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}