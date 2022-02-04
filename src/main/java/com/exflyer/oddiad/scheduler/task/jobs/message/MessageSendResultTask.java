package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.repository.NotificationGroupRepository;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MessageSendResultTask {

  @Autowired
  private NotificationGroupRepository notificationGroupRepository;


  /**
   * ### 메세지 발송 결과 update
   *
   * - 목적 : 메세지 발송 스케쥴러가 발송 한 결과를 취합 한다.
   *
   * - 주기 : 10분 주기
   * - Task Class : MessageSendResultTask.java
   */
  @Scheduled(fixedDelay = 10 * TaskScheduleTime.MIN)
  public void updateMessageSendResult() {
    StopWatch stopWatch = new StopWatch("##### 발송(fixedDelay=10 min) 결과 update");
    stopWatch.start();
    try {
      LocalDate startDate = LocalDateUtils.korNowDate().minusDays(1);
      LocalDate endDate = LocalDateUtils.korNowDate().plusDays(1);
      notificationGroupRepository.updateSuccessAndFailCount(startDate, endDate);
    } catch (Exception e) {
      log.error("send message error {}", e);
    }

    stopWatch.stop();
    log.info("{}", stopWatch.shortSummary());
  }

}
