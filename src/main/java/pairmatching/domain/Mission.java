package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import pairmatching.error.ErrorMessage;

public enum Mission {
    CAR_RACING(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(Level.LEVEL1, "숫자야구게임"),
    SHOPPING_BASKET(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_MAP(Level.LEVEL2, "지하철노선도"),
    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DISTRIBUTION(Level.LEVEL4, "배포");

    private Level level;
    private String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public static Mission valueOf(Level level, String name) {
        List<Mission> candidates = Arrays.stream(values())
                .filter(mission -> mission.level.equals(level) && mission.name.equals(name))
                .toList();
        if (candidates.size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("존재하지 않는 미션입니다."));
        }
        return candidates.get(0);
    }

    public static boolean exist(Level level, String name) {
        return Arrays.stream(values())
                .filter(mission -> mission.level.equals(level) && mission.name.equals(name))
                .count() == 1;
    }

    public Level getLevel() {
        return level;
    }
}