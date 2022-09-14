package com.vbs.vbs.controller;


import com.vbs.vbs.dto.*;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.EventsCostAndRate;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("venue-")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*" )
public class VenueController extends BaseController {

    private final VenueService venueService;
    private final BookingServices bookingServices;

    public VenueController(VenueService venueService, BookingServices bookingServices) {
        this.venueService = venueService;
        this.bookingServices = bookingServices;
    }

    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto>findVenueByEmail(@PathVariable String email){
        VenueDto venue =venueService.findVenueByEmail(email);
        if(venue != null ){
            return new ResponseEntity<>
                    (successResponse("Venue   Fetched.", venue), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin(origins = "*",methods = RequestMethod.PUT,maxAge = 86400,allowedHeaders = "*")
    @PutMapping(path="update/{email}")
    public ResponseEntity<ResponseDto> updateVenue(@RequestBody VenueDto venueDto,@PathVariable("email") String email){
        Integer venue =venueService.update(venueDto,email);
        if(venue!=null){
            return new ResponseEntity<>
                    (successResponse("data Updated.", venue), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Update failed.",null),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("requests/{email}")
    public ResponseEntity<ResponseDto>getBookingRequests(@PathVariable("email") String email){
        List<Booking> booking =venueService.getRequestedBooking(email);
        if(booking !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.", booking), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Requests Fetching Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "*",methods = RequestMethod.PUT,maxAge = 86400,allowedHeaders = "*")
   @PutMapping("response/{id}")
    public ResponseEntity<ResponseDto> BookingResponse(@RequestBody StatusChangeReq statusChangeReq,
                                                       @PathVariable("id")Integer id){
       Integer bookingResponse = bookingServices.VenueBookingResponse(statusChangeReq.getStatus(),id);
       if(bookingResponse != null){
        return new ResponseEntity<>
                (successResponse("response sent",bookingResponse),HttpStatus.OK);
    }
    else{
        return  new ResponseEntity<>(
                errorResponse("sending response unsuccessful",null),HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("booking/{email}")
    public ResponseEntity<ResponseDto>getBooking(@PathVariable("email") String email){
        List<Booking> booking =venueService.getBookingList(email);
        if(booking !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.", booking), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Requests Fetching Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="updateEventDetails/{email}")
    public ResponseEntity<ResponseDto> createClient(@RequestBody EventDto eventDto ,@PathVariable("email") String email) {
        EventsCostAndRate eventsCostAndRate =venueService.uploadEventDetails(eventDto,email);
        if(eventDto !=null){
            return new ResponseEntity<>
                    (successResponse("Event Updated on "+email, eventsCostAndRate), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Event Updation Failed",null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("bookingRequest/{email}")
    public ResponseEntity<ResponseDto> getNumberOfBooking(@PathVariable("email")  String email){
        Integer bookingRequest =venueService.getNumberOfBooking(email);
        return new ResponseEntity<>
                (successResponse("Number of Booking Request", bookingRequest),HttpStatus.OK);
    }
}
