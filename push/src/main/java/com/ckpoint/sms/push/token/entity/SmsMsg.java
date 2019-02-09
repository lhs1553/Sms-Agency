package com.ckpoint.sms.push.token.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SmsMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tokenId;

    private String sendNumber;
    private String recvNumber;

    @Type(type = "text")
    private String message;

    private Date sendDate;
    private Date callBackDate;

    @Transient
    private String serverUrl;

    private boolean split ;

    public SmsMsg(){
        this.split = true;
    }

    public void updateSendDate() {
        this.sendDate = new Date();
    }

    public void updateCallbackDate() {
        this.callBackDate = new Date();
    }
}
