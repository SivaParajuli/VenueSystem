package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.services.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="register")
public class RegisterController extends BaseController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(path="client")
    public ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto) throws IOException {
        clientDto =registerService.clientRegister(clientDto);
        if(clientDto !=null){
            return new ResponseEntity<>
                    (successResponse("Client Created.", clientDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="venue")
    public ResponseEntity<ResponseDto> createVenue(@RequestBody VenueDto venueDto) throws IOException {
        venueDto =registerService.venueRegister(venueDto);
        if(venueDto !=null){
            return new ResponseEntity<>
                    (successResponse("Venue Created.", venueDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }
}
