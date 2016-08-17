package tictactoe.model;

/**
 * Created by alexands on 16.06.2016.
 */
public enum TableCharacter {
    EMPTY(""),
    ZERO("0"),
    X("X");

    private final String character;

    TableCharacter(String character) {
        this.character = character;
    }
    
    public String getCharacter() {
        return character;
    }
    
}
