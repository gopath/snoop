package id.snoop.model;

/**
 * Created by Gopath on 2/7/2018.
 */

public class SmsOtpVerification extends BaseApiResult {

    public boolean status;
    public String message;
    public String maxAttempt;
    public int expireIn;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getMaxAttempt() {
        return maxAttempt;
    }

    public int getExpireIn() {
        return expireIn;
    }
}

