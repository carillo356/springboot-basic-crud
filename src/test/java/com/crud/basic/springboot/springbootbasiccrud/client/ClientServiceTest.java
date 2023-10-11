package com.crud.basic.springboot.springbootbasiccrud.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    ClientService clientService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveSuccess() {
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("client1");
        client1.setEmail("client1@email.com");
        client1.setDateCreated(new Date(1));

        //Given
        given(this.clientRepository.save(client1)).willReturn(client1);

        //When
        Client returnedClient = clientService.save(client1);

        //Then
        assertThat(returnedClient.getId()).isEqualTo(client1.getId());
    }
}