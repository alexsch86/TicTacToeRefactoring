package tictactoe.listeners;

import tictactoe.GameLogic;
import tictactoe.TicTacToeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tictactoe.GameLogic.gameLogicInstance;
import static tictactoe.model.TableCharacter.X;
import static tictactoe.model.TableCharacter.ZERO;

public class TicTacToeGameController implements ActionListener {

    private final TicTacToeFrame ticTacToeFrame;
    private GameLogic gameLogic = gameLogicInstance();

    public TicTacToeGameController(TicTacToeFrame ticTacToeFrame) {
        this.ticTacToeFrame = ticTacToeFrame;
        gameLogic.initGameLogicData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        int positionOfButton = ticTacToeFrame.getPosition(jButton);

        if(gameLogic.isGameRunning())	{
            
            if(gameLogic.isPlayerXTurn()){
                gameLogic.getBoardState().setStateAtPosition(positionOfButton, X);
            } else {
                gameLogic.getBoardState().setStateAtPosition(positionOfButton, ZERO);
            }

            gameLogic.incrementGameTurn();
            gameLogic.checkGameIsOver();

            if(gameLogic.isCPUGame()) {
                if (gameLogic.isNotAWinningCombination() && ! gameLogic.isWin()) {
                    this.gameLogic.doAI();
                }
                this.ticTacToeFrame.refreshTableBoard(gameLogic.getBoardState());
            }
            
            if(gameLogic.isWin()) {
                ticTacToeFrame.markWinningPositionAndEnd(gameLogic.getWinningCombination());
            }
            
            displayResults();
        }
    }

    private void displayResults() {
       if(gameLogic.isWin() || gameLogic.isGameOver())	{
            if(gameLogic.isWin())	{
                ticTacToeFrame.displayPlayerHasWon(gameLogic.getPreviousPlayer());
            } else {
                ticTacToeFrame.displayPlayersHaveTied();
            }
            
            ticTacToeFrame.resetTryAgainAndContinue();
        } else {
           ticTacToeFrame.displayPlayerTurn(gameLogic.getCurrentPlayer());
        }
        
    }

}
