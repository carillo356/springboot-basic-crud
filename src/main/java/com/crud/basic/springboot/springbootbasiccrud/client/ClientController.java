package com.crud.basic.springboot.springbootbasiccrud.client;

import com.crud.basic.springboot.springbootbasiccrud.system.Result;
import com.crud.basic.springboot.springbootbasiccrud.system.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${api.endpoint.base-url}/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/create/client")
    public Result createClient(@RequestBody Client client) {
        Client newClient = clientService.save(client);
        return new Result(true, StatusCode.SUCCESS, "Create Client Success", newClient);
    }
}
