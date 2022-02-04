package com.exflyer.oddiad.scheduler.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageReqDto {


  private List<MessageBody> messages;
  private String yellowidKey;
  private String deptcode;
  private String usercode;
  //-- SMS 용
  private String text;
  private String from;
  private String reservedTime;

}
