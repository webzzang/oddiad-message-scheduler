package com.exflyer.oddiad.scheduler.task.jobs.message;

import com.exflyer.oddiad.scheduler.task.codes.TaskScheduleTime;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.service.message.MessageSender;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MessageSendTask {

  @Autowired
  private MessageSender messageSender;


  /**
   * ### 메세지 발송
   *
   * - 목적 : notification 테이블에 있는 정보를 SureM API 를 이용 하여 발송
   *
   * - 조건 : notification 테이블에 있는 정보가 있어야 함
   * - 주기 : 3초
   * - Task Class : MessageSendTask.java
   * @throws IOException
   */
  @Scheduled(fixedDelay = 5 * TaskScheduleTime.SECOND)
  public void sendMessage() throws IOException {
    StopWatch stopWatch = new StopWatch("##### 메세지 발송");
    stopWatch.start();
    List<Notification> sendTarget = new ArrayList<>();
    try {
      sendTarget = messageSender.send();
    } catch (IOException e) {
      log.error("send message error {}", e);
    }
    stopWatch.stop();
    log.info(" time : {} - send count : {}", stopWatch.shortSummary(), sendTarget.size());
  }


}
