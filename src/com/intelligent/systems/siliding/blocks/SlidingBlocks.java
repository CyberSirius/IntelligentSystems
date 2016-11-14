package com.intelligent.systems.siliding.blocks;


import java.util.*;

public class SlidingBlocks {
    private static Stack<Puzzle> path = new Stack<>();

    public static void solve(int sideOfPuzzle) {
        int[][] puzzle = new int[sideOfPuzzle][sideOfPuzzle];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < sideOfPuzzle; i++) {
            String lineOfPuzzle = in.nextLine();
            List<Integer> line = parseLine(lineOfPuzzle);
            for (int j = 0; j < puzzle[i].length; j++) {
                puzzle[i][j] = line.get(j);
            }
        }
        Puzzle start = new Puzzle(puzzle, 0, "start");
        aStar(start);
    }


    private static List<Integer> parseLine(String lineOfPuzzle) {
        List<Integer> integerLine = new ArrayList<>();
        for (String number : lineOfPuzzle.split(" ")) {
            integerLine.add(Integer.parseInt(number));
        }
        return integerLine;
    }

    private static void aStar(Puzzle start) {
        PriorityQueue<Puzzle> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            Puzzle puzzle = priorityQueue.poll();
            if (puzzle.isGoal()) {
                System.out.println(puzzle.getDistanceTravelled() - 1);
                getPath(puzzle);
                while (!path.empty())
                    System.out.println(path.pop().getDirection());
                break;
            } else
                priorityQueue.addAll(puzzle.getChildren());
        }
    }

    private static void getPath(Puzzle puzzle) {
        path.push(puzzle);
        if (!Objects.equals(puzzle.getDirection(), "start"))
            getPath(puzzle.getParent());
    }
}