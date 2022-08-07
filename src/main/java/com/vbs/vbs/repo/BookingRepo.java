package com.vbs.vbs.repo.venue;

import com.vbs.vbs.models.venue.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VenueBookingRequestRepo extends JpaRepository<BookingRequest,Integer> {

}
