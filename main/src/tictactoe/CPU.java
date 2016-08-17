package tictactoe;

import tictactoe.model.BoardState;

import static tictactoe.model.TableCharacter.*;

public class CPU	{
    
    public static int doMove(BoardState boardState)	{
        return doNextCPUMove(boardState) - 1;
    }

    private static int doNextCPUMove(BoardState boardState) {
        if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(1, 2)== ZERO && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(boardState.getStateAtCell(2, 1)== ZERO && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(2, 3)== EMPTY)
            return 6;
        else if(boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(3, 2)== ZERO && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(boardState.getStateAtCell(1, 2)== ZERO && boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(2, 3)== ZERO && boardState.getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(boardState.getStateAtCell(3, 2)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;

        else if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(boardState.getStateAtCell(2, 1)== ZERO && boardState.getStateAtCell(2, 3)== ZERO && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(3, 2)== EMPTY)
            return 8;

        else if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(2, 1)== ZERO && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(boardState.getStateAtCell(1, 2)== ZERO && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 2)== EMPTY)
            return 8;
        else if(boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(2, 3)== ZERO && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(boardState.getStateAtCell(2, 1)== ZERO && boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 2)== ZERO && boardState.getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(boardState.getStateAtCell(2, 3)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;

        else if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(boardState.getStateAtCell(1, 2)== ZERO && boardState.getStateAtCell(3, 2)== ZERO && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(2, 3)== EMPTY)
            return 6;

        else if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;
        else if(boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(1, 1)== ZERO && boardState.getStateAtCell(3, 3)== ZERO && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(boardState.getStateAtCell(3, 1)== ZERO && boardState.getStateAtCell(1, 3)== ZERO && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(1, 2)== X && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(boardState.getStateAtCell(2, 1)== X && boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(2, 3)== EMPTY)
            return 6;
        else if(boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(3, 2)== X && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(boardState.getStateAtCell(1, 2)== X && boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(2, 3)== X && boardState.getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(boardState.getStateAtCell(3, 2)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(boardState.getStateAtCell(2, 1)== X && boardState.getStateAtCell(2, 3)== X && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(3, 2)== EMPTY)
            return 8;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(2, 1)== X && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(boardState.getStateAtCell(1, 2)== X && boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(3, 2)== EMPTY)
            return 8;
        else if(boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(2, 3)== X && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;

        else if(boardState.getStateAtCell(2, 1)== X && boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(3, 2)== X && boardState.getStateAtCell(1, 2)== EMPTY)
            return 2;
        else if(boardState.getStateAtCell(2, 3)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(2, 1)== EMPTY)
            return 4;
        else if(boardState.getStateAtCell(1, 2)== X && boardState.getStateAtCell(3, 2)== X && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;
        else if(boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(2, 3)== EMPTY)
            return 6;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(3, 3)== EMPTY)
            return 9;
        else if(boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(3, 3)== X && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(3, 1)== EMPTY)
            return 7;
        else if(boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(2, 2)== X && boardState.getStateAtCell(1, 3)== EMPTY)
            return 3;
        else if(boardState.getStateAtCell(3, 1)== X && boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(boardState.getStateAtCell(1, 1)== X && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 3)== X)
            return 6;

        else if(boardState.getStateAtCell(1, 3)== X && boardState.getStateAtCell(2, 2)== ZERO && boardState.getStateAtCell(3, 1)== X)
            return 4;

        else if(boardState.getStateAtCell(2, 2)== EMPTY)
            return 5;

        else if(boardState.getStateAtCell(1, 1)== EMPTY)
            return 1;
        else
            return 0;
    }


}