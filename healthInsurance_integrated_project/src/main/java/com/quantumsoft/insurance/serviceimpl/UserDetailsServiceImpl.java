package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Admin;
import com.quantumsoft.insurance.entity.SuperAdmin;
import com.quantumsoft.insurance.entity.Users;
import com.quantumsoft.insurance.repository.AdminRepository;
import com.quantumsoft.insurance.repository.SuperAdminRepository;
import com.quantumsoft.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("superadmin")) {
            Optional<SuperAdmin> optional = superAdminRepository.findByUsername(username);
            if(optional.isPresent()){
                SuperAdmin superAdmin = optional.get();
                return new User(superAdmin.getUsername(), superAdmin.getPassword(), Collections.emptyList());
            }
        }
            Optional<Admin> optional = adminRepository.findByEmail(username);
            if(optional.isPresent()){
                Admin admin = optional.get();
                return new User(admin.getEmail(), admin.getPassword(), Collections.emptyList());
            }

            Optional<Users> usersOptional = userRepository.findByEmail(username);
            if(usersOptional.isPresent()){
                Users users = usersOptional.get();
                return new User(users.getEmail(), users.getPassword(), Collections.emptyList());
            }

        throw new UsernameNotFoundException("User record is not present");
    }

    public SuperAdmin getSuperAdmin(String username){
        Optional<SuperAdmin> optional = superAdminRepository.findByUsername(username);
        if(optional.isPresent())
            return optional.get();
        else
            throw new UsernameNotFoundException("SuperAdmin record not found in the database");
    }

    public Admin getAdmin(String email){
        Optional<Admin> optional = adminRepository.findByEmail(email);
        if(optional.isPresent())
            return optional.get();
        else
            throw new UsernameNotFoundException("Admin record not found in the database");
    }
}
