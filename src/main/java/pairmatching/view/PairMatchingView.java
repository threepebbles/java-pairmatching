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
    private Course course;
    private Level level;
    private Mission mission;

    public static void printDescription() {
        System.out.println(PAIR_MATCHING_DESCRIPTION);
    }

    public static MissionDto scanMission() {
        return (MissionDto) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            printDescription();
            String userInput = Console.readLine();
            checkMission(userInput);
            return new MissionDto(userInput);
        });
    }

    private static void checkMission(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        Validator.checkListSize(parsed, 3, ErrorMessage.getErrorMessage("올바르지 않은 입력 포맷입니다."));
    }
}