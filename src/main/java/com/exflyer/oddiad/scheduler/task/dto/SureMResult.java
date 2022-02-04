package com.exflyer.oddiad.scheduler.task.dto;

import java.util.List;
import lombok.Data;

@Data
public class SureMResult {


  private List<Results> results;
  private String message;
  private String code;

  @Data
  public static class Results {

    private String messageId;
    private String result;
  }
}
