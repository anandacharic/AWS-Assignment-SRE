package com.example.healthcheck.integrationTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class integrationTest {
    @Test
    public void getRequestTest(@Autowired MockMvc mockMvc) throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/healthz"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
