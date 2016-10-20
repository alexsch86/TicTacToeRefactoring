package tictactoe.model;

import javax.swing.*;

/**
 * Created by alexands on 20.10.2016.
 */
public class GameMenuItem extends JMenuItem implements IOperation {

    private final Operation operation;

    public GameMenuItem(Operation operation) {
        super(operation.getText());
        
        this.operation = operation;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }
    
}
