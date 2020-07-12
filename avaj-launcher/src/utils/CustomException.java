package utils;

public class CustomException extends RuntimeException implements StatusError {
    private final String message;
    private final int code;

    public CustomException(CommonResponse commonResponse) {
        this.message = commonResponse.getStatus();
        this.code = commonResponse.getCode();
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String status() {
        return "Code error " + this.code + " " + message;
    }
}
