package com.vbs.vbs.services;


import com.vbs.vbs.models.Booking;

public interface BookingServices {

    Booking VenueBookingRequest(Booking booking,Integer id, String email);

    Integer VenueBookingResponse(Integer bookingStatus, Integer id);

    Booking findById(Integer id);
}
