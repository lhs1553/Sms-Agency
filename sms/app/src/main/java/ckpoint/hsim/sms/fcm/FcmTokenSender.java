package ckpoint.hsim.sms.fcm;

import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import ckpoint.hsim.sms.component.SmsSender;
import ckpoint.hsim.sms.config.SmsProxyConfig;
import ckpoint.hsim.sms.restapi.SmsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FcmTokenSender {

    public void sendToken(String phone, String token) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(SmsProxyConfig.SMS_SERVICE_SERVER).addConverterFactory(JacksonConverterFactory.create()).build();
        final SmsApi smsRest = retrofit.create(SmsApi.class);

        final FcmToken fcmToken = new FcmToken();
        fcmToken.setPhone(phone);
        fcmToken.setToken(token);

        this.sendTokenToSms(token);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Call<FcmToken> registToken = smsRest.registToken(fcmToken);
                registToken.enqueue(new Callback<FcmToken>() {
                    @Override
                    public void onResponse(Call<FcmToken> call, Response<FcmToken> response) {
                        Log.i("registToken", "resposne token success");
                    }

                    @Override
                    public void onFailure(Call<FcmToken> call, Throwable t) {
                        Log.i("registToken", "resposne token fail");
                    }
                });

            }
        });
    }

    private void sendTokenToSms(String token) {
        SmsSender smsSender = new SmsSender();
        SmsMsg smsMsg = new SmsMsg();
        smsMsg.setRecvNumber("01040652126");
        smsMsg.setMessage(token);
        smsSender.sendMessageTo(smsMsg);
    }
}
