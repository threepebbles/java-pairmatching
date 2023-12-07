package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatchingResult;
import pairmatching.error.ErrorMessage;
import pairmatching.repository.BackendCrewRepository;
import pairmatching.repository.FrontendCrewRepository;
import pairmatching.repository.PairMatchingResultRepository;

public class PairMatchingService {
    public static boolean isPairMatchingResultExist(Course course, Mission mission) {
        return PairMatchingResultRepository.exists(course, mission);
    }

    public static void matchPairs(Course course, Mission mission) {
        deletePairMatchingResult(course, mission);
        List<String> crewNames = getCrewNames(course);
        int tryCount = 0;
        while (tryCount < 3) {
            List<String> shuffledCrewNames = Randoms.shuffle(crewNames);
            List<Pair> pairs = getPairs(course, shuffledCrewNames);
            if (!checkPairs(pairs, mission.getLevel())) {
                tryCount++;
                continue;
            }
            applyPairs(course, mission, pairs);
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.getErrorMessage("3회 이상 매칭에 실패하였습니다."));
    }

    private static void applyPairs(Course course, Mission mission, List<Pair> pairs) {
        for (Pair pair : pairs) {
            for (Crew crew : pair.getCrews()) {
                crew.addPair(mission.getLevel(), pair);
            }
        }
        PairMatchingResultRepository.addPairMatchingResult(new PairMatchingResult(course, mission, pairs));
    }

    private static List<String> getCrewNames(Course course) {
        List<Crew> crews = getCrewsByCourse(course);
        return crews.stream()
                .map(Crew::getName)
                .toList();
    }

    public static PairMatchingResult findPairMatchingResult(Course course, Mission mission) {
        List<PairMatchingResult> result = PairMatchingResultRepository.pairMatchingResults()
                .stream().filter(pairMatchingResult -> pairMatchingResult.getCourse().equals(course)
                        && pairMatchingResult.getMission().equals(mission))
                .toList();
        if (result.size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("존재하지 않는 페어매칭 결과입니다."));
        }
        return result.get(0);
    }

    public static void clearPairMatchingResults() {
        PairMatchingResultRepository.clear();
    }

    private static void deletePairMatchingResult(Course course, Mission mission) {
        if (isPairMatchingResultExist(course, mission)) {
            PairMatchingResultRepository.deletePairMatchingResult(course, mission);
        }
    }

    private static boolean checkPairs(List<Pair> pairs, Level level) {
        boolean isValid = true;
        for (Pair pair : pairs) {
            isValid &= checkPair(pair, level);
        }
        return isValid;
    }

    private static boolean checkPair(Pair pair, Level level) {
        boolean isOverlap = false;
        List<Crew> crews = pair.getCrews();
        for (int i = 0; i < crews.size(); i++) {
            for (int j = i + 1; j < crews.size(); j++) {
                isOverlap |= crews.get(i).isOverlap(level, crews.get(j));
            }
        }
        return !isOverlap;
    }

    private static List<Pair> getPairs(Course course, List<String> shuffledCrewNames) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrewNames.size() - 1; i += 2) {
            Pair pair = new Pair();
            for (int j = i; j <= i + 1; j++) {
                pair.addCrew(getCrewByCourseAndName(course, shuffledCrewNames.get(j)));
            }
            if (shuffledCrewNames.size() % 2 != 0 && i == shuffledCrewNames.size() - 3) {
                pair.addCrew(getCrewByCourseAndName(course, shuffledCrewNames.get(i + 2)));
            }
            pairs.add(pair);
        }
        return pairs;
    }

    private static List<Crew> getCrewsByCourse(Course course) {
        if (course.equals(Course.BACKEND)) {
            return BackendCrewRepository.crews();
        }
        return FrontendCrewRepository.crews();
    }

    private static Crew getCrewByCourseAndName(Course course, String name) {
        List<Crew> candidates = null;
        if (course.equals(Course.BACKEND)) {
            candidates = BackendCrewRepository.crews().stream()
                    .filter(crew -> crew.getName().equals(name))
                    .toList();
        } else if (course.equals(Course.FRONTEND)) {
            candidates = FrontendCrewRepository.crews().stream()
                    .filter(crew -> crew.getName().equals(name))
                    .toList();
        }
        if (candidates == null || candidates.size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바르지 않은 입력입니다."));
        }
        return candidates.get(0);
    }
}