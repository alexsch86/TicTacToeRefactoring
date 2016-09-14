package tictactoe;

import tictactoe.model.BoardState;
import tictactoe.model.GameType;
import tictactoe.model.Player;
import tictactoe.model.TableCharacter;

import static tictactoe.model.GameType.PLAYER_VS_CPU;
import static tictactoe.model.Player.FIRST;
import static tictactoe.model.Player.SECOND;

public class GameLogic {
    
    static final int NUMBER_OF_BUTTONS = 9;
    
    public static final int NOT_A_WINING_COMBINATION = -1;
    private static final int NOT_A_POSITION = -1;
    private static GameLogic gameLogic;

    private BoardState boardState;
    private GameType gameType;
    
    private boolean inGame;
    private boolean win;
    private int winningCombinationIndex;
    private int turn;

    private GameLogic() {
    }
    
    public static GameLogic gameLogicInstance() {
        if(gameLogic == null) {
            gameLogic = new GameLogic();
        }
        return gameLogic;
    }
    
    public void initGameLogicData() {
        turn = 1;
        win = false;
        inGame = false;
        winningCombinationIndex = NOT_A_WINING_COMBINATION;
        boardState = new BoardState();
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
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

    public void checkGameIsOver()	{	
        int winingCombinationIfExisting = getWiningCombinationIfExisting();
        if(winingCombinationIfExisting != NOT_A_WINING_COMBINATION) {
            win = true;
            winningCombinationIndex = winingCombinationIfExisting;
        }
        
    }

    private int getWiningCombinationIfExisting() {
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

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameType getGameType() {
        return gameType;
    }

    public boolean isCPUGame() {
        return gameType == PLAYER_VS_CPU;
    }

    public boolean isNotAWinningCombination() {
        return getWiningCombinationIfExisting() == NOT_A_WINING_COMBINATION;
    }

    public boolean isGameOver() {
        return ! isGameRunning();
    }

    public Player getCurrentPlayer() {
        return isPlayerXTurn() ? FIRST : SECOND;
    }

    public Player getPreviousPlayer() {
        return isPlayerXTurn() ? SECOND : FIRST;
    }
}
