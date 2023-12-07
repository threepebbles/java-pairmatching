package domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public static boolean exist(String name) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .count() == 1;
    }
}