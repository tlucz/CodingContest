package com.tl.advent.year2025.day1;

class Dial {

    private int crossedZeroCount;
    private int currentPosition;
    private final int maxPosition;

    Dial(int currentPosition, int maxPosition) {
        this.currentPosition = currentPosition;
        this.maxPosition = maxPosition;
        this.crossedZeroCount = 0;
    }


    int move(String instruction) {
        char direction = instruction.toLowerCase().charAt(0);
        int value = Integer.parseInt(instruction.substring(1));
        if (direction == 'r') {
            for (int i = 0; i < value; i++) {
                moveRight();
            }
        } else if (direction == 'l') {
            for (int i = 0; i < value; i++) {
                moveLeft();
            }
        } else {
            throw new IllegalArgumentException();
        }
        return currentPosition;
    }

    private void moveRight() {
        currentPosition++;
        if (currentPosition > maxPosition) {
            currentPosition = 0;
        }
        if (currentPosition == 0) {
            crossedZeroCount++;
        }
    }

    private void moveLeft() {
        currentPosition--;
        if (currentPosition < 0) {
            currentPosition = maxPosition;
        }
        if (currentPosition == 0) {
            crossedZeroCount++;
        }
    }

    public int getCrossedZeroCount() {
        return crossedZeroCount;
    }
}
