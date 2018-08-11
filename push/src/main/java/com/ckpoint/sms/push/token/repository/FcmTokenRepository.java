package com.ckpoint.sms.push.token.repository;

import com.ckpoint.sms.push.token.entity.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    List<FcmToken> findByPhone(String phone);
    FcmToken findFirstByTokenIsNotNullOrderBySendCnt();
}
