package com.vbs.vbs.services.venue;
import com.vbs.vbs.dto.venue.VenueDto;
import java.io.IOException;
import java.util.List;

public interface VenueService{
    VenueDto create(VenueDto venueDto) throws IOException;

    List<VenueDto> findAll();

    List<VenueDto> findInAdminPage();

    VenueDto findById(Integer id);

    VenueDto findUserByEmail(String email);

    void deleteBYId(Integer integer);

//    List<BookingRequestDto> getVenueBookingRequestByClient(Integer venueId);

    VenueDto update(Integer id,VenueDto venueDto);
}
