package com.tl.codingcontest.contest.nov2025.level5;

import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {

    private final Asteroid asteroid;
    private final int finalX;
    private final int finalY;

    public RoutePlanner(Asteroid asteroid, int finalX, int finalY) {
        this.asteroid = asteroid;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public List<Point> plan() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        int currentX = 0;
        int currentY = 0;
        if (asteroid.inYRange(0)) {
            currentY = asteroid.getRadius() + 1;
            points.add(new Point(0, currentY));
            points.add(new Point(finalX, currentY));
            points.add(new Point(finalX, finalY));
            return points;
        }
        if (asteroid.inXRange(0)) {
            currentX = asteroid.getRadius() + 1;
            points.add(new Point(currentX, 0));
            points.add(new Point(currentX, finalY));
            points.add(new Point(finalX, finalY));
            return points;
        }
        points.add(new Point(0, finalY));
        points.add(new Point(finalX, finalY));

        return points;
    }
}
