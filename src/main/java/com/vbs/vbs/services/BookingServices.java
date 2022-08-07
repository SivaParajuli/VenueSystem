package com.vbs.vbs.services.venue;


import com.vbs.vbs.models.venue.BookingRequest;

public interface VenueBookingRequestService {

    BookingRequest VenueBookingRequest(BookingRequest bookingRequest, String email, String client);

    BookingRequest VenueBookingResponse(Integer bookingStatus,Integer id);
}
