package com.intelligent.systems;


import java.util.*;

class SlidingBlocks {
    private static Stack<Node> path = new Stack<>();

    static void solve(int sideOfPuzzle) {
        int[][] puzzle = new int[sideOfPuzzle][sideOfPuzzle];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < sideOfPuzzle; i++) {
            String lineOfPuzzle = in.nextLine();
            List<Integer> line = parseLine(lineOfPuzzle);
            for (int j = 0; j < puzzle[i].length; j++) {
                puzzle[i][j] = line.get(j);
            }
        }
        Node start = new Node(puzzle, 0, "start");
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
            if (node.isGoal()) {
                System.out.println(node.getDistanceTravelled() - 1);
                getPath(node);
                while (!path.empty())
                    System.out.println(path.pop().getDirection());
                break;
            } else
                priorityQueue.addAll(node.getChildren());
        }
    }

    private static void getPath(Node node) {
        path.push(node);
        if (!Objects.equals(node.getDirection(), "start"))
            getPath(node.getParent());
    }
}