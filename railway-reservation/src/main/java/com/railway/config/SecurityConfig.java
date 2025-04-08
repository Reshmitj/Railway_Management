package com.railway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/", "/static/index.html", "/error").permitAll()
            	    .requestMatchers("/static/**").permitAll()
            	    .requestMatchers("/passenger/**", "/admin/**", "/trains/**", "/reservations/**","/routes/**").permitAll()
            	    .requestMatchers("/h2-console/**").permitAll()
            	    .anyRequest().authenticated()
            	)
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) 
            .logout(logout -> logout
                .logoutUrl("/admin/logout")  
                .logoutSuccessUrl("/index.html")
                .permitAll()
            );

        return http.build();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:8080")
                        .allowCredentials(true);
            }
        };
    }
}
