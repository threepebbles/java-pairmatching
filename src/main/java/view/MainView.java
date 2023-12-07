package view;

import camp.nextstep.edu.missionutils.Console;
import error.ErrorHandler;
import java.util.List;

public class MainView {
    private final List<String> OPTIONS = List.of("1", "2", "3", "Q");

    public void printOptions() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public String scanOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String userInput = Console.readLine();
            checkOption(userInput);
            return userInput;
        });
    }

    private void checkOption(String userInput) {
        if (!OPTIONS.contains(userInput)) {
            throw new IllegalArgumentException("유효하지 않은 옵션입니다.");
        }
    }
}