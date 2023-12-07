package pairmatching;

import pairmatching.config.CrewInitializer;
import pairmatching.controller.MainController;

public class Application {
    public static void main(String[] args) {
        CrewInitializer.initCrews();
        MainController mainController = new MainController();
        mainController.run();
    }
}