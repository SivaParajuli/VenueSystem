package com.vbs.vbs.repo;

import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer>{

    @Query(value = "select  v from Venue v where  v.email= :e")
    Optional<Venue> findVenueByEmail(@Param("e") String email);

    @Query(value="select v from Venue v where v.venueStatus= :p ")
    List<Venue>findPendingRegister(@Param("p") VenueStatus status);

    @Query(value="select v from Venue v where v.venueStatus = :a ")
    List<Venue>findAllVerifiedVenue(@Param("a")VenueStatus venueStatus);


    @Query(value="SELECT r.bookingDate from Venue v join v.bookingList r where v.id= :i and r.bookingStatus <> :d")
    List<?> getBookedVenueDateById(@Param("i")Integer id,@Param("d")BookingStatus bookingStatus);

    @Query(value = "SELECT r from Venue v join v.bookingList r where v.email= :e and r.bookingStatus= :p")
    List<Booking> getAllPendingBookingRequest(@Param("e") String email, @Param("p")BookingStatus bookingStatus);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Venue v SET v.venueStatus= :s where v.id = :i")
    Integer updateVenueStatus(@Param("s") VenueStatus vStatus,@Param("i")Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Venue v SET v.filePath= :f where v.email = :e")
    void updateVenueImage(@Param("e")String email, @Param("f") String filePath);
}
