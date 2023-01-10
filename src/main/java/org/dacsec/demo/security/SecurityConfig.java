package org.dacsec.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@link SecurityConfig} is a configuration class for Spring Security that
 * will provide an instance of {@link SecurityFilterChain} and enable the
 * user to log in with 0Auth2.
 */
@EnableWebSecurity
public class SecurityConfig {
    
    private final LogoutHandler logoutHandler;
    /**
     * {@link SecurityConfig} constructor for the {@link LogoutHandler}.
     * @param logoutHandler the logout handler
     */
    public SecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }
    
    /**
     * This method will provide an instance of {@link SecurityFilterChain},
     * {@link LogoutHandler}.
     * @param http {@link HttpSecurity} object
     * @return {@link SecurityFilterChain} object
     * @throws Exception if any error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.oauth2Login()
                 .and().logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                 .addLogoutHandler(logoutHandler)
                 .and().build();
    }
}

/*
 You can further configure the HttpSecurity instance to require authentication
 on all or certain paths. For example,
 to require authentication on all paths except the home page:

http.authorizeRequests()
      .mvcMatchers("/").permitAll()
      .anyRequest().authenticated()
      .and().oauth2Login();
*/


