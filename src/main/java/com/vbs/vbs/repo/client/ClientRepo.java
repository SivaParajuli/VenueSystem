package com.vbs.vbs.repo.client;

import com.vbs.vbs.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
  Optional<Client> findClientByEmail(String email);

}
