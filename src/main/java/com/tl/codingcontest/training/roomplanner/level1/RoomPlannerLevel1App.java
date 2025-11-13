package com.tl.codingcontest.training.roomplanner.level1;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RoomPlannerLevel1App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(RoomPlannerLevel1App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int roomsNumber = ParserHelper.getDoubles(inputLines.getFirst()).getFirst().intValue();
            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < roomsNumber; i++) {
                List<Double> sizes = ParserHelper.getDoubles(inputLines.get(i + 1));
                RoomPlannerLevel1 roomPlannerLevel1 = new RoomPlannerLevel1(sizes.get(0).intValue(), sizes.get(1).intValue());
                int calculated = roomPlannerLevel1.calculatePossibleDesks();
                outputLines.add(String.valueOf(calculated));
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
