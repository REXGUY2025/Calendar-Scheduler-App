package fop_group_assignment;

public class Event {

    private int eventId;
    private String title;
    private String description;
    private String startDateTime;
    private String endDateTime;

    public Event(int eventId, String title, String description, String startDateTime, String endDateTime) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setStartDateTime(String s) {
        this.startDateTime = s;
    }

    public void setEndDateTime(String e) {
        this.endDateTime = e;
    }

    @Override
    public String toString() {
        return eventId + "," + title + "," + description + "," + startDateTime + "," + endDateTime;
    }
}
