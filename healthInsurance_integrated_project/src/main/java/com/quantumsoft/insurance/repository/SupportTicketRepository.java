package com.quantumsoft.insurance.repository;


import com.quantumsoft.insurance.entity.SupportTicket;
import com.quantumsoft.insurance.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
   List<SupportTicket> findByUserId(Long userId);
    List<SupportTicket> findByStatus(TicketStatus status);
    List<SupportTicket> findByIssueType(String issueType);
    List<SupportTicket> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<SupportTicket> findByUserIdAndStatus(Long userId, TicketStatus status);
}