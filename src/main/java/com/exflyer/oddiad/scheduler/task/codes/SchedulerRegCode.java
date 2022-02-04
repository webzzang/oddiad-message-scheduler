package com.exflyer.oddiad.scheduler.task.codes;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SchedulerRegCode {

  private String regId;

  private LocalDateTime regDate;

  SchedulerRegCode(String regId, LocalDateTime regDate) {
    this.regId = regId;
    this.regDate = regDate;
  }

  public static SchedulerRegCode createRegInfo(){
    return new SchedulerRegCode("sceduler", LocalDateUtils.korNowDateTime());
  }

}
