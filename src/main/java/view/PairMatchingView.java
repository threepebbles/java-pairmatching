package view;

import camp.nextstep.edu.missionutils.Console;
import domain.Course;
import domain.Level;
import domain.Mission;
import error.ErrorHandler;
import error.ErrorMessage;
import error.Validator;
import java.util.List;
import util.Parser;
import view.inputDto.MissionDto;

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

    public void printDescription() {
        System.out.println(PAIR_MATCHING_DESCRIPTION);
    }

    public MissionDto scanMission() {
        return (MissionDto) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String userInput = Console.readLine();
            checkMission(userInput);
            return new MissionDto(userInput);
        });
    }

    private void checkMission(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        Validator.checkListSize(parsed, 3, ErrorMessage.getErrorMessage("올바르지 않은 입력 포맷입니다."));
    }
}