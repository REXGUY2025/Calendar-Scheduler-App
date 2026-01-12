/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package  fop_group_assignment;


/**
 *
 * @author aiman
 */
public class CalendarApp {
public static void main(String[] args) {
new LoginFrame().setVisible(true);
NotificationTimerService reminderService =
new NotificationTimerService();
reminderService.start();
}
}