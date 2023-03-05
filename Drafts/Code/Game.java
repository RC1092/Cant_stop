import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Dice dice;
    private Turn turn;
    private int currentTurn;

    //Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g 1,2,3,4) indicating the order they move in. Values are the Player objects. 
    private HashMap<Integer,Player> turnOrder;

    public Game(ArrayList<Player> players){
        this.players = players;
        currentTurn = 1;
        turn = new Turn(players, dice);
        turnOrder = turn.getTurnOrder();
        board = new Board(players);
        dice = new Dice();
    }

    //Indexes into the turnOrder Hashmap using currentTurn to get the player whose turn it is
    public Player getCurrentPlayer() {
        return turnOrder.get(currentTurn);
    }

    public void addPlayer(Player player){
        players.add(player);
    }
    public void printInfo(){
        System.out.println(players);
    }
}
