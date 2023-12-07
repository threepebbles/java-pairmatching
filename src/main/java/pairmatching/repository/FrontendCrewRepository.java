package pairmatching.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import pairmatching.domain.Crew;

public class FrontendCrewRepository {
    private static final List<Crew> crews = new ArrayList<>();

    public static List<Crew> crews() {
        return Collections.unmodifiableList(crews);
    }

    public static void addCrew(Crew crew) {
        crews.add(crew);
    }

    public static boolean exists(String name) {
        return crews().stream()
                .anyMatch(crew -> Objects.equals(crew.getName(), name));
    }
}