package com.vbs.vbs.services.client;

import com.vbs.vbs.dto.ClientDto;
import com.vbs.vbs.models.venue.BookingRequest;

import java.util.List;

public interface ClientService {
  ClientDto create(ClientDto clientDto);
  ClientDto findClientByEmail(String email);
  List<ClientDto> findAll();
  List<BookingRequest> getAllRequests(String email);

}
