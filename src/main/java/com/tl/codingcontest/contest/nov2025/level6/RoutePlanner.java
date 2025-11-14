package com.tl.codingcontest.contest.nov2025.level6;

import java.util.List;

public class RoutePlanner {

    final List<Point> directions = List.of(new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1));
    private final Asteroid asteroid;
    private final int finalX;
    private final int finalY;
    int planNumber = 0;

    public RoutePlanner(Asteroid asteroid, int finalX, int finalY) {
        this.asteroid = asteroid;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public List<Point> nextPlan() {
        List<Point> retVal;
        if (planNumber == 0) {
            retVal = List.of(new Point(0, 0), new Point(finalX, finalY));
        } else {
            int movement = ((planNumber - 1) / 4) + 1;
            int direction = (planNumber - 1) % 4;
            Point directionPoint = directions.get(direction);
            Point tempPoint = new Point(directionPoint.getX() * movement, directionPoint.getY() * movement);
            retVal = List.of(
                    new Point(0, 0),
                    tempPoint,
                    new Point(finalX, finalY)
            );
        }
        planNumber++;
        return retVal;
    }
}
