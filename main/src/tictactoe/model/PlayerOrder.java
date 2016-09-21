package tictactoe.model;

/**
 * Created by alexands on 07.09.2016.
 */
public enum PlayerOrder {
    FIRST(TableCharacter.X),
    SECOND(TableCharacter.ZERO);

    private final TableCharacter tableCharacter;

    PlayerOrder(TableCharacter tableCharacter) {
        this.tableCharacter = tableCharacter;
    }

    public TableCharacter getTableCharacter() {
        return tableCharacter;
    }
    
}
