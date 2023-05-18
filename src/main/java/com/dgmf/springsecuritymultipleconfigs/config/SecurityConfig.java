package com.dgmf.springsecuritymultipleconfigs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // "/api/**" ==> Basic Authentication
    @Bean
    @Order(1)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Allows configuring the HttpSecurity to only be invoked when matching
                // the provided pattern ==> directly with the pattern
                .securityMatcher("/api/**")
                // Securization of all the requests on the provided pattern
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Disable Session Management
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Basic protection of the Endpoint
                .httpBasic(withDefaults())
                .build();
    }

    //  H2 console ==> Spring Security disabled
    @Bean
    @Order(2)
    // @Order(1) // Spring Security will run this filter first
    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Allows configuring the HttpSecurity to only be invoked when matching
                // the provided pattern ==> using "antMatcher"
                .securityMatcher(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                .authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                .headers(headers -> headers.frameOptions().disable())
                .build();
    }
}
