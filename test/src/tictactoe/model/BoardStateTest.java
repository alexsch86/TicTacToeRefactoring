package tictactoe.model;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void getStateAtCell_getsStateForConfiguration() {
        boardState.setStateAtPosition(3, TableCharacter.X);

        TableCharacter actual = boardState.getStateAtCell(2, 1);

        assertThat(actual).isEqualTo(TableCharacter.X);
    }
    
}
