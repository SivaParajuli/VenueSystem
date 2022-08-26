package com.vbs.vbs.services;

import com.vbs.vbs.dto.BookingDto;
import com.vbs.vbs.models.Booking;

import java.io.IOException;

public interface BookingServices {

    Booking VenueBookingRequest(BookingDto bookingDto, String vEmail, String email) throws IOException;

    Integer VenueBookingResponse(Integer bookingStatus, Integer id);

    Booking findById(Integer id);
}
