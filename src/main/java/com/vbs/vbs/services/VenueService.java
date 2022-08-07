package com.vbs.vbs.services;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueService{
    List<VenueDto> findAll();

    VenueDto findById(Integer id);

    Optional<Venue> findVenueByEmail(String email);

    void deleteBYId(Integer integer);

//    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);

    VenueDto update(Integer id,VenueDto venueDto);

    List<Booking> getRequestedBooking(String email);
}
