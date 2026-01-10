package com.tl.advent.year2025.day12;

import com.tl.advent.year2025.day11.DevicesGraph;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day12MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day12MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            int sum = 0;
            while ((line = stringIteration.getNext()) != null) {
                if(line.contains("x")) {
                    String[] split = line.split(":");
                    String[] split1 = split[0].split("x");
                    int x = Integer.parseInt(split1[0]);
                    int y = Integer.parseInt(split1[1]);
                    List<Integer> piecesNumber = ParserHelper.getInts(split[1]);
                    outputLines.add(line);
                    lines.add(line);
                    PiecePlacer placer = new PiecePlacer(x,y);
                    boolean canBePlaced = placer.canBePlaced(piecesNumber);
                    if(canBePlaced) {
                        sum++;
                    }
                }
            }

            System.out.println("Part1 " + sum);

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
