package com.quantumsoft.insurance.serviceimpl;


import com.quantumsoft.insurance.entity.SupportAgent;
import com.quantumsoft.insurance.entity.SupportTicket;
import com.quantumsoft.insurance.enums.TicketStatus;
import com.quantumsoft.insurance.repository.SupportAgentRepository;
import com.quantumsoft.insurance.repository.SupportTicketRepository;
import com.quantumsoft.insurance.servicei.SupportTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository repository;

    @Autowired
    private SupportAgentRepository supportAgentRepository;

    @Override
    public SupportTicket createTicket(SupportTicket ticket) {
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        return repository.save(ticket);
    }

    @Override
    public SupportTicket updateStatus(Long ticketId, TicketStatus status) {
        SupportTicket ticket = repository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());
        return repository.save(ticket);
    }

    @Override
    public List<SupportTicket> getTicketsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<SupportTicket> getAllTickets() {
        return repository.findAll();
    }

    @Override
    public List<SupportTicket> filterTickets(String issueType, TicketStatus status, LocalDateTime start, LocalDateTime end, Long userId) {
        // TODO: Implement real filtering logic using JPA Specification or QueryDSL
        return repository.findAll();
    }

    @Override
    public SupportTicket assignTicket(Long ticketId, String assignedTo) {
        SupportTicket ticket = repository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        ticket.setAssignedTo(assignedTo);
        ticket.setUpdatedAt(LocalDateTime.now());
        SupportAgent supportAgent = supportAgentRepository.findByNameIgnoreCase(assignedTo);
        List<SupportTicket> supportTickets = supportAgent.getSupportTickets();
        supportTickets.add(ticket);
        supportAgent.setSupportTickets(supportTickets);
        SupportTicket savedTicket = repository.save(ticket);
        supportAgentRepository.save(supportAgent);
        return savedTicket;
    }
}
