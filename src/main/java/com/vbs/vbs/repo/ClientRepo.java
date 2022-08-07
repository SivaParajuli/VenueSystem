package com.vbs.vbs.repo.client;

import com.vbs.vbs.models.client.Client;
import com.vbs.vbs.models.venue.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
  @Query(value = "SELECT '*' from Client where email=?1")
  Optional<Client> findClientByEmail(String email);

  @Query(value = "SELECT c.bookingRequestList from Client c join c.bookingRequestList r where c.email=?1")
  List<BookingRequest> getAllBookingRequests(String email);

}
