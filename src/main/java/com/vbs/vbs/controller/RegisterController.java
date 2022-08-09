package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.services.RegisterService;
import com.vbs.vbs.utils.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="register")
public class RegisterController extends BaseController {

    private final RegisterService registerService;
    private final EmailSenderService emailSenderService;

    public RegisterController(RegisterService registerService, EmailSenderService emailSenderService) {
        this.registerService = registerService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping(path="client")
    public ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto) {
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
            emailSenderService.sendEmail("svenuebooking.admin001@gmail.com",
                    "Registration Request",
                    venueDto.getVenueName() +" wants to be registered with requirements in vbs.");

            return new ResponseEntity<>
                    (successResponse("Registration Request Sent Successfully.", venueDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }
}
