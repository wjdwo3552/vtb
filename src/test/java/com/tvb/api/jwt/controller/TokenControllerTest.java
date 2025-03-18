package com.tvb.api.jwt.controller;

import com.tvb.api.jwt.security.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private JWTUtil jwtUtil;

    @InjectMocks
    private TokenController tokenController;

    @Test
    void shouldReturnTokens_whenValidRequest() throws Exception {

    }

}
