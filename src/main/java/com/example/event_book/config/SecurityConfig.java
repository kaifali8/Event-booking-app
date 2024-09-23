package com.example.event_book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection using the new syntax
                .authorizeHttpRequests(authorize -> authorize
                        // Admin-only for admin endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Event-specific access
                        .requestMatchers(HttpMethod.POST, "/api/events/**").hasRole("ADMIN")  // Only admin can create events
                        .requestMatchers(HttpMethod.GET, "/api/events/**").permitAll()  // Everyone can view events

                        // User-specific access
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // Allow user registration without login
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")  // User and Admin can view user details

                        // Bookings-specific access
                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasAnyRole("USER", "ADMIN")  // Users and Admin can create bookings
                        .requestMatchers(HttpMethod.GET, "/api/bookings/**").hasAnyRole("USER", "ADMIN")  // Users and Admin can view bookings

                        // Bookmarks-specific access
                        .requestMatchers(HttpMethod.POST, "/api/bookmarks/**").hasAnyRole("USER", "ADMIN")  // Users and Admin can create bookmarks
                        .requestMatchers(HttpMethod.GET, "/api/bookmarks/**").hasAnyRole("USER", "ADMIN")  // Users and Admin can view bookmarks

                        // Catch-all for any remaining endpoints
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/api/events", true)
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(
                User.withUsername("user")
                        .password(passwordEncoder().encode("userpass"))
                        .roles("USER")
                        .build()
        );
        userDetailsService.createUser(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("adminpass"))
                        .roles("ADMIN")
                        .build()
        );
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
