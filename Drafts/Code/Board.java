import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Board extends JFrame{
    private Tile[][] board;
    private ArrayList<Player> players;
    private Game game;
    private JPanel gamePanel;
    private JPanel otherPanel;
    private JMenuBar saveMenu;
    public Board(Game game,ArrayList<Player> players){
        this.players = players;
        this.game = game;
        this.setSize(new Dimension(1000,900));
        saveMenu = new JMenuBar();
        JMenuItem saveButton = new JMenuItem("Save");
        saveButton.addActionListener(e -> {game.saveGame();});
        saveMenu.add(saveButton);
        this.setJMenuBar(saveMenu);
        getContentPane().setLayout(new BorderLayout());
        buildBoard();
        buildSide();
        setResizable(true);
        setVisible(true);

    }
    private void buildBoard(){
        JPanel boardPanel = new JPanel(new GridLayout(13,13));
        boardPanel.setBackground(new Color(149, 240, 252));
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        board = new Tile[13][13];
        for (int x=0; x<13; x++) {
            for (int y = 0; y < 13; y++) {
                if (checkTile(x,y)){
                    board[x][y] = new Tile(x, y, true);
                    board[x][y].setFont(new Font("Calibre", Font.BOLD,20));
                    board[x][y].setForeground(Color.red);
                }
                else {board[x][y] = new Tile(x, y, false);}
                setLabels(x,y);
                boardPanel.add(board[x][y]);
            }
        }


    }
    private void setLabels(int x, int y){
        if ((x==5&&y==1)){
            board[x][y].setText("2");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==4&&y==2)){
            board[x][y].setText("3");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==3&&y==3)){
            board[x][y].setText("4");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==2&&y==4)){
            board[x][y].setText("5");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==1&&y==5)){
            board[x][y].setText("6");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==0&&y==6)){
            board[x][y].setText("7");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==1&&y==7)){
            board[x][y].setText("8");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==2&&y==8)){
            board[x][y].setText("9");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==3&&y==9)){
            board[x][y].setText("10");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==4&&y==10)){
            board[x][y].setText("11");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
        else if ((x==5&&y==11)){
            board[x][y].setText("12");
            board[x][y].setBackground(Color.white);
            board[x][y].setEndTile();
        }
    }

    private boolean checkTile(int x, int y){
        if (y==0 || y==12) {return false;}
        else if ((y==1 || y==11)&& (x<5 || x>7)){return false;}
        else if (((y==2 || y==10)&& (x<4 || x>8))){return false;}
        else if ((y==3 || y==9)&& (x<3 || x>9)){return false;}
        else if ((y==4 || y==8)&& (x<2 || x>10)){return false;}
        else if ((y==5 || y==7)&& (x<1 || x>11)){return false;}
        else {return true;}
    }

    private void buildSide(){
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        otherPanel = new JPanel(new GridLayout(3, 1));
        gamePanel = new JPanel(new GridLayout(3,1));
        JPanel infoPanel = new JPanel(new GridLayout(players.size()+1,3));
        gamePanel.setBackground(Color.red);
        otherPanel.setBackground(Color.red);
        infoPanel.setBackground(Color.red);

        JButton dice = new JButton("Roll Dice");
        dice.setFont(new Font("Calibre",Font.BOLD,20));
        dice.addActionListener(e-> {RollDice();});
        dice.setOpaque(true);
        dice.setBackground(Color.white);
        dice.setForeground(Color.red);
        gamePanel.add(dice);

        JButton endTurn = new JButton("End Turn");
        endTurn.setFont(new Font("Calibre",Font.BOLD,20));
        endTurn.setOpaque(true);
        endTurn.setBackground(Color.white);
        endTurn.setForeground(Color.red);
        gamePanel.add(endTurn);

        JLabel playersNames = new JLabel("Players");
        formatLabel(playersNames);
        infoPanel.add(playersNames);
        JLabel scores = new JLabel("Scores");
        formatLabel(scores);
        infoPanel.add(scores);

        for (Player player : players){
            JLabel playerName = new JLabel(player.getName());
            formatLabel(playerName);
            infoPanel.add(playerName);
            JLabel playerScore = new JLabel(Integer.toString(player.getScore()));
            formatLabel(playerScore);
            infoPanel.add(playerScore);
        }

        buttonPanel.add(otherPanel);
        buttonPanel.add(gamePanel);
        buttonPanel.add(infoPanel);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }
    
    private void formatLabel(JLabel label){
        label.setFont(new Font("Calibre",Font.BOLD,18));
        label.setBorder(BorderFactory.createLineBorder(Color.white));
        label.setOpaque(true);
        label.setBackground(Color.red);
        label.setForeground(Color.white);
    }

    private void RollDice(){
        Turn turn = game.getTurn();
        ArrayList<ArrayList<Integer>> dices =  turn.getDiceCombinations();
        gamePanel.setVisible(false);
        otherPanel.setVisible(false);
        gamePanel.removeAll();
        otherPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //gamePanel.setLayout((new GridLayout(5, 5)));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        
        JPanel dicePanel = new JPanel(new FlowLayout());
        dicePanel.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight()/4));
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
        if (dices == null){
            JLabel label3 = new JLabel("You Have busted!");
            JButton next_turn = new JButton("Next Turn");
            next_turn.addActionListener((ActionEvent e)->{turn_end_bust();});
            label3.setFont(new Font(getName(), Font.BOLD, 25));
            label3.setForeground(Color.white);
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            dicePanel.add(label3);
            dicePanel.add(next_turn);
            
            return;
        }
        otherPanel.add(dicePanel);
        //otherPanel.add(label2);
        dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
    

        JPanel combinationPanel1 = new JPanel(new FlowLayout());
        combinationPanel1.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight()/4));
        JPanel combinationPanel2 = new JPanel(new FlowLayout());
        combinationPanel2.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight()/4));
        JPanel combinationPanel3 = new JPanel(new FlowLayout());
        combinationPanel3.setSize(new Dimension(otherPanel.getWidth(), otherPanel.getHeight()/4));

        if (dices.size() >1 ){
        combinationPanel1.add(new diceImage(dices.get(0).get(0)));
        combinationPanel1.add(new diceImage(dices.get(0).get(1)));
        combinationPanel1.add(new JButton());
        combinationPanel1.add(new diceImage(dices.get(0).get(2)));
        combinationPanel1.add(new diceImage(dices.get(0).get(3)));
        //combinationPanel1.addActionListener(ActionEvent());
        gamePanel.add(combinationPanel1);
        }

        if(dices.size()>2){
        combinationPanel2.add(new diceImage(dices.get(1).get(0)));
        combinationPanel2.add(new diceImage(dices.get(1).get(1)));
        combinationPanel2.add(new JButton());
        combinationPanel2.add(new diceImage(dices.get(1).get(2)));
        combinationPanel2.add(new diceImage(dices.get(1).get(3)));
        gamePanel.add(combinationPanel2);
        }

        if(dices.size()>3){
        combinationPanel3.add(new diceImage(dices.get(2).get(0)));
        combinationPanel3.add(new diceImage(dices.get(2).get(1)));
        combinationPanel3.add(new JButton());
        combinationPanel3.add(new diceImage(dices.get(2).get(2)));
        combinationPanel3.add(new diceImage(dices.get(2).get(3)));
        }

        gamePanel.add(combinationPanel3);
        otherPanel.setVisible(true);
        gamePanel.setVisible(true);

    }

    public void turn_end_bust(){

    }

}
