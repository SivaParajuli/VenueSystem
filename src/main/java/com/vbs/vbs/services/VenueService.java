package com.vbs.vbs.services;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;


import java.util.List;

public interface VenueService{
    List<VenueDto> findAll();

    VenueDto findById(Integer id);

    VenueDto findVenueByEmail(String email);

    void deleteBYId(Integer integer);

    VenueDto update(Integer id,VenueDto venueDto);

    List<Booking> getRequestedBooking(String email);

    List<VenueDto> getAllVerifiedVenue();


}
