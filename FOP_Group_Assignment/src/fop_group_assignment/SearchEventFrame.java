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
import java.awt.*;
import java.util.List;

public class SearchEventFrame extends JFrame {

public SearchEventFrame() {
setTitle("Search Events");
setSize(500, 400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

JTextField title = new JTextField();
JTextField category = new JTextField();
JTextField date = new JTextField(); // yyyy-MM-dd

JTextArea result = new JTextArea();
result.setEditable(false);

JButton search = new JButton("Search");
search.setBackground(new Color(126, 87, 194));
search.setForeground(Color.WHITE);

search.addActionListener(e -> {

String dateInput = date.getText().trim();
String serviceDate = dateInput;

if (!dateInput.isEmpty()) {
serviceDate = dateInput + "T00:00";
}

List<Event> list = EventService.searchEvents(
title.getText(),
category.getText(),
serviceDate,
""
);

result.setText("");

for (Event ev : list) {

// ✅ Correct date filter for STRING-based dates
if (!dateInput.isEmpty()) {
String eventDate = ev.getStartDateTime().substring(0, 10);

if (!eventDate.equals(dateInput)) {
    continue;
}
}

result.append(ev.toString() + "\n");

}

if (result.getText().isEmpty()) {
result.setText("No events found.");
}
});

JPanel top = new JPanel(new GridLayout(4, 2, 5, 5));

top.add(new JLabel("Title contains"));
top.add(title);

top.add(new JLabel("Category"));
top.add(category);

top.add(new JLabel("Date (yyyy-MM-dd)"));
top.add(date);

top.add(new JLabel());
top.add(search);

add(top, BorderLayout.NORTH);
add(new JScrollPane(result), BorderLayout.CENTER);
}
}

