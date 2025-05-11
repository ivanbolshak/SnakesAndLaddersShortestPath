package com.snakesladders.controller;

import com.snakesladders.model.Board;
import com.snakesladders.service.FastestPathService;
import com.snakesladders.view.BoardPrinter;

import java.util.List;
import java.util.Scanner;


public class Controller {

    public Board createDefaultExampleBoard() {
        Board board = new Board(5, 6);

        board.addLadder(3, 22);
        board.addLadder(5, 8);
        board.addLadder(11, 26);
        board.addLadder(20, 29);

        board.addSnake(27, 1);
        board.addSnake(21, 9);
        board.addSnake(19, 7);
        board.addSnake(17, 4);

        return board;
    }

    public Board createBoardFromUserInput(Scanner scanner) {
        System.out.print("Enter number of rows: ");
        int numOfRows = getValidIntInput(scanner, 2, 10);

        System.out.print("Enter number of columns: ");
        int numOfColumns = getValidIntInput(scanner, 2, 10);

        Board board = new Board(numOfRows, numOfColumns);
        int totalCells = numOfRows * numOfColumns;

        System.out.print("Enter number of ladders: ");
        int numOfLadders = getValidIntInput(scanner, 0, totalCells / 2);

        if (numOfLadders > 0){
            System.out.println("Enter ladder positions (bottom top):");
            for (int i = 0; i < numOfLadders; i++) {
                System.out.print("Ladder " + (i + 1) + ": ");
                String[] positions = scanner.nextLine().trim().split("\\s+");

                if (positions.length != 2) {
                    System.out.println("Invalid input. Please enter two numbers separated by space.");
                    i--; // Retry
                    continue;
                }

                try {
                    int bottom = Integer.parseInt(positions[0]);
                    int top = Integer.parseInt(positions[1]);
                    board.addLadder(bottom, top);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid numbers.");
                    i--; // Retry
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    i--; // Retry
                }
            }
        }

        System.out.print("Enter number of snakes: ");
        int numSnakes = getValidIntInput(scanner, 0, totalCells / 2);
        if (numSnakes > 0) {
            System.out.println("Enter snake positions (head tail):");
            for (int i = 0; i < numSnakes; i++) {
                System.out.print("Snake " + (i + 1) + ": ");
                String[] positions = scanner.nextLine().trim().split("\\s+");

                if (positions.length != 2) {
                    System.out.println("Invalid input. Please enter two numbers separated by space.");
                    i--; // Retry
                    continue;
                }

                try {
                    int head = Integer.parseInt(positions[0]);
                    int tail = Integer.parseInt(positions[1]);
                    board.addSnake(head, tail);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid numbers.");
                    i--; // Retry
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    i--; // Retry
                }
            }
        }

        return board;
    }

    public int getValidIntInput(Scanner scanner, int min, int max) {
        int retryCount = 0;
        final int maxRetries = 5;

        while (retryCount < maxRetries) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                    retryCount++;
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                retryCount++;
            }
        }

        throw new IllegalArgumentException("Maximum retries reached.");
    }

    public void printSnakesAndLadders(Board board) {
        System.out.println("Ladders:");
        board.getLadders().forEach((key, value) -> System.out.println(key + " → " + value));

        System.out.println("Snakes:");
        board.getSnakes().forEach((key, value) -> System.out.println(key + " → " + value));
    }

    public List<Integer> solveBoard(Board board) {
        return FastestPathService.fastestPathCombination(board);
    }

    public void printBoard(Board board) {
        System.out.println("Board:");
        BoardPrinter.printBoard(board);
    }

    public void printSolution(List<Integer> fastestPath) {
        System.out.println("\nFastest path:");
        BoardPrinter.printSolution(fastestPath);
    }
}