package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.config.NotificationConfig;
import com.exflyer.oddiad.scheduler.task.dto.MessageBody;
import com.exflyer.oddiad.scheduler.task.dto.MessageReqDto;
import com.exflyer.oddiad.scheduler.task.dto.SureMResult;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationHistory;
import com.exflyer.oddiad.scheduler.task.repository.NotificationHistoryRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSender {

  private final String RESEN = "Y";

  private final String SUREM_SUCCESS = "200";

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private NotificationHistoryRepository notificationHistoryRepository;

  @Autowired
  private NotificationConfig notificationConfig;

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private Gson gsonUnderScores;

  @Autowired
  private Gson gson;

  public SureMResult sendMessage(Notification notification) throws IOException {
    MessageReqDto messageReqDto;
    HttpPost post;
    if (notification.isAlrimTalk()) {
      messageReqDto = createAlrimTalkMessageReqBody(notification);
      post = new HttpPost(notificationConfig.getAlimtalkApiHost());
    } else {
      messageReqDto = createSmsMessageReqBody(notification);
      if (notification.getContents().getBytes().length > 80) {
        post = new HttpPost(notificationConfig.getLmsApiHost());
      } else {
        post = new HttpPost(notificationConfig.getSmsApiHost());
      }
    }
    post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    post.setEntity(new StringEntity(gsonUnderScores.toJson(messageReqDto), "UTF-8"));
    HttpResponse response = httpClient.execute(post);
    String responseBody = EntityUtils.toString(response.getEntity());
    log.info("sureM res-body : {}", responseBody);
    SureMResult sureMResult = gson.fromJson(responseBody, SureMResult.class);
    log.info("sureM result : {}", sureMResult);
    EntityUtils.consumeQuietly(response.getEntity());

    return sureMResult;
  }

  private MessageReqDto createAlrimTalkMessageReqBody(Notification notification) {
    MessageReqDto messageReqDto = new MessageReqDto();
    messageReqDto.setUsercode(notificationConfig.getId());
    messageReqDto.setDeptcode(notificationConfig.getDeptCode());
    messageReqDto.setYellowidKey(notificationConfig.getYellowKey());
    MessageBody msgBody = new MessageBody();
    String targetPhoneNumber = notification.getReceivePhoneNumber();
    String firstNumber = notification.getReceivePhoneNumber().substring(0, 1);
    if (firstNumber.equals("0")) {
      targetPhoneNumber = "82" + notification.getReceivePhoneNumber().substring(1);
    }
    msgBody.setTo(targetPhoneNumber);
    String messageId = LocalDateUtils.korNowDateTimeHHmmssSSS();
    msgBody.setMessageId(messageId);
    msgBody.setReSend(RESEN);
    msgBody.setTemplateCode(notification.getKakaoTemplateId());
    if (notification.getSendTime() != null) {
      msgBody.setReservedTime(notification.getSendTime());
    }
    if (StringUtils.isBlank(notification.getSenderPhoneNumber())) {
      msgBody.setFrom(notificationConfig.getSenderPhone());
    } else {
      msgBody.setFrom(notification.getSenderPhoneNumber());
    }
    msgBody.setText(notification.getContents());
    messageReqDto.setMessages(Arrays.asList(msgBody));
    notification.setMessageId(messageId);
    return messageReqDto;
  }

  private MessageReqDto createSmsMessageReqBody(Notification notification) {
    MessageReqDto messageReqDto = new MessageReqDto();
    messageReqDto.setUsercode(notificationConfig.getId());
    messageReqDto.setDeptcode(notificationConfig.getDeptCode());
    MessageBody msgBody = new MessageBody();
    msgBody.setTo(notification.getReceivePhoneNumber());
    String messageId = LocalDateUtils.korNowDateTimeHHmmssSSS();
    msgBody.setMessageId(messageId);
    if (notification.getSendTime() != null) {
      msgBody.setReservedTime(notification.getSendTime());
    }
    if (StringUtils.isBlank(notification.getSenderPhoneNumber())) {
      messageReqDto.setFrom(notificationConfig.getSenderPhone());
    } else {
      messageReqDto.setFrom(notification.getSenderPhoneNumber());
    }
    messageReqDto.setText(notification.getContents());
    messageReqDto.setMessages(Arrays.asList(msgBody));
    notification.setMessageId(messageId);
    return messageReqDto;
  }

  public List<Notification> send() throws IOException {
    // 페이지 시작은 0 페이지 부터 이다.
    String targetTime = LocalDateUtils.korNowDateTimeYYYYMMDDHHmmss();
    List<Notification> notificationTarget = notificationRepository.findBySendTime(targetTime);
    for (Notification notification : notificationTarget) {
      SureMResult sureMResult = sendMessage(notification);
      if (notification.getGroupSeq() != null && notification.getGroupSeq() > 0) {
        NotificationHistory notificationHistory = new NotificationHistory(notification, sureMResult);
        notificationHistoryRepository.saveAndFlush(notificationHistory);
      }
      if (sureMResult.getCode().equalsIgnoreCase(SUREM_SUCCESS)) {
        notificationRepository.delete(notification);
      } else {
        notification.setFail(true);
        notification.setResponse(notification.getResponse());
        notificationRepository.saveAndFlush(notification);
      }
    }
    return notificationTarget;
  }
}



