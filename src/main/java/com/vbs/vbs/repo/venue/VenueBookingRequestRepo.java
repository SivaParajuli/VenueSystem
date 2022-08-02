package com.vbs.vbs.repo.venue;

import com.vbs.vbs.models.venue.Venue;
import com.vbs.vbs.models.venue.VenueBookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VenueBookingRequestRepo extends JpaRepository<VenueBookingRequest,Integer>{
//    @Query(value = "select venue from VenueBookingRequest where venue.email=?1")
//    Optional<Venue> findVenueByEmail(String email);

//    @Query(value = "select bookingDate from VenueBookingRequest where venueEmail=?1")
//    Date getBookingDateByEmail(String email);

}
