package id.snoop.model;

/**
 * Created by Gopath on 2/7/2018.
 */

public class SmsOtpBean extends BaseApiResult {

    public boolean status;
    public String msgId;
    public String message;
    public String maxAttempt;
    public int expireIn;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMaxAttempt() {
        return maxAttempt;
    }

    public void setMaxAttempt(String maxAttempt) {
        this.maxAttempt = maxAttempt;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    @Override
    public String toString() {
        return "SmsOtpBean{" +
                "status=" + status +
                ", msgId='" + msgId + '\'' +
                ", message='" + message + '\'' +
                ", maxAttempt='" + maxAttempt + '\'' +
                ", expireIn=" + expireIn +
                '}';
    }
}
