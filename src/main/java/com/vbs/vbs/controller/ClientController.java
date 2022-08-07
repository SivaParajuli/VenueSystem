package com.vbs.vbs.controller.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.SignInRequest;
import com.vbs.vbs.models.User;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.security.service.AuthenticationService;
import com.vbs.vbs.services.client.ClientService;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="client")

public class ClientController extends BaseController {
    private final VenueBookingRequestService venueBookingRequestService;
    private final ClientService clientService;
    private final AuthenticationService authenticationService;
    public ClientController(VenueBookingRequestService venueBookingRequestService, ClientService clientService, AuthenticationService authenticationService) {
        this.venueBookingRequestService = venueBookingRequestService;
        this.clientService = clientService;
        this.authenticationService = authenticationService;
    }



    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest){
       User signInRequest1 = authenticationService.signInAndReturnJWT(signInRequest);
       if(signInRequest1 != null){
           return new ResponseEntity<>
                   (successResponse("Login Sucessfull",signInRequest1),HttpStatus.OK);
       }
       return new ResponseEntity<>
               (errorResponse("login failed",signInRequest),HttpStatus.BAD_REQUEST);
    }



    @PostMapping(path="register")
    public ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto) throws IOException {
        clientDto =clientService.create(clientDto);
        if(clientDto !=null){
            return new ResponseEntity<>
                    (successResponse("Client Created.", clientDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

//TODO
    @GetMapping
    public ResponseEntity findAll(){
        List<ClientDto> clientDtoList =clientService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", clientDtoList),HttpStatus.OK);
    }

    @PostMapping(path="book-venue/{email}")
    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody BookingRequest bookingRequest, @PathVariable String email){
        CurrentUser user = new CurrentUser();
         String clientMail = user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        bookingRequest = venueBookingRequestService.VenueBookingRequest(bookingRequest,email,clientMail);
        if(bookingRequest!=null){
            return new ResponseEntity<>
                    (successResponse("Request Sent",bookingRequest), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("Request Sent",null),HttpStatus.BAD_REQUEST);


    }



    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto>findClientByEmail(@PathVariable String email){
        ClientDto clientDto=clientService.findClientByEmail(email);
        if(clientDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Client   Fetched.", clientDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

}
