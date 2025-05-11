package com.snakesladders.view;

import com.snakesladders.model.Board;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardPrinterTest {

    @Test
    public void testPrintBoard_EmptyBoard() {
        // Arrange
        Board board = new Board(5, 5); // 5x5 board
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        BoardPrinter.printBoard(board);

        // Assert
        String expectedOutput = """
                +-----+-----+-----+-----+-----+
                | 21  | 22  | 23  | 24  | 25  |
                +-----+-----+-----+-----+-----+
                | 20  | 19  | 18  | 17  | 16  |
                +-----+-----+-----+-----+-----+
                | 11  | 12  | 13  | 14  | 15  |
                +-----+-----+-----+-----+-----+
                | 10  |  9  |  8  |  7  |  6  |
                +-----+-----+-----+-----+-----+
                |  1  |  2  |  3  |  4  |  5  |
                +-----+-----+-----+-----+-----+
                """;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintBoard_WithSnakesAndLadders() {
        // Arrange
        Board board = new Board(3, 3); // 3x3 board
        board.addSnake(8, 3); // Snake from 8 to 3
        board.addLadder(2, 7); // Ladder from 2 to 7
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        BoardPrinter.printBoard(board);

        // Assert
        String expectedOutput =
                "+-----+-----+-----+\n" +
                        "|  7  |  8↓ |  9  |\n" +
                        "+-----+-----+-----+\n" +
                        "|  6  |  5  |  4  |\n" +
                        "+-----+-----+-----+\n" +
                        "|  1  |  2↑ |  3  |\n" +
                        "+-----+-----+-----+\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintBoard_SingleCellBoard() {
        // Arrange
        Board board = new Board(1, 1); // 1x1 board
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        BoardPrinter.printBoard(board);

        // Assert
        String expectedOutput =
                "+-----+\n" +
                        "|  1  |\n" +
                        "+-----+\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}