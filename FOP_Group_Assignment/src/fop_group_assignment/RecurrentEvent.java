package fop_group_assignment;

public class RecurrentEvent {
    private int eventId;           //variable declaration
    private String interval;
    private int times;
    private String endDate;
  
    
    //constructor
    public RecurrentEvent(int eventId, String interval, int times, String endDate) {
        this.eventId = eventId;
        this.interval = interval;
        this.times = times;
        this.endDate = endDate;
    }

    public int getEventId() {  //getter
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
    public String toString() { //convert text in the format
        return eventId + "," + interval + "," + times + "," + endDate;
    }
}
