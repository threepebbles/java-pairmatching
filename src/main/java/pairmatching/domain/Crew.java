package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public class Crew {
    private Course course;
    private String name;
    private Map<Level, Pair> myPairs;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        this.myPairs = new HashMap<>();
    }

    public boolean isOverlap(Level level, Crew crew) {
        if (myPairs.get(level) == null) {
            return false;
        }
        Pair pair = myPairs.get(level);
        return pair.getCrews().contains(crew);
    }

    public void addPair(Level level, Pair pair) {
        myPairs.put(level, pair);
    }

    public String getName() {
        return name;
    }
}