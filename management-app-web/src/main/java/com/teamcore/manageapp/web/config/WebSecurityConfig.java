package com.teamcore.manageapp.web.config;

import com.teamcore.manageapp.service.config.ServiceSecurityConfig;
import com.teamcore.manageapp.web.security.UrlAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends ServiceSecurityConfig {

    private final static String CSRF_TOKEN_NAME = "C00L_CSRF_TOKEN";

    private final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    private CsrfTokenRepository  csrfTokenRepository;

    @Bean
    public AuthenticationSuccessHandler authSuccessHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        //setup cookie csrf token security
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setHeaderName(CSRF_TOKEN_NAME);
        return csrfTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                .csrf().csrfTokenRepository(csrfTokenRepository);
        http.csrf().disable();

        //setup general access policy
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**",
                        "/403", "/").permitAll()
                .anyRequest().authenticated();

        //form login
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login").loginPage("/login")
                .successHandler(authSuccessHandler)
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("authFail", "true");

                    logger.warn("Auth failed!: "+e.getMessage());

                    httpServletResponse.sendRedirect("/login");
                })
                .permitAll();

        //remember-me functionality
        http.rememberMe()
                .tokenValiditySeconds(3600)
                .key("maToken");

        //logout handler
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        //access denied page
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
