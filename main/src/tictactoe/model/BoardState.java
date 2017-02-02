package tictactoe.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static tictactoe.model.TableCharacter.EMPTY;

/**
 * Created by alexands on 16.06.2016.
 */
class BoardState {

    static final int NOT_A_POSITION = -1;

    private final int winningCombinations[][] = new int[][]{
            {0, 1, 2}, {0, 3, 6}, {0, 4, 8},
            {3, 4, 5}, {1, 4, 7}, {2, 4, 6},
            {6, 7, 8}, {2, 5, 8}
    };
    
    private final TableCharacter[] STATE;

    BoardState() {
        STATE = initializeBoard();
    }

    private TableCharacter[] initializeBoard() {
        return new TableCharacter[] {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
    }

    TableCharacter[] getTableCharacters() {
        return STATE;
    }

    void setStateAtPosition(int zeroBasedPosition, TableCharacter tableCharacter) {
        this.STATE[zeroBasedPosition] = tableCharacter;
    }

    TableCharacter getStateAtPosition(int position) {
        return STATE[position];
    }

    boolean isWinningCombination(int winningCombinationIndex) {
        return getStateAtPosition(winningCombinations[winningCombinationIndex][0]) != TableCharacter.EMPTY &&
                getStateAtPosition(winningCombinations[winningCombinationIndex][0]) == getStateAtPosition(winningCombinations[winningCombinationIndex][1]) &&
                getStateAtPosition(winningCombinations[winningCombinationIndex][1]) == getStateAtPosition(winningCombinations[winningCombinationIndex][2]);
    }

    int[] getWinningCombinations(int winningCombinationIndex) {
        return winningCombinations[winningCombinationIndex];
    }

    // get next position or 0, if no more positions
    int getNextMoveForCPU() {
        Optional<int[]> winningCombination = getCombinationWith2SameCellsAndThirdEmpty(TableCharacter.ZERO);
        if(winningCombination.isPresent()) {
            return getEmptyCellIndex(winningCombination);
        }

        Optional<int[]> blockingCombination = getCombinationWith2SameCellsAndThirdEmpty(TableCharacter.X);
        if(blockingCombination.isPresent()) {
            return getEmptyCellIndex(blockingCombination);
        }

        return nextPredefinedMoveWhenNoOpportunityExists();
    }

    private int getEmptyCellIndex(Optional<int[]> winningCombination) {
        return Arrays.stream(winningCombination.get())
                .filter(index -> getStateAtPosition(index) == TableCharacter.EMPTY)
                .findFirst()
                .getAsInt();
    }

    private Optional<int[]> getCombinationWith2SameCellsAndThirdEmpty(TableCharacter character) {
        return Arrays.stream(winningCombinations)
                .filter(arr -> isCombinationWith2SameCellsAndThirdEmpty(arr, character))
                .findFirst();
    }

    private int nextPredefinedMoveWhenNoOpportunityExists() {
        if(getStateAtPosition(4) == EMPTY) {
            return 4;
        } else if(getStateAtPosition(0) == EMPTY) {
            return 0;
        }

        return NOT_A_POSITION;
    }

    private boolean isCombinationWith2SameCellsAndThirdEmpty(int[] winCombination, TableCharacter character) {
        TableCharacter firstValue = getStateAtPosition(winCombination[0]);
        TableCharacter secondValue = getStateAtPosition(winCombination[1]);
        TableCharacter thirdValue = getStateAtPosition(winCombination[2]);

        List<TableCharacter> array = Arrays.asList(firstValue, secondValue, thirdValue);
        long emptyCharacters = array.stream().filter(tableCharacter -> tableCharacter == TableCharacter.EMPTY).count();
        long zeroCharacters = array.stream().filter(tableCharacter -> tableCharacter == character).count();

        return emptyCharacters == 1 && zeroCharacters == 2;
    }
}
