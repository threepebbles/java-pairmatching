package pairmatching.domain;

import java.util.List;

public class PairMatchingResult {
    private Course course;
    private Mission mission;
    private List<Pair> pairs;

    public PairMatchingResult(Course course, Mission mission, List<Pair> pairs) {
        this.course = course;
        this.mission = mission;
        this.pairs = pairs;
    }

    public Course getCourse() {
        return course;
    }

    public Mission getMission() {
        return mission;
    }
}