package tictactoe;

import tictactoe.model.BoardState;
import tictactoe.model.TableCharacter;

public class GameLogic {
    
    public static final int NUMBER_OF_BUTTONS = 9;
    
    public static final int NOT_A_WINING_COMBINATION = -1;
    public static final int NOT_A_POSITION = -1;
    private int turn;

    private boolean inGame, win;
    private final BoardState boardState;
    private int winningCombinationIndex;

    public GameLogic() {
        turn = 1;
        winningCombinationIndex = NOT_A_WINING_COMBINATION;
        boardState = new BoardState();
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public boolean isGameRunning() {
        return turn < 10;
    }

    public boolean isPlayerXTurn() {
        return !(turn % 2 == 0);
    }

    public void incrementGameTurn() {
        turn++;
    }

    public void doAI()	{
        int computerButton;
        if(turn <= 9)	{
            turn++;
            computerButton = CPU.doMove(boardState);
            if(computerButton == NOT_A_POSITION) {
                doRandomMove();
            } else {
                boardState.setStateAtPosition(computerButton, TableCharacter.ZERO);
            }
            // check win
            checkGameIsOver();
            
        }
    }

    public boolean isWin() {
        return win;
    }

    public int[] getWinningCombination() {
        return boardState.getWinCombination0BasedIndexes(winningCombinationIndex);
    }

    public void checkGameIsOver()	{	//	checks if there are 3 symbols in a row vertically, diagonally, or horizontally.
        //	then shows a message and disables buttons. If the game is over then it asks
        //	if you want to play again.
        int winingCombinationIfExisting = getWiningCombinationIfExisting();
        if(winingCombinationIfExisting != NOT_A_WINING_COMBINATION) {
            win = true;
            winningCombinationIndex = winingCombinationIfExisting;
        }
        
//        if(win || (!win && turn > NUMBER_OF_BUTTONS))	{
//            if(win)	{
//                if(buttonsOfTableBoard[wonNumber1].getText().equals("X"))	{
//                    message = player1Name + " has won";
//                    winCounterPlayer1++;
//                } else	{
//                    message = player2Name + " has won";
//                    winCounterPlayer2++;
//                }
//                
//            } else if(!win && turn>NUMBER_OF_BUTTONS) {
//                message = "Both players have tied!\nBetter luck next time.";
//            }
//            
//            showMessage(message);
//            for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
//                buttonsOfTableBoard[i].setEnabled(false);
//            }
//            btnTryAgain.setEnabled(true);
//            checkWinStatus();
//        } else {
//            checkTurn();
//        }
    }

    public void checkTurn()	{
        String whoTurn;
//        if(!(turn % 2 == 0))	{
//            whoTurn = player1Name + " [X]";
//        }	else	{
//            whoTurn = player2Name + " [O]";
//        }
//        lblTurn.setText("Turn: " + whoTurn);
    }

    public int getWiningCombinationIfExisting() {
        for(int i=0; i < 8; i++)	{   
            if(boardState.isCombinationForWinSituation(i)) {
                return i;
            }
        }
        return NOT_A_WINING_COMBINATION;
    }

    

    private void doRandomMove()	{
        int randomPosition = 0;
        if(turn <= 9)	{
            randomPosition = (int)(Math.random() * 9);
            if(isAvailableCell(randomPosition))	{
                boardState.setStateAtPosition(randomPosition, TableCharacter.ZERO);
            } else {
                doRandomMove();
            }
        }
    }

    private boolean isAvailableCell(int randomPosition) {
        return boardState.getStateAtPosition(randomPosition) == TableCharacter.EMPTY;
    }

}
