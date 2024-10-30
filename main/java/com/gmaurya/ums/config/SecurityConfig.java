package com.gmaurya.ums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.gmaurya.ums.service.interfaces.LoginService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Properties;

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

            // No role check here; just return the user with their email and password
            return User
                    .withUsername(user.get().getEmail())
                    .password(user.get().getPassword())
                    .authorities("USER") // You can assign a default authority
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
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/student-list-admission-admin/accept/**", "/student-list-admission-admin/reject/**") // Adjust as necessary for endpoints needing CSRF ignored
//                )
//                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/", "/home", "/login", "/registration", "/forgot-password", "/reset-password").permitAll()
                        .requestMatchers("/student-dashboard", "/faculty-dashboard", "/admin-dashboard", "/prospective-student-dashboard").permitAll()
                        .requestMatchers("/admission-personal-info", "/admission-contact-info", "/admission-education-info").permitAll()
                        .requestMatchers("/student-list-admission", "/student-details-admission").permitAll()
                        .requestMatchers("/document-view", "/document-view-admin", "/document-upload").permitAll()
                        .requestMatchers("/timetable", "/view-timetable").permitAll()
                        .requestMatchers("/add-faculty", "/view-faculty", "/assign-subject", "/view-faculty-subjects", "/record-management").permitAll()
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
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
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
            var profile = loginService.getProfileByEmail(email);

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
                case "prospective_student":
                    redirectUrl = "/prospective-student-dashboard";
                    break;
                default:
                    redirectUrl = "/login?error=true"; // Default error URL
            }
            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("mauryagitanjali666@gmail.com");
        mailSender.setPassword("dluubzpbgxrnsewn");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
