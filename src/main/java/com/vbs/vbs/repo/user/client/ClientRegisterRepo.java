package com.vbs.vbs.repo.user.client;

import com.vbs.vbs.entity.user.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRegisterRepo extends JpaRepository<Client, Integer> {
    public Client findClientRegisterByEmail(String email);

}
