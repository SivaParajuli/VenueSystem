package com.vbs.vbs.repo;

import com.vbs.vbs.dto.EventDto;
import com.vbs.vbs.dto.EventsCostCalculation;
import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.enums.EventType;
import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.models.Booking;
import com.vbs.vbs.models.EventsCostAndRate;
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

    @Query(value = "select  v from Venue v  where  v.email= :e")
    Optional<Venue> findVenueByEmail(@Param("e") String email);

    @Query(value="select v from Venue v where v.venueStatus= :p ")
    List<Venue>findPendingRegister(@Param("p") VenueStatus status);

    @Query(value="select v from Venue v where v.venueStatus = :a ")
    List<Venue>findAllVerifiedVenue(@Param("a")VenueStatus venueStatus);


    @Query(value="SELECT r.bookingDate from Venue v join v.bookingList r where v.email= :e and r.bookingStatus <> :d")
    List<?> getBookedVenueDateById(@Param("e")String email,@Param("d")BookingStatus bookingStatus);

    @Query(value = "SELECT r from Venue v join v.bookingList r where v.email= :e and r.bookingStatus= :p")
    List<Booking> getAllPendingBookingRequest(@Param("e") String email, @Param("p")BookingStatus bookingStatus);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Venue v SET v.venueStatus= :s where v.id = :i")
    Integer updateVenueStatus(@Param("s") VenueStatus vStatus,@Param("i")Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Venue v SET v.venueName= :n,v.userName= :u,v.address= :a,v.contactNumber= :c,v.password = :p,v.description= :d where v.email = :e")
    Integer update(@Param("n") String venueName,
                   @Param("u") String userName,
                   @Param("a") String address,
                   @Param("c") String contactNumber,
                   @Param("p") String password,
                   @Param("d") String description,
                   @Param("e")String email);

    @Query(value = "SELECT r from Venue v join v.bookingList r where v.email= :e order by r.id desc")
    List<Booking> getAllBookingList(@Param("e") String email);

    @Query(value = "SELECT new com.vbs.vbs.dto.EventsCostCalculation(f.marriageCost,f.annualMeetCost,f.conclaveCost,f.collegeEventCost,f.familyFunctionCost,f.rate) from Venue v join v.functionList f where v.email= :e")
    EventsCostCalculation getRateAndCost(@Param("e") String vEmail);
}
