package utils;

import java.util.Objects;

public class ErrorInterceptor {
    private static final ErrorsProvider errorsProvider = new ErrorsProvider();

    public ErrorInterceptor() {}

    public static void intercept(CustomException exception) {
        errorsProvider.registration(exception);
    }

}
