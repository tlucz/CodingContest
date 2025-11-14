package com.tl.codingcontest.contest.nov2025.level5;

import com.tl.utils.map2d.Point2D;

public class Asteroid {
    private final int x;
    private final int y;
    private final int radius;

    public Asteroid(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean inRange(int x, int y) {
        return inXRange(x) || inYRange(y);
    }

    public boolean inXRange(int x) {
        return x >= this.x - radius && x <= this.x + radius;
    }

    public boolean inYRange(int y) {
        return y >= this.y - radius && y <= this.y + radius;
    }
}
