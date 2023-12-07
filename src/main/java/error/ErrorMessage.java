package error;

public class ErrorMessage {
    public static final String ERROR_HEADER = "[ERROR]";
    public static final String WHITE_SPACE = " ";

    public static String getErrorMessage(String message) {
        return ERROR_HEADER + WHITE_SPACE + message;
    }
}