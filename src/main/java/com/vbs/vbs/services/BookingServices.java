package com.vbs.vbs.services;


import com.vbs.vbs.models.Booking;

public interface BookingServices {

    Booking VenueBookingRequest(Booking booking, String email, String client);

    Booking VenueBookingResponse(Integer bookingStatus, Integer id);

    Booking findById(Integer id);
}
