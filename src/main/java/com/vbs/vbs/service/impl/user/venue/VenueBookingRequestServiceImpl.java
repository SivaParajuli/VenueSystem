package com.vbs.vbs.service.impl.user.venue;

import com.vbs.vbs.dto.ResponseDto;
import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.entity.user.client.Client;
import com.vbs.vbs.entity.user.venue.Venue;
import com.vbs.vbs.entity.user.venue.VenueBookingRequest;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.repo.user.client.ClientRepo;
import com.vbs.vbs.repo.user.venue.VenueBookingRequestRepo;
import com.vbs.vbs.repo.user.venue.VenueRepo;
import com.vbs.vbs.service.user.venue.VenueBookingRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class VenueBookingRequestServiceImpl implements VenueBookingRequestService {
    private final VenueBookingRequestRepo venueBookingRequestRepo;

    private final VenueRepo venueRepo;

    private final ClientRepo clientRepo;


    public VenueBookingRequestServiceImpl(VenueBookingRequestRepo venueBookingRequestRepo,
                                          VenueRepo venueRepo, ClientRepo clientRepo) {
        this.venueBookingRequestRepo = venueBookingRequestRepo;
        this.venueRepo = venueRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public BookingRequestDto VenueBookingRequest(BookingRequestDto bookingRequestDto) {
        VenueBookingRequest entity=new VenueBookingRequest();
        entity.setBookingDate(bookingRequestDto.getBookingDate());
        entity.setFunctionType(bookingRequestDto.getFunctionType());
        entity.setRequiredCapacity(bookingRequestDto.getRequiredCapacity());

       Optional<Venue> venue = venueRepo.findById(bookingRequestDto.getVenueId());
       Venue venue1 = new Venue();
       if (venue.isPresent()){
           venue1 = venue.get();
       }
        entity.setVenue(venue1);
       Optional<Client> client=clientRepo.findById(bookingRequestDto.getClientId());
       Client client1=new Client();
       if(client.isPresent()){
           client1=client.get();
       }
       if (bookingRequestDto.getId() ==null){
           entity.setBookingStatus(BookingStatus.PENDING);
       }
       entity.setClient(client1);
       entity.setPayment(bookingRequestDto.getPayment());
       venueBookingRequestRepo.save(entity);
       return null;
    }

    @Override
    public Integer BookingResponse(Integer integer) {
        if(integer==1){

                return 1;
            }
        else{
                return 0;
        }
    }
}
