package com.vbs.vbs.services;


import com.vbs.vbs.dto.BookingDto;
import com.vbs.vbs.models.Booking;

public interface BookingServices {

    Booking VenueBookingRequest(BookingDto bookingDto, Integer id, String email);

    Integer VenueBookingResponse(Integer bookingStatus, Integer id);

    Booking findById(Integer id);
}
