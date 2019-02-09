package com.ckpoint.sms.push.token.controller;

import com.ckpoint.sms.push.token.service.SmsSendService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
@RequiredArgsConstructor
public class CallbackController {
    private final @NonNull
    SmsSendService smsSendService;

    @GetMapping("/{id}")
    public void sendSms(@PathVariable Long id) {
        this.smsSendService.callback(id);
    }


}
