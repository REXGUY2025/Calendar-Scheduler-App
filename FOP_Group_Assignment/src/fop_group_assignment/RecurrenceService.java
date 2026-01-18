
package fop_group_assignment;

import java.util.*;

public class RecurrenceService {

    private static final String FILE = "recurrent.csv";
    private static final String HEADER = "eventId,interval,times,endDate";

    public static void save(RecurrentEvent r) {

        List<String> lines = FileService.readAll(FILE);

        // If file does not exist or is empty, write header first
        if (lines == null || lines.isEmpty()) {
            List<String> init = new ArrayList<>();
            init.add(HEADER);
            FileService.writeAll(FILE, init);
        }

        FileService.appendLine(FILE, r.toString());
    }

    public static void delete(int eventId) {

        List<String> lines = FileService.readAll(FILE);

        // Nothing to delete
        if (lines == null || lines.isEmpty()) {
            return;
        }

        List<String> out = new ArrayList<>();
        out.add(HEADER); // always keep header

        // Start from 1 to skip header
        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;

            String[] s = line.split(",", -1);

            try {
                int id = Integer.parseInt(s[0]);
                if (id != eventId) {
                    out.add(line);
                }
            } catch (NumberFormatException e) {
                // skip malformed lines
            }
        }

        FileService.writeAll(FILE, out);
    }
}

        