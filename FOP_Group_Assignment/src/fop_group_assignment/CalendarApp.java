
package  fop_group_assignment;

public class CalendarApp {
public static void main(String[] args) {
new LoginFrame().setVisible(true);
NotificationTimerService reminderService =
new NotificationTimerService();
reminderService.start();
}
}