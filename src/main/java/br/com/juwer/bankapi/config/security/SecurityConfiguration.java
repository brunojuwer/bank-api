package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.filter.ExceptionHandlerFilter;
import br.com.juwer.bankapi.config.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter filter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfigurationSource corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfig))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/accounts")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);
        return http.build();
    }
}
