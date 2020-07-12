package utils;

public class ErrorsProvider {
    StatusError statusError;

    public void registration(StatusError statusError) {
        this.statusError = statusError;
        currentStatus();
    }

    public void currentStatus() {
        System.out.println(this.statusError.status());
    }
}
