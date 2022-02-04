package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long>,
  JpaSpecificationExecutor<NotificationHistory> {

}
