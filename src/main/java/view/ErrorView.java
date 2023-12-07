package view;

public class ErrorView {
    public static final String ERROR_HEADER = "[ERROR]";
    public static final String WHITE_SPACE = " ";

    public static void println(String message) {
        System.out.println(getErrorMessage(message));
    }

    public static String getErrorMessage(String message) {
        return ERROR_HEADER + WHITE_SPACE
                + message;
    }
}