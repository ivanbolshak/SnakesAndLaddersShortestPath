package com.snakesladders.service;

import com.snakesladders.model.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FastestPathService {
    private static final int MIN_DICE_VALUE = 1;
    private static final int MAX_DICE_VALUE = 6;


    public static List<Integer> fastestPathCombination(Board board) {
        Queue<Integer> positionQueue = new LinkedList<>();
        Map<Integer, List<Integer>> pathMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        positionQueue.add(1);
        pathMap.put(1, new ArrayList<>());
        visited.add(1);

        while (!positionQueue.isEmpty()) {
            int currentPosition = positionQueue.poll();
            List<Integer> currentPath = pathMap.get(currentPosition);

            for (int diceValue = MIN_DICE_VALUE; diceValue <= MAX_DICE_VALUE; diceValue++) {
                int nextPosition = board.getNextPosition(currentPosition + diceValue);

                if (board.isWinningPosition(nextPosition)) {
                    List<Integer> winningPath = new ArrayList<>(currentPath);
                    winningPath.add(diceValue);
                    return winningPath;
                }

                if (visited.add(nextPosition)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(diceValue);
                    pathMap.put(nextPosition, newPath);
                    positionQueue.add(nextPosition);
                }
            }
        }

        return Collections.emptyList();
    }

}
