package tictactoe.listeners;

import tictactoe.GameLogic;
import tictactoe.TicTacToeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tictactoe.GameLogic.gameLogicInstance;

public class MenuController implements ActionListener {
    
    private TicTacToeFrame ticTacToeFrame;
    private GameLogic gameLogic = gameLogicInstance();

    public MenuController(TicTacToeFrame ticTacToeFrame) {
        this.ticTacToeFrame = ticTacToeFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(ticTacToeFrame.isNewGamePushed(source) || ticTacToeFrame.isMenuInstructionPushed(source) || ticTacToeFrame.isMenuAboutPushed(source))	{
            ticTacToeFrame.clearPanelSouth();
            ticTacToeFrame.setDefaultLayout();

            if(ticTacToeFrame.isNewGamePushed(source))	{
                ticTacToeFrame.addNewGamePanelToTopPanel();
            } else {
                if(ticTacToeFrame.isMenuInstructionPushed(source))	{
                    ticTacToeFrame.displayInstructionPopup();
                    
                } else	{//About
                    ticTacToeFrame.displayAboutPopup();
                }
            }
            ticTacToeFrame.addTopPanelToMainPanel();
            
        } else if(ticTacToeFrame.isGameHumanVsHuman(source) || ticTacToeFrame.isGameHumanVersusComputer(source))	{
            if(gameLogic.isInGame())	{
                gameLogic.setInGame(ticTacToeFrame.startNewGameWhileGameRunning());
            } else	{
                initializeNewGame(source);
            }
        } else if(ticTacToeFrame.isContinueButtonPushed(source))	{
            ticTacToeFrame.displayLayoutOfBoard();
        }
//        else if(source == btnSetName)	{
//            askUserForPlayerNames();
//        }
        else if(ticTacToeFrame.isExitMenuPushed(source))	{
            int option = ticTacToeFrame.askMessage(
                    "Are you sure you want to exit?",
                    "Exit Game",
                    JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if(ticTacToeFrame.isTryAgainButtonPushed(source))	{
            initializeNewGame(source);
        } else if(ticTacToeFrame.isQuitButtonPushed(source))	{
            ticTacToeFrame.quit();
        }

        ticTacToeFrame.resetMainPanelVisibility();
    }

    private void initializeNewGame(Object source) {
        gameLogic.setGameType(ticTacToeFrame.getTypeOfGame(source));
        gameLogic.initGameLogicData();
        ticTacToeFrame.startNewGame(gameLogic.getGameType());
    }


}
