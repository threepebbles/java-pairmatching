package pairmatching.controller;

import java.util.HashMap;
import java.util.Map;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.error.ErrorHandler;
import pairmatching.error.ErrorMessage;
import pairmatching.service.PairMatchingService;
import pairmatching.view.ErrorView;
import pairmatching.view.MainView;
import pairmatching.view.PairMatchingView;
import pairmatching.view.inputDto.MissionDto;

public class MainController {
    public static final String END_OPTION = "Q";
    private final Map<String, Runnable> functions = new HashMap<>();

    public MainController() {
        initControllers();
    }

    private void initControllers() {
        functions.put("1", this::makePairResult);
        functions.put("2", this::lookupPairResult);
        functions.put("3", this::initializePairResults);
        functions.put(END_OPTION, null);
    }

    public void run() {
        String option = MainView.scanOption();
        if (option.equals(END_OPTION)) {
            return;
        }
        functions.get(option).run();
    }

    private void makePairResult() {
        ErrorHandler.retryUntilSuccess(() -> {
            MissionDto missionDto = PairMatchingView.scanMission();
            Course course = Course.getCourseByName(missionDto.getCourse());
            Level level = Level.getLevelByName(missionDto.getLevel());
            Mission mission = Mission.valueOf(level, missionDto.getMission());

            if (PairMatchingService.isPairMatchingResultExist(course, mission)) {
                boolean rematch = requestRematch();
                if (!rematch) {
                    return;
                }
            }
            PairMatchingService.matchPairs(course, mission);
        });
    }

    private static Boolean requestRematch() {
        String rematch = PairMatchingView.scanRematch();
        return rematch.equals("네");
    }

    private void lookupPairResult() {
        ErrorHandler.retryUntilSuccess(() -> {
            MissionDto missionDto = PairMatchingView.scanMission();
            Course course = Course.getCourseByName(missionDto.getCourse());
            Level level = Level.getLevelByName(missionDto.getLevel());
            Mission mission = Mission.valueOf(level, missionDto.getMission());

            if (PairMatchingService.isPairMatchingResultExist(course, mission)) {
                ErrorView.println(ErrorMessage.getErrorMessage("매칭 이력이 없습니다."));
                return;
            }
            PairMatchingView.printPairMatchingResult(PairMatchingService.findPairMatchingResult(course, mission));
        });
    }

    private void initializePairResults() {
        PairMatchingService.clearPairMatchingResults();
        PairMatchingView.printClearPairMatchingResults();
    }
}