package com.bicasteam.movigestion.api.iam.infrastructure.config;

import com.bicasteam.movigestion.api.iam.infrastructure.authorization.UnauthorizedRequestHandlerEntryPoint;
import com.bicasteam.movigestion.api.iam.infrastructure.authorization.pipeline.BearerAuthorizationRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration {

    private final BearerAuthorizationRequestFilter filter;
    private final UnauthorizedRequestHandlerEntryPoint entry;

    public WebSecurityConfiguration(BearerAuthorizationRequestFilter filter,
                                    UnauthorizedRequestHandlerEntryPoint entry) {
        this.filter = filter;
        this.entry = entry;
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/api/v1/authentication/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(entry))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}
