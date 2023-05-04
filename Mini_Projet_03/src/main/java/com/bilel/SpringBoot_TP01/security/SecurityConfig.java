package com.bilel.SpringBoot_TP01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.inMemoryAuthentication().withUser("admin")
                .password(passwordEncoder.encode("123")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("nadhem")
                .password(passwordEncoder.encode("123")).roles("AGENT","USER");
        auth.inMemoryAuthentication().withUser("bilel")
                .password(passwordEncoder.encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/newSpeciality").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/manageSpeciality").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/specialities")
                .hasAnyRole("ADMIN","AGENT","USER");

        http.authorizeRequests().antMatchers("/newCourse").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/manageCourse").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/courses")
                .hasAnyRole("ADMIN","AGENT","USER");

        http.authorizeRequests().antMatchers("/createTeacher").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/new").hasAnyRole("ADMIN","AGENT");
        http.authorizeRequests().antMatchers("/teachers")
                .hasAnyRole("ADMIN","AGENT","USER");

        http.authorizeRequests()
                .antMatchers("/deleteTeacher","/editTeacher", "/deleteSpeciality", "/editSpeciality", "/deleteCourse", "/editCourse")
                .hasAnyRole("ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
