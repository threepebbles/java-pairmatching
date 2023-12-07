package pairmatching.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.PairResult;

public class PairResultRepository {
    private static final List<PairResult> pairResults = new ArrayList<>();

    public static List<PairResult> pairResults() {
        return Collections.unmodifiableList(pairResults);
    }

    public static void addPairResult(PairResult pairResult) {
        pairResults.add(pairResult);
    }

    public static boolean deletePairResult(Course course, Mission mission) {
        return pairResults.removeIf(pairResult ->
                pairResult.getCourse().equals(course)
                        && pairResult.getMission().equals(mission));
    }

    public static boolean exists(Course course, Mission mission) {
        return pairResults().stream()
                .anyMatch(pairResult ->
                        pairResult.getCourse().equals(course)
                                && pairResult.getMission().equals(mission));
    }
}