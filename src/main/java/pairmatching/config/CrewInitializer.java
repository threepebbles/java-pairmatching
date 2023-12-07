package pairmatching.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.error.ErrorMessage;
import pairmatching.repository.BackendCrewRepository;
import pairmatching.repository.FrontendCrewRepository;
import pairmatching.view.ErrorView;

public class CrewInitializer {
    public static void initCrews() {
        initFrontendCrews();
        initBackendCrews();
    }

    private static void initFrontendCrews() {
        File file = new File("src/main/resources/frontend-crew.md");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String crewName = scanner.next();
                FrontendCrewRepository.addCrew(new Crew(Course.BACKEND, crewName));
            }
        } catch (FileNotFoundException e) {
            ErrorView.println(ErrorMessage.getErrorMessage("프론트 엔드 크루 파일이 존재하지 않습니다."));
        }
    }

    private static void initBackendCrews() {
        File file = new File("src/main/resources/backend-crew.md");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String crewName = scanner.next();
                BackendCrewRepository.addCrew(new Crew(Course.BACKEND, crewName));
            }
        } catch (FileNotFoundException e) {
            ErrorView.println(ErrorMessage.getErrorMessage("백엔드 크루 파일이 존재하지 않습니다."));
        }
    }
}