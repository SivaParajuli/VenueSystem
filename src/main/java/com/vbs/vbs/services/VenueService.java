package com.vbs.vbs.services;
import com.vbs.vbs.dto.EventDto;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.models.Booking;


import java.util.List;

public interface VenueService{
    List<VenueDto> findAll();

    VenueDto findById(Integer id);

    VenueDto findVenueByEmail(String email);

    void deleteBYId(Integer integer);

   Integer update(VenueDto venueDto , String email);

    List<Booking> getRequestedBooking(String email);

    List<VenueDto> getAllVerifiedVenue();

    List<Booking> getBookingList(String email);

    List<?> getAllBookedDate(Integer id);


    EventDto uploadEventDetails(EventDto eventDto, String email);
}
