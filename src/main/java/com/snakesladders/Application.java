package com.snakesladders;

import com.snakesladders.controller.Controller;
import com.snakesladders.model.Board;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("Snakes and Ladders Game Solver");
        System.out.println("------------------------------");

        Controller controller = new Controller();

        Board board;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Do you want to configure the board? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equalsIgnoreCase("y")) {
                board = controller.createBoardFromUserInput(scanner);
            } else {
                System.out.println("Using default example board...");
                board = controller.createDefaultExampleBoard();
            }
        }

        controller.printBoard(board);
        controller.printSnakesAndLadders(board);

        List<Integer> fastestPath = controller.solveBoard(board);

        controller.printSolution(fastestPath);
    }
}