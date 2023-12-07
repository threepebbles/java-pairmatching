package view.inputDto;

import error.ErrorMessage;
import error.Validator;
import java.util.List;
import util.Parser;

public class MissionDto {
    private final String course;
    private final String level;
    private final String mission;

    public MissionDto(String course, String level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public MissionDto(String userInput) {
        validate(userInput);
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        course = parsed.get(0);
        level = parsed.get(1);
        mission = parsed.get(2);
    }

    private void validate(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        Validator.checkListSize(parsed, 3, ErrorMessage.getErrorMessage("올바르지 않은 포맷입니다."));
        parsed.forEach(s -> Validator.checkBlank(s, ErrorMessage.getErrorMessage("공백이 입력되었습니다.")));
    }
}