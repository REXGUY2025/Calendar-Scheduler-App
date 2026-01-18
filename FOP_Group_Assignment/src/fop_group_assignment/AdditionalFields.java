package fop_group_assignment;

public class AdditionalFields {
    private int eventId;
    private String location;
    private String category;
    private String attendees;
    private int reminderMinutes;

    public AdditionalFields(int eventId, String location, String category, String attendees, int reminderMinutes) {
        this.eventId = eventId;
        this.location = location;
        this.category = category;
        this.attendees = attendees;
        this.reminderMinutes = reminderMinutes;
    }

    public int getEventId() { return eventId; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public String getAttendees() { return attendees; }
    public int getReminderMinutes() { return reminderMinutes; }

    @Override
    public String toString() {
        return eventId + "," + location + "," + category + "," + attendees + "," + reminderMinutes;
    }
}
