package com.tl.advent.year2025.day4;

import com.tl.utils.map2d.Map2D;
import com.tl.utils.map2d.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GridManager {

    private final Map2D<Character> map2D;

    public GridManager(List<String> lines) {
        map2D = new Map2D<>(lines.size(), lines.get(0).length());
        for (int row = 0; row < map2D.getRows(); row++) {
            for (int column = 0; column < map2D.getColumns(); column++) {
                map2D.setValue(row, column, lines.get(row).charAt(column));
            }
        }
    }

    public int calculatePart1() {
        int result = 0;
        for (int row = 0; row < map2D.getRows(); row++) {
            for (int column = 0; column < map2D.getColumns(); column++) {
                if (map2D.getValue(row, column) == '@') {
                    List<Point2D> neighbors8 = map2D.getNeighbors8(new Point2D(row, column));
                    long rolls = neighbors8.stream().map(map2D::getValue).filter(v -> v == '@').count();
                    if (rolls < 4) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public int calculatePart2() {
        int result = 0;
        while (true) {
            List<Point2D> pointsToRemove = new ArrayList<>();
            for (int row = 0; row < map2D.getRows(); row++) {
                for (int column = 0; column < map2D.getColumns(); column++) {
                    if (map2D.getValue(row, column) == '@') {
                        List<Point2D> neighbors8 = map2D.getNeighbors8(new Point2D(row, column));
                        long rolls = neighbors8.stream().map(map2D::getValue).filter(v -> v == '@').count();
                        if (rolls < 4) {
                            pointsToRemove.add(new Point2D(row, column));
                        }
                    }
                }
            }
            int iterationResult = pointsToRemove.size();
            if (iterationResult == 0) {
                break;
            }
            result += iterationResult;
            map2D.setValues(pointsToRemove,'.');
        }
        return result;
    }
}
