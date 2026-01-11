/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop_group_assignment;

/**
 *
 * @author aiman
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

public class CalendarMonthView extends JFrame {

private YearMonth currentMonth;
private JPanel grid;
private JLabel title;

public CalendarMonthView() {
setTitle("Calendar - Program Overview");
setSize(900, 600);
setLocationRelativeTo(null);
setLayout(new BorderLayout());

currentMonth = YearMonth.now();

// ===== TOP NAV =====
JPanel top = new JPanel();
JButton prev = new JButton("◀");
JButton today = new JButton("Today");
JButton next = new JButton("▶");

title = new JLabel("", JLabel.CENTER);
title.setFont(new Font("Arial", Font.BOLD, 20));

prev.addActionListener(e -> {
currentMonth = currentMonth.minusMonths(1);
refresh();
});

next.addActionListener(e -> {
currentMonth = currentMonth.plusMonths(1);
refresh();
});

today.addActionListener(e -> {
currentMonth = YearMonth.now();
refresh();
});

top.add(prev);
top.add(today);
top.add(next);
top.add(title);

add(top, BorderLayout.NORTH);

// ===== GRID =====
grid = new JPanel(new GridLayout(0, 7));
add(grid, BorderLayout.CENTER);

refresh();
}

private void refresh() {
grid.removeAll();

title.setText(
currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
+ " " + currentMonth.getYear()
);

String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
for (String d : days) {
JLabel l = new JLabel(d, JLabel.CENTER);
l.setFont(new Font("Arial", Font.BOLD, 12));
grid.add(l);
}

LocalDate first = currentMonth.atDay(1);
int start = first.getDayOfWeek().getValue() % 7;

for (int i = 0; i < start; i++) {
grid.add(new JLabel(""));
}

LocalDate today = LocalDate.now();

for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
LocalDate date = currentMonth.atDay(day);
JPanel cell = createDayCell(date, today);
grid.add(cell);
}

grid.revalidate();
grid.repaint();
}

private JPanel createDayCell(LocalDate date, LocalDate today) {
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());
panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
panel.setBackground(Color.WHITE);

// Date number
JLabel dayLabel = new JLabel(String.valueOf(date.getDayOfMonth()));
dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
dayLabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 2));

panel.add(dayLabel, BorderLayout.NORTH);

// Event list
JPanel eventsPanel = new JPanel();
eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
eventsPanel.setBackground(Color.WHITE);

List<Event> events = new ArrayList<>();
DateTimeFormatter inputFmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

// get all events
for (Event e : EventService.getAllEvents()) {

// parse "yyyy-MM-ddTHH:mm:ss" OR "yyyy-MM-dd HH:mm"
LocalDate eventDate = LocalDateTime
.parse(e.getStartDateTime().replace(" ", "T"), inputFmt)
.toLocalDate();

if (eventDate.equals(date)) {
events.add(e);
}
}

DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

if (!events.isEmpty()) {
panel.setBackground(new Color(255, 245, 204)); // light yellow

for (Event e : events) {
JLabel lbl = new JLabel(
(e.getStartDateTime()) + " " + e.getTitle()
);
lbl.setFont(new Font("Arial", Font.PLAIN, 11));
eventsPanel.add(lbl);
}
}

panel.add(eventsPanel, BorderLayout.CENTER);

if (date.equals(today)) {
panel.setBorder(new LineBorder(Color.BLUE, 2));
}

return panel;
}
}
