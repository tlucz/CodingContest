package com.tl.codingcontest.training.roomplanner.level2;

import java.util.ArrayList;
import java.util.List;

public class RoomPlannerLevel2 {

    private final int xSize;
    private final int ySize;
    private final int deskAmount;
    private int[][] plan;

    public RoomPlannerLevel2(int xSize, int ySize, int deskAmount) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.deskAmount = deskAmount;
    }

    public int calculatePossibleDesks() {
        return xSize / 3 * ySize;
    }

    public int[][] planDesks() {
        plan = new int[ySize][xSize];
        int curX=0;
        int curY=0;
        for(int deskId=1;deskId<=deskAmount;deskId++) {
            if(curX>=xSize) {
                curY++;
                curX=0;
            }
            plan[curY][curX]=deskId;
            plan[curY][curX+1]=deskId;
            plan[curY][curX+2]=deskId;
            curX+=3;
        }
        return plan;
    }

    public List<String> displayPlan() {
        List<String> lines = new ArrayList<>();
        for (int y=0;y<ySize;y++) {
            String line = "";
            for (int x=0;x<xSize;x++) {
                line+=plan[y][x]+" ";
            }
            lines.add(line);
        }
        return lines;
    }
}
