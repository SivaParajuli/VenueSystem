package com.vbs.vbs.services.venue;
import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.models.venue.BookingRequest;

import java.io.IOException;
import java.util.List;

public interface VenueService{
    VenueDto create(VenueDto venueDto) throws IOException;

    List<VenueDto> findAll();

    List<VenueDto> findInAdminPage();

    VenueDto findById(Integer id);

    VenueDto findVenueByEmail(String email);

    void deleteBYId(Integer integer);

//    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);

    VenueDto update(Integer id,VenueDto venueDto);

    List<BookingRequest> getRequestedBooking(String email);
}
