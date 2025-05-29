package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.SuperAdmin;
import com.quantumsoft.insurance.enums.Role;
import com.quantumsoft.insurance.repository.SuperAdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperAdminInitializer implements CommandLineRunner {

    @Value(value = "${superadmin.username}")
    private String username;

    @Value(value = "${superadmin.password}")
    private String password;

    @Autowired
    private SuperAdminRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(SuperAdminInitializer.class);

    @Override
    public void run(String... args) throws Exception {
        Optional<SuperAdmin> optional = repository.findByUsername(username);
        if (optional.isEmpty()) {
            SuperAdmin superAdmin = new SuperAdmin();
            superAdmin.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            superAdmin.setPassword(encodedPassword);
            superAdmin.setRole(Role.SUPERADMIN);
            repository.save(superAdmin);
        }else
            logger.info("SuperAdmin already registered...!");
    }
}
