package com.tl.advent.year2025.day9;

import com.tl.utils.map2d.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RectangleFinder {


    private final List<Point2D> redTiles;
    private List<HorizontalLine> horizontalLines = new ArrayList<>();
    private List<VerticalLine> verticalLines = new ArrayList<>();


    public RectangleFinder(List<String> lines) {
        redTiles = lines.stream().map(l -> l.split(","))
                .map(a -> new Point2D(Integer.parseInt(a[1]), Integer.parseInt(a[0]))).toList();
        for (int tileIdx = 0; tileIdx < redTiles.size(); tileIdx++) {
            int tileToLine = tileIdx == 0 ? redTiles.size() - 1 : tileIdx - 1;
            Point2D tile = redTiles.get(tileIdx);
            Point2D tile2 = redTiles.get(tileToLine);
            if (tile.row() == tile2.row()) {
                // horizontal line
                long columnStart = Math.min(tile.column(), tile2.column());
                long columnEnd = Math.max(tile.column(), tile2.column());
                horizontalLines.add(new HorizontalLine(tile.row(), columnStart, columnEnd));
            } else if (tile.column() == tile2.column()) {
                // vertical line
                long rowStart = Math.min(tile.row(), tile2.row());
                long rowEnd = Math.max(tile.row(), tile2.row());
                verticalLines.add(new VerticalLine(tile.column(), rowStart, rowEnd));
            }
        }
        horizontalLines = horizontalLines.stream().sorted((a, b) -> (int) (Long.compare(a.row, b.row))).toList();
        verticalLines = verticalLines.stream().sorted((a, b) -> (int) (Long.compare(a.column, b.column))).toList();
        // check if there are any overlaps
        for (int i = 0; i < horizontalLines.size() - 1; i++) {
            HorizontalLine line = horizontalLines.get(i);
            //find lines in the same row
            List<HorizontalLine> sameRowLines = horizontalLines.stream().filter(l -> l.row == line.row).toList();
            for (HorizontalLine sameRowLine : sameRowLines) {
                if (sameRowLine != line && sameRowLine.columnStart <= line.columnEnd && sameRowLine.columnEnd >= line.columnStart) {
                    // overlap
                    System.out.println("overlap:" + line + " " + sameRowLine);
                }
            }
        }
        for (int i = 0; i < verticalLines.size() - 1; i++) {
            VerticalLine line = verticalLines.get(i);
            List<VerticalLine> sameRowLines = verticalLines.stream().filter(l -> l.column == line.column).toList();
            for (VerticalLine sameRowLine : sameRowLines) {
                if (sameRowLine != line && sameRowLine.rowStart <= line.rowEnd && sameRowLine.rowEnd >= line.rowStart) {
                    // overlap
                    System.out.println("overlap:" + line + " " + sameRowLine);
                }
            }
        }

        // check if there are any lines close to each other
        for (int i = 0; i < horizontalLines.size() - 1; i++) {
            HorizontalLine line = horizontalLines.get(i);
            //find lines in the same row
            List<HorizontalLine> nextRowLines = horizontalLines.stream().filter(l -> l.row + 1 == line.row).toList();
            for (HorizontalLine sameRowLine : nextRowLines) {
                if (sameRowLine.columnStart <= line.columnEnd && sameRowLine.columnEnd >= line.columnStart) {
                    // overlap
                    System.out.println("close:" + line + " " + sameRowLine);
                }
            }
        }
        for (int i = 0; i < verticalLines.size() - 1; i++) {
            VerticalLine line = verticalLines.get(i);
            List<VerticalLine> nextRowLines = verticalLines.stream().filter(l -> l.column + 1 == line.column).toList();
            for (VerticalLine sameRowLine : nextRowLines) {
                if (sameRowLine.rowStart <= line.rowEnd && sameRowLine.rowEnd >= line.rowStart) {
                    // overlap
                    System.out.println("close:" + line + " " + sameRowLine);
                }
            }
        }
    }

    public long calculatePart1() {
        long best = 0;
        for (Point2D tile1 : redTiles) {
            for (Point2D tile2 : redTiles) {
                long rectangleSize = calculateRectangleSize(tile1, tile2);
                if (rectangleSize > best) {
                    best = rectangleSize;
                }
            }
        }
        return best;
    }

    private long calculateRectangleSize(Point2D tile1, Point2D tile2) {
        return ((long) Math.abs(tile1.row() - tile2.row()) + 1) * (long) (Math.abs(tile1.column() - tile2.column()) + 1);
    }

    public long calculatePart2() {
        // no overlaps, no lines close to each other
        // find the largest rectangle
        long best = 0;
        for (Point2D tile1 : redTiles) {
            for (Point2D tile2 : redTiles) {
                // rectangle lines should not cross any other line
                boolean crosses = false;
                Point2D upperLeft = new Point2D(Math.min(tile1.row(), tile2.row()), Math.min(tile1.column(), tile2.column()));
                Point2D lowerRight = new Point2D(Math.max(tile1.row(), tile2.row()), Math.max(tile1.column(), tile2.column()));
                HorizontalLine upRectangleLine = new HorizontalLine(upperLeft.row(), upperLeft.column(), lowerRight.column());
                HorizontalLine downRectangleLine = new HorizontalLine(lowerRight.row(), upperLeft.column(), lowerRight.column());
                VerticalLine leftRectangleLine = new VerticalLine(upperLeft.column(), upperLeft.row(), lowerRight.row());
                VerticalLine rightRectangleLine = new VerticalLine(lowerRight.column(), upperLeft.row(), lowerRight.row());
                for (HorizontalLine line : horizontalLines) {
                    if (areLeftLinesCrossing(leftRectangleLine, line) || areRightLinesCrossing(rightRectangleLine, line)) {
                        crosses = true;
                        break;
                    }
                }
                for (VerticalLine line : verticalLines) {
                    if (areUpLinesCrossing(upRectangleLine, line) || areDownLinesCrossing(downRectangleLine, line)) {
                        crosses = true;
                        break;
                    }
                }

                if (!crosses) {
                    long rectangleSize = calculateRectangleSize(tile1, tile2);
                    if (rectangleSize > best) {
                        best = rectangleSize;
                        System.out.println(best + " " + upperLeft + " " + lowerRight);
                    }
                }
            }
        }
        return best;
    }

    private boolean areUpLinesCrossing(HorizontalLine rectangleHorizontalLine, VerticalLine verticalLine) {
        return
                verticalLine.column > rectangleHorizontalLine.columnStart && verticalLine.column < rectangleHorizontalLine.columnEnd &&
                        verticalLine.rowStart <= rectangleHorizontalLine.row && verticalLine.rowEnd > rectangleHorizontalLine.row;
    }

    private boolean areDownLinesCrossing(HorizontalLine rectangleHorizontalLine, VerticalLine verticalLine) {
        return
                verticalLine.column > rectangleHorizontalLine.columnStart && verticalLine.column < rectangleHorizontalLine.columnEnd &&
                        verticalLine.rowStart < rectangleHorizontalLine.row && verticalLine.rowEnd >= rectangleHorizontalLine.row;
    }

    private boolean areLeftLinesCrossing(VerticalLine rectangleVerticalLine, HorizontalLine horizontalLine) {
        return horizontalLine.row > rectangleVerticalLine.rowStart && horizontalLine.row < rectangleVerticalLine.rowEnd &&
                horizontalLine.columnStart <= rectangleVerticalLine.column && horizontalLine.columnEnd > rectangleVerticalLine.column;
    }

    private boolean areRightLinesCrossing(VerticalLine rectangleVerticalLine, HorizontalLine horizontalLine) {
        return horizontalLine.row > rectangleVerticalLine.rowStart && horizontalLine.row < rectangleVerticalLine.rowEnd &&
                horizontalLine.columnStart < rectangleVerticalLine.column && horizontalLine.columnEnd >= rectangleVerticalLine.column;
    }

    record HorizontalLine(long row, long columnStart, long columnEnd) {

    }

    record VerticalLine(long column, long rowStart, long rowEnd) {

    }
}
