package com.vbs.vbs.repo;

import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {

    @Query(value = "SELECT r from Booking r where r.venue= :i and r.bookingStatus= :p")
    List<Booking> getAllPendingBookingRequest(@Param("i") Integer venueId, @Param("p")BookingStatus bookingStatus);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Booking b SET b.bookingStatus = :s where b.id = :i")
    Integer updateBookingStatus(@Param("s") BookingStatus bStatus, @Param("i")Integer id);

}
