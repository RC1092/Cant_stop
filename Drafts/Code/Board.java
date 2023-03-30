import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JFrame {
    private Tile[][] board;
    private ArrayList<Player> players;
    private Game game;
    private JPanel gamePanel;
    private JPanel otherPanel;
    // create "Option" menu
    private JMenu gameMenu;
    private JMenuItem newGameItem;
    private JMenuItem saveItem;
    private JMenuItem quitItem;
    private JMenuBar menuBar;

    private JButton dice;
    private JButton endTurn;
    private ArrayList<JLabel> labelsLst, scoresLst;

    public Board(Game game, ArrayList<Player> players) {
        this.players = players;
        this.game = game;
        this.setSize(new Dimension(1000, 900));
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Options");
        gameMenu.setFont(new Font("Calibrie", Font.BOLD, 15));
        // Menu Items
        newGameItem = new JMenuItem("New Game");
        newGameItem.setFont(new Font("Calibrie", Font.BOLD, 15));
        saveItem = new JMenuItem("Save");
        saveItem.setFont(new Font("Calibrie", Font.BOLD, 15));
        quitItem = new JMenuItem("Quit");
        quitItem.setFont(new Font("Calibrie", Font.BOLD, 15));

        // add action listeners
        saveItem.addActionListener(e -> {
            game.saveGame();
        });

        newGameItem.addActionListener(e -> {
            this.newGame();
        });

        quitItem.addActionListener(e -> {
            this.quitGame();
        });
        // add menu items to "Game" menu
        gameMenu.add(newGameItem);
        gameMenu.add(saveItem);
        gameMenu.addSeparator();
        gameMenu.add(quitItem);

        menuBar.add(gameMenu);
        // saveMenu.add(saveButton);
        this.setJMenuBar(menuBar);

        board = new Tile[13][13];
        getContentPane().setLayout(new BorderLayout());
        buildBoard();
        buildSide();
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // updatePlayerLabels(); //testing
    }

    private void buildBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(13, 13));
        boardPanel.setBackground(new Color(149, 240, 252));
        getContentPane().add(boardPanel, BorderLayout.CENTER);

        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 13; y++) {
                if (checkTile(x, y)) {
                    board[x][y] = new Tile(x, y, true);
                    board[x][y].setFont(new Font("Calibre", Font.BOLD, 20));
                    board[x][y].setForeground(Color.red);
                } else {
                    board[x][y] = new Tile(x, y, false);
                }
                setLabels(x, y);
                boardPanel.add(board[x][y]);
            }
        }

    }

    private void setLabels(int x, int y) {
        if ((x == 5 && y == 1)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("2"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 4 && y == 2)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("3"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 3 && y == 3)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("4"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 2 && y == 4)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("5"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 1 && y == 5)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("6"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 0 && y == 6)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("7"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 1 && y == 7)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("8"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 2 && y == 8)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("9"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 3 && y == 9)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("10"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 4 && y == 10)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("11"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 5 && y == 11)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("12"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 8 && y == 1)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("C"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 9 && y == 2)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("A"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 10 && y == 3)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("N"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 11 && y == 4)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("T"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 11 && y == 8)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("S"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 10 && y == 9)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("T"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 9 && y == 10)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("O"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 8 && y == 11)) {
            board[x][y].setLayout(new FlowLayout());
            board[x][y].add(createBoardLabel("P"));
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 6 && y == 0)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 7 && y == 0)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 7 && y == 12)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 6 && y == 12)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 12 && y == 5)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        } else if ((x == 12 && y == 7)) {
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
    }
    public int TESTcheckEndTile(Tile tile){
        int x = tile.getColumn();
        int y = tile.getRow();
        if ((x == 5 && y == 1)) {
            board[x][y].setBackground(Color.BLACK); //setting black to test
            return 2;}
        else if ((x == 4 && y == 2)) {
            board[x][y].setBackground(Color.BLACK);
            return 3;}
        else if ((x == 3 && y == 3)) {
            board[x][y].setBackground(Color.BLACK);
            return 4;
        } else if ((x == 2 && y == 4)) {
            board[x][y].setBackground(Color.BLACK);
            return 5;
        } else if ((x == 1 && y == 5)) {
            board[x][y].setBackground(Color.BLACK);
            return 6;
        } else if ((x == 0 && y == 6)) {
            board[x][y].setBackground(Color.BLACK);
            return 7;
        } else if ((x == 1 && y == 7)) {
            board[x][y].setBackground(Color.BLACK);
            return 8;
        } else if ((x == 2 && y == 8)) {
            board[x][y].setBackground(Color.BLACK);
            return 9;
        } else if ((x == 3 && y == 9)) {
            board[x][y].setBackground(Color.BLACK);
            return 10;
        } else if ((x == 4 && y == 10)) {
            board[x][y].setBackground(Color.BLACK);
            return 11;
        } else if ((x == 5 && y == 11)) {
            board[x][y].setBackground(Color.BLACK);
            return 12;
    }
    return 0;}

    private JLabel createBoardLabel(String character) {
        JLabel number = new JLabel(character);
        number.setFont(new Font("Calibrie", Font.BOLD, 20));
        number.setBackground(Color.white);
        number.setForeground(Color.red);
        number.setOpaque(true);
        return number;
    }

    private boolean checkTile(int x, int y) {
        if ((y == 0 || y == 12) && (x < 5 || x > 8)) {
            return false;
        } else if ((y == 1 || y == 11) && (x < 4 || x > 9)) {
            return false;
        } else if (((y == 2 || y == 10) && (x < 3 || x > 10))) {
            return false;
        } else if ((y == 3 || y == 9) && (x < 2 || x > 11)) {
            return false;
        } else if ((y == 4 || y == 8) && (x < 1 || x > 12)) {
            return false;
        } else if ((y == 5 || y == 7) && (x < 0 || x > 13)) {
            return false;
        } else {
            return true;
        }
    }

    private void buildSide() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        buttonPanel.setSize(new Dimension(344, buttonPanel.getHeight()));
        buttonPanel.setVisible(false);
        buttonPanel.removeAll();
        otherPanel = new JPanel(new GridLayout(3, 1));
        gamePanel = new JPanel(new GridLayout(3, 1));

        JPanel infoPanel = new JPanel(new GridLayout(players.size() + 1, 3));
        gamePanel.setBackground(Color.red);
        otherPanel.setBackground(Color.red);
        infoPanel.setBackground(Color.red);

        dice = new JButton("Roll Dice");
        dice.setFont(new Font("Calibre", Font.BOLD, 20));
        dice.addActionListener(e -> {
            RollDice();
        });
        dice.setOpaque(true);
        dice.setBackground(Color.white);
        dice.setForeground(Color.red);
        gamePanel.add(dice);

        endTurn = new JButton("End Turn");
        endTurn.setFont(new Font("Calibre", Font.BOLD, 20));
        endTurn.setOpaque(true);
        endTurn.setBackground(Color.white);
        endTurn.setForeground(Color.red);
        endTurn.addActionListener(e -> {
            game.endTurn();
        });
        gamePanel.add(endTurn);

        JLabel playersNames = new JLabel("Players");
        formatLabel(playersNames);
        infoPanel.add(playersNames);
        JLabel scores = new JLabel("Scores");
        formatLabel(scores);
        infoPanel.add(scores);

        labelsLst = new ArrayList<>();
        scoresLst = new ArrayList<>();
        for (Player player : players) {
            JLabel playerName = new JLabel(player.getName());
            formatLabel(playerName);
            labelsLst.add(playerName);
            infoPanel.add(playerName);
            JLabel playerScore = new JLabel(Integer.toString(player.getScore()));
            formatLabel(playerScore);
            scoresLst.add(playerScore);
            infoPanel.add(playerScore);
        }

        buttonPanel.add(otherPanel);
        buttonPanel.add(gamePanel);
        buttonPanel.add(infoPanel);
        buttonPanel.setVisible(true);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }

    private void formatLabel(JLabel label) {
        label.setFont(new Font("Calibre", Font.BOLD, 18));
        label.setBorder(BorderFactory.createLineBorder(Color.white));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
    }

    private void RollDice() {
        Turn turn = game.getTurn();
        ArrayList<ArrayList<Integer>> dices = turn.getDiceCombinations();
        gamePanel.setVisible(false);
        otherPanel.setVisible(false);
        gamePanel.removeAll();
        otherPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        JPanel dicePanel = new JPanel(new FlowLayout());
        dicePanel.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight() / 4));
        JLabel label1 = new JLabel("You have rolled! ");
        label1.setFont(new Font(getName(), Font.BOLD, 25));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel label2 = new JLabel("Select a combination! ");
        label2.setFont(new Font(getName(), Font.BOLD, 25));
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        dicePanel.add(new diceImage(dices.get(0).get(0)));
        dicePanel.add(new diceImage(dices.get(0).get(1)));
        dicePanel.add(new diceImage(dices.get(0).get(2)));
        dicePanel.add(new diceImage(dices.get(0).get(3)));
        otherPanel.add(label1);
        otherPanel.add(dicePanel);
        if (dices.size() == 1) {
            label2.setText("You Have Busted");
            otherPanel.add(label2);
            JButton endTurn1 = new JButton("Next Turn");
            endTurn1.setFont(new Font("Calibre", Font.BOLD, 20));
            endTurn1.setOpaque(true);
            endTurn1.setBackground(Color.white);
            endTurn1.setForeground(Color.red);
            endTurn1.addActionListener(e -> {
                game.getTurn().endTurnBust();
            });
            gamePanel.add(endTurn1);
            otherPanel.setVisible(true);
            gamePanel.setVisible(true);
            return;
        }

        otherPanel.add(label2);
        dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JPanel combinationPanel1 = new JPanel(new FlowLayout());
        combinationPanel1.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight() / 4));
        JPanel combinationPanel2 = new JPanel(new FlowLayout());
        combinationPanel2.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight() / 4));
        JPanel combinationPanel3 = new JPanel(new FlowLayout());
        combinationPanel3.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight() / 4));



        if (dices.size() > 1) {
            combinationPanel1.add(new diceImage(dices.get(1).get(0)));
            combinationPanel1.add(new diceImage(dices.get(1).get(1)));
            combinationPanel1.add(new JButton());
            combinationPanel1.add(new diceImage(dices.get(1).get(2)));
            combinationPanel1.add(new diceImage(dices.get(1).get(3)));

            JPanel littlePanel = new JPanel(new GridLayout(2,1));
            addButtons(dices.get(1),littlePanel);
            //if (addButtons((dices.get(1).get(0)+dices.get(1).get(1)),(dices.get(1).get(2))+dices.get(1).get(3),littlePanel)){
                combinationPanel1.add(littlePanel);
            //}


            /*combinationPanel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    movePiece(dices.get(0));
                }
            });*/
            gamePanel.add(combinationPanel1);
        }

        if (dices.size() > 2) {
            combinationPanel2.add(new diceImage(dices.get(2).get(0)));
            combinationPanel2.add(new diceImage(dices.get(2).get(1)));
            combinationPanel2.add(new JButton());
            combinationPanel2.add(new diceImage(dices.get(2).get(2)));
            combinationPanel2.add(new diceImage(dices.get(2).get(3)));

            JPanel littlePanel = new JPanel(new GridLayout(2,1));
            addButtons(dices.get(2),littlePanel);
           // if (addButtons((dices.get(2).get(0)+dices.get(2).get(1)),(dices.get(2).get(2))+dices.get(2).get(3),littlePanel)){
                combinationPanel2.add(littlePanel);
            //}

            /*combinationPanel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    movePiece(dices.get(1));
                }
            });*/
            gamePanel.add(combinationPanel2);
        }

        if (dices.size() > 3) {
            combinationPanel3.add(new diceImage(dices.get(3).get(0)));
            combinationPanel3.add(new diceImage(dices.get(3).get(1)));
            combinationPanel3.add(new JButton());
            combinationPanel3.add(new diceImage(dices.get(3).get(2)));
            combinationPanel3.add(new diceImage(dices.get(3).get(3)));
            JPanel littlePanel = new JPanel(new GridLayout(2,1));
            addButtons(dices.get(3),littlePanel);
            //if (addButtons((dices.get(3).get(0)+dices.get(3).get(1)),(dices.get(3).get(2))+dices.get(3).get(3),littlePanel)){
                combinationPanel3.add(littlePanel);
            //}

            /*combinationPanel3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    movePiece(dices.get(2));
                }
            });*/
            gamePanel.add(combinationPanel3);
        }

        otherPanel.setVisible(true);
        gamePanel.setVisible(true);

    }
    private boolean addButtons(ArrayList<Integer> diceCombo, JPanel panel){
        int diceRoll1 = diceCombo.get(0)+diceCombo.get(1);
        int diceRoll2 = diceCombo.get(2)+diceCombo.get(3);
        if (diceRoll1!=diceRoll2){
            System.out.print(game.checkRunners());
            System.out.println("runner list");
        if (game.checkRunners()<2){ //case: at least two runners are available
            if (validRow(diceRoll1)&&validRow(diceRoll2)){//case both columns open
                System.out.println("less then 2 runners and both buttons");
                panel.add(buildButton("Columns ".concat(Integer.toString(diceRoll1)).concat(" and ").concat(Integer.toString(diceRoll2)),diceCombo));
            }
            else if (validRow(diceRoll2)){ //case one is not open
                System.out.println("less then 2 runners only one button");
                ArrayList<Integer> newCombo = new ArrayList<>();
                newCombo.add(diceCombo.get(2));
                newCombo.add(diceCombo.get(3));
                newCombo.add(diceCombo.get(0));
                newCombo.add(diceCombo.get(1));
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll2)),newCombo));
            }
            else if (validRow(diceRoll1)){ //case other one is not open
                System.out.println("less then 2 runners only one button");
                /*ArrayList<Integer> newCombo = new ArrayList<>();
                newCombo.add(diceCombo.get(0));
                newCombo.add(diceCombo.get(1));
                newCombo.add(diceCombo.get(2));
                newCombo.add(diceCombo.get(3));*/
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll1)),diceCombo));
            }}
        else if(game.checkRunners()==2){
            if (validRow(diceRoll1)&&validRow(diceRoll2)) {
                if ((emptyRow(diceRoll1) && hasRunner(diceRoll2)) || (emptyRow(diceRoll2) && hasRunner(diceRoll1))) { //can use both
                    System.out.println("2 runners but one is empty and one has runner");
                    panel.add(buildButton("Columns ".concat(Integer.toString(diceRoll1)).concat(" and ").concat(Integer.toString(diceRoll2)),
                            diceCombo));
                } else if (hasRunner(diceRoll2) && hasRunner(diceRoll1)) {
                    System.out.println("2 runners but both have runners");
                    panel.add(buildButton("Columns ".concat(Integer.toString(diceRoll1)).concat(" and ").concat(Integer.toString(diceRoll2)),
                            diceCombo));
                } else {
                    System.out.println("2 runners but only one choice");
                    panel.add(buildButton("Column ".concat(Integer.toString(diceRoll1)), diceCombo));
                    ArrayList<Integer> newCombo = new ArrayList<>();
                    newCombo.add(diceCombo.get(2));
                    newCombo.add(diceCombo.get(3));
                    newCombo.add(diceCombo.get(0));
                    newCombo.add(diceCombo.get(1));
                    panel.add(buildButton("Column ".concat(Integer.toString(diceRoll2)), newCombo));
                }}}
        else if (game.checkRunners()==3){
            if (hasRunner(diceRoll1)&&hasRunner(diceRoll2)){
                System.out.println("3 runners both have buttons");
                panel.add(buildButton("Columns ".concat(Integer.toString(diceRoll1)).concat(" and ").concat(Integer.toString(diceRoll2)),
                        diceCombo));
            }
            else if(hasRunner(diceRoll1)){
                System.out.println("3 runners only one button");
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll1)),diceCombo));
            }
            else if (hasRunner(diceRoll2)){
                System.out.println("3 runners only one button");
                ArrayList<Integer> newCombo = new ArrayList<>();
                newCombo.add(diceCombo.get(2));
                newCombo.add(diceCombo.get(3));
                newCombo.add(diceCombo.get(0));
                newCombo.add(diceCombo.get(1));
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll2)), newCombo));
            }
        }
            }
            /*else if (validRow(diceRoll2)){ //case one is not open
                ArrayList<Integer> newCombo2 = new ArrayList<>();
                newCombo2.add(diceCombo.get(2));
                newCombo2.add(diceCombo.get(3));
                newCombo2.add(diceCombo.get(0));
                newCombo2.add(diceCombo.get(1));
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll2)),newCombo2));
                }
            else if (validRow(diceRoll1)){ //case other one is not open
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll1)),diceCombo));
            }*/
        else{
            if ((game.checkRunners()<=2&&validRow(diceRoll1)||hasRunner(diceRoll1))){
                System.out.println("same combo");
                System.out.println(diceCombo);
                panel.add(buildButton("Column ".concat(Integer.toString(diceRoll1)),diceCombo));
            }
        }
        return true;}


    private JButton buildButton(String columns, ArrayList<Integer> diceCombo){
        JButton select = new JButton(columns);
        select.setForeground(Color.white);
        select.setBackground(Color.red);
        //select.setBorder(BorderFactory.createLineBorder(Color.red));
        select.setFont(new Font("Calibrie",Font.BOLD,15));
        select.addActionListener(e->movePiece(diceCombo));
        return select;
    }
    private boolean validRow(int diceRoll){
        return checkNotCaptured(diceRoll) &&(hasRunner(diceRoll)||emptyRow(diceRoll));
    }
    private boolean hasRunner(int diceRoll){
        return game.getTurn().hasRunner(diceRoll);
    }
    private boolean emptyRow(int diceRoll){ //only called when there are available runners
        return !game.getTurn().hasRunner(diceRoll) && checkNotCaptured(diceRoll);
    }
    private boolean checkNotCaptured(int diceRoll){
        for (Player player: players){
            ArrayList<Integer> columns = player.getColumns();
            for (int columnNum: columns){
                if (columnNum==diceRoll){
                    return false;}}}
        return true;
    }

    public void movePiece(ArrayList<Integer> selected_combintion) {

        gamePanel.setVisible(false);
        otherPanel.setVisible(false);

        gamePanel.removeAll();
        otherPanel.removeAll();
        gamePanel.add(dice);
        gamePanel.add(endTurn);
        gamePanel.setVisible(true);
        otherPanel.setVisible(true);
        if (selected_combintion == null) {
            return;
        }
        game.getTurn().movePiece(selected_combintion);
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public void updateScoreLabels(){
        for(int i=0; i< players.size();i++){
            int playerScore = players.get(i).getScore();
            if (!Integer.toString(playerScore).equals(scoresLst.get(i).getText())){
                scoresLst.get(i).setText(Integer.toString(playerScore));

            }
        }
    }
    public void updateGameBoard(ArrayList<pieces> pieces) {

        pieces.forEach((e) -> {
            if (e == null) {
            } else {
                board[e.getColumn()][e.getRow()].setVisible(false);
                //if (board[e.getColumn()][e.getRow()].getBackground().equals(Color.red)){
                board[e.getColumn()][e.getRow()].add(e);//}
                board[e.getColumn()][e.getRow()].setVisible(true);

            }
        });
        setCurrentPlayer();
    }

    public void setCurrentPlayer() {
        Player current = game.getCurrentPlayer();
        for (JLabel label : labelsLst) {
            if (label.getText().equals(current.getName())) {
                label.setForeground(Color.red);
                label.setBackground(Color.white);
            } else {
                label.setBackground(Color.red);
                label.setForeground(Color.white);
            }
        }
    }

    public void removeRunners(ArrayList<pieces> pieces) {
        pieces.forEach((e) -> board[e.getColumn()][e.getRow()].remove(e));
    }

    private void newGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?",
                "Start a new Game", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Process further if user confirms
            this.dispose();
            new StartUp();
        }
    }

    private void quitGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "Quit Game",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Process further if user confirms
            System.exit(0);
        }
    }

}
