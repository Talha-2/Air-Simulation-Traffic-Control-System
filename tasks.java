package ASTC;

public enum tasks {
	
    DEPARTURE(4),
    LANDING(5),
    TAXING(2),
    PARKING(3),
	exploration(1);

    private final int priority;

    tasks(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
