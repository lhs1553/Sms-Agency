package com.ckpoint.sms.push.token.service;

import com.ckpoint.sms.push.token.entity.SmsMsg;

public interface SmsSendService {

    SmsMsg sendSms(SmsMsg smsMsg);
    void callback(Long id);
}
