package com.exflyer.oddiad.scheduler.share;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {

  public static final String YYYYMMDD = "yyyyMMdd";

  public static final String HH = "HH";

  public static final String mm = "mm";

  public static final String ss = "ss";

  public static final String YYYYMMDDHHmmss = YYYYMMDD+HH+mm+ss;
  public static final String HHmmssSSS = HH+mm+ss + "SSS";

  public static LocalDateTime korNowDateTime() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
  }

  public static String korNowDateTimeYYYYMMDD() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern(YYYYMMDD));
  }


  public static String korNowDateTimeYYYYMMDDHHmmss() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern(YYYYMMDDHHmmss));
  }

  public static String korNowDateTimeHHmmssSSS() {
    return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern(HHmmssSSS));
  }

  public static LocalDate korNowDate() {
    return LocalDate.now(ZoneId.of("Asia/Seoul"));
  }
}
