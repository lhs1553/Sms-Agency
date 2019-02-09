package ckpoint.hsim.sms.component;

import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ckpoint.hsim.sms.config.SmsProxyConfig;
import ckpoint.hsim.sms.fcm.FcmToken;
import ckpoint.hsim.sms.fcm.SmsMsg;
import ckpoint.hsim.sms.model.SmsText;
import ckpoint.hsim.sms.restapi.SmsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SmsSender {

    public void sendMessageTo(SmsMsg smsMsg) {

        SmsManager smsManager = SmsManager.getDefault();
        SmsText smsText =new SmsText(smsMsg.getMessage());

        for (String msg : smsText.getSmsMessages()){
            smsManager.sendTextMessage(smsMsg.getRecvNumber(), null, msg, null, null);
        }
        Retrofit retrofit= new Retrofit.Builder().baseUrl(SmsProxyConfig.SMS_SERVICE_SERVER).addConverterFactory(JacksonConverterFactory.create()).build();
        final SmsApi smsRest= retrofit.create(SmsApi.class);

        if(smsMsg.getId() != null) {
            smsRest.smsSendCallback(smsMsg.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i("smsCallback", "callback success");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.i("smsCallback", "callback fail");
                }
            });
        }

    }

}

