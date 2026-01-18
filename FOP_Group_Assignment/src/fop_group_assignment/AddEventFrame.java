package fop_group_assignment;

import javax.swing.*;
import java.awt.*;

public class AddEventFrame extends JFrame {

    public AddEventFrame() {
        setTitle("Add Event");
        setSize(400, 600);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(14, 1, 5, 5));
        p.setBackground(Color.WHITE);

        JTextField title = new JTextField();
        JTextField start = new JTextField("2025-10-05T11:00");
        JTextField end = new JTextField("2025-10-05T12:00");
        JTextField location = new JTextField();
        JTextField category = new JTextField();
        JTextField reminder = new JTextField("30");
        JTextArea desc = new JTextArea(3, 20);

        // 🔁 Recurrence fields
        JCheckBox recurring = new JCheckBox("Recurring Event");
        JComboBox<String> interval = new JComboBox<>(new String[]{
                "Daily", "Weekly", "Monthly"
        });
        JTextField times = new JTextField("1");
        JTextField endDate = new JTextField("2025-12-31");

        interval.setEnabled(false);
        times.setEnabled(false);
        endDate.setEnabled(false);

        recurring.addActionListener(e -> {
            boolean enabled = recurring.isSelected();
            interval.setEnabled(enabled);
            times.setEnabled(enabled);
            endDate.setEnabled(enabled);
        });

        JButton save = new JButton("Save Event");
        save.setBackground(new Color(126, 87, 194));
        save.setForeground(Color.WHITE);

        save.addActionListener(e -> {
            try {
                int id = EventService.getNextEventId();

                Event ev = new Event(
                        id,
                        title.getText(),
                        desc.getText(),
                        start.getText(),
                        end.getText()
                );

                AdditionalFields af = new AdditionalFields(
                        id,
                        location.getText(),
                        category.getText(),
                        "",
                        Integer.parseInt(reminder.getText())
                );

                if (ConflictCheckerService.conflicts(ev)) {
                    JOptionPane.showMessageDialog(this, "Time Conflict Detected!");
                    return;
                }

                EventService.addEvent(ev, af);

                // 🔁 Save recurrence if selected
                if (recurring.isSelected()) {
                    RecurrentEvent re = new RecurrentEvent(
                            id,
                            interval.getSelectedItem().toString(),
                            Integer.parseInt(times.getText()),
                            endDate.getText()
                    );
                    RecurrenceService.save(re);
                }

                JOptionPane.showMessageDialog(this, "Event Added!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        });

        // 🧱 UI layout
        p.add(new JLabel("Title"));
        p.add(title);
        p.add(new JLabel("Description"));
        p.add(new JScrollPane(desc));
        p.add(new JLabel("Start (yyyy-MM-ddTHH:mm)"));
        p.add(start);
        p.add(new JLabel("End"));
        p.add(end);
        p.add(new JLabel("Location"));
        p.add(location);
        p.add(new JLabel("Category"));
        p.add(category);
        p.add(new JLabel("Reminder (minutes)"));
        p.add(reminder);

        p.add(recurring);
        p.add(new JLabel("Recurrence Interval"));
        p.add(interval);
        p.add(new JLabel("Number of Times"));
        p.add(times);
        p.add(new JLabel("Recurrence End Date (yyyy-MM-dd)"));
        p.add(endDate);

        p.add(save);

        add(new JScrollPane(p));
    }
}
