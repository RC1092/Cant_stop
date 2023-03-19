import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;

    //Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g 1,2,3,4) indicating the order they move in. Values are the Player objects. 
    private HashMap<Integer, Player> turnOrder;

    private ArrayList<pieces> movementPieces = new ArrayList<pieces>();

    public Turn(ArrayList<Player> players, Dice dice){
        this.players = players;
        this.dice = dice;
        turnOrder = this.setTurnOrder(new HashMap<Integer, Player>());
        currentTurn = 1;
        while (movementPieces.size() < 3){
            movementPieces.add(new pieces("Arrow", "White"));
        }
    }

    public HashMap<Integer, Player> setTurnOrder(HashMap<Integer,Player> order){
        HashMap<Integer, Player> turnOrderSetter = new HashMap<Integer, Player>();
        if (order.size() != 0){
            turnOrder = order;
        } else {
            ArrayList<Integer> orderRolls = dice.getTurnOrderRolls(players.size());
            for (int i =0; i < orderRolls.size(); i++){
                int highest_remaining = Collections.max(orderRolls);
                int index = orderRolls.indexOf(highest_remaining);
                turnOrderSetter.put(i + 1,players.get(index));
                orderRolls.set(index, 0);
            }
        }
        return turnOrderSetter;
    }

    public int getCurrentPlayerKey(){
        return currentTurn;
    }

    public void setCurrentPlayerkey(int key){
        currentTurn = key;
    }
    public HashMap<Integer, Player> getTurnOrder(){
        return turnOrder;
    }

    //Called when player rolls dice on their turn
    public ArrayList<ArrayList<Integer>> getDiceCombinations() {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> values = dice.makeTurnRoll();
        //Stores the dice pairs into combinations as ordered ArrayList. So [1,2,3,4] means the pairs 1,2 + 3,4
        combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));
        return combinations;
    }

    //Called when a player selects their dice combination and moves their pieces appropriately
    public void movePiece(int x, int y){
        
    }

    //Called when a player elects to end their turn. Once called will set all moved pieces to their new location.
    public void endTurn(){ //Arg will be movementPieces: ArrayList<MovementPieces> once movementPieces are implemented
        //move pieces
        //for each piece getPosition(): Tile
        //for each postion; tile
        /*
        if(tile.checkEndTile()){
            Player currentPlayer = turnOrder.get(currentTurn);
            currentPlayer.captureColumn(); //(Arg) Tile Position
        }

        if (currentTurn < players.size()){
            currentTurn ++;
        } else{
            currentTurn = 1;
        }
        */
    }
}
