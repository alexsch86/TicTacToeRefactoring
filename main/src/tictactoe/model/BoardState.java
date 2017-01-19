package tictactoe.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static tictactoe.model.TableCharacter.EMPTY;

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
        return getNextCellMoveForCPU() - 1;
    }

    // get next position or 0, if no more positions
    private int getNextCellMoveForCPU() {
        Optional<int[]> winningCombination = getCombinationWith2SameCellsAndThirdEmpty(TableCharacter.ZERO);
        if(winningCombination.isPresent()) {
            return getEmptyCellIndex(winningCombination);
        }

        Optional<int[]> blockingCombination = getCombinationWith2SameCellsAndThirdEmpty(TableCharacter.X);
        if(blockingCombination.isPresent()) {
            return getEmptyCellIndex(blockingCombination);
        }

        return nextMove();
    }

    private int getEmptyCellIndex(Optional<int[]> winningCombination) {
        return Arrays.stream(winningCombination.get())
                .filter(index -> getStateAtPosition(index - 1) == TableCharacter.EMPTY)
                .findFirst()
                .getAsInt();
    }

    private Optional<int[]> getCombinationWith2SameCellsAndThirdEmpty(TableCharacter character) {
        return Arrays.stream(winningCombinations)
                .filter(arr -> isCombinationWith2SameCellsAndThirdEmpty(arr, character))
                .findFirst();
    }

    private int nextMove() {
        if(getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(getStateAtCell(1, 1)== EMPTY)
            return 1;
        else
            return NOT_A_POSITION + 1;
    }

    private boolean isCombinationWith2SameCellsAndThirdEmpty(int[] winCombination, TableCharacter character) {
        TableCharacter firstValue = getStateAtPosition(winCombination[0] - 1);
        TableCharacter secondValue = getStateAtPosition(winCombination[1] - 1);
        TableCharacter thirdValue = getStateAtPosition(winCombination[2] - 1);

        List<TableCharacter> array = Arrays.asList(firstValue, secondValue, thirdValue);
        long emptyCharacters = array.stream().filter(tableCharacter -> tableCharacter == TableCharacter.EMPTY).count();
        long zeroCharacters = array.stream().filter(tableCharacter -> tableCharacter == character).count();

        return emptyCharacters == 1 && zeroCharacters == 2;
    }


}
