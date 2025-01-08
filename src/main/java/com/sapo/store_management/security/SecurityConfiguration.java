package com.sapo.store_management.security;

import com.sapo.store_management.filter.JwtFilter;
import com.sapo.store_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    public static final String front_end_host = "http://localhost:5173";

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh-token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, Endpoints.ADMIN_GET_ENDPOINT).hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, Endpoints.ADMIN_POST_ENDPOINT).hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, Endpoints.ADMIN_PUT_ENDPOINT).hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, Endpoints.ADMIN_DELETE_ENDPOINT)
                        .hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, Endpoints.STAFF_GET_ENDPOINT).hasAnyAuthority("ROLE_STAFF")
                        .requestMatchers(HttpMethod.POST, Endpoints.STAFF_POST_ENDPOINT).hasAnyAuthority("ROLE_STAFF")
                        .requestMatchers(HttpMethod.PUT, Endpoints.STAFF_PUT_ENDPOINT).hasAnyAuthority("ROLE_STAFF")
                        .requestMatchers(HttpMethod.DELETE, Endpoints.STAFF_DELETE_ENDPOINT)
                        .hasAnyAuthority("ROLE_STAFF")
                        .requestMatchers(HttpMethod.GET, Endpoints.CSR_GET_ENDPOINT).hasAnyAuthority("ROLE_CSR")
                        .requestMatchers(HttpMethod.POST, Endpoints.CSR_POST_ENDPOINT).hasAnyAuthority("ROLE_CSR")
                        .requestMatchers(HttpMethod.PUT, Endpoints.CSR_PUT_ENDPOINT).hasAnyAuthority("ROLE_CSR")
                        .requestMatchers(HttpMethod.DELETE, Endpoints.CSR_DELETE_ENDPOINT).hasAnyAuthority("ROLE_CSR"));
        http.cors(Customizer.withDefaults());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(front_end_host));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
