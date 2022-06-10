package com.vbs.vbs.service.user.venue;

import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.user.venue.VenueDto;


import java.util.List;

public interface VenueService{
    VenueDto create(VenueDto venueDto);

    List<VenueDto> findAll();

    List<VenueDto> findInAdminPage();

    VenueDto findById(Integer id);

    void deleteBYId(Integer integer);

    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);







}
