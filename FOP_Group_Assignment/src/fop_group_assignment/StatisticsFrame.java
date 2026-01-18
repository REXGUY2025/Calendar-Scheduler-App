package fop_group_assignment;
<<<<<<< HEAD

=======
>>>>>>> fede75459490d51bd5cf681a90faa0b936b89096

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

