package com.vbs.vbs.controller.venue;


import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.client.BookingRequestDto;
import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.services.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("venue")
@RestController
@CrossOrigin
public class VenueController extends BaseController {

    private final VenueService venueService;
    private final VenueBookingRequestService venueBookingRequestService;

    public VenueController(VenueService venueService, VenueBookingRequestService venueBookingRequestService) {
        this.venueService = venueService;
        this.venueBookingRequestService = venueBookingRequestService;
    }


        @PostMapping(path="create")
    public ResponseEntity<ResponseDto> createVenue(@RequestBody VenueDto venueDto) throws IOException {
        venueDto =venueService.create(venueDto);
        if(venueDto !=null){
            

            return new ResponseEntity<>
                    (successResponse("Venue Created.", venueDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="updateDetails")
    public ResponseEntity<ResponseDto> updateVenue(@PathVariable("venueId") Integer id ,@RequestBody VenueDto venueDto){
        venueDto =venueService.update(id,venueDto);
        if(venueDto !=null){


            return new ResponseEntity<>
                    (successResponse("Venue Created.", venueDto), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Creation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{email}")
    public ResponseEntity<ResponseDto>findUserByEmail(@PathVariable String email){
        VenueDto venueDto =venueService.findUserByEmail(email);
        if(venueDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Venue   Fetched.", venueDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("requests")
    public ResponseEntity<ResponseDto>getVenueRequestByClient(Integer id){
        List<BookingRequestDto> bookingRequestDto =venueService.getVenueBookingRequestByClient(id);
        return null;
    }

   /* @PostMapping("response/{bookingStatus}")
    public ResponseEntity<ResponseDto> BookingResponse(BookingRequestDto bookingRequestDto,
                                                       @PathVariable("bookingStatus") Integer bookingStatus){
        venueBookingRequestService.BookingResponse(bookingStatus);
        return new ResponseEntity<>(successResponse("venue booked",HttpStatus.OK)
    }*/
}
