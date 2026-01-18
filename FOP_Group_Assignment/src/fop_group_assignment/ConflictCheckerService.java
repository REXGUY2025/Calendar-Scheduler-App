
package fop_group_assignment;

import java.time.*;
import java.util.*;

public class ConflictCheckerService {

    public static boolean conflicts(Event e) {
        return conflicts(e, -1);
    }

    public static boolean conflicts(Event e, int excludeEventId) {
        for (Event existing : EventService.getAllEvents()) {
            // Skip the event we're updating
            if (excludeEventId != -1 && existing.getEventId() == excludeEventId) {
                continue;
            }

            LocalDateTime s1 = LocalDateTime.parse(e.getStartDateTime());
            LocalDateTime e1 = LocalDateTime.parse(e.getEndDateTime());
            LocalDateTime s2 = LocalDateTime.parse(existing.getStartDateTime());
            LocalDateTime e2 = LocalDateTime.parse(existing.getEndDateTime());

            boolean overlap = s1.isBefore(e2) && s2.isBefore(e1);

            if (overlap) return true;
        }
        return false;
    }
}