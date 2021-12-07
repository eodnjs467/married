package com.daewon.married.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoder(){

        String password = "skdisk1010";

        String enPw = passwordEncoder.encode(password);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println(matchResult);

    }

}