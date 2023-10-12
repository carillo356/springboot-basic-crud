package com.crud.basic.springboot.springbootbasiccrud.client;

import com.crud.basic.springboot.springbootbasiccrud.system.exception.UserAlreadyExistException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return (Client) clientRepository.findByEmail(client.getEmail())
                .map(existingUser -> {
                    throw new UserAlreadyExistException(client.getEmail());
                })
                .orElseGet(() -> {
                    return clientRepository.save(client);
                });
    }


}
