package fop_group_assignment;

import javax.swing.*;
import java.awt.*;

public class UpdateEventFrame extends JFrame {

    public UpdateEventFrame() {
        setTitle("Update Event");
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(3, 1, 10, 10));
        p.setBackground(Color.WHITE);

        JTextField id = new JTextField();
        JButton update = new JButton("Update");

        update.setBackground(new Color(126, 87, 194));
        update.setForeground(Color.WHITE);

        update.addActionListener(e -> {
            int eventId = Integer.parseInt(id.getText());
            Event ev = EventService.getById(eventId);
            if (ev == null) {
                JOptionPane.showMessageDialog(this, "Event not found");
                return;
            }
            new AddEventFrame().setVisible(true);
            dispose();
        });

        p.add(new JLabel("Event ID"));
        p.add(id);
        p.add(update);

        add(p);
    }
}

