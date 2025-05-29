package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.SupportAgent;
import com.quantumsoft.insurance.entity.SupportTicket;
import com.quantumsoft.insurance.enums.TicketStatus;
import com.quantumsoft.insurance.exception.SupportAgentNotFoundException;
import com.quantumsoft.insurance.repository.SupportAgentRepository;
import com.quantumsoft.insurance.repository.SupportTicketRepository;
import com.quantumsoft.insurance.servicei.SupportAgentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportAgentServiceImpl implements SupportAgentServiceI {

    @Autowired
    private SupportAgentRepository repository;

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Override
    public String addSupportAgent(SupportAgent supportAgent) {
        repository.save(supportAgent);
        return "Support Agent record has saved successfully...!";
    }

    @Override
    public List<SupportAgent> fetchAllSupportAgents() {
        return repository.findAll();
    }

    @Override
    public SupportAgent updateSupportAgent(Long supportAgentId, SupportAgent supportAgent) {
        Optional<SupportAgent> supportAgentOptional = repository.findById(supportAgentId);
        if(supportAgentOptional.isPresent()) {
            List<SupportTicket> supportTickets = supportAgentOptional.get().getSupportTickets();
            supportAgent.setSupportTickets(supportTickets);
            return repository.save(supportAgent);
        }
        else
            throw new SupportAgentNotFoundException("Support Agent updated successfully...!");
    }

    @Override
    public SupportAgent fetchSupportAgent(Long supportAgentId) {
        Optional<SupportAgent> supportAgentOptional = repository.findById(supportAgentId);
        if(supportAgentOptional.isPresent())
            return supportAgentOptional.get();
        else
            throw new SupportAgentNotFoundException("Support Agent record not found");
    }

    @Override
    public String deleteSupportAgent(Long supportAgentId) {
        Optional<SupportAgent> supportAgentOptional = repository.findById(supportAgentId);
        if(supportAgentOptional.isPresent()) {
            repository.delete(supportAgentOptional.get());
            return "Support Agent record deleted successfully...!";
        }
        else
            throw new SupportAgentNotFoundException("Support Agent record not found");
    }

    @Override
    public String updateTicketStatus(Long supportAgentId, Long supportTicketId, TicketStatus ticketStatus) {
        Optional<SupportAgent> supportAgentOptional = repository.findById(supportAgentId);
        if(supportAgentOptional.isPresent()) {
            SupportAgent supportAgent = supportAgentOptional.get();
            List<SupportTicket> supportTickets = supportAgent.getSupportTickets();
            for (SupportTicket supportTicket : supportTickets) {
                if(supportTicket.getSupportTicketId().equals(supportAgentId)){
                    supportTicket.setStatus(ticketStatus);
                    supportTicketRepository.save(supportTicket);
                }
            }
            supportAgent.setSupportTickets(supportTickets);
            repository.save(supportAgent);
            return "Support ticket status updated successfully...!";
        }else
            throw new SupportAgentNotFoundException("Support");
    }
}
