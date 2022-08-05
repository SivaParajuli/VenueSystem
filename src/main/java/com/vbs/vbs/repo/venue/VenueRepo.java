package com.vbs.vbs.repo.venue;
import com.vbs.vbs.models.venue.BookingRequest;
import com.vbs.vbs.models.venue.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{

    @Query(value = "select  * from Venue where  city_address= ?1",nativeQuery = true)
    List<Venue> findByCity_name(String city_name);

    @Query(value="select * from Venue",nativeQuery = true)
    List<Venue>findInMainPage();


    @Query(value = "select  * from Venue where  street_address= ?1",nativeQuery = true)
    List<Venue> findByStreet_name(String street_name);

    @Query(value = "select  * from Venue where  v_name= ?1",nativeQuery = true)
    Venue findByV_name(String v_name);

    @Query(value = "select  * from Venue where  email= ?1" ,nativeQuery = true)
    Optional<Venue> findVenueByEmail(String email);

    @Query(value="select * from Venue where venueUpdateStatus=PENDING",nativeQuery = true)
    List<Venue>findInAdminPage();

    @Query(value="SELECT r.bookingDate from Venue v join v.bookingRequestList r where v.email=?1")
    Date getBookedVenueDateByEmail(String email);

    @Query(value = "SELECT v.bookingRequestList from Venue v join v.bookingRequestList r where v.email=?1 and r.bookingStatus='PENDING'")
    List<BookingRequest> getAllPendingBookingRequest(String email);
}
