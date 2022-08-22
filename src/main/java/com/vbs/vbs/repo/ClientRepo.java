package com.vbs.vbs.repo;

import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
  @Query(value = "SELECT c from Client c where c.email = :e")
  Optional<Client> findClientByEmail(@Param("e") String email);

  @Query(value = "SELECT c.bookingList from Client c where c.email= :e")
  List<Booking> getAllBookingRequests(@Param("e") String email);

  @Transactional
  @Modifying
  @Query(value = "UPDATE Client c SET c.name= :u,c.mobile_no= :m,c.password = :p where c.email = :e")
  Integer updateClient(@Param("u") String userName,
                 @Param("m") String mobile_no,
                 @Param("p") String password,
                 @Param("e")String email);

}
