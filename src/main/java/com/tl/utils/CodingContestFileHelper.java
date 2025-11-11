package com.tl.utils;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CodingContestFileHelper {

    private final Class appClass;

    public CodingContestFileHelper(Class appClass) {
        this.appClass = appClass;
    }

    public static List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveOutputFile(Path inputPath, List<String> outputLines) {
        try {
            Path path = Path.of(inputPath.toString()
                    .replace("input", "output")
                    .replace(".in", ".out"));
            Files.createDirectories(path.getParent());
            Files.write(path, outputLines, CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Path> readInputFiles() {
        try {
            String resourceFolder = appClass
                    .getPackageName()
                    .replace('.', '/')
                    .replace("com/tl/", "");
            URL resource = appClass.getClassLoader().getResource(resourceFolder);
            Path path = Paths.get(resource.toURI());
            return Files.list(path)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".in"))
                    .map(Path::toAbsolutePath)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
