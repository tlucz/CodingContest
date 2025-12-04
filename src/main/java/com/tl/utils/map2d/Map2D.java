package com.tl.utils.map2d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class Map2D<T> {

    private final int rows;
    private final int columns;
    private final Object map[][];

    public Map2D(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Object[rows][columns];
    }

    public void setValue(int row, int col, T value) {
        map[row][col] = value;
    }

    public void setValue(Point2D point, T value) {
        setValue(point.row(), point.column(), value);
    }

    public T getValue(int row, int column) {
        return (T) map[row][column];
    }

    public T getValue(Point2D point) {
        return getValue(point.row(), point.column());
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Object[][] getRawMap() {
        return map;
    }

    public void initializeWith(T value) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                map[r][c] = value;
            }
        }
    }

    public List<String> displayMap() {
        List<String> lines = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            String line = "";
            for (int x = 0; x < columns; x++) {
                line += map[y][x] + " ";
            }
            lines.add(line);
        }
        return lines;
    }

    public boolean arePointsInside(List<Point2D> points) {
        return points.stream().allMatch(this::isPointInside);
    }

    public boolean isPointInside(Point2D point) {
        return point.row() >= 0 && point.row() < rows && point.column() >= 0 && point.column() < columns;
    }

    public List<T> getValues(List<Point2D> points) {
        return points.stream()
                .filter(this::isPointInside)
                .map(this::getValue)
                .toList();
    }

    public List<Point2D> getNeighbors8(Point2D point) {
        return Stream.of(
                point.add(-1, -1),
                point.add(-1, 0),
                point.add(-1, 1),
                point.add(0, -1),
                point.add(0, 1),
                point.add(1, -1),
                point.add(1, 0),
                point.add(1, 1)
        ).filter(this::isPointInside).toList();
    }

    public List<Point2D> getNeighbors4(Point2D point) {
        return Stream.of(
                point.add(-1, 0),
                point.add(0, -1),
                point.add(0, 1),
                point.add(1, 0)
        ).filter(this::isPointInside).toList();
    }

    public List<Point2D> getNeighbors8(List<Point2D> points) {
        return points.stream()
                .flatMap(p -> getNeighbors8(p).stream())
                .filter(p -> !points.contains(p))
                .toList();
    }

    public void setValues(List<Point2D> points, T value) {
        points.forEach(p -> setValue(p, value));
    }
}
