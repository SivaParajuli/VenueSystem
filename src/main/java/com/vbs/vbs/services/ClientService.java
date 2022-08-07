package com.vbs.vbs.services;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.models.Booking;

import java.util.List;

public interface ClientService {
  ClientDto findClientByEmail(String email);
  List<ClientDto> findAll();
  List<Booking> getAllRequests(String email);

}
