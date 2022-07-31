package com.vbs.vbs.repo.venue;

import com.vbs.vbs.models.venue.VenueBookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueBookingRequestRepo extends JpaRepository<VenueBookingRequest,Integer>{

    @Query(value = "select  * from venue_booking_request where venue_id = ?1",nativeQuery = true)
    List<VenueBookingRequest> getVenueBookingRequestByClient(Integer venueId);

}
