import java.util.ArrayList;

public class Player {
    private String name, color, shape;
    private ArrayList<Integer> columnsCaptured;
    private ArrayList<pieces> piecesList = new ArrayList<pieces>();
    public Player(String shape, String color, String name){
        this.shape = shape;
        this.name = name;
        this.color = color;
        columnsCaptured = new ArrayList<>();
        for (int i =0; i< 11; i++){
            piecesList.add(null);
        }
    }

    public int getScore(){
        return columnsCaptured.size();
    }

    //Note for future. This is the logic for choosing a winner. Right now it is simulated randomly in Board class due to some missing implementation of other classes.
    public void captureColumn(int column){
        columnsCaptured.add(column);
        if(this.checkWinner()){
            displayWinner();
        }
    }

    private boolean checkWinner(){
        return (this.getScore() == 3) ? true : false;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public ArrayList<pieces> getPieces() {
        return piecesList;
    }
    private void displayWinner(){

        //This is and Winnerdisplay are proboably gonna have to change or this get moved so that we have access to board
        /* 
        Player winner = players.get((int) Math.random()*players.size());
        winningDisplay winningmessage = new winningDisplay(this,winner);
        */
    }

    public ArrayList<Integer> getColumns(){
        return columnsCaptured;
    }

    public pieces getPieceInColumn(int col){
        if(this.piecesList.get(col-2) == null){
            return null;
            
        }else {return this.piecesList.get(col-2);}
    }
    
    public void updatePieces(ArrayList<pieces> runners){
        runners.forEach(e -> {
            if(piecesList.get(e.getRow()-1) == null){
                pieces temp = new pieces(shape, color);
                temp.setLocation(e.getTile());
                piecesList.set(e.getRow()-1,temp);
            }else{
                piecesList.get(e.getRow()-1).setLocation(e.getTile());
            }
        });
    }
}
