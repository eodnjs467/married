package com.daewon.married.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/security/all").permitAll()
        .antMatchers("/security/admin").hasRole("ADMIN");

        http.formLogin();// LoginPage 따로 있을경우 LoginUrl 사용 -> 블로그 정리
        http.csrf().disable(); // csrf 계속 생성 개발자 도구에서 다 보여서 생성안하게 disable
        http.logout();  // logout 페이지 있으면 logoutUrl ->
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("29a49f80-c1c1-44a9-aa5a-22819205de68")
//                .roles("USER");
//    }

}
