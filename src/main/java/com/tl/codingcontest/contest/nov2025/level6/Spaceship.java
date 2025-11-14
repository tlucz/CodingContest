package com.tl.codingcontest.contest.nov2025.level6;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    int currentPosition = 0;
    Pace currentPace = new Pace(0);
    int directionModifier = 1;

    public List<Integer> travelTo(Integer targetPosition){
        if (targetPosition == 0){
            return List.of();
        }
        currentPosition = 0;
        currentPace = new Pace(0);
        List<Integer> paces = new ArrayList<>();
        paces.add(currentPace.getPace());

        if (currentPosition == targetPosition){
            return paces;
        }
        if (targetPosition > currentPosition){
            directionModifier = 1;
        } else {
            directionModifier = -1;
        }
        targetPosition = Math.abs(targetPosition);


        int restDistance = targetPosition - currentPosition;
        while (restDistance > 0){
            currentPace = calculateNextPace(restDistance);
            paces.add(currentPace.getPace() * directionModifier);
            if (currentPace.getPace() > 0){
                restDistance--;
            }
        }
        paces.add(new Pace(0).getPace());
        return paces;
    }

    private Pace calculateNextPace(int restDistance) {
        if (restDistance -1 >= currentPace.accelerate().distanceToStop()){
            return currentPace.accelerate();
        }else if (restDistance - 1>=currentPace.distanceToStop()){
            return currentPace;
        } else if (restDistance - 1>=currentPace.brake().distanceToStop()){
            return currentPace.brake();
        } else {
            throw new IllegalStateException("wrong pace");
        }
    }

}
