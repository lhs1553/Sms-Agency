package com.ckpoint.sms.push.token.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SmsPushProperties {

    @Value("${fcm.api.key}")
    private String fcmApiKey;

    @Value("${fcm.api.url}")
    private String fcmApiUrl;
}
