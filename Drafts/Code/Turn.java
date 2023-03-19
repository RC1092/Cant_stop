import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;
    private Board board;
    //Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g 1,2,3,4) indicating the order they move in. Values are the Player objects. 
    private HashMap<Integer, Player> turnOrder;
    private ArrayList<pieces> runners;

    private ArrayList<pieces> movementPieces = new ArrayList<pieces>();

    public Turn(ArrayList<Player> players, Dice dice,Board board){
        this.players = players;
        this.dice = dice;
        this.board = board;
        turnOrder = this.setTurnOrder(new HashMap<Integer, Player>());
        currentTurn = 1;
        //players.forEach(e -> InvalidColumns.addAll(e.getColumns()));
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
        
        if (runners == null || runners.size() < 3){
            combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
            combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
            combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));
            //return combinations;
        }
        else if(runners.size() == 3){
            ArrayList<Integer> valids = new ArrayList<>();
            runners.forEach(e -> valids.add(e.getColumn()));
            if(valids.contains(values.get(0)+values.get(1))||valids.contains(values.get(2)+values.get(3))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
                
            }
            if(valids.contains(values.get(0)+values.get(2))||valids.contains(values.get(1)+values.get(3))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(2), values.get(3), values.get(1))));
                
            }
            if(valids.contains(values.get(0)+values.get(3))||valids.contains(values.get(1)+values.get(2))){
                combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(3), values.get(1), values.get(2))));    
            }


        }
        return combinations;
    }

    //Called when a player selects their dice combination and moves their pieces appropriately
    public void movePiece(ArrayList<Integer> selected_combination){
        int col1 = selected_combination.get(0) + selected_combination.get(1); 
        int col2 = selected_combination.get(2) + selected_combination.get(3); 
        ArrayList<Integer> cols= new ArrayList<Integer>(){{add(col1); add(col2);}};
        if (runners == null){
            if(col1 == col2){
                pieces runner = new pieces("Arrow", "White");
                runner.setLocation(board.getTile(col1,Math.abs(7-col1)+1));
                runners.add(runner);
            }else {
                pieces runner1 = new pieces("Arrow", "White");
                runner1.setLocation(board.getTile(col1,Math.abs(7-col1)));
                runners.add(runner1);
                pieces runner2 = new pieces("Arrow", "White");
                runner2.setLocation(board.getTile(col2,Math.abs(7-col2)));
                runners.add(runner2);
            }
    
        }
        else if(runners.size() == 3){
            runners.forEach((e) -> {if(e.getColumn() == col1 || e.getColumn() == col2){e.setLocation(board.getTile(e.getColumn(),e.getRow()+1));}});   

        }
        else {
            runners.forEach(e -> { if(e.getColumn() == col1 || e.getColumn() == col2){e.setLocation(board.getTile(e.getColumn(),e.getRow()+1));cols.removeIf(r ->  r==e.getColumn());}});
            if (cols != null && cols.size() == 1){
                    pieces runner2 = new pieces("Arrow", "White");
                    runner2.setLocation(board.getTile(cols.get(0),Math.abs(7-cols.get(0))));
                    runners.add(runner2);    
            }
            if (cols != null && cols.size() == 2){
                
                        pieces runner2 = new pieces("Arrow", "White");
                        runner2.setLocation(board.getTile(cols.get(0),Math.abs(7-cols.get(0))));
                        runners.add(runner2);
                    
                        if (runners.size() < 3 ){
                            pieces runner3 = new pieces("Arrow", "White");
                            runner3.setLocation(board.getTile(cols.get(1),Math.abs(7-cols.get(1))));
                            runners.add(runner3);
                        }
            }
        
        }
        board.updateGameBoard(runners);
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
