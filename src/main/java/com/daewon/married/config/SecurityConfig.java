package com.daewon.married.config;

import com.daewon.married.filter.ApiCheckFilter;
import com.daewon.married.filter.ApiLoginFilter;
import com.daewon.married.handler.ApiLoginFailHandler;
import com.daewon.married.handler.MarriedLoginSuccessHandler;
import com.daewon.married.service.MemberDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberDetailsService memberDetailsService;


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/security/all").permitAll()
//        .antMatchers("/security/admin").hasRole("ADMIN")
//        .antMatchers("/security/member").hasRole("MEMBER");

        http.formLogin();// LoginPage 따로 있을경우 LoginUrl 사용 -> 블로그 정리
        http.csrf().disable(); // csrf 계속 생성 개발자 도구에서 다 보여서 생성안하게 disable
        http.oauth2Login().successHandler(successHandler()); //소셜로그인(1)
        http.logout();  // logout 페이지 있으면 logoutUrl ->
        http.rememberMe().tokenValiditySeconds(60 * 60 * 7).userDetailsService(memberDetailsService);

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public MarriedLoginSuccessHandler successHandler() {
        return new MarriedLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception{

        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager());

        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        log.info("/api/login 호출됨");
        return apiLoginFilter;
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/notes/**/*");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("29a49f80-c1c1-44a9-aa5a-22819205de68")
//                .roles("USER");
//    }

}
