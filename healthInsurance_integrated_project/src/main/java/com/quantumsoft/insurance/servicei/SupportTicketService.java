package com.quantumsoft.insurance.servicei;



import com.quantumsoft.insurance.entity.SupportTicket;
import com.quantumsoft.insurance.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface SupportTicketService {

    SupportTicket createTicket(SupportTicket ticket);

    SupportTicket updateStatus(Long ticketId, TicketStatus status);

    List<SupportTicket> getTicketsByUser(Long userId);

    List<SupportTicket> getAllTickets();

    List<SupportTicket> filterTickets(String issueType, TicketStatus status, LocalDateTime start, LocalDateTime end, Long userId);

    SupportTicket assignTicket(Long ticketId, String assignedTo);
}
