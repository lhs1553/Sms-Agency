package ckpoint.hsim.sms.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import ckpoint.hsim.sms.component.SmsSender;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        SmsSender smsSender = new SmsSender();

        Map<String, String> message = remoteMessage.getData();
        SmsMsg smsMsg = new SmsMsg();
        smsMsg.setRecvNumber(message.get("recvNumber"));
        smsMsg.setMessage(message.get("message"));
        smsMsg.setId(Long.parseLong(message.get("id")));
        smsSender.sendMessageTo(smsMsg);
    }
}
