package app;

import javax.swing.*;
import java.awt.*;

public class AddEventFrame extends JFrame {

    private boolean isUpdateMode = false;
    private int existingEventId;

    public AddEventFrame() {
        this.isUpdateMode = false;
        initializeUI(null, null);
    }

    public AddEventFrame(Event existingEvent, AdditionalFields additionalFields) {
        this.isUpdateMode = true;
        this.existingEventId = existingEvent.getEventId();
        initializeUI(existingEvent, additionalFields);
    }

    private void initializeUI(Event existingEvent, AdditionalFields additionalFields) {
        setTitle(isUpdateMode ? "Update Event" : "Add Event");
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

        // If in update mode, populate fields with existing data
        if (isUpdateMode && existingEvent != null) {
            title.setText(existingEvent.getTitle());
            desc.setText(existingEvent.getDescription());
            start.setText(existingEvent.getStartDateTime());
            end.setText(existingEvent.getEndDateTime());
            
            if (additionalFields != null) {
                location.setText(additionalFields.getLocation());
                category.setText(additionalFields.getCategory());
                reminder.setText(String.valueOf(additionalFields.getReminderMinutes())); // Fixed here
            }
            
            // Check if this event has recurrence
            RecurrentEvent re = RecurrenceService.get(existingEventId);
            if (re != null) {
                recurring.setSelected(true);
                interval.setSelectedItem(re.getInterval());
                times.setText(String.valueOf(re.getTimes()));
                endDate.setText(re.getEndDate());
                interval.setEnabled(true);
                times.setEnabled(true);
                endDate.setEnabled(true);
            }
        }

        JButton save = new JButton(isUpdateMode ? "Update Event" : "Save Event");
        save.setBackground(new Color(126, 87, 194));
        save.setForeground(Color.WHITE);

        save.addActionListener(e -> {
            try {
                int eventId;
                if (isUpdateMode) {
                    eventId = existingEventId;
                } else {
                    eventId = EventService.getNextEventId();
                }

                Event ev = new Event(
                        eventId,
                        title.getText(),
                        desc.getText(),
                        start.getText(),
                        end.getText()
                );

                AdditionalFields af = new AdditionalFields(
                        eventId,
                        location.getText(),
                        category.getText(),
                        "",
                        Integer.parseInt(reminder.getText())
                );

                // Check for conflicts (exclude current event when updating)
                boolean hasConflict;
                if (isUpdateMode) {
                    hasConflict = ConflictCheckerService.conflicts(ev, eventId);
                } else {
                    hasConflict = ConflictCheckerService.conflicts(ev);
                }
                
                if (hasConflict) {
                    JOptionPane.showMessageDialog(this, "Time Conflict Detected!");
                    return;
                }

                if (isUpdateMode) {
                    EventService.updateEvent(ev, af);
                    
                    // 🔁 Update or delete recurrence
                    if (recurring.isSelected()) {
                        RecurrentEvent re = new RecurrentEvent(
                                eventId,
                                interval.getSelectedItem().toString(),
                                Integer.parseInt(times.getText()),
                                endDate.getText()
                        );
                        RecurrenceService.update(re);
                    } else {
                        RecurrenceService.delete(eventId);
                    }
                    
                    JOptionPane.showMessageDialog(this, "Event Updated!");
                } else {
                    EventService.addEvent(ev, af);

                    // 🔁 Save recurrence if selected
                    if (recurring.isSelected()) {
                        RecurrentEvent re = new RecurrentEvent(
                                eventId,
                                interval.getSelectedItem().toString(),
                                Integer.parseInt(times.getText()),
                                endDate.getText()
                        );
                        RecurrenceService.save(re);
                    }
                    
                    JOptionPane.showMessageDialog(this, "Event Added!");
                }

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input: " + ex.getMessage());
                ex.printStackTrace();
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