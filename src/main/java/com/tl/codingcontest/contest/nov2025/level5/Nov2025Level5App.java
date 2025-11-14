package com.tl.codingcontest.contest.nov2025.level5;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nov2025Level5App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Nov2025Level5App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));

            int sequencesNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < sequencesNumber; i++) {
                List<Integer> numbers = ParserHelper.getInts(stringIteration.getNext().replace(",", " "));
                int travelToX = numbers.get(0);
                int travelToY = numbers.get(1);
                int maxTime = numbers.get(2);

                List<Integer> asteroidNumbers = ParserHelper.getInts(stringIteration.getNext().replace(",", " "));
                int asteroidX = asteroidNumbers.get(0);
                int asteroidY = asteroidNumbers.get(1);
                Asteroid asteroid = new Asteroid(asteroidX, asteroidY, 2);

                System.out.println("From (0,0) to ("+travelToX+","+travelToY+"), asteroid ("+asteroidX+","+asteroidY+")");

                RoutePlanner routePlanner = new RoutePlanner(new Asteroid(asteroidX, asteroidY, 2), travelToX, travelToY);
                List<Point> plan = routePlanner.plan();
                String planTxt = plan.stream()
                        .map(point -> "(" + point.getX() + "," + point.getY() + ")")
                        .collect(Collectors.joining(" "));
                System.out.println("Plan: "+planTxt);


                PlanExecutor planExecutor = new PlanExecutor();
                List<List<Integer>> paces = planExecutor.execute(plan);

                List<Integer> pacesX = paces.get(0);
                List<Integer> pacesY = paces.get(1);

                if (planExecutor.time(pacesX)> maxTime){
                    throw new IllegalStateException("too much time spent");
                }


                outputLines.add(pacesX.stream()
                        .map(integer -> Integer.toString(integer))
                        .collect(Collectors.joining(" ")));
                outputLines.add(pacesY.stream()
                        .map(integer -> Integer.toString(integer))
                        .collect(Collectors.joining(" ")));
                outputLines.add("");

            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
