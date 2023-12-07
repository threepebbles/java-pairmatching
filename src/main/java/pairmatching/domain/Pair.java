package pairmatching.domain;

import java.util.List;

public class Pair {
    private List<Crew> crews;

    public Pair() {
    }

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }
}