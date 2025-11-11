package com.tl.codingcontest.training.roomplanner.level3;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RoomPlannerLevel3App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(RoomPlannerLevel3App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles();
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int roomsNumber = ParserHelper.getNumbers(inputLines.getFirst()).getFirst().intValue();
            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < roomsNumber; i++) {
                List<Double> sizes = ParserHelper.getNumbers(inputLines.get(i + 1));
                int xSize = sizes.get(0).intValue();
                int ySize = sizes.get(1).intValue();
                int deskAmount = sizes.get(2).intValue();
                RoomPlannerLevel3 roomPlannerLevel3 = new RoomPlannerLevel3(xSize, ySize, deskAmount);
                int[][] calculated = roomPlannerLevel3.planDesks();
                List<String> displayedPlan = roomPlannerLevel3.displayPlan();

                outputLines.addAll(displayedPlan);
                outputLines.add("");
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
