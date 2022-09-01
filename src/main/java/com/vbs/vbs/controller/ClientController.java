package com.vbs.vbs.controller;

import com.vbs.vbs.dto.BookingDto;
import com.vbs.vbs.dto.ClientDto;
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

import java.io.IOException;
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
    @GetMapping("clientHome")
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


    @PostMapping(path="book-venue/{vEmail}/{email}")
    public ResponseEntity<ResponseDto> BookingRequest(@RequestBody BookingDto bookingDto, @PathVariable("vEmail") String vEmail,
                                                      @PathVariable("email") String email) throws IOException {
        Booking booking1 = bookingServices.VenueBookingRequest(bookingDto,vEmail,email);
        if(booking1 !=null){
            emailSenderService.sendEmail(vEmail,
                    "Booking Request",
                    "You have Booking request for "+bookingDto.getBookingDate()+". Please response in time .");

            return new ResponseEntity<>
                    (successResponse("Request Sent", bookingDto), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>
                    (errorResponse("There is some error to send request .please try again",null),HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="bookedDate/{email}")
    public ResponseEntity<ResponseDto> getAllBookedDate(@PathVariable("email")String email){
        List<?> dateList =venueService.getAllBookedDate(email);
        return new ResponseEntity<>
                (successResponse("Date List fetched.", dateList),HttpStatus.OK);
    }

    @CrossOrigin(origins = "*",methods = RequestMethod.PUT,maxAge = 86400,allowedHeaders = "*")
    @PutMapping(path="update/{email}")
    public ResponseEntity<ResponseDto> updateVenue(@RequestBody ClientDto clientDto,@PathVariable("email") String email){
        Integer client =clientService.updateClient(clientDto,email);
        if(client!=null){
            return new ResponseEntity<>
                    (successResponse("data Updated.",client), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Update failed.",null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("booking/{email}")
    public ResponseEntity<ResponseDto>getBooking(@PathVariable("email") String email){
        List<Booking> booking =clientService.getBooking(email);
        if(booking !=null) {
            return new ResponseEntity<>
                    (successResponse("Requested Booking List  Fetched.", booking), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Requests Fetching Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="venue/{email}")
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

}
