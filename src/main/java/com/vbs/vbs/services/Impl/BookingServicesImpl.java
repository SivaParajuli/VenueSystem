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
    public Booking VenueBookingRequest(BookingDto bookingDto, Integer id, String email ) {
        Booking entity = new Booking();
        Optional<Client> client = clientRepo.findClientByEmail(email);
            client.ifPresent(entity::setClient);
            Venue venue = venueRepo.findById(id).orElseThrow(()->new RuntimeException("venueNotFound"));
            entity.setBookingDate(bookingDto.getBookingDate());
            entity.setFunctionType(bookingDto.getFunctionType());
            entity.setBookingStatus(BookingStatus.PENDING);
            entity.setCalculatedPayment(bookingDto.getCalculatedPayment());
            entity.setVenue(venue);
            entity.setRequiredCapacity(bookingDto.getRequiredCapacity());
            entity.setContactNumber(bookingDto.getContactNumber());
           Booking  booking = bookingRepo.save(entity);
           if(booking!= null){
            return Booking.builder()
                    .venue(booking.getVenue())
                    .bookingDate(booking.getBookingDate())
                    .functionType(booking.getFunctionType())
                    .bookingStatus(booking.getBookingStatus())
                    .contactNumber(booking.getContactNumber())
                    .build();
        }
        return null;
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