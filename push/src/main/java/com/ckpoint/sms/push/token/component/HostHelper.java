package com.ckpoint.sms.push.token.component;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class HostHelper {

    @Value("${server.port}")
    private int port;

    private String ip;

    public String getHost() {
        return "http://" + this.ip + ":" + this.port;
    }

    @PostConstruct
    public void init() throws UnknownHostException {
        InetAddress local = InetAddress.getLocalHost();
        this.ip = local.getHostAddress();
    }

}
