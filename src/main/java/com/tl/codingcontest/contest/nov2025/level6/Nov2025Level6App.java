package com.tl.codingcontest.contest.nov2025.level6;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nov2025Level6App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Nov2025Level6App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(true);

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
                System.out.println("From (0,0) to (" + travelToX + "," + travelToY + "), asteroid (" + asteroidX + "," + asteroidY + ")");

                RoutePlanner routePlanner = new RoutePlanner(asteroid, travelToX, travelToY);
                PacesDecomposer pacesDecomposer = new PacesDecomposer();
                List<Point> plan = new ArrayList<>();

                while (true) {
                    plan = routePlanner.nextPlan();
                    String planTxt = plan.stream()
                            .map(point -> "(" + point.getX() + "," + point.getY() + ")")
                            .collect(Collectors.joining(" "));
                    System.out.println("Plan: " + planTxt);

                    PlanExecutor planExecutor = new PlanExecutor();

                    // first execution
                    List<List<Integer>> paces = planExecutor.execute(plan);
                    List<Point> travelPoints = pacesDecomposer.decompose(paces);
                    boolean collision = CollisionChecker.isCollision(travelPoints, asteroid);
                    if (!collision) {
                        List<Integer> pacesX = paces.get(0);
                        List<Integer> pacesY = paces.get(1);
                        System.out.println(pacesX);
                        System.out.println(pacesY);
                        if (planExecutor.time(pacesX) <= maxTime) {
                            outputLines.add(pacesX.stream()
                                    .map(integer -> Integer.toString(integer))
                                    .collect(Collectors.joining(" ")));
                            outputLines.add(pacesY.stream()
                                    .map(integer -> Integer.toString(integer))
                                    .collect(Collectors.joining(" ")));
                            outputLines.add("");
                            break;
                        }
                    }

//                     second execution
                    paces = planExecutor.execute(plan, true);
                    travelPoints = pacesDecomposer.decompose(paces);
                    collision = CollisionChecker.isCollision(travelPoints, asteroid);
                    if (!collision) {
                        List<Integer> pacesX = paces.get(0);
                        List<Integer> pacesY = paces.get(1);
                        System.out.println(pacesX);
                        System.out.println(pacesY);
                        if (planExecutor.time(pacesX) <= maxTime) {
                            outputLines.add(pacesX.stream()
                                    .map(integer -> Integer.toString(integer))
                                    .collect(Collectors.joining(" ")));
                            outputLines.add(pacesY.stream()
                                    .map(integer -> Integer.toString(integer))
                                    .collect(Collectors.joining(" ")));
                            outputLines.add("");
                            break;
                        }
                    }
                }
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
