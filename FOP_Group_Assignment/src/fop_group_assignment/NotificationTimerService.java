package fop_group_assignment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NotificationTimerService {

    private Timer timer;

    public NotificationTimerService() {
        // runs every 60 seconds
        timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkNotifications();
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void checkNotifications() {
        List<String> notifications =
                NotificationService.getDueNotifications();

        for (String msg : notifications) {
            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "Event Reminder",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}