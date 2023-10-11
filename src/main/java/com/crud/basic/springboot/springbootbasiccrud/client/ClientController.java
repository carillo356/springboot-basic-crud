package com.crud.basic.springboot.springbootbasiccrud.client;

import com.crud.basic.springboot.springbootbasiccrud.system.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${api.endpoint.base-url}/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/create")
    public Result createClient(@RequestBody Client client) {
        return null;
    }
}
