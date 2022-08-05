package com.vbs.vbs.services.servicesImpl.venue;


import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.models.client.Client;
import com.vbs.vbs.models.venue.Venue;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.repo.client.ClientRepo;
import com.vbs.vbs.repo.venue.VenueBookingRequestRepo;
import com.vbs.vbs.repo.venue.VenueRepo;
import com.vbs.vbs.services.venue.VenueBookingRequestService;
import com.vbs.vbs.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class  VenueBookingRequestServiceImpl implements VenueBookingRequestService {
    private final VenueBookingRequestRepo venueBookingRequestRepo;
    private final VenueRepo venueRepo;
    private final ClientRepo clientRepo;


    @Autowired
    public VenueBookingRequestServiceImpl(VenueBookingRequestRepo venueBookingRequestRepo,
                                          VenueRepo venueRepo, ClientRepo clientRepo) {
        this.venueBookingRequestRepo = venueBookingRequestRepo;
        this.venueRepo = venueRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public BookingRequest VenueBookingRequest(BookingRequest bookingRequestDto, String email, String client) {
        BookingRequest entity = new BookingRequest();
        if ( bookingRequestDto.getBookingDate() !=venueRepo.getBookedVenueDateByEmail(email)) {
            Optional<Venue> venue = venueRepo.findVenueByEmail(email);
            if(venue.isPresent()){
                entity.setVenue(venue.get());
            }
            Client client1 = clientRepo.findClientByEmail(email).orElseThrow(()->new RuntimeException("clientNotFound"));
            entity.setBookingDate(bookingRequestDto.getBookingDate());
            entity.setFunctionType(bookingRequestDto.getFunctionType());
            entity.setBookingStatus(BookingStatus.PENDING);
            entity.setOfferedPayment(bookingRequestDto.getOfferedPayment());
            entity.setClient(client1);
            entity.setRequiredCapacity(bookingRequestDto.getRequiredCapacity());
            entity.setContactNumber(bookingRequestDto.getContactNumber());
            venueBookingRequestRepo.save(entity);
        }
        return null;
    }

    public BookingRequest VenueBookingResponse(Integer bookingStatus,Integer id){
         BookingRequest bookingRequest = venueBookingRequestRepo.findById(id).orElseThrow(()->new RuntimeException("invalid id"));
        if(bookingStatus==1) {
            bookingRequest.setBookingStatus(BookingStatus.BOOKED);
        }
        else
            bookingRequest.setBookingStatus(BookingStatus.DELETED);
        venueBookingRequestRepo.save(bookingRequest);
        return null;
    }
}