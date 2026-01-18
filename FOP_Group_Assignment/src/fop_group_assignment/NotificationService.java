package fop_group_assignment;

import java.time.*;
import java.util.*;

public class NotificationService {

    public static List<String> getDueNotifications() {
        List<String> list = new ArrayList<>();

        for (Event e : EventService.getAllEvents()) {
            AdditionalFields af = AdditionalFieldService.get(e.getEventId());
            if (af == null) continue;

            LocalDateTime eventTime = LocalDateTime.parse(e.getStartDateTime());
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime reminderTime = eventTime.minusMinutes(af.getReminderMinutes());

            if (now.isAfter(reminderTime) && now.isBefore(eventTime)) {
                list.add("Reminder: " + e.getTitle() + " at " + e.getStartDateTime());
            }
        }

        return list;
    }
}

