public class Player {
    private String name, color, shape;
    public Player(String shape, String color, String name){
        this.shape = shape;
        this.name = name;
        this.color = color;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
