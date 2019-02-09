package com.ckpoint.sms.push.token.controller;

import com.ckpoint.sms.push.token.entity.FcmToken;
import com.ckpoint.sms.push.token.service.FcmTokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenContorller {

    private final @NonNull FcmTokenService fcmTokenService;

    @PostMapping("/regist/token")
    public FcmToken registToken(@RequestBody FcmToken fcmToken) {
        return this.fcmTokenService.registFcmToken(fcmToken);
    }
}
