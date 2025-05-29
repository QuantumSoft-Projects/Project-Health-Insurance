package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.SupportTicket;
import com.quantumsoft.insurance.enums.TicketStatus;
import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
import com.quantumsoft.insurance.servicei.SupportTicketService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/support-tickets")
@CrossOrigin("*")
public class SupportTicketController {

    private final SupportTicketService service; // ✅ final field ensures it's injected

    @Autowired
    private JwtService jwtService;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createTicket(@RequestBody SupportTicket ticket) {
        return ResponseEntity.ok(service.createTicket(ticket));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupportTicket> updateStatus(@PathVariable Long id, @RequestParam TicketStatus status, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN"))
            return ResponseEntity.ok(service.updateStatus(id, status));
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SupportTicket>> getUserTickets(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getTicketsByUser(userId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<SupportTicket>> getAllTickets(@RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN"))
            return ResponseEntity.ok(service.getAllTickets());
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<SupportTicket> assignTicket(@PathVariable Long id, @RequestParam String assignedTo, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN"))
            return ResponseEntity.ok(service.assignTicket(id, assignedTo));
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SupportTicket>> filterTickets(
            @RequestParam(required = false) String issueType,
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam(required = false) Long userId
    ) {
        return ResponseEntity.ok(service.filterTickets(issueType, status, start, end, userId));
    }
}
