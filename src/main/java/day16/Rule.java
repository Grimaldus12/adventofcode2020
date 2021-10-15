package day16;

public class Rule {

    private final String name;
    private final long firstLowerBound;
    private final long firstUpperBound;
    private final long secondLowerBound;
    private final long secondUpperBound;
    private Long assignedIndex;


    public Rule(String name, long firstLowerBound, long firstUpperBound, long secondLowerBound, long secondUpperBound) {
        this.name = name;
        this.firstLowerBound = firstLowerBound;
        this.firstUpperBound = firstUpperBound;
        this.secondLowerBound = secondLowerBound;
        this.secondUpperBound = secondUpperBound;
    }

    public boolean isInRange(long value) {
        return ((firstLowerBound <= value && value <= firstUpperBound) || (secondLowerBound <= value && value <= secondUpperBound));
    }

    public String getName() { return name; }
    public void setAssignedIndex(long index) { this.assignedIndex = index; }
    public Long getAssignedIndex() { return this.assignedIndex; }
}
