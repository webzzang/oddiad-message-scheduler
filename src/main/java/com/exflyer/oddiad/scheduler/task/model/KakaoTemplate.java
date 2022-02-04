package com.exflyer.oddiad.scheduler.task.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 카카오 알림톡 템플릿
 */
@Data
@Entity
@Table(name = "kakao_template")
public class KakaoTemplate implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 슈어엠에 등록되어있는 템플릿 코드
   */
  @Id
  @Column(name = "template_id", nullable = false)
  private String templateId;

  /**
   * 템플릿명
   */
  @Column(name = "template_name", nullable = false)
  private String templateName;

  /**
   * 템플릿 내용
   */
  @Column(name = "template_contents", nullable = false)
  private String templateContents;

}
