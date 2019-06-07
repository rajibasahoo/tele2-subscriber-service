package com.tele2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * 
 * @author rajiba sahoo
 * This class is the enabling security,to authorize we have to comment permitAll() and uncomment out authenticated().
 * 
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()              
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
        		.withUser("rajiba").password("test").roles("USER");               
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .anyRequest()
                //.authenticated()
                .permitAll()
                .and()
                .httpBasic();
        httpSecurity.csrf().disable();

    }

   
}
