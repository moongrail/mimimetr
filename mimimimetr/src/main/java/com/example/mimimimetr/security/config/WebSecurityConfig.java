package com.example.mimimimetr.security.config;

import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.security.filter.TokenAuthenticationFilter;
import com.example.mimimimetr.security.filter.TokenAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //TODO: Ldap in future.
    public static final String SIGNUP = "/signup";
    public static final String GAME = "/game/*";
    public static final String LOGIN = "/login";
    public static final String LOGIN_ERROR = "/login?error";
    public static final String HOME = "/";
    public static final String IMAGES = "/images/**";
    public static final String LOGOUT = "/logout";
    public static final String LOGIN_FILTER_PROCESSES_URL = "/api/login/**";

    @Autowired
    private UserDetailsService accountCatDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CatRepository catRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountCatDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter filter =
                new TokenAuthenticationFilter(authenticationManagerBean(), mapper, catRepository);
        filter.setFilterProcessesUrl(LOGIN_FILTER_PROCESSES_URL);
        http
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 24)
                .and()
                .authorizeRequests()
                .antMatchers(SIGNUP).permitAll()
                .antMatchers(HOME).permitAll()
                .antMatchers(IMAGES).permitAll()
                .antMatchers(GAME).authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN)
                .defaultSuccessUrl(HOME)
                .failureUrl(LOGIN_ERROR)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.addFilter(filter);
        http.addFilterBefore(new TokenAuthorizationFilter(catRepository, mapper),
                UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
