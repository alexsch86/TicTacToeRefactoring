package tictactoe.model;

import javax.swing.*;

/**
 * Created by alexands on 20.10.2016.
 */
public enum Operation {
    
    NEW_GAME("New Game"),
    SHOW_INSTRUCTIONS("Instructions"),
    SHOW_ABOUT("About"),
    PLAY_HUMAN_VS_HUMAN("Player vs Player"),
    PLAY_HUMAN_VS_CPU("Player vs Computer"), 
    QUIT("Quit"), 
    CONTINUE_GAME("Continue..."), 
    SET_PLAYER_NAMES("Set Player Names"), 
    EXIT_MENU("Exit"), 
    TRY_AGAIN("Try Again?");

    private final String textName;

    Operation(String textName) {
        this.textName = textName;
    }

    public String getText() {
        return textName;
    }
}
