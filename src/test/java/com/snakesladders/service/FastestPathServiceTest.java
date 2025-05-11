package com.snakesladders.service;

import com.snakesladders.model.Board;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FastestPathServiceTest {

    @Test
    public void testBoardWithoutSnakesAndLadders() {
        Board board = new Board(2, 2);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);
        assertEquals(1, fastestPath.size());
        assertEquals(3, fastestPath.get(0));
    }

    @Test
    public void testBoardWithLadder() {
        int numRows = 3;
        int numCols = 3;
        Board board = new Board(numRows, numCols);
        board.addLadder(2, 8);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);
        // the fastest path [1, 1]

        int winPosition = fastestPath.stream().reduce(1, (pos, diceRoll) -> board.getNextPosition(pos + diceRoll));
        assertEquals(numCols * numRows, winPosition);

        assertEquals(2, fastestPath.size());
        assertEquals(1, fastestPath.get(0));
        assertEquals(1, fastestPath.get(1));
    }

    @Test
    public void testBoardWithAvoidSnakeCall() {
        int numRows = 3;
        int numCols = 3;
        Board board = new Board(numRows, numCols);
        int snakeHead = 8;
        int snakeTail = 2;
        board.addSnake(snakeHead, snakeTail);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);
        assertFalse(fastestPath.isEmpty());

        int position = 1;
        for (int diceRoll : fastestPath) {
            position += diceRoll;
            assertNotEquals(snakeHead, position);
        }
        assertEquals(numCols * numRows, position);
    }

    @Test
    public void testDefaultBoardFromTask() {
        int numRows = 5;
        int numCols = 6;
        Board board = new Board(numRows, numCols);

        board.addLadder(3, 22);
        board.addLadder(5, 8);
        board.addLadder(11, 26);
        board.addLadder(20, 29);

        board.addSnake(27, 1);
        board.addSnake(21, 9);
        board.addSnake(19, 7);
        board.addSnake(17, 4);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);

        assertFalse(fastestPath.isEmpty());

        // solution might be (2,2,6)
        assertEquals(3, fastestPath.size(), "The fastest path should have 3 dice rolls");
        assertEquals(2, fastestPath.get(0), "First dice roll should be 2");
        assertEquals(2, fastestPath.get(1), "Second dice roll should be 2");
        assertEquals(6, fastestPath.get(2), "Third dice roll should be 6");

        int winPosition = fastestPath.stream().reduce(1, (pos, diceRoll) -> board.getNextPosition(pos + diceRoll));
        assertEquals(numRows * numCols, winPosition);
    }


    @Test
    public void testBoardWithMultipleLadders() {
        int numRows = 4;
        int numCols = 5;
        Board board = new Board(numRows, numCols);
        board.addLadder(2, 10);
        board.addLadder(7, 19);
        board.addLadder(12, 20);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);

        assertFalse(fastestPath.isEmpty(), "The fastest path should not be empty");
        int winPosition = fastestPath.stream().reduce(1, (pos, diceRoll) -> board.getNextPosition(pos + diceRoll));
        assertEquals(numRows * numCols, winPosition, "The final position should be a win");
    }

    @Test
    public void testUnwinnableBoard() {
        int numRows = 4;
        int numCols = 4;
        Board board = new Board(numRows, numCols);

        board.addSnake(10, 1);
        board.addSnake(11, 2);
        board.addSnake(12, 3);
        board.addSnake(13, 4);
        board.addSnake(14, 5);
        board.addSnake(15, 6);

        List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);

        assertTrue(fastestPath.isEmpty(), "The path should be empty as the board is unwinnable");
    }
}
