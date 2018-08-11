package ckpoint.hsim.sms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import ckpoint.hsim.sms.config.SmsProxyConfig;
import ckpoint.hsim.sms.fcm.FcmTokenSender;
import io.reactivex.Observable;

public class SmsProxy extends AppCompatActivity {

    private Observable permissionRx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FcmTokenSender tokenSender = new FcmTokenSender();
        int sms = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int phone = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (sms == PackageManager.PERMISSION_DENIED || phone == PackageManager.PERMISSION_DENIED) {


       ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE}, SmsProxyConfig.PERMISSION_CODE_SMS);
        } else {
            String token = FirebaseInstanceId.getInstance().getToken();

            tokenSender.sendToken(this.getPhone(), token);

            Log.i("token", "token :" + token);
            finish();
        }

        setContentView(R.layout.activity_sms_proxy);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case SmsProxyConfig.PERMISSION_CODE_SMS:
                break;
        }
    }


    private String getPhone() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "010";
        }
        return phoneMgr.getLine1Number();
    }
}
