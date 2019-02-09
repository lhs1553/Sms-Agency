package com.ckpoint.sms.push.token.service;

import com.ckpoint.sms.push.token.component.HostHelper;
import com.ckpoint.sms.push.token.component.RestRequest;
import com.ckpoint.sms.push.token.component.SmsPushProperties;
import com.ckpoint.sms.push.token.entity.FcmToken;
import com.ckpoint.sms.push.token.entity.SmsMsg;
import com.ckpoint.sms.push.token.model.FcmPushMessage;
import com.ckpoint.sms.push.token.repository.FcmTokenRepository;
import com.ckpoint.sms.push.token.repository.SmsMsgRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class SmsSendServiceImpl implements SmsSendService {

    private final @NonNull
    SmsMsgRepository smsMsgRepository;

    private final @NonNull
    FcmTokenRepository fcmTokenRepository;

    private final @NonNull
    SmsPushProperties smsPushProperties;
    private final @NonNull
    RestRequest restRequest;

    private final @NonNull
    HostHelper hostHelper;

    @PostConstruct
    public void notiToOwner() {
        SmsMsg notiMsg = new SmsMsg();
        notiMsg.setSendNumber("01040652126");
        notiMsg.setRecvNumber("01040652126");
        notiMsg.setMessage(hostHelper.getHost());
        notiMsg.setSplit(false);
        this.sendSms(notiMsg);
    }

    @Override
    public SmsMsg sendSms(SmsMsg smsMsg) {
        FcmToken fcmToken = this.fcmTokenRepository.findFirstByPhoneOrderByIdDesc(smsMsg.getSendNumber().replaceAll("-", ""));
        if (fcmToken == null) {
            throw new RuntimeException("unauthorization");
        }
        smsMsg.updateSendDate();
        smsMsg.setTokenId(fcmToken.getId());
        smsMsg.setServerUrl(hostHelper.getHost());
        smsMsg = this.smsMsgRepository.save(smsMsg);
        FcmPushMessage pushMessage = new FcmPushMessage(fcmToken);
        pushMessage.getMessage().setData(smsMsg);
        this.restRequest.commonRestApiCall(this.smsPushProperties.getFcmApiUrl(), pushMessage.getMessage(), HttpMethod.POST, null, "key=" + this.smsPushProperties.getFcmApiKey(), String.class);

        fcmToken.plusSendCnt();
        this.fcmTokenRepository.save(fcmToken);

        return smsMsg;

    }

    @Override
    public void callback(Long id) {
        SmsMsg smsMsg = this.smsMsgRepository.findById(id).get();
        smsMsg.updateCallbackDate();
        this.smsMsgRepository.save(smsMsg);

        FcmToken fcmToken = this.fcmTokenRepository.findById(smsMsg.getTokenId()).get();
        fcmToken.plusSuccessCnt();
        this.fcmTokenRepository.save(fcmToken);

    }
}
