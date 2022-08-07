package com.vbs.vbs.controller;


import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequestMapping("venue-")
@RestController
@CrossOrigin(origins = "*")
public class VenueController extends BaseController {

    private final VenueService venueService;

    private final BookingServices bookingServices;

    public VenueController(VenueService venueService, BookingServices bookingServices) {
        this.venueService = venueService;
        this.bookingServices = bookingServices;
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
        Optional<Venue> venue =venueService.findVenueByEmail(email);
        if(venue.isPresent()) {
            return new ResponseEntity<>
                    (successResponse("Venue   Fetched.", venue), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("requests")
    public ResponseEntity<ResponseDto>getBookingRequests(){
        CurrentUser user = new CurrentUser();
        List<Booking> booking =venueService.getRequestedBooking(user.CurrentUserName((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        if(booking !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.", booking), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

   @PutMapping("response/{bookingStatus}/{id}")
    public ResponseEntity<ResponseDto> BookingResponse(@PathVariable("bookingStatus") Integer bookingStatus,
                                                       @PathVariable("id")Integer id){
       bookingServices.VenueBookingResponse(bookingStatus,id);
        return new ResponseEntity<>
                (successResponse("venue booked",null),HttpStatus.OK);
    }
}
