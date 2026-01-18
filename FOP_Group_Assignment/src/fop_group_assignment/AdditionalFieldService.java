package fop_group_assignment;

import java.util.*;

public class AdditionalFieldService {

    private static final String FILE = "additional.csv";

    public static void save(AdditionalFields af) {
        FileService.appendLine(FILE, af.toString());
    }

    public static AdditionalFields get(int id) {
        List<String> lines = FileService.readAll(FILE);
        for (int i = 1; i < lines.size(); i++) {
            String[] s = lines.get(i).split(",");
            if (Integer.parseInt(s[0]) == id)
                return new AdditionalFields(id, s[1], s[2], s[3], Integer.parseInt(s[4]));
        }
        return null;
    }

    public static void update(AdditionalFields af) {
        List<String> lines = FileService.readAll(FILE);
        List<String> out = new ArrayList<>();
        out.add("eventId,location,category,attendees,reminderMinutes");

        for (int i = 1; i < lines.size(); i++) {
            String[] s = lines.get(i).split(",");
            if (Integer.parseInt(s[0]) == af.getEventId())
                out.add(af.toString());
            else
                out.add(lines.get(i));
        }

        FileService.writeAll(FILE, out);
    }

    public static void delete(int id) {
        List<String> lines = FileService.readAll(FILE);
        List<String> out = new ArrayList<>();
        out.add("eventId,location,category,attendees,reminderMinutes");

        for (int i = 1; i < lines.size(); i++) {
            String[] s = lines.get(i).split(",");
            if (Integer.parseInt(s[0]) != id)
                out.add(lines.get(i));
        }

        FileService.writeAll(FILE, out);
    }
}

