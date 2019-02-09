package com.ckpoint.sms.push.token.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class HostHelper {

    @Value("${server.port}")
    private int port;

    private String ip;

    public String getHost() {
        return "http://" + this.ip + ":" + this.port;
    }


    @PostConstruct
    public void init() {

        RestTemplate restTemplate = new RestTemplate();
        this.ip = restTemplate.getForObject("http://bot.whatismyipaddress.com", String.class);
        log.info(this.getHost());

    }


}
