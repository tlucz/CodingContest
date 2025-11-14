package com.tl.codingcontest.contest.nov2025.level6;

import java.util.List;

public class CollisionChecker {

    public static boolean isCollision(List<Point> points, Asteroid asteroid) {
        return points.stream()
                .filter(p-> asteroid.isPointInside(p.getX(),p.getY()))
                .peek(point -> System.out.println("Collision at point:"+point))
                .findAny()
                .isPresent();
    }
}
