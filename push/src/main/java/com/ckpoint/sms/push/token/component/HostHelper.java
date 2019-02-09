package com.ckpoint.sms.push.token.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.*;
import java.util.Enumeration;
import java.util.List;

@Component
@Slf4j
public class HostHelper {

    @Value("${server.port}")
    private int port;

    private String ip;

    public String getHost() {
        return "http://" + this.ip + ":" + this.port;
    }

    private String getHostAddress() throws SocketException, UnknownHostException {
        InetAddress localAddress = getLocalAddress();
        if (localAddress == null) {
            return Inet4Address.getLocalHost().getHostAddress();
        } else {
            return localAddress.getHostAddress();
        }
    }

    private InetAddress getLocalAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            List<InterfaceAddress> interfaceAddresses = networkInterfaces.nextElement().getInterfaceAddresses();
            for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                InetAddress address = interfaceAddress.getAddress();
                if (address.isSiteLocalAddress()) {
                    return address;
                }
            }
        }
        return null;
    }

    @PostConstruct
    public void init() throws UnknownHostException, SocketException {
        this.ip = getHostAddress();
        log.info(this.ip);

    }


}
