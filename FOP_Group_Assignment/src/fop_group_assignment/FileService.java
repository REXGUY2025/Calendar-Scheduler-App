package fop_group_assignment;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileService {

    private static final String DATA_FOLDER = "data";

    public static List<String> readAll(String fileName) {
        try {
            Path path = Paths.get(DATA_FOLDER, fileName);
            if (!Files.exists(path)) return new ArrayList<>();
            return Files.readAllLines(path);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void writeAll(String fileName, List<String> lines) {
        try {
            Path path = Paths.get(DATA_FOLDER, fileName);
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception ignored) {}
    }

    public static void appendLine(String fileName, String line) {
        try {
            Path path = Paths.get(DATA_FOLDER, fileName);
            Files.write(path, Arrays.asList(line), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception ignored) {}
    }
}
