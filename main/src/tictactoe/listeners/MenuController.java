package tictactoe.listeners;

import tictactoe.GameLogic;
import tictactoe.TicTacToeFrame;
import tictactoe.model.Operation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tictactoe.GameLogic.gameLogicInstance;
import static tictactoe.model.Operation.*;

public class MenuController implements ActionListener {
    
    private TicTacToeFrame ticTacToeFrame;
    private GameLogic gameLogic = gameLogicInstance();

    public MenuController(TicTacToeFrame ticTacToeFrame) {
        this.ticTacToeFrame = ticTacToeFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Operation operation = ticTacToeFrame.getOperation(source);
        
        switch (operation) {
            case PLAY_HUMAN_VS_HUMAN:
            case PLAY_HUMAN_VS_CPU:
                if(gameLogic.isInGame())	{
                    gameLogic.setInGame(ticTacToeFrame.startNewGameWhileGameRunning());
                } else	{
                    initializeNewGame(source);
                }
                break;
            case CONTINUE_GAME:
                ticTacToeFrame.displayLayoutOfBoard();
                break;
            case SET_PLAYER_NAMES:
                ticTacToeFrame.askUserForPlayerNames();
                break;
            case EXIT_MENU:
                askAndExecuteExit();
                break;
            case TRY_AGAIN:
                initializeNewGame(source);
                break;
            case QUIT:
                ticTacToeFrame.quit();
                break;
            case NEW_GAME:
            case SHOW_INSTRUCTIONS:
            case SHOW_ABOUT:
                ticTacToeFrame.clearPanelSouth();
                ticTacToeFrame.setDefaultLayout();

                addPanelToFrameBasedOnMenuItemOperation(operation);
                ticTacToeFrame.addTopPanelToMainPanel();
                break;
        }

        ticTacToeFrame.resetMainPanelVisibility();
    }

    private void askAndExecuteExit() {
        int option = ticTacToeFrame.askMessage(
                "Are you sure you want to exit?",
                "Exit Game",
                JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void addPanelToFrameBasedOnMenuItemOperation(Operation operation) {
        switch(operation) {
            case NEW_GAME:
                ticTacToeFrame.addNewGamePanelToTopPanel();
                break;
            case SHOW_INSTRUCTIONS:
                ticTacToeFrame.displayInstructionPopup();
                break;
            case SHOW_ABOUT:                
                ticTacToeFrame.displayAboutPopup();
                break;
        }
    }

    private void initializeNewGame(Object source) {
        gameLogic.setGameType(ticTacToeFrame.getTypeOfGame(source));
        gameLogic.initGameLogicData();
        ticTacToeFrame.startNewGame(gameLogic.getGameType());
    }


}
