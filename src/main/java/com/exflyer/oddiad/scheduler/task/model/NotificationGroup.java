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

/**
 * 알림_group
 */
@Data
@Entity
@Table(name = "notification_group")
public class NotificationGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 알림 대상 그룹 순번
   */
  @Column(name = "target_group_seq")
  private Long targetGroupSeq;

  /**
   * 내용
   */
  @Column(name = "contents")
  private String contents;

  /**
   * 광고 메세지 여부
   */
  @Column(name = "adv_message")
  private Boolean advMessage;

  /**
   * 발송 시간(즉시 일경우 0)
   */
  @Column(name = "send_time")
  private String sendTime;

  /**
   * 발신자 전화 번호
   */
  @Column(name = "sender_phone_number")
  private String senderPhoneNumber;

  /**
   * 자동 여부
   */
  @Column(name = "auto", nullable = false)
  private Boolean auto;

  @Column(name = "total_count")
  private Integer totalCount;

  /**
   * 성공 카운트
   */
  @Column(name = "success_count")
  private Integer successCount;

  /**
   * 실패 카운트
   */
  @Column(name = "fail_count")
  private Integer failCount;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 생성 id
   */
  @Column(name = "reg_id", nullable = false)
  private String regId;

  @Transient
  private String regName;

  /**
   * 완료 여부
   */
  @Column(name = "done", nullable = false)
  private Boolean done;

  /**
   * template_id
   */
  @Column(name = "template_id")
  private String templateId;


  /**
   * 알림톡 여부
   */
  @Column(name = "alrim_talk")
  private boolean alrimTalk = false;

  @Column(name = "target_create_done")
  private boolean targetCreateDone;

}
