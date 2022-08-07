package com.vbs.vbs.services.Impl;


import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Venue;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.repo.ClientRepo;
import com.vbs.vbs.repo.BookingRepo;
import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookingServicesImpl implements BookingServices {
    private final BookingRepo bookingRepo;
    private final VenueRepo venueRepo;
    private final ClientRepo clientRepo;


    @Autowired
    public BookingServicesImpl(BookingRepo bookingRepo,
                               VenueRepo venueRepo, ClientRepo clientRepo) {
        this.bookingRepo = bookingRepo;
        this.venueRepo = venueRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public Booking VenueBookingRequest(Booking bookingDto, String email, String client) {
        Booking entity = new Booking();
        if ( bookingDto.getBookingDate() !=venueRepo.getBookedVenueDateByEmail(email)) {
            Optional<Venue> venue = venueRepo.findVenueByEmail(email);
            if(venue.isPresent()){
                entity.setVenue(venue.get());
            }
            Client client1 = clientRepo.findClientByEmail(email).orElseThrow(()->new RuntimeException("clientNotFound"));
            entity.setBookingDate(bookingDto.getBookingDate());
            entity.setFunctionType(bookingDto.getFunctionType());
            entity.setBookingStatus(BookingStatus.PENDING);
            entity.setOfferedPayment(bookingDto.getOfferedPayment());
            entity.setClient(client1);
            entity.setRequiredCapacity(bookingDto.getRequiredCapacity());
            entity.setContactNumber(bookingDto.getContactNumber());
            bookingRepo.save(entity);
        }
        return null;
    }

    public Booking VenueBookingResponse(Integer bookingStatus, Integer id){
         Booking booking = bookingRepo.findById(id).orElseThrow(()->new RuntimeException("invalid id"));
        if(bookingStatus==1) {
            booking.setBookingStatus(BookingStatus.BOOKED);
        }
        else
            booking.setBookingStatus(BookingStatus.DELETED);
        bookingRepo.save(booking);
        return null;
    }
}