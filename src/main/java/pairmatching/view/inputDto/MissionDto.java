package pairmatching.view.inputDto;

import java.util.List;
import pairmatching.error.ErrorMessage;
import pairmatching.error.Validator;
import pairmatching.util.Parser;

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
        course = parsed.get(0).trim();
        level = parsed.get(1).trim();
        mission = parsed.get(2).trim();
    }

    private void validate(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");
        Validator.checkListSize(parsed, 3, ErrorMessage.getErrorMessage("올바르지 않은 포맷입니다."));
        parsed.forEach(s -> Validator.checkBlank(s, ErrorMessage.getErrorMessage("공백이 입력되었습니다.")));
    }

    public String getCourse() {
        return course;
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}