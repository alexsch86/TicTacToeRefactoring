package tictactoe.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static tictactoe.model.TableCharacter.EMPTY;

/**
 * Created by alexands on 16.06.2016.
 */
public class BoardState {

    private final int winCombo[][] = new int[][]{
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

    public void setStateAtPosition(int position, TableCharacter tableCharacter) {
        this.STATE[position] = tableCharacter;
    }

    public TableCharacter getStateAtPosition(int position) {
        return STATE[position];
    }

    public boolean isCombinationForWinSituation(int i) {
        return getStateAtPosition(winCombo[i][0] - 1) != TableCharacter.EMPTY &&
                getStateAtPosition(winCombo[i][0] - 1) == getStateAtPosition(winCombo[i][1] - 1) &&
                getStateAtPosition(winCombo[i][1] - 1) == getStateAtPosition(winCombo[i][2] - 1);
    }

    public int[] getWinCombination0BasedIndexes(int winningCombinationIndex) {
        return Arrays.stream(winCombo[winningCombinationIndex]).map(index -> index - 1).toArray();
    }
}
