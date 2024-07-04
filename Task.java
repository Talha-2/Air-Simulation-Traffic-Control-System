package ASTC;

public class Task {
    // Task identifier
    private String primaryTaskLabel;
    private String secondaryTaskLabel;
    private int priority;
  
    // Task supplemental data

    private String timeMark;
    private int  airplane_id;
    private int current_state_id;
    private int destination_id;
 

    public Task(String primaryTaskLabel, String secondaryTaskLabel, int priority, String timeMark,
                int airplane_id, int current_state_id, int destination_id) 
    {
        this.primaryTaskLabel = primaryTaskLabel;
        this.secondaryTaskLabel = secondaryTaskLabel;
        this.priority = priority;
        this.timeMark = timeMark;
        this.airplane_id = airplane_id;
        this.current_state_id = current_state_id;
        this.destination_id = destination_id;
    }

    public String getPrimaryTaskLabel() {
        return primaryTaskLabel;
    }

    public String getSecondaryTaskLabel() {
        return secondaryTaskLabel;
    }

    public void setSecondaryTaskLabel(String secondaryTaskLabel) {
        this.secondaryTaskLabel = secondaryTaskLabel;
    }

    public int getPriority() {
        return priority;
    }

    public int get_current_state_id() {
        return current_state_id;
    }

    public String getTimeMark() {
        return timeMark;
    }
    
    public int get_airplane_id() {
        return airplane_id;
    }


    public int get_destination_id() {
        return destination_id;
    }
}
