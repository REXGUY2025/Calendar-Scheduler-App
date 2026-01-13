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
import java.awt.event.*;

public class MainMenuFrame extends JFrame {

public MainMenuFrame() {
setTitle("Calendar Scheduler - Main Menu");
setSize(400, 450);
setLocationRelativeTo(null);
setDefaultCloseOperation(EXIT_ON_CLOSE);

JPanel p = new JPanel(new GridLayout(7, 1, 10, 10));
p.setBackground(Color.WHITE);

JButton add = btn("Add Event");
JButton update = btn("Update Event");
JButton delete = btn("Delete Event");
JButton search = btn("Search Events");
JButton calendar = btn("Calendar View");
JButton stats = btn("Statistics");
JButton backup = btn("Backup / Restore");

add.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new AddEventFrame().setVisible(true);
}
});

update.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new UpdateEventFrame().setVisible(true);
}
});

delete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new DeleteEventFrame().setVisible(true);
}
});

search.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new SearchEventFrame().setVisible(true);
}
});

calendar.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new CalendarMonthView().setVisible(true);
}
});

stats.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new StatisticsFrame().setVisible(true);
}
});

backup.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
new BackupRestoreFrame().setVisible(true);
}
});

p.add(add);
p.add(update);
p.add(delete);
p.add(search);
p.add(calendar);
p.add(stats);
p.add(backup);

add(p);
}

private JButton btn(String text) {
JButton b = new JButton(text);
b.setBackground(new Color(126, 87, 194));
b.setForeground(Color.WHITE);
return b;



}
}