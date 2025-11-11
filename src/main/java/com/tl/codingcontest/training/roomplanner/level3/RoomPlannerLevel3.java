package com.tl.codingcontest.training.roomplanner.level3;

import java.util.ArrayList;
import java.util.List;

public class RoomPlannerLevel3 {

    private final int xSize;
    private final int ySize;
    private final int deskAmount;
    private int[][] plan;

    public RoomPlannerLevel3(int xSize, int ySize, int deskAmount) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.deskAmount = deskAmount;
    }

    public int calculatePossibleDesks() {
        return xSize / 3 * ySize;
    }

    //    012345
    public int[][] planDesks() {
        initializePlan();
        for (int deskId = 1; deskId <= deskAmount; deskId++) {
            placeDesk(deskId);
        }
        return plan;
    }

    private void placeDesk(int deskId) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (plan[y][x] == 0) {
                    if (x + 2 < xSize) {
                        plan[y][x] = deskId;
                        plan[y][x + 1] = deskId;
                        plan[y][x + 2] = deskId;
                        return;
                    }
                }
            }
        }
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (plan[y][x] == 0) {
                    if (y + 2 < ySize) {
                        plan[y][x] = deskId;
                        plan[y+1][x] = deskId;
                        plan[y+2][x] = deskId;
                        return;
                    }
                }
            }
        }
    }

    private void initializePlan() {
        plan = new int[ySize][xSize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                plan[y][x] = 0;
            }
        }
    }

    public List<String> displayPlan() {
        List<String> lines = new ArrayList<>();
        for (int y = 0; y < ySize; y++) {
            String line = "";
            for (int x = 0; x < xSize; x++) {
                line += plan[y][x] + " ";
            }
            lines.add(line);
        }
        return lines;
    }
}
