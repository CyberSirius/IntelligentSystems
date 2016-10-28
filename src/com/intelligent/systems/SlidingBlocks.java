package com.intelligent.systems;


import java.util.*;

public class SlidingBlocks {

    static void solve(int sideOfPuzzle) {
        List<List<Integer>> puzzle = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < sideOfPuzzle; i++) {
            String lineOfPuzzle = in.nextLine();
            puzzle.add(parseLine(lineOfPuzzle));
        }
        Node start = new Node(puzzle);
        aStar(start);
    }


    private static List<Integer> parseLine(String lineOfPuzzle) {
        List<Integer> integerLine = new ArrayList<>();
        for (String number : lineOfPuzzle.split(" ")) {
            integerLine.add(Integer.parseInt(number));
        }
        return integerLine;
    }

    private static void aStar(Node start) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            System.out.println(Node.getManhattanDistance(node));
            if (Node.getManhattanDistance(node) == 0)
                System.out.println("yeah bby");
            priorityQueue.addAll(node.getChildren());
        }
    }
}