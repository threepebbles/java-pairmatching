package pairmatching.error;

import java.util.List;
import java.util.Objects;

public class Validator {
    public static void checkNull(Object o, String errorMessage) {
        if (Objects.isNull(o)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkBlank(String input, String errorMessage) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkIsInteger(String input, String errorMessage) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkIsPositiveInteger(int input, String errorMessage) {
        if (input <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkStringLengthInRange(String input,
                                                int startInclusive, int endInclusive,
                                                String errorMessage) {
        if (input.length() < startInclusive || input.length() > endInclusive) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkStringLength(String input, int min, int max,
                                         String errorMessage) {
        if (input.length() < min || input.length() > max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> void checkListSize(List<T> list, int size, String errorMessage) {
        if (list.size() != size) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkNumberInRange(int input, int min, int max, String errorMessage) {
        if (input > max || input < min) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}