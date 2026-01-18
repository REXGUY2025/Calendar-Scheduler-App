package fop_group_assignment;


import javax.swing.*;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame() {
        setTitle("Statistics");
        setSize(350, 200);
        setLocationRelativeTo(null);

        String msg =
                "Busiest Day: " + StatisticsService.busiestDay() +
                "\nBusiest Hour: " + StatisticsService.busiestHour() +
                "\nEvents per Category:\n" +
                StatisticsService.eventsPerCategory();

        JTextArea area = new JTextArea(msg);
        area.setEditable(false);
        add(area);
    }
}

