package com.exflyer.oddiad.scheduler.task.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class NotificationConfig {

  @Value("${notification.alimtalk-api-host}")
  private String alimtalkApiHost;

  @Value("${notification.sms-api-host}")
  private String smsApiHost;

  @Value("${notification.lms-api-host}")
  private String lmsApiHost;

  @Value("${notification.id}")
  private String id;

  @Value("${notification.dept-code}")
  private String deptCode;

  @Value("${notification.kakao-yellow-key}")
  private String yellowKey;

  @Value("${notification.sender-phone}")
  private String senderPhone;

}
