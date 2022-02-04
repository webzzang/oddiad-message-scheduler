package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long>,
  JpaSpecificationExecutor<Notification> {

  @Query(value = "select * from notification "
    + "where fail != true or fail is null "
    + "and send_time is null or send_time = '0' or send_time <= :targetTime limit 1000", nativeQuery = true)
  List<Notification> findBySendTime(String targetTime);
}
