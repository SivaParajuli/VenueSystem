package com.vbs.vbs.controller;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.services.BookingServices;
import com.vbs.vbs.services.ClientService;
import com.vbs.vbs.services.VenueService;
import com.vbs.vbs.utils.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="client-")

public class ClientController extends BaseController {
    private final BookingServices bookingServices;
    private final VenueService venueService;
    private final ClientService clientService;
    private final EmailSenderService emailSenderService;

    public ClientController(BookingServices bookingServices,
                            VenueService venueService, ClientService clientService, EmailSenderService emailSenderService) {
        this.bookingServices = bookingServices;
        this.venueService = venueService;
        this.clientService = clientService;
        this.emailSenderService = emailSenderService;
    }
    @GetMapping()
    public ResponseEntity<ResponseDto> getAllVerifiedVenue(){
        List<VenueDto> venueList =venueService.getAllVerifiedVenue();
        return new ResponseEntity<>
                (successResponse("Verified venue fetched", venueList),HttpStatus.OK);
    }

    @GetMapping(path="{email}")
    public ResponseEntity<ResponseDto> findUser(@PathVariable String email){
        Client currentUser = clientService.findClientByEmail(email);
        if(currentUser !=null){
            return new ResponseEntity<>
                    (successResponse("CurrentUser", currentUser), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("sorry",null),HttpStatus.BAD_REQUEST);
    }


    @PostMapping(path="book-venue/{id}/{email}")
    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody Booking booking, @PathVariable("id") Integer id,
                                                      @PathVariable("email") String email){
        Booking booking1 = bookingServices.VenueBookingRequest(booking,id,email);
        if(booking1 !=null){
            emailSenderService.sendEmail(venueService.findById(id).getEmail(),
                    "Registration Request",
                    "You have Booking for "+booking.getBookingDate()+". Please response in time .");

            return new ResponseEntity<>
                    (successResponse("Request Sent", booking), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("There is some error to send request .please try again",null),HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="bookedDate/{id}")
    public ResponseEntity<ResponseDto> getAllBookedDate(@PathVariable("id")Integer id){
        List<?> dateList =venueService.getAllBookedDate(id);
        return new ResponseEntity<>
                (successResponse("Date List fetched.", dateList),HttpStatus.OK);
    }
}
