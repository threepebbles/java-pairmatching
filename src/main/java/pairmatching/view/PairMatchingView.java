package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.error.ErrorHandler;
import pairmatching.error.ErrorMessage;
import pairmatching.error.Validator;
import pairmatching.util.Parser;
import pairmatching.view.inputDto.MissionDto;

public class PairMatchingView {
    private static final String PAIR_MATCHING_DESCRIPTION = """
            #############################################
                  과정: 백엔드 | 프론트엔드
                  미션:
                    - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                    - 레벨2: 장바구니 | 결제 | 지하철노선도
                    - 레벨3:
                    - 레벨4: 성능개선 | 배포
                    - 레벨5:
                  ############################################
                  과정, 레벨, 미션을 선택하세요.
                  ex) 백엔드, 레벨1, 자동차경주
            """;

    public static void printDescription() {
        System.out.println(PAIR_MATCHING_DESCRIPTION);
    }
    public static void printRematchMessage() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
    }
    public static void printInitializePairMatchingResults() {
        System.out.println("초기화 되었습니다.");
    }

    public static MissionDto scanMission() {
        return (MissionDto) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            printDescription();
            String userInput = Console.readLine();
            checkMission(userInput);
            return new MissionDto(userInput);
        });
    }

    public static String scanRematch() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            printRematchMessage();
            String userInput = Console.readLine();
            checkRematch(userInput);
            return userInput;
        });
    }

    private static void checkRematch(String userInput) {
        if(!(userInput.equals("네") || userInput.equals("아니오"))) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("네, 아니오로 입력해야합니다."))
        }
    }

    private static void checkMission(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        Validator.checkListSize(parsed, 3, ErrorMessage.getErrorMessage("올바르지 않은 입력 포맷입니다."));
    }
}