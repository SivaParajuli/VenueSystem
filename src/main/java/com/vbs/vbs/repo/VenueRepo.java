package com.vbs.vbs.repo;
import com.vbs.vbs.dto.VenueDto;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{

    @Query(value = "select  v from Venue v where  v.email= :e")
    Optional<Venue> findVenueByEmail(@Param("e") String email);

    @Query(value="select v from Venue v where v.venueStatus= :p ")
    List<VenueDto>findPendingRegister(@Param("p") VenueStatus status);

    @Query(value="select v from Venue v where v.venueStatus = :a ")
    List<VenueDto>findAllVerifiedVenue(@Param("a")VenueStatus venueStatus);


    @Query(value="SELECT r.bookingDate from Venue v join v.bookingList r where v.email= :e")
    Date getBookedVenueDateByEmail(@Param("e")String email);

    @Query(value = "SELECT v.bookingList from Venue v join v.bookingList r where v.email= :e and r.bookingStatus= :p")
    List<Booking> getAllPendingBookingRequest(@Param("e") String email, @Param("p")BookingStatus bookingStatus);

    @Modifying
    @Query(value = "UPDATE Venue v SET v.venueStatus= :s where v.email = :e")
    Venue updateVenueStatus(@Param("s") VenueStatus vStatus,@Param("e")String email);


}
