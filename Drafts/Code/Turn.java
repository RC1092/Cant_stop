import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.Timer;

public class Turn {
    private ArrayList<Player> players;
    private Dice dice;
    private int currentTurn;
    private Board board;
    private Timer timer;
    // Stores the turn order as a key value pair, keys are 1-numberofplayers (e.g
    // 1,2,3,4) indicating the order they move in. Values are the Player objects.
    private HashMap<Integer, Player> turnOrder;
    private ArrayList<pieces> runners = new ArrayList<pieces>();
    private ArrayList<Integer> capturedColumns = new ArrayList<Integer>();

    public Turn(ArrayList<Player> players, Dice dice, Board board) {
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.setTurnOrder(new HashMap<Integer, Player>());
        currentTurn = 1;
    }

    public void setTurnOrder(HashMap<Integer, Player> order) {
        HashMap<Integer, Player> turnOrderSetter = new HashMap<Integer, Player>();
        if (order.size() != 0) {
            turnOrder = order;
        } else {
            ArrayList<Integer> orderRolls = dice.getTurnOrderRolls(players.size());
            for (int i = 0; i < orderRolls.size(); i++) {
                int highest_remaining = Collections.max(orderRolls);
                int index = orderRolls.indexOf(highest_remaining);
                turnOrderSetter.put(i + 1, players.get(index));
                orderRolls.set(index, 0);
            }
            turnOrder = turnOrderSetter;
        }
    }

    public int getCurrentPlayerKey() {
        return currentTurn;
    }

    public void setCurrentPlayerkey(int key) {
        currentTurn = key;
    }

    public HashMap<Integer, Player> getTurnOrder() {
        return turnOrder;
    }

    // Called when player rolls dice on their turn
    public ArrayList<ArrayList<Integer>> getDiceCombinations() {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        ArrayList<ArrayList<Integer>> validCombos = new ArrayList<>();
        ArrayList<Integer> values = dice.makeTurnRoll();

        // Stores the dice pairs into combinations as ordered ArrayList. So [1,2,3,4]
        // means the pairs 1,2 + 3,4
        validCombos.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        /*
         * if (runners.size() == 1 || runners.size() == 0 || runners.size() == 2)
         * {
         */
        combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1), values.get(2), values.get(3))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(2), values.get(3), values.get(0))));
        combinations.add(new ArrayList<>(Arrays.asList(values.get(1), values.get(3), values.get(2), values.get(0))));

        for (ArrayList<Integer> combo : combinations) {
            int diceRoll1 = combo.get(0) + combo.get(1);
            int diceRoll2 = combo.get(2) + combo.get(3);
            if (isColValid(diceRoll1) || isColValid(diceRoll2)) {
                validCombos.add(combo);

            }
        }
        /*
         * }
         * else if(runners.size() == 3){
         * ArrayList<Integer> valids = new ArrayList<>();
         * runners.forEach(e -> valids.add(e.getRow()+1));
         * 
         * if(valids.contains(values.get(0)+values.get(1))||valids.contains(values.get(2
         * )+values.get(3))){
         * combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(1),
         * values.get(2), values.get(3))));
         * 
         * }
         * if(valids.contains(values.get(0)+values.get(2))||valids.contains(values.get(1
         * )+values.get(3))){
         * combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(2),
         * values.get(3), values.get(1))));
         * 
         * }
         * if(valids.contains(values.get(0)+values.get(3))||valids.contains(values.get(1
         * )+values.get(2))){
         * combinations.add(new ArrayList<>(Arrays.asList(values.get(0), values.get(3),
         * values.get(1), values.get(2))));
         * }
         * 
         * 
         * }
         */
        return validCombos;
    }

    private boolean isColValid(int diceRoll) {
        if (runners.size() == 0) {
            if (freeColumn(diceRoll)) {
                return true;
            }
        } else if (runners.size() == 1 || runners.size() == 2) {
            if (freeColumn(diceRoll) || hasRunner(diceRoll)) {
                return true;
            }
        } else if (runners.size() == 3) {
            if (hasRunner(diceRoll)) {
                return true;
            }
        }
        return false;
    }

    private boolean freeColumn(int diceRoll) {
        return !hasRunner(diceRoll) && checkNotCaptured(diceRoll);
    }

    public boolean checkNotCaptured(int diceRoll) {
        for (Player player : players) {
            ArrayList<Integer> columns = player.getColumns();
            for (int columnNum : columns) {
                if (columnNum == diceRoll) {
                    return false;
                }
            }
        }
        return true;
    }

    public int runnerCount() {
        return runners.size();
    }

    public void movePiece(int diceRoll) {
        for (pieces runner : runners) {

            if (runner.getRow() + 1 == diceRoll
                    && !(board.getTile(runner.getColumn(), runner.getRow()).checkEndTile())) {

                runner.setLocation(board.getTile(runner.getRow(), runner.getColumn() - 1));
                if (board.getTile(runner.getColumn(), runner.getRow()).checkEndTile()) {
                    runner.cantMove();
                }
                board.updateGameBoard(runners);
                return;
            }
        }
        if (runners.size() != 3) {
            pieces runner1 = new pieces("Arrow", "White");
            if (turnOrder.get(currentTurn).getPieceInColumn(diceRoll) != null) {
                pieces temp = turnOrder.get(currentTurn).getPieceInColumn(diceRoll);
                runner1.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
            } else {
                runner1.setLocation(board.getTile(diceRoll - 1, 13 - Math.abs(7 - diceRoll) - 1));
            }

            if (!capturedColumns.contains(runner1.getRow() + 1)) {
                runners.add(runner1);
            }
        }
        board.updateGameBoard(runners);

    }
    // Called when a player selects their dice combination and moves their pieces
    // appropriately
    /*
     * public void movePiece(ArrayList<Integer> selected_combination) {
     * 
     * int col1 = selected_combination.get(0) + selected_combination.get(1);
     * int col2 = selected_combination.get(2) + selected_combination.get(3);
     * System.out.println("Combinations selected: " + col1 + " " + col2);
     * runners.forEach(
     * e -> System.out.println("Runner location before change" + " " + e.getColumn()
     * + " " + e.getRow()));
     * ArrayList<Integer> cols = new ArrayList<Integer>() {
     * {
     * add(col1);
     * add(col2);
     * }
     * };
     * cols.removeIf((e) -> capturedColumns.contains(e));
     * if (cols.size() == 0) {
     * 
     * }
     * if (runners.size() == 0) {
     * if (col1 == col2) {
     * System.out.println("Case with no runners and same combination.");
     * 
     * pieces runner1 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(col1) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(col1);
     * if (temp.getColumn() - 2 < 0) {
     * runner1.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner1.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 2));
     * }
     * } else {
     * runner1.setLocation(board.getTile(col1 - 1, 13 - Math.abs(7 - col1) - 2));
     * }
     * 
     * if(!capturedColumns.contains(runner1.getRow()+1)){
     * runners.add(runner1);
     * }
     * 
     * }
     * 
     * else {
     * System.out.println("Case with no runners and different combination.");
     * 
     * pieces runner1 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(col1) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(col1);
     * runner1.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner1.setLocation(board.getTile(col1 - 1, 13 - Math.abs(7 - col1) - 1));
     * }
     * 
     * if(!capturedColumns.contains(runner1.getRow()+1)){
     * runners.add(runner1);
     * }
     * 
     * pieces runner2 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(col2) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(col2);
     * runner2.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner2.setLocation(board.getTile(col2 - 1, 13 - Math.abs(7 - col2) - 1));
     * }
     * 
     * if(!capturedColumns.contains(runner2.getRow()+1)){
     * runners.add(runner2);
     * }
     * }
     * 
     * } else if (runners.size() == 3) {
     * System.out.println("Case with 3 runners");
     * runners.forEach((e) -> {
     * if (cols.contains(e.getRow() + 1)&& !(board.getTile(e.getColumn(),
     * e.getRow()).checkEndTile())) {
     * e.setLocation(board.getTile(e.getRow(), e.getColumn() - 1));
     * System.out.println("Column Match");
     * cols.remove((Integer) (e.getRow() + 1));
     * if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
     * 
     * System.out.println("cant move pieces in "+(e.getRow()+1));
     * e.cantMove();
     * }
     * }
     * });
     * runners.forEach((e) -> {
     * if (cols.contains(e.getRow() + 1)&& !(board.getTile(e.getColumn(),
     * e.getRow()).checkEndTile())) {
     * e.setLocation(board.getTile(e.getRow(), e.getColumn() - 1));
     * System.out.println("Column Match");
     * cols.remove((Integer) (e.getRow() + 1));
     * if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
     * 
     * System.out.println("cant move pieces in "+(e.getRow()+1));
     * e.cantMove();
     * }
     * }
     * });
     * 
     * } else {
     * runners.forEach((e) -> {
     * if (cols.contains(e.getRow() + 1)&& !(board.getTile(e.getColumn(),
     * e.getRow()).checkEndTile())) {
     * e.setLocation(board.getTile(e.getRow(), e.getColumn() - 1));
     * System.out.println("Column Match");
     * cols.remove((Integer) (e.getRow() + 1));
     * if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
     * 
     * System.out.println("cant move pieces in "+(e.getRow()+1));
     * e.cantMove();
     * }
     * }
     * });
     * 
     * runners.forEach((e) -> {
     * if (cols.contains(e.getRow() + 1)&& !(board.getTile(e.getColumn(),
     * e.getRow()).checkEndTile())) {
     * e.setLocation(board.getTile(e.getRow(), e.getColumn() - 1));
     * System.out.println("Column Match");
     * cols.remove((Integer) (e.getRow() + 1));
     * if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
     * 
     * System.out.println("cant move pieces in "+(e.getRow()+1));
     * e.cantMove();
     * }
     * }
     * });
     * 
     * if (cols.size() == 1) {
     * System.out.println("Case with 1 or 2 runners where the one column is new ");
     * 
     * pieces runner3 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(cols.get(0)) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(cols.get(0));
     * runner3.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner3.setLocation(board.getTile(cols.get(0) - 1, 13 - Math.abs(7 -
     * cols.get(0)) - 1));
     * }
     * if(!capturedColumns.contains(runner3.getRow()+1)){
     * runners.add(runner3);
     * }
     * }
     * if (cols.size() == 2) {
     * System.out.println("Case with 1 or 2 runners where the both column are new "
     * );
     * pieces runner3 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(cols.get(0)) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(cols.get(0));
     * runner3.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner3.setLocation(board.getTile(cols.get(0) - 1, 13 - Math.abs(7 -
     * cols.get(0)) - 1));
     * }
     * if(!capturedColumns.contains(runner3.getRow()+1)){
     * runners.add(runner3);
     * }
     * 
     * if (runners.size() < 3) {
     * pieces runner4 = new pieces("Arrow", "White");
     * if (turnOrder.get(currentTurn).getPieceInColumn(cols.get(1)) != null) {
     * pieces temp = turnOrder.get(currentTurn).getPieceInColumn(cols.get(1));
     * runner4.setLocation(board.getTile(temp.getRow(), temp.getColumn() - 1));
     * } else {
     * runner4.setLocation(board.getTile(cols.get(1) - 1, 13 - Math.abs(7 -
     * cols.get(1)) - 1));
     * }
     * if(!capturedColumns.contains(runner4.getRow()+1)){
     * runners.add(runner4);
     * }
     * }
     * }
     * 
     * }
     * runners.forEach((e) -> {
     * System.out.println("Runner location after change" + " " + e.getColumn() + " "
     * + e.getRow());
     * });
     * board.updateGameBoard(runners);
     * }
     */

    public void addCapturedColumn(int col) {
        capturedColumns.add(col);
    }

    public ArrayList<Integer> getCapturedColumn() {
        return capturedColumns;
    }

    public void endTurn() {
        int num = turnOrder.size();

        Player current_player = turnOrder.get(currentTurn);
        current_player.updatePieces(runners);
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                if (board.getTile(x, y).checkEndTile()) {

                }

            }
        }
        runners.forEach((e) -> {
            if (board.getTile(e.getColumn(), e.getRow()).checkEndTile()) {
                //e.setText("Blue");
                current_player.captureColumn(e.getRow() + 1);
                capturedColumns.add(e.getRow() + 1);
                board.capturedColumn(e.getRow());
                board.updateScores();
                if (current_player.checkWinner()) {
                    winningDisplay winner = new winningDisplay(board, current_player);
                }
                ;
            }
        });

        board.removeRunners(runners);

        board.updateGameBoard(current_player.getPieces());
        runners = new ArrayList<pieces>();
        if (currentTurn == num) {
            currentTurn = 1;
        } else {
            currentTurn += 1;
        }
        board.setCurrentPlayer();
        // board.updateGameBoard(player_Pieces);

        if (getCurrentplayer().getAI()) {
            AIGamePlay();
        }

    }

    public Player getCurrentplayer() {
        Player current_player = turnOrder.get(currentTurn);
        return current_player;
    }

    public boolean hasRunner(int diceRoll) {
        ArrayList<Integer> valids = new ArrayList<>();
        runners.forEach(e -> valids.add(e.getRow() + 1));
        for (int valid : valids) {
            if (valid == diceRoll) {
                return true;
            }
        }
        return false;
    }

    public void endTurnBust() {
        int num = turnOrder.size();
        if (currentTurn == num) {
            currentTurn = 1;
        } else {
            currentTurn += 1;
        }
        board.movePiece(0, 0);
        board.removeRunners(runners);
        runners = new ArrayList<pieces>();
        if (getCurrentplayer().getAI()) {
            AIGamePlay();
        }
    }

    public void AIGamePlay() {
        for (int i = 0; i < 3; i++) {

            board.rollDiceAction();

            if (board.validChoice.size() == 0) {
                break;
            }
            // try {
            // Thread.sleep(2000);
            // } catch (InterruptedException ex) {
            // ex.printStackTrace();
            // }
            board.validChoice.get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button clicked!");
                }
            });

            ActionListener[] actionListeners = board.validChoice.get(0).getActionListeners();
            for (ActionListener listener : actionListeners) {
                listener.actionPerformed(new ActionEvent(board.validChoice.get(0),
                        ActionEvent.ACTION_PERFORMED, ""));
            }

            // Random rand = new Random(board.validChoice.size());
            System.out.println("lenght is" + board.validChoice.size());

        }

        // for (int i = 0; i < 3; i++) {

        // timer = new Timer(2000, (ActionEvent e) -> {
        // board.validChoice.clear();
        // board.rollDiceAction();
        // int count = 0;
        // board.validChoice.get(0).addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // System.out.println("Button clicked!");
        // }
        // });

        // ActionListener[] actionListeners =
        // board.validChoice.get(0).getActionListeners();
        // for (ActionListener listener : actionListeners) {
        // listener.actionPerformed(new ActionEvent(board.validChoice.get(0),
        // ActionEvent.ACTION_PERFORMED, ""));
        // }

        // // Random rand = new Random(board.validChoice.size());
        // System.out.println("lenght is" + board.validChoice.size());
        // count++;
        // });

        // int n = rand.nextInt(board.validChoice.size());

        // board.validChoice.get(0).addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // System.out.println("Button clicked!");
        // }
        // });

        // ActionListener[] actionListeners =
        // board.validChoice.get(0).getActionListeners();
        // for (ActionListener listener : actionListeners) {
        // listener.actionPerformed(new ActionEvent(board.validChoice.get(0),
        // ActionEvent.ACTION_PERFORMED, ""));
        // }

        System.out.println("Ai Player");
        // board.validChoice.clear()

        endTurn();
        board.validChoice.clear();
    }
}
