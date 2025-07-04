package com.lucas.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita a proteção CSRF para o console H2
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))

                // Permite que o console H2 seja exibido dentro de um frame
                .headers(headers ->
                        headers.frameOptions(frameOptions -> frameOptions.disable())) // <-- Sintaxe moderna para frameOptions

                // Define as regras de autorização
                .authorizeHttpRequests(auth -> auth
                        // Permite todas as requisições para o console H2
                        .requestMatchers("/h2-console/**").permitAll()

                        // (Temporário) Permite TODAS as outras requisições
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}