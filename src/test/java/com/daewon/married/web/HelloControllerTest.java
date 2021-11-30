package com.daewon.married.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;


@SpringBootTest
class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    // 모의 http request, response 만들어 테스트 진행
    @Autowired
    //private MockMvc mvc;

    // 단지 메소드 호출 테스트
    @Test
    public void hello() {
        String hello = "hello!";

        Assertions.assertThat(helloController.hello()).isEqualTo(hello);
    }

    // 실제 http 테스트
    @Test
    public void mockMvcHello() throws Exception{
        String hello;
    }
}