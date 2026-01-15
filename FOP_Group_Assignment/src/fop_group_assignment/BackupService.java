package app;

import java.nio.file.*;
import java.io.*;
import java.time.*;
import java.util.*;

public class BackupService {

    public static String backup(boolean event, boolean additional, boolean recurrent) {
        try {
            String fileName = "backup_" +
                    LocalDateTime.now().toString().replace(":", "-") + ".bak";

            Path backup = Paths.get("backups", fileName);
            Files.createDirectories(backup.getParent());

            List<String> out = new ArrayList<>();

            if (event) {
                out.add("[EVENT]");
                out.addAll(FileService.readAll("event.csv"));
            }

            if (additional) {
                out.add("[ADDITIONAL]");
                out.addAll(FileService.readAll("additional.csv"));
            }

            if (recurrent) {
                out.add("[RECURRENT]");
                out.addAll(FileService.readAll("recurrent.csv"));
            }

            Files.write(backup, out);
            return "Backup saved:\n" + backup;

        } catch (Exception e) {
            return "Backup failed.";
        }
    }

    public static void restore(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            Map<String, List<String>> data = new HashMap<>();
            String current = null;

            for (String line : lines) {
                if (line.startsWith("[") && line.endsWith("]")) {
                    current = line;
                    data.put(current, new ArrayList<>());
                    continue;
                }
                if (current != null)
                    data.get(current).add(line);
            }

            // Restore even if files were deleted
            if (data.containsKey("[EVENT]"))
                FileService.writeAll("event.csv", data.get("[EVENT]"));

            if (data.containsKey("[ADDITIONAL]"))
                FileService.writeAll("additional.csv", data.get("[ADDITIONAL]"));

            if (data.containsKey("[RECURRENT]"))
                FileService.writeAll("recurrent.csv", data.get("[RECURRENT]"));

        } catch (Exception ignored) {}
    }
}

