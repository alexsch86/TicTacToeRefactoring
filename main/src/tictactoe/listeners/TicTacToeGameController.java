package tictactoe.listeners;

import tictactoe.model.GameLogic;
import tictactoe.view.TicTacToeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static tictactoe.model.GameLogic.gameLogicInstance;
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
        int positionOfButton = ticTacToeFrame.getPositionOfButtonPressed((JButton) e.getSource());

        if(gameLogic.isGameRunning())	{
            
            if(gameLogic.isPlayerXTurn()){
                gameLogic.setBoardState(positionOfButton, X);
            } else {
                gameLogic.setBoardState(positionOfButton, ZERO);
            }

            gameLogic.incrementGameTurn();
            gameLogic.checkGameIsOver();

            if(isCPUMoveTime()) {
                this.gameLogic.doAI();
            }
            this.ticTacToeFrame.refreshTableBoard(gameLogic.getTableCharacters());
            
            if(gameLogic.isWin()) {
                ticTacToeFrame.markWinningPositionAndEnd(gameLogic.getWinningCombination());
            }
            
            displayResults();
        }
    }

    private boolean isCPUMoveTime() {
        return gameLogic.isCPUGame() && 
                gameLogic.isNotAWinningCombination() &&
                !gameLogic.isWin();
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
