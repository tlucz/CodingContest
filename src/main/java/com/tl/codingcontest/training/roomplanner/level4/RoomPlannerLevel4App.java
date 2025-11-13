package com.tl.codingcontest.training.roomplanner.level4;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.map2d.Map2D;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RoomPlannerLevel4App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(RoomPlannerLevel4App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int roomsNumber = ParserHelper.getDoubles(inputLines.getFirst()).getFirst().intValue();
            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < roomsNumber; i++) {
                List<Double> sizes = ParserHelper.getDoubles(inputLines.get(i + 1));
                int xSize = sizes.get(0).intValue();
                int ySize = sizes.get(1).intValue();
                int deskAmount = sizes.get(2).intValue();
                RoomPlannerLevel4 roomPlannerLevel4 = new RoomPlannerLevel4(xSize, ySize, deskAmount);
                Map2D<Integer> calculated = roomPlannerLevel4.planDesks();
                List<String> displayedPlan = calculated.displayMap();

                outputLines.addAll(displayedPlan);
                outputLines.add("");
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
