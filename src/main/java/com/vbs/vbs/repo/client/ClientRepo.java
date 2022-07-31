package com.vbs.vbs.repo.client;

import com.vbs.vbs.models.client.Client;
import com.vbs.vbs.models.venue.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
  Client findClientByEmail(String email);
  @Query(value = "select  * from tbl_client where  email= ?1 , password=?1" ,nativeQuery = true)
  Optional<Client> findUserByEmail(String email);

}
