package com.ckpoint.sms.push.token.model;

import com.ckpoint.sms.push.token.entity.SmsMsg;
import lombok.Data;

@Data
public class FcmHeaderMessage {
    private String to;
    private SmsMsg data ;
    //private FcmNotificationMessage notification = new FcmNotificationMessage();
}
