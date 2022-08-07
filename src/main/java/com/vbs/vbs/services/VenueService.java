package com.vbs.vbs.services.venue;
import com.vbs.vbs.dto.venue.VenueDto;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.models.venue.Venue;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VenueService{
    VenueDto create(VenueDto venueDto) throws IOException;

    List<VenueDto> findAll();

    List<VenueDto> findInAdminPage();

    VenueDto findById(Integer id);

    Optional<Venue> findVenueByEmail(String email);

    void deleteBYId(Integer integer);

//    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);

    VenueDto update(Integer id,VenueDto venueDto);

    List<BookingRequest> getRequestedBooking(String email);
}
