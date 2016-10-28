package com.intelligent.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by siriu on 27-Oct-16.
 */
public class Node implements Comparable {
    private List<List<Integer>> puzzle;
    private List<Node> nodes;
    private int distanceTravelled = 0;

    public Node(List<List<Integer>> puzzle) {
        this.puzzle = puzzle;
        this.nodes = new ArrayList<>();
    }

    public List<Node> getChildren() {
        Coordinates zeroCoordinates = getZeroCoordinates();
        if (zeroCoordinates.getX() > 0)
            nodes.add(swapTiles(zeroCoordinates, new Coordinates(zeroCoordinates.getX() - 1, zeroCoordinates.getY())));
        if (zeroCoordinates.getX() < this.puzzle.size() - 1)
            nodes.add(swapTiles(zeroCoordinates, new Coordinates(zeroCoordinates.getX() + 1, zeroCoordinates.getY())));
        if (zeroCoordinates.getY() > 0)
            nodes.add(swapTiles(zeroCoordinates, new Coordinates(zeroCoordinates.getX(), zeroCoordinates.getY() - 1)));
        if (zeroCoordinates.getY() < this.puzzle.size() - 1)
            nodes.add(swapTiles(zeroCoordinates, new Coordinates(zeroCoordinates.getX(), zeroCoordinates.getY() + 1)));
        return this.nodes;
    }


    private Node swapTiles(Coordinates zeroCoordinates, Coordinates coordinates) {
        Node swappedNode = new Node(this.puzzle);
        swappedNode.setTileAtCoordinates(zeroCoordinates, swappedNode.getTileAtCoordinates(coordinates));
        swappedNode.setTileAtCoordinates(coordinates, 0);
        swappedNode.setDistanceTravelled(this.distanceTravelled++);
        return swappedNode;
    }

    private void setTileAtCoordinates(Coordinates coordinates, int i) {
        puzzle.get(coordinates.getY()).set(coordinates.getY(), i);
    }

    private int getTileAtCoordinates(Coordinates coordinates) {
        return this.puzzle.get(coordinates.getX()).get(coordinates.getY());
    }

    private Coordinates getZeroCoordinates() {
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j) == 0)
                    return new Coordinates(i, j);
            }
        }
        return null;
    }

    public static int getManhattanDistance(Node node) {
        int puzzleSize = node.puzzle.size();
        int manhattanSum = 0;
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                int tempCalculation = Math.abs(node.puzzle.get(i).get(j) - i - j);
                manhattanSum += tempCalculation / puzzleSize;
                manhattanSum += tempCalculation % puzzleSize;
            }
        }
        return manhattanSum;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(getManhattanDistance(this) + this.distanceTravelled, getManhattanDistance((Node) o) + ((Node) o).distanceTravelled);
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
