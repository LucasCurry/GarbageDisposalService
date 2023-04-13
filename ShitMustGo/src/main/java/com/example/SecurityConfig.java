package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    //Configure Security
    @Bean
    @Order(1)
    public SecurityFilterChain auth0FilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests().requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll();
        http.userDetailsService(userDetailsService);

        http
                .authorizeHttpRequests()
                .requestMatchers("/","/login","/register", "/css/**", "/images/**", "/task/**", "/contact/**", "/customerservice/**", "/faq/**","/hello/**","/webjars/**","/gs-guide-websocket/**", "/home/**", "/home2/**", "/index/**", "/stopFilter/**").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .failureUrl("/login-error");
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

