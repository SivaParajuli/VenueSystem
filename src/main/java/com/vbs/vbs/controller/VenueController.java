package com.vbs.vbs.controller.venue;


import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.services.venue.VenueService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequestMapping("venue")
@RestController
@CrossOrigin(origins = "*")
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
        VenueDto venueDto =venueService.findVenueByEmail(email);
        if(venueDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Venue   Fetched.", venueDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity findAll(){
        List<VenueDto> venueDtoList =venueService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", venueDtoList),HttpStatus.OK);
    }




    @GetMapping("requests")
    public ResponseEntity<ResponseDto>getBookingRequests(){
        CurrentUser user = new CurrentUser();
        List<BookingRequest> bookingRequest =venueService.getRequestedBooking(user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        if(bookingRequest !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.", bookingRequest), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

   @PutMapping("response/{bookingStatus}/{id}")
    public ResponseEntity<ResponseDto> BookingResponse(@PathVariable("bookingStatus") Integer bookingStatus,
                                                       @PathVariable("id")Integer id){
       venueBookingRequestService.VenueBookingResponse(bookingStatus,id);
        return new ResponseEntity<>
                (successResponse("venue booked",null),HttpStatus.OK);
    }
}
