package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.services.ClientService;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="client-")

public class ClientController extends BaseController {
    private final BookingServices bookingServices;
    private final VenueService venueService;
    private final ClientService clientService;

    public ClientController(BookingServices bookingServices, VenueService venueService, ClientService clientService) {
        this.bookingServices = bookingServices;
        this.venueService = venueService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<VenueDto> venueDtoList =venueService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", venueDtoList),HttpStatus.OK);
    }

    @GetMapping(path="currentUser")
    public ResponseEntity<ResponseDto> findUser(){
        CurrentUser user = new CurrentUser();
        String clientMail = user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       ClientDto currentUser = clientService.findClientByEmail(clientMail);
        if(currentUser !=null){
            return new ResponseEntity<>
                    (successResponse("CurrentUser", currentUser), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("sorry",null),HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path="book-venue/{email}")
    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody Booking booking, @PathVariable String email){
        CurrentUser user = new CurrentUser();
         String clientMail = user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        booking = bookingServices.VenueBookingRequest(booking,email,clientMail);
        if(booking !=null){
            return new ResponseEntity<>
                    (successResponse("Request Sent", booking), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("Request Sent",null),HttpStatus.BAD_REQUEST);
    }
}
