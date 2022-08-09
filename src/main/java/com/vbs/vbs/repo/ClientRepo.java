package com.vbs.vbs.repo;

import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
  @Query(value = "SELECT c from Client c where c.email = :e")
  Optional<Client> findClientByEmail(@Param("e") String email);

  @Query(value = "SELECT c.bookingList from Client c join c.bookingList r where c.email= :e")
  List<Booking> getAllBookingRequests(@Param("e") String email);

}
