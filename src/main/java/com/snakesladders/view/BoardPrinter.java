package com.snakesladders.view;

import com.snakesladders.model.Board;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardPrinter {

    public static void printBoard(Board board) {
        int rowsNum = board.getNumOfRows();
        int columnsNum = board.getNumOfColumns();

        printHorizontalBorder(columnsNum);
        for (int i = 1; i <= rowsNum; i++) {
            System.out.print("|");
            for (int col = 1; col <= columnsNum; col++) {
                int cellNumber;
                if ((rowsNum - i + 1) % 2 == 1) {
                    // Odd rows from bottom (1, 3, 5, ...) - left to right
                    cellNumber = (rowsNum - i) * columnsNum + col;
                } else {
                    // Even rows from bottom (2, 4, 6, ...) - right to left
                    cellNumber = (rowsNum - i + 1) * columnsNum - col + 1;
                }
                System.out.print(" ");
                printCell(cellNumber, board.getSnakes(), board.getLadders());
                System.out.print(" |");
            }
            System.out.println();
            printHorizontalBorder(columnsNum);
        }
    }

    private static void printHorizontalBorder(int columns) {
        System.out.print("+");
        for (int i = 0; i < columns; i++) {
            System.out.print("-----+");
        }
        System.out.println();
    }

    private static void printCell(int cellNumber, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        String cellText = String.format("%2d", cellNumber);
        if (snakes.containsKey(cellNumber)) {
            cellText += "↓";
        } else if (ladders.containsKey(cellNumber)) {
            cellText += "↑";
        } else {
            cellText += " ";
        }
        System.out.print(cellText);
    }

    public static void printSolution(List<Integer> diceRolls) {
        if (diceRolls.isEmpty()) {
            System.out.println("No solution found!");
        } else {
            System.out.print("Solution: (");
            System.out.print(diceRolls.stream().map(String::valueOf).collect(Collectors.joining(",")));
            System.out.println(")");
            System.out.println("Total dice rolls: " + diceRolls.size());
        }

    }
}
