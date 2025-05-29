package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.SupportAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportAgentRepository extends JpaRepository<SupportAgent, Long> {

    public SupportAgent findByNameIgnoreCase(String name);
}
