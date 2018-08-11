package com.ckpoint.sms.push.token.repository;

import com.ckpoint.sms.push.token.entity.SmsMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsMsgRepository extends JpaRepository<SmsMsg, Long> {
}
