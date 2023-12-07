package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import pairmatching.error.ErrorMessage;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level getLevelByName(String name) {
        List<Level> candidates = Arrays.stream(values())
                .filter(level -> level.name.equals(name))
                .toList();
        if (candidates.size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바르지 않은 코스 이름입니다."));
        }
        return candidates.get(0);
    }

    public static boolean exist(String name) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(name))
                .count() == 1;
    }
}
