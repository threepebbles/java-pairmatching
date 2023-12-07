package pairmatching.error;

import java.util.List;

public class Validator {
    public static void checkBlank(String input, String errorMessage) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> void checkListSize(List<T> list, int size, String errorMessage) {
        if (list.size() != size) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}