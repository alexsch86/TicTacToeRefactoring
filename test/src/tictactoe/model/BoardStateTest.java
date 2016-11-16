package tictactoe.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.assertj.core.api.Assertions.assertThat;
import static tictactoe.model.BoardState.NOT_A_POSITION;
import static tictactoe.model.TableCharacter.X;
import static tictactoe.model.TableCharacter.ZERO;

/**
 * Created by alexands on 26.10.2016.
 */
public class BoardStateTest  {
    
    private BoardState boardState;
    
    @Before
    public void setUp() {
        boardState = new BoardState();
        
    }

    @Test
    public void setStateAtPosition_setsCharacterAtPosition() throws Exception {

        boardState.setStateAtPosition(4, ZERO);

        Object actualObj = Whitebox.getInternalState(boardState, "STATE");
        TableCharacter[] actual = (TableCharacter[]) actualObj;
        assertThat(actual[4]).isEqualTo(ZERO);
    }

    @Test
    public void getStateAtCell_getsStateForConfiguration() {
        boardState.setStateAtPosition(3, X);

        TableCharacter actual = boardState.getStateAtCell(2, 1);

        assertThat(actual).isEqualTo(X);
    }

    @Test
    public void doNextMoveForCPU_CPUCanWinRow1Cell3_CPUWins() throws Exception {
        boardState.setStateAtPosition(0, ZERO);
        boardState.setStateAtPosition(1, ZERO);
        boardState.setStateAtPosition(4, X);
        
        int nextMove = boardState.getNextMoveForCPU();
        
        assertThat(nextMove).isEqualTo(2);
    }

    @Test
    public void doNextMoveForCPU_CPUCanWinColumn2Cell2_CPUWins() throws Exception {
        boardState.setStateAtPosition(4, ZERO);
        boardState.setStateAtPosition(7, ZERO);
        boardState.setStateAtPosition(0, X);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(1);
    }

    @Test
    public void doNextMoveForCPU_CPUCanWinDiagonal1Cell9_CPUWins() throws Exception {
        boardState.setStateAtPosition(0, ZERO);
        boardState.setStateAtPosition(4, ZERO);
        boardState.setStateAtPosition(5, X);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(8);
    }

   @Test
    public void doNextMoveForCPU_CPUCanWinColumn1Cell7_PlayerCanWinColumn3Cell9_CPUWins() throws Exception {
        boardState.setStateAtPosition(0, ZERO);
        boardState.setStateAtPosition(3, ZERO);
        boardState.setStateAtPosition(2, X);
        boardState.setStateAtPosition(5, X);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(6);
    }

   @Test
    public void doNextMoveForCPU_PlayerCanWinRow1Cell2_CPUBlocks() throws Exception {
        boardState.setStateAtPosition(0, X);
        boardState.setStateAtPosition(2, X);
        boardState.setStateAtPosition(5, ZERO);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(1);
    }

    @Test
    public void doNextMoveForCPU_PlayerCanWinColumn3Cell6_CPUBlocks() throws Exception {
        boardState.setStateAtPosition(2, X);
        boardState.setStateAtPosition(8, X);
        boardState.setStateAtPosition(3, ZERO);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(5);
    }

    @Test
    public void doNextMoveForCPU_PlayerCanWinDiagonal2Cell5_CPUBlocks() throws Exception {
        boardState.setStateAtPosition(2, X);
        boardState.setStateAtPosition(6, X);
        boardState.setStateAtPosition(3, ZERO);

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(4);
    }

    @Test
    public void doNextMoveForCPU_NoMoreMovesAvailable_ReturnsNoMove() throws Exception {
        for(int i = 0; i < 9; i++) {
            boardState.setStateAtPosition(i, X);
        }

        int nextMove = boardState.getNextMoveForCPU();

        assertThat(nextMove).isEqualTo(NOT_A_POSITION);
    }
}
