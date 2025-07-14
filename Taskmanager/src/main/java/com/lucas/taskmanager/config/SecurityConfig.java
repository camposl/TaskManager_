package com.lucas.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                // --- PASSO 1: Desabilitar o CSRF ---
                // Esta linha desabilita a proteção CSRF, resolvendo o erro 403 em POSTs.
                .csrf(csrf -> csrf.disable())

                // --- PASSO 2: Configurar a API para ser Stateless ---
                // Diz ao Spring para não criar sessões HTTP, que é a norma para APIs REST.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Permite que o console H2 seja exibido dentro de um frame
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

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