
package fop_group_assignment;

import java.util.*;
import java.time.*;

public class EventService {

    private static final String FILE = "event.csv";

    public static int getNextEventId() {
        List<Event> list = getAllEvents();
        if (list.isEmpty()) return 1;
        return list.get(list.size() - 1).getEventId() + 1;
    }

    public static void addEvent(Event e, AdditionalFields af) {
        FileService.appendLine(FILE, e.toString());
        AdditionalFieldService.save(af);
    }

    public static List<Event> getAllEvents() {
        List<String> lines = FileService.readAll(FILE);
        List<Event> events = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] s = lines.get(i).split(",");
            events.add(new Event(
                    Integer.parseInt(s[0]),
                    s[1], s[2], s[3], s[4]
            ));
        }
        return events;
    }

    public static Event getById(int id) {
        for (Event e : getAllEvents()) {
            if (e.getEventId() == id) return e;
        }
        return null;
    }

    public static void updateEvent(Event e, AdditionalFields af) {
    List<Event> all = getAllEvents();
    List<String> out = new ArrayList<>();
    out.add("eventId,title,description,startDateTime,endDateTime");
    
    // Find and update the existing event
    boolean updated = false;
    for (Event ev : all) {
        if (ev.getEventId() == e.getEventId()) {
            // Replace with the updated event
            out.add(e.toString());
            updated = true;
        } else {
            // Keep other events unchanged
            out.add(ev.toString());
        }
    }
    
    // If event wasn't found (shouldn't happen), add it
    if (!updated) {
        out.add(e.toString());
    }
    
    FileService.writeAll(FILE, out);
    AdditionalFieldService.update(af);
}
 
    public static void deleteEvent(int id) {
        List<Event> all = getAllEvents();
        List<String> out = new ArrayList<>();
        out.add("eventId,title,description,startDateTime,endDateTime");

        for (Event e : all) {
            if (e.getEventId() != id)
                out.add(e.toString());
        }

        FileService.writeAll(FILE, out);
        AdditionalFieldService.delete(id);
        RecurrenceService.delete(id);
    }

    public static List<Event> searchEvents(String title, String category, String from, String to) {
        List<Event> all = getAllEvents();
        List<Event> results = new ArrayList<>();

        LocalDateTime f = (from == null || from.isEmpty()) ? LocalDateTime.MIN : LocalDateTime.parse(from);
        LocalDateTime t = (to == null || to.isEmpty()) ? LocalDateTime.MAX : LocalDateTime.parse(to);

        for (Event e : all) {
            AdditionalFields af = AdditionalFieldService.get(e.getEventId());

            boolean matchTitle = title.isEmpty() || e.getTitle().toLowerCase().contains(title.toLowerCase());
            boolean matchCategory = category.isEmpty() || (af != null && af.getCategory().equalsIgnoreCase(category));

            LocalDateTime dt = LocalDateTime.parse(e.getStartDateTime());

            if (matchTitle && matchCategory && (dt.isAfter(f) && dt.isBefore(t)))
                results.add(e);
        }

        return results;
    }
}

