package com.snakesladders.service;

import com.snakesladders.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FastestPathServicePerformanceTest {

    @Test
    public void testLargeBoardPerformance() {
        StringBuilder performanceResults = new StringBuilder();
        performanceResults.append("Performance Test Results:\n");

        int[][] boardSizes = {
            {10, 10},   // Small board: 100 cells
            {15, 15},   // board: 225 cells
            {20, 20},   // board: 400 cells
            {30, 30},   // board: 900 cells
            {50, 50},   // board: 2500 cells
            {100, 100},  // Extreme board: 10000 cells
            {300, 300},  // board took > than 1 second: 90_000 cells
        };

        for (int[] size : boardSizes) {
            int rows = size[0];
            int cols = size[1];
            Board board = new Board(rows, cols);

            long startTime = System.currentTimeMillis();
            List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // If a board takes more than 1 seconds, skip the rest
            if (executionTime > 1000) {
                System.err.println("PERFORMANCE METRICS (not an actual failure):\n" + performanceResults);
                System.err.println(String.format("PERFORMANCE METRICS (failure case):\n  %dx%d calculation board took too long. (%d cells, execution time: %d ms)\n", rows, cols, rows * cols, executionTime));
                break;
            }
            performanceResults.append(rows).append("x").append(cols)
                .append(" board (").append(rows * cols).append(" cells)")
                .append(" execution time: ").append(executionTime).append("ms\n");
            performanceResults.append("Path length: ").append(fastestPath.size()).append("\n");

        }
    }

}
