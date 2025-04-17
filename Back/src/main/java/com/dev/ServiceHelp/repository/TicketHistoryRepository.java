package com.dev.ServiceHelp.repository;

import com.dev.ServiceHelp.models.entities.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

    Set<TicketHistory> findByTicketId(Long ticketId);

    @Query(value = "SELECT * FROM TICKET_HISTORY  WHERE ticket_id = :ticketId",
            countQuery = "SELECT count(*) FROM TICKET_HISTORY  WHERE ticket_id = :ticketId",
            nativeQuery = true)
    List<TicketHistory> findByTicketIdNative(@Param("ticketId") Long ticketId);

}
