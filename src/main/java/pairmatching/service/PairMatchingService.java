package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
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
        List<Crew> crews = getCrewsByCourse(course);
        int tryCount = 0;
        while (tryCount < 3) {
            List<Crew> shuffledCrews = Randoms.shuffle(crews);
            List<Pair> pairs = getPairs(shuffledCrews);
            if (!checkPairs(pairs)) {
                tryCount++;
                continue;
            }
            PairMatchingResultRepository.addPairMatchingResult(new PairMatchingResult(course, mission, pairs));
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.getErrorMessage("3회 이상 매칭에 실패하였습니다."));
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

    private static boolean checkPairs(List<Pair> pairs) {
        boolean isValid = true;
        for (Pair pair : pairs) {
            isValid &= checkPair(pair);
        }
        return isValid;
    }

    private static boolean checkPair(Pair pair) {
        //TODO: 같은 레벨에서 이미 페어로 만난적 있는지 체크
        return true;
    }

    private static List<Pair> getPairs(List<Crew> shuffledCrews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrews.size() - 1; i += 2) {
            Pair pair = new Pair();
            for (int j = i; j < i + 1; j++) {
                pair.addCrew(shuffledCrews.get(j));
            }
            if (shuffledCrews.size() % 2 != 0 && i == shuffledCrews.size() - 3) {
                pair.addCrew(shuffledCrews.get(i + 2));
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
}