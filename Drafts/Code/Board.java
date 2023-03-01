import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    private Tile[][] board;
    public Board(){
        this.setSize(new Dimension(600,600));
        buildBoard();
        getContentPane().setLayout(new GridLayout(13,13));
        setResizable(false);
        setVisible(true);

    }
    private void buildBoard(){
        board = new Tile[13][13];
        for (int x=0; x<13; x++) {
            for (int y = 0; y < 13; y++) {
                if (checkTile(x,y)){
                    board[x][y] = new Tile(x, y, true);
                    board[x][y].setFont(new Font("Calibre", Font.BOLD,16));
                    board[x][y].setForeground(Color.red);
                }
                else {board[x][y] = new Tile(x, y, false);}
                setLabels(x,y);
                getContentPane().add(board[x][y]);
            }
        }


    }
    private void setLabels(int x, int y){
        if ((x==5&&y==1)){
            board[x][y].setText("2");
            board[x][y].setBackground(Color.white);}
        else if ((x==4&&y==2)){
            board[x][y].setText("3");
            board[x][y].setBackground(Color.white);}
        else if ((x==3&&y==3)){
            board[x][y].setText("4");
            board[x][y].setBackground(Color.white);}
        else if ((x==2&&y==4)){
            board[x][y].setText("5");
            board[x][y].setBackground(Color.white);}
        else if ((x==1&&y==5)){
            board[x][y].setText("6");
            board[x][y].setBackground(Color.white);}
        else if ((x==0&&y==6)){
            board[x][y].setText("7");
            board[x][y].setBackground(Color.white);}
        else if ((x==1&&y==7)){
            board[x][y].setText("8");
            board[x][y].setBackground(Color.white);}
        else if ((x==2&&y==8)){
            board[x][y].setText("9");
            board[x][y].setBackground(Color.white);}
        else if ((x==3&&y==9)){
            board[x][y].setText("10");
            board[x][y].setBackground(Color.white);}
        else if ((x==4&&y==10)){
            board[x][y].setText("11");
            board[x][y].setBackground(Color.white);}
        else if ((x==5&&y==11)){
            board[x][y].setText("12");
            board[x][y].setBackground(Color.white);}
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
}
