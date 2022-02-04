package com.exflyer.oddiad.scheduler.task.jobs.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
class MessageSendResultTaskTest {

  @Autowired
  private MessageSendResultTask messageSendResultTask;

  @DisplayName("메세지 발송 결과 update")
  @Test
  public void updateMessageSendResult() throws Exception {
    messageSendResultTask.updateMessageSendResult();
  }

}
