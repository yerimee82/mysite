package com.poscodx.mysite.config.app;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                .requestMatchers(new AntPathRequestMatcher("/assets/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutUrl("/user/logout")
                .and()

                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/auth")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?result=fail")
                .failureHandler((request, response, exception) -> {
                    request.setAttribute("email", request.getParameter("email"));
                    request
                            .getRequestDispatcher("/user/login")
                            .forward(request, response);
                })
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(registry -> {
                    registry
                            /* ACL */
                            .requestMatchers(new RegexRequestMatcher("^/admin/?.*$", null))
                            .hasRole("ADMIN")

                            .requestMatchers(new RegexRequestMatcher("^/board/?(write|reply|delete|modify)?/.*$", null))
                            .hasAnyRole("ADMIN", "USER")

                            .requestMatchers(new RegexRequestMatcher("^/user/update$", null))
                            .hasAnyRole("ADMIN", "USER")

                            .anyRequest()
                            .permitAll();
                });

        return http.build();
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        /* 4$ ~ 31 */ //   속도에 영향을 주는 요소
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userRepository);
    }

}