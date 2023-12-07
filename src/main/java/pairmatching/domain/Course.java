package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import pairmatching.error.ErrorMessage;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public static Course getCourseByName(String name) {
        List<Course> candidates = Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .toList();
        if (candidates.size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바르지 않은 코스 이름입니다."));
        }
        return candidates.get(0);
    }

    public static boolean exist(String name) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .count() == 1;
    }
}