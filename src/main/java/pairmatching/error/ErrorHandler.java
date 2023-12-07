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
// Feat(ErrorHandler): 에러 발생 시 에러 메세지 출력 후 재시도 하도록 하는 기능 추가