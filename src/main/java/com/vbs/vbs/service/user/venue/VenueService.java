package com.vbs.vbs.service.user.venue;

import com.vbs.vbs.dto.user.client.BookingRequestDto;
import com.vbs.vbs.dto.user.venue.VenueDto;


import java.util.List;

public interface VenueService{
    VenueDto create(VenueDto venueDto);

    List<VenueDto> findAll();

    VenueDto findById(Integer id);

    List<VenueDto> findInMainPage();

    List<VenueDto>findInAdminPage();

    List<VenueDto> findByCity_name(String city_name);


    List<VenueDto> findByStreet_name(String street_name);

    void deleteBYId(Integer integer);

    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);







}
