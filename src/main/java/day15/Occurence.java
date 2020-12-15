package day15;

import java.sql.SQLOutput;

public class Occurence {

    long lastSpoken;
    long mostRecentSpoken;

    public Occurence(long turn, long old) {
        lastSpoken = turn;
        mostRecentSpoken = old;
    }

    public boolean isNew() {
        return lastSpoken == mostRecentSpoken;
    }

    public Occurence setNewOccurence(long turn) {
        mostRecentSpoken = lastSpoken;
        lastSpoken = turn;
        return this;
    }

    public long getLastSpoken() {
        return lastSpoken;
    }

    public void setLastSpoken(long lastSpoken) {
        this.lastSpoken = lastSpoken;
    }

    public long getMostRecentSpoken() {
        return mostRecentSpoken;
    }

    public void setMostRecentSpoken(long mostRecentSpoken) {
        this.mostRecentSpoken = mostRecentSpoken;
    }

    @Override
    public String toString() {
        return "last Spoken " + lastSpoken + ", most recent spoken " + mostRecentSpoken;
    }

    public long getDiff() {
        return lastSpoken - mostRecentSpoken;
    }
}
