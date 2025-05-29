package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.SupportAgent;
import com.quantumsoft.insurance.enums.TicketStatus;
import com.quantumsoft.insurance.servicei.SupportAgentServiceI;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/supportAgent")
@CrossOrigin("*")
public class SupportAgentController {

    @Autowired
    private SupportAgentServiceI service;

    @Autowired
    private JwtService jwtService;

    @PostMapping(produces = "plain/text", consumes = "application/json")
    public ResponseEntity<String> addSupportAgent(@Valid @RequestBody SupportAgent supportAgent, @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN")) {
            String message = service.addSupportAgent(supportAgent);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SupportAgent>> fetchAllSupportAgents(@RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN")) {
            List<SupportAgent> supportAgents = service.fetchAllSupportAgents();
            return new ResponseEntity<List<SupportAgent>>(supportAgents, HttpStatus.OK);
        }else
            return new ResponseEntity<List<SupportAgent>>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/{supportAgentId}", produces = "application/json")
    public ResponseEntity<SupportAgent> fetchSupportAgent(@PathVariable Long supportAgentId, @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN")) {
            SupportAgent supportAgent = service.fetchSupportAgent(supportAgentId);
            return new ResponseEntity<SupportAgent>(supportAgent, HttpStatus.OK);
        }else
            return new ResponseEntity<SupportAgent>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(value = "/{supportAgentId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SupportAgent> updateSupportAgent(@PathVariable Long supportAgentId, @RequestBody SupportAgent supportAgent, @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN")) {
            SupportAgent updatedSupportAgent = service.updateSupportAgent(supportAgentId, supportAgent);
            return new ResponseEntity<SupportAgent>(updatedSupportAgent, HttpStatus.OK);
        }else
            return new ResponseEntity<SupportAgent>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping(value = "/{supportAgentId}", produces = "application/json")
    public ResponseEntity<String> removeSupportAgent(@PathVariable Long supportAgentId, @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPPORTADMIN")) {
            String message = service.deleteSupportAgent(supportAgentId);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping(value = "/{supportAgentId}/{supportTicketId}")
    public ResponseEntity<String> updateTicketStatus(@PathVariable Long supportAgentId, @PathVariable Long supportTicketId, @RequestParam TicketStatus ticketStatus){
        String message = service.updateTicketStatus(supportAgentId, supportAgentId, ticketStatus);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
