package com.event.event_pi.Daos;

import com.event.event_pi.Entities.Event;
import com.event.event_pi.Entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface EventDao extends JpaRepository<Event, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.status = " +
            "CASE " +
            "WHEN CURRENT_TIMESTAMP < e.startDate THEN :planifie " +
            "WHEN CURRENT_TIMESTAMP BETWEEN e.startDate AND e.endDate THEN :enCours " +
            "ELSE :termine " +
            "END")
    void updateEventStatus(@Param("planifie") StatusType planifie,
                           @Param("enCours") StatusType enCours,
                           @Param("termine") StatusType termine);
    List<Event> findByStatus(StatusType status);

    List<Event> findByStartDate(LocalDate today);
    List<Event> findByStartDateAfterOrderByStartDate(Timestamp startDate);
}
