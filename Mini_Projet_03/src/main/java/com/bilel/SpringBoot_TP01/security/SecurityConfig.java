package com.bilel.SpringBoot_TP01.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username , password , enabled from user where username =?")
//                .authoritiesByUsernameQuery(
//                        "SELECT u.username, r.role " +
//                                "FROM user_role ur, user u , role r " +
//                                "WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = ?")
//                .passwordEncoder(passwordEncoder)
//                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/new",
                "/createTeacher",
                "/newCourse",
                "/manageCourse",
                "/newSpeciality",
                "/manageSpeciality"
            ).hasAnyRole("ADMIN","AGENT");

        http.authorizeRequests().antMatchers(
                "/specialities",
                "/courses",
                "/teachers"
            ).hasAnyRole("ADMIN","AGENT","USER");


        http.authorizeRequests()
                .antMatchers(
                        "/deleteTeacher",
                        "/editTeacher",
                        "/deleteSpeciality",
                        "/editSpeciality",
                        "/deleteCourse",
                        "/editCourse"
                ).hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/webjars/**").permitAll();


        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
