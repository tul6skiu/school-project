package utils;

import javax.print.DocFlavor;

public enum CommonResponse {
    BAD_COUNT_ARGUMENT(1,"This application takes one argument"),
    BAD_WRITE_FILE(2, "This file can not write"),
    BAD_READ_FILE(3,"This file can not read"),
    ILLEGAL_COUNT_ITERATION(4, "First line has counter iteration programs. " +
            "He must be greater than 0"),
    ILLEGAL_COORDINATES(5, "Longitude, latitude or height can not greater that 0"),
    ILLEGAL_AIRCRAFT(6, "This plane is not defined");


    private final int code;
    private String status;


    CommonResponse(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return this.code;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
