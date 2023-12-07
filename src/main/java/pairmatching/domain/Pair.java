package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private List<Crew> crews = new ArrayList<>();

    public Pair() {
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
    }

    public List<Crew> getCrews() {
        return crews;
    }
}