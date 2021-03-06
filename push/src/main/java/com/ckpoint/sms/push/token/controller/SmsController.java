package com.ckpoint.sms.push.token.controller;

import com.ckpoint.sms.push.token.entity.SmsMsg;
import com.ckpoint.sms.push.token.service.SmsSendService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SmsController {
    private final @NonNull
    SmsSendService smsSendService;

    @PostMapping("/send/sms")
    public void sendSms(@RequestBody SmsMsg smsMsg) {
        this.smsSendService.sendSms(smsMsg);
    }


}
