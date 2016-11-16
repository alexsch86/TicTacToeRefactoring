package tictactoe.model;

import java.util.Arrays;

import static tictactoe.model.TableCharacter.EMPTY;
import static tictactoe.model.TableCharacter.X;
import static tictactoe.model.TableCharacter.ZERO;

/**
 * Created by alexands on 16.06.2016.
 */
public class BoardState {

    static final int NOT_A_POSITION = -1;

    private final int winningCombinations[][] = new int[][]{
            {1, 2, 3}, {1, 4, 7}, {1, 5, 9},
            {4, 5, 6}, {2, 5, 8}, {3, 5, 7},
            {7, 8, 9}, {3, 6, 9}
    };
    
    private final TableCharacter[] STATE;

    public BoardState() {
        STATE = initializeBoard();
    }

    private TableCharacter[] initializeBoard() {
        return new TableCharacter[] {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
    }

    /**
     * 
     * @param line non-0 index based
     * @param column non-0 index based
     * @return  state of the cell
     */
    public TableCharacter getStateAtCell(int line, int column) {
        int arrayPosition = (line - 1) * 3 + column - 1;
        return STATE[arrayPosition];
    }

    public void setStateAtPosition(int zeroBasedPosition, TableCharacter tableCharacter) {
        this.STATE[zeroBasedPosition] = tableCharacter;
    }

    public TableCharacter getStateAtPosition(int position) {
        return STATE[position];
    }

    public boolean isWinningCombination(int winningCombinationIndex) {
        return getStateAtPosition(winningCombinations[winningCombinationIndex][0] - 1) != TableCharacter.EMPTY &&
                getStateAtPosition(winningCombinations[winningCombinationIndex][0] - 1) == getStateAtPosition(winningCombinations[winningCombinationIndex][1] - 1) &&
                getStateAtPosition(winningCombinations[winningCombinationIndex][1] - 1) == getStateAtPosition(winningCombinations[winningCombinationIndex][2] - 1);
    }

    public int[] getWinCombination0BasedIndexes(int winningCombinationIndex) {
        return Arrays.stream(winningCombinations[winningCombinationIndex]).map(index -> index - 1).toArray();
    }

    public int getNextMoveForCPU()	{
        return getNextCellPositionForCPU() - 1;
    }
    
    private int getNextCellPositionForCPU() {
        Arrays.stream(winningCombinations).filter(arr -> {return isWithTwoCellsZeroAndThirdCellEmpty(arr); }).findFirst();

        if(getStateAtCell(1, 1)== ZERO && getStateAtCell(1, 2)== ZERO && getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(getStateAtCell(2, 1)== ZERO && getStateAtCell(2, 2)== ZERO && getStateAtCell(2, 3)== EMPTY)
            return 6;
        else if(getStateAtCell(3, 1)== ZERO && getStateAtCell(3, 2)== ZERO && getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(getStateAtCell(1, 2)== ZERO && getStateAtCell(1, 3)== ZERO && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(2, 2)== ZERO && getStateAtCell(2, 3)== ZERO && getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(getStateAtCell(3, 2)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(3, 1)== EMPTY)
            return 7;

        else if(getStateAtCell(1, 1)== ZERO && getStateAtCell(1, 3)== ZERO && getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(getStateAtCell(2, 1)== ZERO && getStateAtCell(2, 3)== ZERO && getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(getStateAtCell(3, 1)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(3, 2)== EMPTY)
            return 8;

        else if(getStateAtCell(1, 1)== ZERO && getStateAtCell(2, 1)== ZERO && getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(getStateAtCell(1, 2)== ZERO && getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 2)== EMPTY)
            return 8;
        else if(getStateAtCell(1, 3)== ZERO && getStateAtCell(2, 3)== ZERO && getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(getStateAtCell(2, 1)== ZERO && getStateAtCell(3, 1)== ZERO && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 2)== ZERO && getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(getStateAtCell(2, 3)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(1, 3)== EMPTY)
            return 3;

        else if(getStateAtCell(1, 1)== ZERO && getStateAtCell(3, 1)== ZERO && getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(getStateAtCell(1, 2)== ZERO && getStateAtCell(3, 2)== ZERO && getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(getStateAtCell(1, 3)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(2, 3)== EMPTY)
            return 6;

        else if(getStateAtCell(1, 1)== ZERO && getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 3)== EMPTY)
            return 9;
        else if(getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(1, 1)== ZERO && getStateAtCell(3, 3)== ZERO && getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(getStateAtCell(1, 3)== ZERO && getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(getStateAtCell(3, 1)== ZERO && getStateAtCell(2, 2)== ZERO && getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(getStateAtCell(3, 1)== ZERO && getStateAtCell(1, 3)== ZERO && getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(1, 2)== X && getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(getStateAtCell(2, 1)== X && getStateAtCell(2, 2)== X && getStateAtCell(2, 3)== EMPTY)
            return 6;
        else if(getStateAtCell(3, 1)== X && getStateAtCell(3, 2)== X && getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(getStateAtCell(1, 2)== X && getStateAtCell(1, 3)== X && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(2, 2)== X && getStateAtCell(2, 3)== X && getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(getStateAtCell(3, 2)== X && getStateAtCell(3, 3)== X && getStateAtCell(3, 1)== EMPTY)
            return 7;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(1, 3)== X && getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(getStateAtCell(2, 1)== X && getStateAtCell(2, 3)== X && getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(getStateAtCell(3, 1)== X && getStateAtCell(3, 3)== X && getStateAtCell(3, 2)== EMPTY)
            return 8;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(2, 1)== X && getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(getStateAtCell(1, 2)== X && getStateAtCell(2, 2)== X && getStateAtCell(3, 2)== EMPTY)
            return 8;
        else if(getStateAtCell(1, 3)== X && getStateAtCell(2, 3)== X && getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(getStateAtCell(2, 1)== X && getStateAtCell(3, 1)== X && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(2, 2)== X && getStateAtCell(3, 2)== X && getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(getStateAtCell(2, 3)== X && getStateAtCell(3, 3)== X && getStateAtCell(1, 3)== EMPTY)
            return 3;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(3, 1)== X && getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(getStateAtCell(1, 2)== X && getStateAtCell(3, 2)== X && getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(getStateAtCell(1, 3)== X && getStateAtCell(3, 3)== X && getStateAtCell(2, 3)== EMPTY)
            return 6;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(2, 2)== X && getStateAtCell(3, 3)== EMPTY)
            return 9;
        else if(getStateAtCell(2, 2)== X && getStateAtCell(3, 3)== X && getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(getStateAtCell(1, 1)== X && getStateAtCell(3, 3)== X && getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(getStateAtCell(1, 3)== X && getStateAtCell(2, 2)== X && getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(getStateAtCell(3, 1)== X && getStateAtCell(2, 2)== X && getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(getStateAtCell(3, 1)== X && getStateAtCell(1, 3)== X && getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(getStateAtCell(1, 1)== X && getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 3)== X)
            return 6;

        else if(getStateAtCell(1, 3)== X && getStateAtCell(2, 2)== ZERO && getStateAtCell(3, 1)== X)
            return 4;

        else if(getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(getStateAtCell(1, 1)== EMPTY)
            return 1;
        else
            return NOT_A_POSITION + 1;
    }

    private boolean isWithTwoCellsZeroAndThirdCellEmpty(int[] winCombination) {
        return availableCellForWinning(getStateAtPosition(winCombination[0] - 1),
                getStateAtPosition(winCombination[1] - 1), getStateAtPosition(winCombination[2] - 1)) != NOT_A_POSITION;
    }

    // compare if two are equal and third is empty
    private int availableCellForWinning(TableCharacter character, TableCharacter character1, TableCharacter character2) {
//        if(character == EMPTY) {
//            return character1 == ZERO && character2 == ZERO ?
//        }
//        return charactersAreZero;
        return 0;
    }


}
