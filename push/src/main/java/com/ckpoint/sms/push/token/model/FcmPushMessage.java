package com.ckpoint.sms.push.token.model;

import com.ckpoint.sms.push.token.entity.FcmToken;
import com.ckpoint.sms.push.token.entity.SmsMsg;
import lombok.Data;

@Data
public class FcmPushMessage {
    public FcmPushMessage(FcmToken token){
        this.message = new FcmHeaderMessage();
        this.message.setTo(token.getToken());
    }
    private FcmHeaderMessage message;
}

