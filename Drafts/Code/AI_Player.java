import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AI_Player extends Player {
    // Variables for AI Logic

    AI_Player(String shape, String color, String name) {

        super(shape, color, name);
        this.AIPlayer = true;
    }

    public void clickRollButton() {

        super.rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        ActionListener[] actionListeners = rollButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(super.rollButton,
                    ActionEvent.ACTION_PERFORMED, ""));
        }

    }

    // AI Logic

}
