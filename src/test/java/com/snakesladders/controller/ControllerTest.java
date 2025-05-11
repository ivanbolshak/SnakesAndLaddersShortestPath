package com.snakesladders.controller;

import com.snakesladders.model.Board;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {


    @Test
    void testPrintBoard_correctDirection() {
        Controller controller = new Controller();
        Board board = new Board(3, 3);

        String expectedOutput = """
                Board:
                +-----+-----+-----+
                |  7  |  8  |  9  |
                +-----+-----+-----+
                |  6  |  5  |  4  |
                +-----+-----+-----+
                |  1  |  2  |  3  |
                +-----+-----+-----+
                """;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.printBoard(board);

        String actualOutput = outContent.toString().trim();
        assertEquals(expectedOutput.trim(), actualOutput);
    }


    @Test
    void testPrintBoard_defaultExampleBoard() {
        Controller controller = new Controller();
        Board board = controller.createDefaultExampleBoard();

        String expectedOutput = """
                Board:
                +-----+-----+-----+-----+-----+-----+
                | 25  | 26  | 27↓ | 28  | 29  | 30  |
                +-----+-----+-----+-----+-----+-----+
                | 24  | 23  | 22  | 21↓ | 20↑ | 19↓ |
                +-----+-----+-----+-----+-----+-----+
                | 13  | 14  | 15  | 16  | 17↓ | 18  |
                +-----+-----+-----+-----+-----+-----+
                | 12  | 11↑ | 10  |  9  |  8  |  7  |
                +-----+-----+-----+-----+-----+-----+
                |  1  |  2  |  3↑ |  4  |  5↑ |  6  |
                +-----+-----+-----+-----+-----+-----+""";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.printBoard(board);

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void testPrintBoard_emptyBoard() {
        Controller controller = new Controller();
        Board board = new Board(5, 5);

        String expectedOutput = """
                Board:
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
                +-----+-----+-----+-----+-----+""";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.printBoard(board);

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void testPrintSnakesAndLadders_withSnakesAndLadders() {
        Controller controller = new Controller();
        Board board = controller.createDefaultExampleBoard();

        String expectedOutput = """
                Ladders:
                3 → 22
                11 → 26
                20 → 29
                5 → 8
                Snakes:
                17 → 4
                19 → 7
                27 → 1
                21 → 9
                """;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.printSnakesAndLadders(board);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintSnakesAndLadders_withEmptyBoard() {
        Board board = new Board(5, 6);
        Controller controller = new Controller();

        String expectedOutput = """
                Ladders:
                Snakes:
                """;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.printSnakesAndLadders(board);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testGetValidIntInput_validWithinRange() {
        Controller controller = new Controller();
        String input = "5\n";
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 1, 10);

        assertEquals(5, result);
    }

    @Test
    void testGetValidIntInput_minBoundary() {
        Controller controller = new Controller();
        String input = "2\n";
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 2, 10);

        assertEquals(2, result);
    }

    @Test
    void testGetValidIntInput_maxBoundary() {
        Controller controller = new Controller();
        String input = "10\n";
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 2, 10);

        assertEquals(10, result);
    }

    @Test
    void testGetValidIntInput_belowMinRetriesUntilValid() {
        Controller controller = new Controller();
        String input = "-1\n1\n3\n"; // -1 is invalid, 1 is out of range, 3 is valid
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 2, 10);

        assertEquals(3, result);
    }

    @Test
    void testGetValidIntInput_aboveMaxRetriesUntilValid() {
        Controller controller = new Controller();
        String input = "11\n15\n8\n"; // 11 & 15 are out of range, 8 is valid
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 2, 10);

        assertEquals(8, result);
    }

    @Test
    void testGetValidIntInput_nonNumericInputRetriesUntilValid() {
        Controller controller = new Controller();
        String input = "abc\nabcd\n9\n"; // "abc" & "abcd" are invalid, 9 is valid
        Scanner scanner = new Scanner(input);

        int result = controller.getValidIntInput(scanner, 2, 10);

        assertEquals(9, result);
    }

    @Test
    void testGetValidIntInput_maRetriesReachedThrowsException() {
        Controller controller = new Controller();
        String input = "abc\n11\n15\n0\n200\nxyz\n"; // All invalid inputs
        Scanner scanner = new Scanner(input);

        assertThrows(IllegalArgumentException.class, () -> controller.getValidIntInput(scanner, 1, 10));
    }
}
