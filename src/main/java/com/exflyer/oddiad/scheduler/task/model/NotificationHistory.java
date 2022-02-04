package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.dto.SureMResult;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notification_history")
@NoArgsConstructor
public class NotificationHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  private Long seq;

  /**
   * 그룹 순번
   */
  @Column(name = "group_seq", nullable = false)
  private Long groupSeq;

  /**
   * 수신자 이름
   */
  @Column(name = "receive_name")
  private String receiveName;

  /**
   * 수신자 전화 번호
   */
  @Column(name = "receive_phone_number", nullable = false)
  private String receivePhoneNumber;

  /**
   * 내용
   */
  @Column(name = "contents", nullable = false)
  private String contents;

  /**
   * 발송 시간
   */
  @Column(name = "send_time")
  private String sendTime;

  /**
   * 요청 시간
   */
  @Column(name = "request_time", nullable = false)
  private LocalDateTime requestTime;

  /**
   * 응답 코드
   */
  @Column(name = "response_code", nullable = false)
  private String responseCode;

  /**
   * 응답 메세지
   */
  @Column(name = "response_message")
  private String responseMessage;

  /**
   * 성공 여부
   */
  @Column(name = "success", nullable = false)
  private Boolean success;

  /**
   * 발신자 이름
   */
  @Column(name = "sender_id", nullable = false)
  private String senderId;

  /**
   * 발신자 이름
   */
  @Column(name = "sender_name", nullable = false)
  private String senderName;

  /**
   * 발신자 전화 번호
   */
  @Column(name = "sender_phone_number")
  private String senderPhoneNumber;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 카카오 템플릿 id
   */
  @Column(name = "kakao_template_id")
  private String kakaoTemplateId;

  /**
   * 수신 id
   */
  @Column(name = "receive_id")
  private String receiveId;

  /**
   * 알림톡여부
   */
  @Column(name = "alrim_talk")
  private Boolean alrimTalk;

  @Column(name = "message_id")
  private String messageId;

  public NotificationHistory(Notification notification, SureMResult sureMResult) {
    this.seq = notification.getSeq();
    this.groupSeq = notification.getGroupSeq();
    this.receiveName = notification.getReceiveName();
    this.receivePhoneNumber = notification.getReceivePhoneNumber();
    this.contents = notification.getContents();
    this.sendTime = LocalDateUtils.korNowDateTime().format(DateTimeFormatter.ofPattern(LocalDateUtils.YYYYMMDDHHmmss));
    this.requestTime = notification.getRegDate();
    this.success = sureMResult.getCode().equalsIgnoreCase("200");
    this.senderId = notification.getSenderId();
    this.senderName = notification.getSenderName();
    this.senderPhoneNumber = notification.getSenderPhoneNumber();
    this.regDate = LocalDateUtils.korNowDateTime();
    this.kakaoTemplateId = notification.getKakaoTemplateId();
    this.receiveId = notification.getReceiveId();
    this.alrimTalk = notification.isAlrimTalk();
    this.responseCode = sureMResult.getCode();
    this.responseMessage = sureMResult.getMessage();
    this.messageId = notification.getMessageId();
  }
}
