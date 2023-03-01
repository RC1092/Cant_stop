import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    public Game(ArrayList<Player> players){
        this.players = players;
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public void printInfo(){
        System.out.println(players);
    }
}
