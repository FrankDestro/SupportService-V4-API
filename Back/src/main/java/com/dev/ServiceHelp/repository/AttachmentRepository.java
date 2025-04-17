package com.dev.ServiceHelp.repository;

import com.dev.ServiceHelp.models.entities.Attachment;
import com.dev.ServiceHelp.models.entities.TicketHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Set<Attachment> findByTicketId(Long ticketId);

    @Query(value = "SELECT * FROM ATTACHMENT  WHERE ticket_id = :ticketId",
            countQuery = "SELECT count(*) FROM ATTACHMENT  WHERE ticket_id = :ticketId",
            nativeQuery = true)
    List<Attachment> findByTicketIdNative(@Param("ticketId") Long ticketId);

}