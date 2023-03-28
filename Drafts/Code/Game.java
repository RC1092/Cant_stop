import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

//import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.ImageIcon;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;

    public Game(ArrayList<Player> players) {
        this.players = players;
        dice = new Dice();
        board = new Board(this, players);
        turn = new Turn(players, dice, board);
        board.setCurrentPlayer();

    }

    public void endTurn() {
        turn.endTurn();
    }

    public Turn getTurn() {
        return turn;
    }

    public void saveGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the game?", "Save Game",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Process further if user confirms
            FileManager fm = new FileManager();
            fm.writeSave(turn, players);
            ImageIcon originalIcon = new ImageIcon(Game.class.getResource("success.png"));
            ImageIcon resizedIcon = new ImageIcon(
                    originalIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            JOptionPane.showMessageDialog(null, "Game saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE,
                    resizedIcon);

        }
    }

    public void loadGame(ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations, int currentPlayer,
            HashMap<Integer, Player> turnOrder) {
        turn.setCurrentPlayerkey(currentPlayer);
        turn.setTurnOrder(turnOrder);
        for (int i = 0; i < players.size(); i++) {
            ArrayList<pieces> pieces = players.get(i).getPieces();
            ArrayList<ArrayList<Integer>> playerPieceLocations = pieceLocations.get(i);
            for (int j = 0; j < playerPieceLocations.size(); j++) {
                if (playerPieceLocations.get(j) != null) {
                    int x = playerPieceLocations.get(j).get(0);
                    int y = playerPieceLocations.get(j).get(1);
                    pieces.set(j, new pieces(players.get(i).getShape(), players.get(i).getColor()));
                    pieces.get(j).setLocation(board.getTile(x, y));
                } 
            }
            board.updateGameBoard(pieces);
        }
    }

    public Player getCurrentPlayer() {
        return turn.getTurnOrder().get(turn.getCurrentPlayerKey());
    }
    public int checkRunners(){
        return turn.runnerCount();
    }


    public static void main(String[] args) {

        // FlatDarkLaf.setup();
        Player p1 = new Player("Circle", "Blue", "p1");
        Player p2 = new Player("Square", "Green", "player2");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        Game game = new Game(players);
    }
}
