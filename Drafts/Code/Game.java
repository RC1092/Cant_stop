import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;

    public Game(ArrayList<Player> players){
        this.players = players;
        dice = new Dice();
        board = new Board(this,players);
        turn = new Turn(players, dice);
    }

    public Turn getTurn(){
        return turn;
    }

    public void saveGame(){
        FileManager fm = new FileManager();
        fm.writeSave(turn, players);
    }

    public void loadPieces(ArrayList<ArrayList<ArrayList<Integer>>> pieceLocations){
        for (int i = 0; i < players.size(); i++){
            ArrayList<pieces> pieces = players.get(i).getPieces();
            ArrayList<ArrayList<Integer>> playerPieceLocations = pieceLocations.get(i);
            for (int j = 0; j< playerPieceLocations.size(); j++){
                int x = playerPieceLocations.get(j).get(0);
                int y = playerPieceLocations.get(j).get(1);
                if (x >=0){
                    pieces.get(j).setLocation(board.getTile(x, y));
                }
            }
        }
    }
}
