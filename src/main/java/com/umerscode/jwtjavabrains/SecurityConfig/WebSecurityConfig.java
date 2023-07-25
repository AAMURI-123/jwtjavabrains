package com.umerscode.jwtjavabrains.SecurityConfig;

import com.umerscode.jwtjavabrains.Filters.JwtAuthFilters;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilters jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/authenticate").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
                    .and()
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .httpBasic();

        return http.build();
    }
}
