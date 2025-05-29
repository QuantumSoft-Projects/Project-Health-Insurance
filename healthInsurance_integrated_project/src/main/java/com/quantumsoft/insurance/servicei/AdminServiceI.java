package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Admin;

public interface AdminServiceI {

    public String registerAdmin(Admin admin);

    public String sendOtp(String email);

    public String resetPassword(String otp, String password, String confirmPassword);

    //public String resetPassword(String email, String password);

    public String adminLogin(String email, String password);
}
