package com.vbs.vbs.controller.user.venue;


import com.vbs.vbs.controller.BaseController;
import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.user.venue.VenueDto;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.service.user.venue.VenueBookingRequestService;
import com.vbs.vbs.service.user.venue.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("venue")
@RestController
public class VenueController extends BaseController {

    private final VenueService venueService;
    private final VenueBookingRequestService venueBookingRequestService;

    public VenueController(VenueService venueService, VenueBookingRequestService venueBookingRequestService) {
        this.venueService = venueService;
        this.venueBookingRequestService = venueBookingRequestService;
    }


    @PostMapping("create")
    public ResponseEntity<ResponseDto> createVenue(@RequestBody VenueDto venueDto){
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

    @GetMapping
    public ResponseEntity findAll(){
        List<VenueDto> venueDtoList =venueService.findAll();
        return new ResponseEntity<>
                (successResponse("Venue List Fetched", venueDtoList),HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<ResponseDto>findById(@PathVariable Integer id){
        VenueDto venueDto =venueService.findById(id);
        if(venueDto !=null) {
            return new ResponseEntity<>
                    (successResponse("Venue   Fetched.", venueDto), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>
                    (errorResponse("Venue Fetched Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/requests")
    public ResponseEntity<ResponseDto>getVenueRequestByClient(Integer id){
        List<BookingRequestDto> bookingRequestDto =venueService.getVenueBookingRequestByClient(id);
        return null;
    }

    @PostMapping("response/{bookingStatus}")
    public ResponseEntity<ResponseDto> BookingResponse(BookingRequestDto bookingRequestDto,
                                                       @PathVariable("bookingStatus") String bookingStatus){
//    TODO    bookingRequestDto=venueBookingRequestService.findById();
        if(bookingStatus=="ok") {
            bookingRequestDto.setBookingStatus(BookingStatus.BOOKED);
            venueBookingRequestService.VenueBookingRequest(bookingRequestDto);
            venueBookingRequestService.BookingResponse(1);
        }
        if(bookingStatus=="deny"){
            venueBookingRequestService.BookingResponse(0);
//    TODO        venueBookingRequestService.deleteBYId(id);
        }
        return null;
    }
}
