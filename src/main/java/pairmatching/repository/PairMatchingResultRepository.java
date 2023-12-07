package pairmatching.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.PairMatchingResult;

public class PairMatchingResultRepository {
    private static final List<PairMatchingResult> PAIR_MATCHING_RESULTS = new ArrayList<>();

    public static List<PairMatchingResult> pairMatchingResults() {
        return Collections.unmodifiableList(PAIR_MATCHING_RESULTS);
    }

    public static void addPairMatchingResult(PairMatchingResult pairMatchingResult) {
        PAIR_MATCHING_RESULTS.add(pairMatchingResult);
    }

    public static boolean deletePairMatchingResult(Course course, Mission mission) {
        return PAIR_MATCHING_RESULTS.removeIf(pairMatchingResult ->
                pairMatchingResult.getCourse().equals(course)
                        && pairMatchingResult.getMission().equals(mission));
    }

    public static boolean exists(Course course, Mission mission) {
        return pairMatchingResults().stream()
                .anyMatch(pairMatchingResult ->
                        pairMatchingResult.getCourse().equals(course)
                                && pairMatchingResult.getMission().equals(mission));
    }

    public static void clear() {
        PAIR_MATCHING_RESULTS.clear();
    }
}