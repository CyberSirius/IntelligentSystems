package com.intelligent.systems.queens;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final ArrayList<Queen> queens;

    public Board(int number) {
        Random random = new Random();
        this.queens = new ArrayList<>(8);
        for (int i = 0; i < this.queens.size(); i++) {
            this.queens.get(i).setColumn(i);
            this.queens.get(i).setRow(random.nextInt(this.queens.size()));
        }
    }

    public int getConflicts(Queen queen) {

    }
}
