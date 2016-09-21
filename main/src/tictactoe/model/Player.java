package tictactoe.model;

import static tictactoe.model.PlayerOrder.SECOND;
import static tictactoe.model.PlayerType.CPU;
import static tictactoe.model.PlayerType.HUMAN;

/**
 * Created by alexands on 21.09.2016.
 */
public class Player {

    public static final String COMPUTER_NAME = "COMPUTER";
    
    private PlayerType type;
    private PlayerOrder order;
    private String name;
    
    private Player(PlayerType type, PlayerOrder order, String name) {
        this.type = type;
        this.order = order;
        this.name = name;
    }

    public static Player newHumanPlayer(PlayerOrder playerOrder, String name) {
        return new Player(HUMAN, playerOrder, name);
    }
    
    public static Player newCPUPlayer() {
        return new Player(CPU, SECOND, COMPUTER_NAME);
    } 
    
}
