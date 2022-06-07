package com.vbs.vbs.controller.user.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.user.client.ClientDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.service.user.client.ClientService;
import com.vbs.vbs.service.user.venue.VenueBookingRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController extends BaseController {
    private final ClientService clientService;
    private final VenueBookingRequestService venueBookingRequestService;


    public ClientController(ClientService clientService, VenueBookingRequestService venueBookingRequestService) {
        this.clientService = clientService;
        this.venueBookingRequestService = venueBookingRequestService;
    }

    @PostMapping("create")
    ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto){
        clientDto =clientService.create(clientDto);
        if(clientDto !=null){
           return new ResponseEntity<>
                   (successResponse("Client is Created", clientDto),HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Creation Failed. ",null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("BookingRequest")
    public ResponseEntity<ResponseDto>BookingRequest(@RequestBody BookingRequestDto bookingRequestDto){
      bookingRequestDto = venueBookingRequestService.VenueBookingRequest(bookingRequestDto);
       if(bookingRequestDto!=null){
           return new ResponseEntity<>
                   (successResponse("Request Sent",bookingRequestDto),HttpStatus.OK);
       }
       else
           return new ResponseEntity<>
                   (errorResponse("Request Sent",null),HttpStatus.BAD_REQUEST);


    }

}
