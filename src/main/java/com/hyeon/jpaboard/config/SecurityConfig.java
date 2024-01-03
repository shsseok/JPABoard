package com.hyeon.jpaboard.config;


import com.hyeon.jpaboard.config.handle.LoginAuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/", "/auth/login-form", "/member/register-form", "/member/register").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated());
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.loginPage("/auth/login-form")
                        .usernameParameter("email")
                        .failureHandler(loginAuthFailureHandler())
                        .loginProcessingUrl("/auth/login").permitAll().defaultSuccessUrl("/"));
        http
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public LoginAuthFailureHandler loginAuthFailureHandler()
    {
        return  new LoginAuthFailureHandler();
    }

}
