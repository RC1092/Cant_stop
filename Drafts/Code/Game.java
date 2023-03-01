import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    public Game(ArrayList<Player> players){
        this.players = players;
        board = new Board();
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public void printInfo(){
        System.out.println(players);
    }
}
