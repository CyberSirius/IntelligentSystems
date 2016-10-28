package com.intelligent.systems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siriu on 20-Oct-16.
 */
class Frogs {
    static void solve(int numberOfFrogs) {
        List<Integer> state = createInitialState(numberOfFrogs);
        DFS(state, numberOfFrogs);
    }

    private static List<Integer> createInitialState(int numberOfFrogs) {
        List<Integer> state = new ArrayList<>();
        for (int i = 0; i < numberOfFrogs * 2 + 1; i++) {
            if (i < numberOfFrogs)
                state.add(-1);
            else if (i == numberOfFrogs)
                state.add(0);
            else state.add(1);
        }
        return state;
    }

    private static boolean DFS(List<Integer> state, int positionOfZero) {
        if (isGoal(state, positionOfZero)) {
            return true;
        } else {
            for (List<Integer> nextState : getStates(state, positionOfZero)) {
                if (DFS(nextState, nextState.indexOf(0))) {
                    printState(state);
                    return true;
                }
            }
        }
        return false;
    }

    private static void printState(List<Integer> state) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : state) {
            if (integer == -1)
                stringBuilder.append('<');
            else if (integer == 1)
                stringBuilder.append('>');
            else stringBuilder.append('_');
        }
        System.out.println(stringBuilder.toString());
    }

    private static boolean isGoal(List<Integer> state, int positionOfZero) {
        if (positionOfZero != (state.size() - 1) / 2)
            return false;
        for (int i = 0; i < state.size(); i++) {
            if (i < positionOfZero) {
                if (state.get(i) == -1)
                    return false;
            } else if (state.get(i) == 1)
                return false;
        }
        printState(state);
        return true;
    }

    private static List<List<Integer>> getStates(List<Integer> state, int positionOfZero) {
        List<List<Integer>> possibleStates = new ArrayList<>();
        int newPositionOfZero = positionOfZero - 1;
        if (newPositionOfZero > 0) {
            if (state.get(newPositionOfZero) < state.get(positionOfZero)) {
                possibleStates.add(swapPlaces(state, positionOfZero, newPositionOfZero, -1));
            }
        }
        newPositionOfZero = positionOfZero - 2;
        if (newPositionOfZero >= 0) {
            if (state.get(newPositionOfZero) < state.get(positionOfZero)) {
                possibleStates.add(swapPlaces(state, positionOfZero, newPositionOfZero, -1));
            }
        }
        newPositionOfZero = positionOfZero + 1;
        if (newPositionOfZero < state.size())
            if (state.get(newPositionOfZero) > state.get(positionOfZero)) {
                possibleStates.add(swapPlaces(state, positionOfZero, newPositionOfZero, 1));

            }

        newPositionOfZero = positionOfZero + 2;
        if (newPositionOfZero < state.size())
            if (state.get(newPositionOfZero) > state.get(positionOfZero)) {
                possibleStates.add(swapPlaces(state, positionOfZero, newPositionOfZero, 1));
            }
        return possibleStates;
    }

    private static List<Integer> swapPlaces(List<Integer> state, int positionOfZero, int newPositionOfZero, int swapNumber) {
        ArrayList<Integer> newState = new ArrayList<>(state);
        newState.set(newPositionOfZero, 0);
        newState.set(positionOfZero, swapNumber);
        return newState;
    }
}