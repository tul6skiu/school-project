package utils;

public enum CommonResponse implements StatusError {
    BAD_ARGUMENT(1,"This application will has more arguments");


    private int code;
    private String status;


    CommonResponse(int code, String status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
