import java.util.ArrayList;
import java.util.Random;

public class Dice {
    private Random rand;

    public Dice(){
        rand = new Random();
    }
    
    public ArrayList<Integer> makeTurnRoll(){
        ArrayList<Integer> turnRolls = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            turnRolls.add(roll());
        }
        return turnRolls;
    }

    public ArrayList<Integer> getTurnOrderRolls(int numOfPlayers){
        ArrayList<Integer> turnOrderRolls = new ArrayList<Integer>();
        for (int i=0; i<numOfPlayers; i++) {
            turnOrderRolls.add(roll() + roll());
        }

        return turnOrderRolls;
    }

    private int roll(){
        return (rand.nextInt(6) + 1);
    }
}
