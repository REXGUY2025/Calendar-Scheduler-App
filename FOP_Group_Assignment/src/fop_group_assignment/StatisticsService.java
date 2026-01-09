
package app;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class StatisticsService {

    public static DayOfWeek busiestDay() {
        return EventService.getAllEvents().stream()
                .map(e -> LocalDate.parse(e.getStartDateTime().substring(0, 10)).getDayOfWeek())
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    public static int busiestHour() {
        return EventService.getAllEvents().stream()
                .map(e -> LocalDateTime.parse(e.getStartDateTime()).getHour())
                .collect(Collectors.groupingBy(h -> h, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(-1);
    }

    public static Map<String, Long> eventsPerCategory() {
        return EventService.getAllEvents().stream()
                .map(e -> AdditionalFieldService.get(e.getEventId()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(AdditionalFields::getCategory, Collectors.counting()));
    }
}
