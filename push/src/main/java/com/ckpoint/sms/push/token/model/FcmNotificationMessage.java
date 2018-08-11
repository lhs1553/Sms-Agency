package com.ckpoint.sms.push.token.model;

import lombok.Data;

@Data
public class FcmNotificationMessage {
    private String title;
    private String body;

    public FcmNotificationMessage(){
        this.title = "Sms send";
        this.body = "Sms send request";
    }

}
