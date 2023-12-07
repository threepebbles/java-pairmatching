package pairmatching.error;

import java.util.function.Supplier;
import pairmatching.view.ErrorView;

public class ErrorHandler {
    public static <T> Object retryUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                ErrorView.println(e.getMessage());
            }
        }
    }

    public static void retryUntilSuccess(Runnable toRun) {
        while (true) {
            try {
                toRun.run();
                return;
            } catch (IllegalArgumentException e) {
                ErrorView.println(e.getMessage());
            }
        }
    }
}