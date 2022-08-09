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
            venue.ifPresent(entity::setVenue);
            Client client1 = clientRepo.findClientByEmail(email).orElseThrow(()->new RuntimeException("clientNotFound"));
            entity.setBookingDate(bookingDto.getBookingDate());
            entity.setFunctionType(bookingDto.getFunctionType());
            entity.setBookingStatus(BookingStatus.PENDING);
            entity.setOfferedPayment(bookingDto.getOfferedPayment());
            entity.setClient(client1);
            entity.setRequiredCapacity(bookingDto.getRequiredCapacity());
            entity.setContactNumber(bookingDto.getContactNumber());
            bookingRepo.save(entity);
            return Booking.builder()
                    .venue(bookingDto.getVenue())
                    .bookingDate(bookingDto.getBookingDate())
                    .contactNumber(bookingDto.getContactNumber())
                    .build();
        }
        return null;
    }

    @Override
    public Booking VenueBookingResponse(Integer bookingStatus, Integer id) {
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