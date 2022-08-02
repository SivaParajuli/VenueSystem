package com.vbs.vbs.controller.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.client.BookingRequestDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.client.ClientDto;
import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.services.client.ClientService;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.services.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="client")
public class ClientController extends BaseController {
    private final VenueBookingRequestService venueBookingRequestService;
    private final VenueService venueService;
    private final ClientService clientService;


    public ClientController(VenueBookingRequestService venueBookingRequestService, VenueService venueService, ClientService clientService) {
        this.venueBookingRequestService = venueBookingRequestService;
        this.venueService = venueService;
        this.clientService = clientService;
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
//TODO
//    @PostMapping(path="book-venue/{email}")
//    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody BookingRequestDto bookingRequestDto,@PathVariable String email){
//        bookingRequestDto = venueBookingRequestService.VenueBookingRequest(bookingRequestDto,email);
//        if(bookingRequestDto!=null){
//            return new ResponseEntity<>
//                    (successResponse("Request Sent",bookingRequestDto), HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>
//                    (errorResponse("Request Sent",null),HttpStatus.BAD_REQUEST);
//
//
//    }


//TODO
//    @GetMapping
//    public ResponseEntity findAll(){
//        List<VenueDto> venueDtoList =venueService.findAll();
//        return new ResponseEntity<>
//                (successResponse("Venue List Fetched", venueDtoList),HttpStatus.OK);
//    }




    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto>findClientByEmail(@PathVariable String email){
        ClientDto clientDto=clientService.findUserByEmail(email);
        if(clientDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Client   Fetched.", clientDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Client Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("create")
//    ResponseEntity<ResponseDto> createClient(@RequestBody ClientDto clientDto){
//        clientDto =clientService.create(clientDto);
//        if(clientDto !=null){
//           return new ResponseEntity<>
//                   (successResponse("Client is Created", clientDto),HttpStatus.CREATED);
//        }
//        else{
//            return new ResponseEntity<>
//                    (errorResponse("Client Creation Failed. ",null),HttpStatus.BAD_REQUEST);
//        }
//    }

}
