/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop_group_assignment;

/**
 *
 * @author aiman
 */
import java.time.*;
import java.util.*;

public class ConflictCheckerService {

public static boolean conflicts(Event e) {
for (Event existing : EventService.getAllEvents()) {
if (existing.getEventId() == e.getEventId()) continue;

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