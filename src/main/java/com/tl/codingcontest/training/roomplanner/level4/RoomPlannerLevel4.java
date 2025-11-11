package com.tl.codingcontest.training.roomplanner.level4;

import com.tl.utils.map2d.Map2D;
import com.tl.utils.map2d.Point2D;
import java.util.List;
import java.util.Optional;

public class RoomPlannerLevel4 {

    private final int deskAmount;
    private final Map2D<Integer> map;

    public RoomPlannerLevel4(int xSize, int ySize, int deskAmount) {
        this.map = new Map2D<>(ySize, xSize);
        this.map.initializeWith(0);
        this.deskAmount = deskAmount;
    }

    public Map2D<Integer> planDesks() {
        for (int deskId = 1; deskId <= deskAmount; deskId++) {
            placeDeskHorizontallyWithSpace(deskId);
        }
        return map;
    }

    private boolean placeDeskHorizontallyWithSpace(int deskId) {
        var point = new Point2D(0, 0);
        Optional<Point2D> foundPoint = map.getFirstPointWithValueSearchingHorizontally(point, 0);
        if(foundPoint.isEmpty()) {
            return false;
        }
        List<Point2D> deskPoints = List.of(foundPoint.get().add(0, 1), foundPoint.get().add(0, 2), foundPoint.get().add(0, 3));
        if (map.arePointsInside(deskPoints) &&
                map.getValues(deskPoints).stream().allMatch(v->v==0)
                && map.getValues(map.getNeighbors8(deskPoints)).stream().allMatch(v->v==0)) {
            map.setValues(deskPoints, deskId);
            return true;
        }
    }
}
