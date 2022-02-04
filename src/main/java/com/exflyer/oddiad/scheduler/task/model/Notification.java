package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림
 */
@Data
@Entity
@Table(name = "notification")
@NoArgsConstructor
public class Notification implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 그룹 순번
   */
  @Column(name = "group_seq", nullable = false)
  private Long groupSeq;

  @Column(name = "receive_id")
  private String receiveId;

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
   * 발송 시간(즉시 일경우 0)
   */
  @Column(name = "send_time")
  private String sendTime;

  /**
   * 발신자 id
   */
  @Column(name = "sender_id", nullable = false)
  private String senderId;

  /**
   * 발신자 이름
   */
  @Column(name = "sender_name")
  private String senderName;

  /**
   * 발신자 전화 번호
   */
  @Column(name = "sender_phone_number")
  private String senderPhoneNumber;

  @Column(name = "kakao_template_id")
  private String kakaoTemplateId;

  @Column(name = "alrim_talk")
  private boolean alrimTalk = false;

  @Column(name = "fail")
  private Boolean fail;

  @Column(name = "response")
  private String response;

  private LocalDateTime regDate;


  @Transient
  private String messageId;

}
