package com.tl.utils.map2d;

import static com.tl.utils.map2d.Point2D.RIGHT;

import java.util.Optional;

public class SimpleMap2DManager<T> {

    Map2D<T> map;

    public SimpleMap2DManager(Map2D<T> map) {
        this.map = map;
    }

    public Optional<Point2D> getFirstPointWithValueSearchingHorizontally(Point2D point, T value) {
        Optional<Point2D> nextPoint = Optional.of(new Point2D(point.row(), point.column()));
        while ((nextPoint = getNextPointHorizontallySearching(nextPoint.get())).map(p -> !map.getValue(p).equals(value)).orElse(false)) {
        }
        return nextPoint;
    }

    private Optional<Point2D> getNextPointHorizontallySearching(Point2D point) {
        Point2D nextPoint = point.add(RIGHT);
        if (map.isPointInside(nextPoint)) {
            return Optional.of(nextPoint);
        }
        nextPoint = new Point2D(point.row() + 1, 0);
        return map.isPointInside(nextPoint) ? Optional.of(nextPoint) : Optional.empty();
    }


}
