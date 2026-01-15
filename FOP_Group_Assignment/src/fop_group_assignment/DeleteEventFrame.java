package app;

import javax.swing.*;
import java.awt.*;

public class DeleteEventFrame extends JFrame {

    public DeleteEventFrame() {
        setTitle("Delete Event");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(3, 1, 10, 10));
        p.setBackground(Color.WHITE);

        JTextField id = new JTextField();
        JButton del = new JButton("Delete");

        del.setBackground(Color.RED);
        del.setForeground(Color.WHITE);

        del.addActionListener(e -> {
            EventService.deleteEvent(Integer.parseInt(id.getText()));
            JOptionPane.showMessageDialog(this, "Event Deleted");
            dispose();
        });

        p.add(new JLabel("Event ID"));
        p.add(id);
        p.add(del);

        add(p);
    }
}

