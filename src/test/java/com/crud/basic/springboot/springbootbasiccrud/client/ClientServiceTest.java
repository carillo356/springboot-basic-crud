package com.crud.basic.springboot.springbootbasiccrud.client;

import com.crud.basic.springboot.springbootbasiccrud.system.exception.UserAlreadyExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        assertThat(returnedClient.getEmail()).isEqualTo(client1.getEmail());
    }
    @Test
    void testSaveAlreadyExist() {
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("client1");
        client1.setEmail("client1@email.com");
        client1.setDateCreated(new Date(1));

        //Given
        given(this.clientRepository.findByEmail(Mockito.any(String.class))).willThrow(new UserAlreadyExistException("client1@email.com"));

        //When
        Throwable thrown = catchThrowable(() -> {
            clientService.save(client1);
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(UserAlreadyExistException.class)
                .hasMessage("User already exist with email client1@email.com");
        verify(clientRepository, times(1)).findByEmail("client1@email.com");
    }
}