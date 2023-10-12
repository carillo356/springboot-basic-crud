package com.crud.basic.springboot.springbootbasiccrud.client;

import com.crud.basic.springboot.springbootbasiccrud.system.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ClientControllerTest {
    @Value("${api.endpoint.base-url}/client")
    String baseUrl;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ClientService clientService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createClientSuccess() throws Exception {
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("client1");
        client1.setEmail("client1@email.com");
        client1.setDateCreated(new Date(1));

        String json = objectMapper.writeValueAsString(client1);

        //Given
        given(this.clientService.save(Mockito.any(Client.class))).willReturn(client1);

        //When and Then
        this.mockMvc.perform(post(this.baseUrl + "/create/client")
                .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.flag").value(true))
                    .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                    .andExpect(jsonPath("$.message").value("Create Client Success"))
                    .andExpect(jsonPath("$.data.email").value("client1@email.com"));

    }
}