package id.snoop.parser;

import org.json.JSONException;
import org.json.JSONObject;

import id.snoop.model.SmsOtpBean;
import id.snoop.model.TokenBean;

/**
 * Created by Gopath on 2/17/2018.
 */

public class SmsOtpParser {
    public SmsOtpBean parse(JSONObject jObj){
        SmsOtpBean otp = new SmsOtpBean();

        try {
            otp.setStatus(jObj.getBoolean("status"));
            otp.setMsgId(jObj.getString("msgId"));
            otp.setMessage(jObj.getString("message"));
            otp.setMaxAttempt(jObj.getString("maxAttempt"));
            otp.setExpireIn(jObj.getInt("expireIn"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return otp;
    }
}
