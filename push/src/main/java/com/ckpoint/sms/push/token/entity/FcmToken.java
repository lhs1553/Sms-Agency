package com.ckpoint.sms.push.token.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class FcmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String phone;
    private String token;

    private long sendCnt ;
    private long successCnt;

    public void plusSendCnt(){
        this.sendCnt ++;
    }
    public void plusSuccessCnt(){
        this.successCnt++;
    }
}
