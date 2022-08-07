package com.vbs.vbs.repo;

import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
  @Query(value = "SELECT '*' from Client where email=?1")
  Optional<Client> findClientByEmail(String email);

  @Query(value = "SELECT c.bookingList from Client c join c.bookingList r where c.email=?1")
  List<Booking> getAllBookingRequests(String email);

}
