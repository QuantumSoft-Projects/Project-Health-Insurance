package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.SupportAgent;
import com.quantumsoft.insurance.enums.TicketStatus;

import java.util.List;

public interface SupportAgentServiceI {

   public String addSupportAgent(SupportAgent supportAgent);

   public List<SupportAgent> fetchAllSupportAgents();

   public SupportAgent updateSupportAgent(Long supportAgentId, SupportAgent supportAgent);

   public SupportAgent fetchSupportAgent(Long supportAgentId);

   public String deleteSupportAgent(Long supportAgentId);

   public String updateTicketStatus(Long supportAgentId, Long supportTicketId, TicketStatus ticketStatus);
}
