import java.util.ArrayList;

public class Player {
    private String name, color, shape;
    private ArrayList<Integer> columnsCaptured;
    public Player(String shape, String color, String name){
        this.shape = shape;
        this.name = name;
        this.color = color;
        columnsCaptured = new ArrayList<>();
    }
    public int getScore(){
        return columnsCaptured.size();
    }
    public void captureColumn(int column){
        columnsCaptured.add(column);
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
}
