package com.vbs.vbs.controller.user.client;

import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.user.venue.VenueDto;
import com.vbs.vbs.service.user.client.ClientService;
import com.vbs.vbs.service.user.venue.VenueBookingRequestService;
import com.vbs.vbs.service.user.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController extends BaseController {
    private final VenueBookingRequestService venueBookingRequestService;
    private final VenueService venueService;


    public ClientController( VenueBookingRequestService venueBookingRequestService, VenueService venueService) {
        this.venueBookingRequestService = venueBookingRequestService;
        this.venueService = venueService;
    }



    @GetMapping
    public ResponseEntity findAll(){
        List<VenueDto> venueDtoList =venueService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", venueDtoList),HttpStatus.OK);
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


//    @GetMapping("by-id/{id}")
//    public ResponseEntity<ResponseDto>findById(@PathVariable Integer id){
//        VenueDto venueDto =venueService.findById(id);
//        if(venueDto !=null) {
//            return new ResponseEntity<>
//                    (successResponse("Venue   Fetched.", venueDto), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>
//                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
//        }
//    }

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
