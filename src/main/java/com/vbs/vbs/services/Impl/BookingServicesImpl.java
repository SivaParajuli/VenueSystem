package com.vbs.vbs.services.Impl;


import com.vbs.vbs.dto.BookingDto;
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

import java.util.Date;
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
    public Booking VenueBookingRequest(Booking booking, Integer id, String email ) {
        Client client1 = clientRepo.findClientByEmail(email).orElseThrow(()->new RuntimeException("clientNotFound"));
        Venue venue1 = venueRepo.findById(id).orElseThrow(()->new RuntimeException("venueNotFound"));
        Booking entity = Booking.builder()
                .bookingDate(booking.getBookingDate())
                .functionType(booking.getFunctionType())
                .bookingStatus(BookingStatus.PENDING)
                .calculatedPayment(booking.getCalculatedPayment())
                .requiredCapacity(booking.getRequiredCapacity())
                .contactNumber(booking.getContactNumber())
                .client(client1)
                .venue(venue1).build();
        entity = bookingRepo.save(entity);
        return Booking.builder()
                .venue(entity.getVenue())
                .bookingDate(entity.getBookingDate())
                .functionType(entity.getFunctionType())
                .bookingStatus(entity.getBookingStatus())
                .contactNumber(entity.getContactNumber())
                .build();

    }

    @Override
    public Integer VenueBookingResponse(Integer bookingStatus, Integer id) {
        if(bookingStatus == 1 ){
            return bookingRepo.updateBookingStatus(BookingStatus.SUCCESSFUL,id);
        }
       if(bookingStatus == 2){
           return bookingRepo.updateBookingStatus(BookingStatus.UNSUCCESSFUL,id);
       }
       return null;
    }

    @Override
    public Booking findById(Integer id) {
        Optional<Booking> optionalBooking =bookingRepo.findById(id);
        if(optionalBooking.isPresent()){
           Booking booking = optionalBooking.get();
            return Booking.builder()
                    .client(booking.getClient())
                    .venue(booking.getVenue())
                    .contactNumber(booking.getContactNumber())
                    .build();
        }
        return null;
    }


}