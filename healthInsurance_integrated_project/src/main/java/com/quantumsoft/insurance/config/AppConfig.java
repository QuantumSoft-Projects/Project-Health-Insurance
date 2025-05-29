package com.quantumsoft.insurance.config;

import com.quantumsoft.insurance.filter.AppFilter;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import com.quantumsoft.insurance.serviceimpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AppFilter appFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("in filter chain");
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/superadmin/login", "/api/admin/sendOtp", "/api/admin/verifyOtp", "/api/admin/resetPswd", "/api/admin/login").permitAll()

                        .requestMatchers("/api/users/**", "/api/premium/**", "/api/policy/all", "/api/addOn/fetchAll", "/api/rider/fetchAll").permitAll()
                        .requestMatchers("/api/customizedPolicy/**").permitAll()
                        .requestMatchers("/api/claim/add", "/api/claim/saveDocuments/**", "/api/claim/singleClaim/**", "/api/claim/documents/**").permitAll()
                        .requestMatchers("/api/hospitals/city/**", "/api/hospitals/network", "/api/hospitals/icu", "/api/hospitals").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/hospitals/*").permitAll()
                        .requestMatchers("/api/doctors/*", "/api/doctors", "/api/hospitals/*/doctors", "/api/doctors/search", "/api/doctors/available").permitAll()
                        .requestMatchers("/api/appointments/book", "/api/appointments/user/*").permitAll()
                        .requestMatchers("/api/teleconsultation/appointment/*", "/api/teleconsultation/*", "/api/teleconsultation/download-prescription/*").permitAll()
                        .requestMatchers( "/api/reviews", "/api/reviews/policy/*", "/api/reviews/hospital/*", "/api/reviews/doctor/*").permitAll()
                        .requestMatchers("/api/support-tickets", "/api/support-tickets/user/*", "/api/support-tickets/filter").permitAll()
                        .requestMatchers("/api/supportAgent/*/*").permitAll()
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/api/**").authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println("in filter chain");
//        return http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/api/superadmin/login", "/api/admin/sendOtp", "/api/admin/verifyOtp", "/api/admin/resetPswd", "/api/admin/login").permitAll()
//                        .requestMatchers("/api/users/**", "/api/premium/**", "/api/policy/all", "/api/addOn/fetchAll", "/api/rider/fetchAll").permitAll()
//                        .requestMatchers("/api/customizedPolicy/**").permitAll()
//                        .requestMatchers("/api/claim/add", "/api/claim/saveDocuments", "/api/claim/get", "/api/claim/documents").permitAll()
//                        .requestMatchers("/api/admin/register", "/api/policy/").authenticated())
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class).build();
//    }

    @Bean

    public UrlBasedCorsConfigurationSource corsConfigurationSource() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOriginPattern("*");

        config.addAllowedHeader("*");

        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return source;

    }

}

