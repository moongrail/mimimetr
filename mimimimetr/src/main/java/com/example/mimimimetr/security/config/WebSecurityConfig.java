package com.example.mimimimetr.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SIGNUP = "/signup";
    public static final String PLAY = "/play";
    public static final String LOGIN = "/login";
    public static final String LOGIN_ERROR = "/login?error";
    public static final String HOME = "/";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(SIGNUP).permitAll()
                .antMatchers(HOME).permitAll()
                .antMatchers(PLAY).authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN)
                .defaultSuccessUrl(PLAY)
                .failureUrl(LOGIN_ERROR)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll();
    }
}
