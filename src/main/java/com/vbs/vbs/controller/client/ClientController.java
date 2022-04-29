package com.vbs.vbs.controller.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.client.ClientRegisterDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.service.user.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController extends BaseController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("create")
    ResponseEntity<ResponseDto> createClient(@RequestBody ClientRegisterDto clientRegisterDto){
        clientRegisterDto =clientService.create(clientRegisterDto);
        if(clientRegisterDto !=null){
           return new ResponseEntity<>
                   (successResponse("Client is Created", clientRegisterDto),HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed. ",null),HttpStatus.BAD_REQUEST);
        }
    }

}
