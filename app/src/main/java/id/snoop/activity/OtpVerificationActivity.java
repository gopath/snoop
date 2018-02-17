package id.snoop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.snoop.R;
import id.snoop.connection.AsynConnection;
import id.snoop.connection.ConnectionHelper;
import id.snoop.connection.ConnectionInterface;
import id.snoop.connection.Constant;
import id.snoop.model.SmsOtpBean;
import id.snoop.model.TokenBean;
import id.snoop.parser.SmsOtpParser;
import id.snoop.parser.TokenParser;

public class OtpVerificationActivity extends AppCompatActivity implements ConnectionInterface {

    private OtpVerificationActivity self = this;
    private Toolbar toolbar;
    private Button btnVerification;
    private PinEntryEditText otpView;
    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_otp_verification);

        toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SMS OTP");

        btnVerification = findViewById(R.id.btn_otp_verification);
        otpView = findViewById(R.id.otp_pin_entry);

        btnVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpView != null) {
                    otp = otpView.getText().toString();
                    postTokenRequest();
                }
            }
        });
    }

    private void postTokenRequest(){
        if (ConnectionHelper.isOnline(self)) {
            AsynConnection connection = new AsynConnection(self, Constant.URL_TOKEN, 1);
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Content-Type", "application/x-www-form-urlencoded");
            header.put("Authorization", Constant.AUTH_BASIC);

            HashMap<String, String> param = new HashMap<String, String>();
            param.put("grant_type", "client_credentials");
            connection.asyncConnectRequest(header, param, AsynConnection.RequestType.POST);
        } else {
            Toast.makeText(self, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void postSmsOtpVerificationRequest(TokenBean token, String otpCode){
        if (ConnectionHelper.isOnline(self)) {
            AsynConnection connection = new AsynConnection(self, Constant.URL_SMS_OTP_VERIVICATION, 3);
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Content-Type", "application/x-www-form-urlencoded");
            header.put("Authorization",  token.getToken_type() + " " +token.getAccess_token());

            HashMap<String, String> param = new HashMap<String, String>();
            param.put("otpstr", otpCode);
            param.put("digit", "0");
            connection.asyncConnectRequest(header, param, AsynConnection.RequestType.POST);
        } else {
            Toast.makeText(self, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void postSmsNotificationRequest(TokenBean token, String handphone){
        if (ConnectionHelper.isOnline(self)) {
            AsynConnection connection = new AsynConnection(self, Constant.URL_SMS_NOTIFICATION, 2);
            HashMap<String, String> header = new HashMap<String, String>();
            header.put("Content-Type", "application/x-www-form-urlencoded");
            header.put("Authorization",  token.getToken_type() + " " +token.getAccess_token());

            HashMap<String, String> param = new HashMap<String, String>();
            param.put("msisdn", handphone);
            param.put("content", "snoop.id - Hai, Fathia Mohamad. Selamat bergabung di layanan kami. Snoop, Share Your Stories Now!");
            connection.asyncConnectRequest(header, param, AsynConnection.RequestType.POST);
        } else {
            Toast.makeText(self, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                self.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void connectionOnSuccess(final Object value, final int responseCode, final int type) {
        self.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Response", value.toString().trim());
                JSONObject jObj;
                switch (type){
                    case 1:
                        try {
                            jObj = new JSONObject(value.toString().trim());
                            TokenParser tokenParser = new TokenParser();
                            TokenBean token = tokenParser.parse(jObj);
                            //Toast.makeText(self, token.getAccess_token(), Toast.LENGTH_SHORT).show();
                            //postSmsOtpVerificationRequest(token, otp);
                            postSmsNotificationRequest(token, "081573637585");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            jObj = new JSONObject(value.toString().trim());
                            Intent intent = new Intent(self, MainActivity.class);
                            startActivity(intent);
                            self.finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void connectionOnFailed(final Object value, final int responseCode, final int type) {
        self.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Response", value.toString().trim());

                switch (type){
                    case 1:
                        break;
                    case 2:
                        Intent intent = new Intent(self, MainActivity.class);
                        startActivity(intent);
                        self.finish();
                        break;
                    case 3:
                        break;
                }
            }
        });
    }
}
