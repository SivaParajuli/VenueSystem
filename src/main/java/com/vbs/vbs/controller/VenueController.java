package com.vbs.vbs.controller;


import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.services.ClientService;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.EmailSenderService;
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
    private final ClientService clientService;
    private final EmailSenderService emailSenderService;

    public VenueController(VenueService venueService, BookingServices bookingServices, ClientService clientService, EmailSenderService emailSenderService) {
        this.venueService = venueService;
        this.bookingServices = bookingServices;
        this.clientService = clientService;
        this.emailSenderService = emailSenderService;
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
   @PutMapping("response/{bookingStatus}/{id}")
    public ResponseEntity<ResponseDto> BookingResponse(@PathVariable("bookingStatus") Integer bookingStatus,
                                                       @PathVariable("id")Integer id){
       Integer bookingResponse = bookingServices.VenueBookingResponse(bookingStatus,id);
       if(bookingResponse != null){
           Booking booking = bookingServices.findById(id);
           emailSenderService.sendEmail(booking.getClient().getEmail(),
                   "Booking Response",
                   "Mr/Miss " + booking.getClient().getName()+" Your Booking is Successful."
           );
        return new ResponseEntity<>
                (successResponse("response sent",bookingResponse),HttpStatus.OK);
    }
    else{
        return  new ResponseEntity<>(
                errorResponse("sending response unsuccessful",null),HttpStatus.BAD_REQUEST);
       }

    }
}
