package com.vbs.vbs.repo;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{

    @Query(value = "select  * from Venue where  email= ?1" ,nativeQuery = true)
    Optional<Venue> findVenueByEmail(String email);

    @Query(value="select * from Venue where venueUpdateStatus=PENDING",nativeQuery = true)
    List<Venue>findPendingRegister();

    @Query(value="SELECT r.bookingDate from Venue v join v.bookingList r where v.email=?1")
    Date getBookedVenueDateByEmail(String email);

    @Query(value = "SELECT v.bookingList from Venue v join v.bookingList r where v.email=?1 and r.bookingStatus='PENDING'")
    List<Booking> getAllPendingBookingRequest(String email);
}
