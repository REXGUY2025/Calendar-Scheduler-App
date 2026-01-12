
package app;

public class RecurrentEvent {

    private int eventId;
    private String interval;
    private int times;
    private String endDate;

    public RecurrentEvent(int eventId, String interval, int times, String endDate) {
        this.eventId = eventId;
        this.interval = interval;
        this.times = times;
        this.endDate = endDate;
    }

    public int getEventId() {
        return eventId;
    }

    public String getInterval() {
        return interval;
    }

    public int getTimes() {
        return times;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return eventId + "," + interval + "," + times + "," + endDate;
    }
}
