package com.ckpoint.sms.push.token.service;

import com.ckpoint.sms.push.token.entity.FcmToken;
import com.ckpoint.sms.push.token.repository.FcmTokenRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FcmTokenServiceImpl implements FcmTokenService {

    private final @NonNull
    FcmTokenRepository fcmTokenRepository;


    @Override
    public FcmToken registFcmToken(FcmToken fcmToken) {

        log.info(fcmToken.toString());

        List<FcmToken> existTokens = this.fcmTokenRepository.findByPhone(fcmToken.getPhone());
        this.fcmTokenRepository.deleteAll(existTokens);
        return this.fcmTokenRepository.save(fcmToken);
    }
}
