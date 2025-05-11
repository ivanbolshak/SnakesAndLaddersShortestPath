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
        };

        for (int[] size : boardSizes) {
            int rows = size[0];
            int cols = size[1];
            Board board = new Board(rows, cols);

            long startTime = System.currentTimeMillis();
            List<Integer> fastestPath = FastestPathService.fastestPathCombination(board);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            performanceResults.append(rows).append("x").append(cols)
                .append(" board (").append(rows * cols).append(" cells)")
                .append(" execution time: ").append(executionTime).append("ms\n");
            performanceResults.append("Path length: ").append(fastestPath.size()).append("\n");

            // If a board takes more than 1 seconds, skip the rest
            if (executionTime > 1000) {
                performanceResults.append("\n!!!\n").append(rows).append("x").append(cols).append(" board took too long.\n");
                Assertions.fail("PERFORMANCE METRICS (not a real failure):\n" + performanceResults);
                break;
            }
        }
        System.out.println(performanceResults);
    }

}
