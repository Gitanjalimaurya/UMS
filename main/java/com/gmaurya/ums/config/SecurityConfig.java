package com.gmaurya.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gmaurya.ums.service.interfaces.LoginService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final LoginService loginService;

    // Inject LoginService lazily to break the circular dependency
    public SecurityConfig(@Lazy LoginService loginService) {
        this.loginService = loginService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            var user = loginService.findByEmail(email);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return User
                    .withUsername(user.get().getEmail())
                    .password(user.get().getPassword())
                    .authorities("USER") // Adjust authorities as needed
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/images/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/", "/home", "/login", "/registration", "/forgot-password", "/reset-password").permitAll()
                    .requestMatchers("/student-dashboard", "/faculty-dashboard", "/admin-dashboard").permitAll()
                    .requestMatchers("/admission-personal-info", "/admission-contact-info", "/admission-education-info").permitAll()
                    .requestMatchers("/student-list-admission", "/student-details-admission").permitAll()
                    .requestMatchers("/document-view", "document-upload").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")  // Use email as username
                    .successHandler(customAuthenticationSuccessHandler())
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .accessDeniedPage("/403")
            );

        return http.build();
    }


    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            var email = authentication.getName();
            System.out.println("Authenticated email: " + email); // Debug line
            var profile = loginService.getProfileByEmail(email);
            System.out.println("User profile: " + profile); // Debug line

            String redirectUrl;
            switch (profile) {
                case "student":
                    redirectUrl = "/student-dashboard";
                    break;
                case "faculty":
                    redirectUrl = "/faculty-dashboard";
                    break;
                case "admin":
                    redirectUrl = "/admin-dashboard";
                    break;
                default:
                    redirectUrl = "/login?error=true"; // Default error URL
            }
            response.sendRedirect(redirectUrl);
        };
    }

}
