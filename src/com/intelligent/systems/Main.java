package com.intelligent.systems;

import com.intelligent.systems.siliding.blocks.SlidingBlocks;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SlidingBlocks.solve(scanner.nextInt());
    }
}