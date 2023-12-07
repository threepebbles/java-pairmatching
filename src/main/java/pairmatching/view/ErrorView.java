package pairmatching.view;

import pairmatching.error.ErrorMessage;

public class ErrorView {
    public static void println(String message) {
        System.out.println(ErrorMessage.getErrorMessage(message));
    }
}